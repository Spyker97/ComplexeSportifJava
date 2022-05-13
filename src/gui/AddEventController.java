/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.services.EvenementCrud;
import complexesportifJava.entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField; 
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 


/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class AddEventController implements Initializable {

    @FXML
    private Button btnGestEvent;
    @FXML
    private TextField tfTitreEve;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button btnannuler;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField nbr;
    @FXML
    private TextField txtregionid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
void setData  (int id) {
        txtregionid.setText(""+id);

    }

    @FXML
    private void listDesRegions(ActionEvent event) {
    }

    @FXML
    private void AjouterEve(ActionEvent event)  {
        if (tfTitreEve.getText().isEmpty() || nbr.getText().equals(null) || tfDescription.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        if (tfTitreEve.getText().matches("[a-z]*$"))
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le titre doit etre une chaine de caracteres");
            alert.showAndWait();
        
        
        
        } else {
        
            
        String titre = tfTitreEve.getText();  
             
        String desc = tfDescription.getText();
         /* String deb = String.valueOf(datedebut.getValue());
       String fin = String.valueOf(datefin.getValue());  
        SimpleDateFormat deb1 =new SimpleDateFormat("dd/MM/yyyy");  
        SimpleDateFormat fin1 =new SimpleDateFormat("dd/MM/yyyy");  
        
         Date date1=deb1.parse(deb);  
         Date date2=fin1.parse(fin);  
         
        */
         int iddtype = Integer.parseInt(txtregionid.getText());
         int nmbr = Integer.parseInt(nbr.getText());
       
        Evenement r1 = new Evenement(1,iddtype,titre,nmbr, desc);
        EvenementCrud rc = new EvenementCrud();
        rc.ajouterEvenement(r1);
    
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("EventsList.fxml"));
            try {
          
            
                Parent root = Loader.load();
           
                tfTitreEve.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
             EventsListController el = Loader.getController();
                el.refreshETable( iddtype);
                Parent p = Loader.getRoot();
                
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(p));
                stage.show();
            
            
        } 
         
    }

    @FXML
    private void annuler(ActionEvent event) {
          FXMLLoader Loader = new FXMLLoader(getClass().getResource("EventsList.fxml"));
        try {

            Parent root = Loader.load();

            tfTitreEve.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
