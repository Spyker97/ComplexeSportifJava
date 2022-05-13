/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.services.EvenementCrud;
import complexesportifJava.services.TypeEventCrud;
import complexesportifJava.entities.Evenement;
import complexesportifJava.entities.TypeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class EditEventController implements Initializable {

    @FXML
    private Button btnGestEvent;
    @FXML
    private TextField tfTitreEve;
    @FXML
    private Button btnModifier;
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
    private TextField idEvent;
    @FXML
    private TextField idType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    void setidData  (int id) {
        idType.setText(""+id);

    }
 public void setData(String titre, int nmbr_par, String description, int idd) {        
        tfTitreEve.setText(titre);   
        tfDescription.setText(description);
        nbr.setText(""+nmbr_par);
        idEvent.setText("" + idd);
    }

    @FXML
    private void listDesRegions(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("TypeEventTable.fxml"));
            try {         
            
                Parent root = Loader.load();
 
                btnModifier.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
    }

    @FXML
    private void savechanges(ActionEvent event) {
        int iddtype = Integer.parseInt(idType.getText());
        
        String titre = tfTitreEve.getText();
        String desc = tfDescription.getText();
        int nbrrpar = Integer.parseInt(nbr.getText());
        int id = Integer.parseInt(idEvent.getText());
        Evenement r1 = new Evenement(id, titre,nbrrpar, desc);
        EvenementCrud rc = new EvenementCrud();
        rc.modifierEvenemenet(r1);

     
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("EventsList.fxml"));
        try {

            Parent root = Loader.load();

            btnModifier.getScene().setRoot(root);
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

    @FXML
    private void annuler(ActionEvent event) {
        
        int iddtype = Integer.parseInt(idType.getText());
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("EventsList.fxml"));
        try {

            Parent root = Loader.load();

            btnModifier.getScene().setRoot(root);
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
