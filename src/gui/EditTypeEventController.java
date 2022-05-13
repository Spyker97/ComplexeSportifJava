/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.services.TypeEventCrud;
import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.TypeEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class EditTypeEventController implements Initializable {

    @FXML
    private TextField tfNomRegionm;
    @FXML
    private Button btnModifier;
    @FXML
    private TextArea tfDescriptionm;
    
    @FXML
    private TextField tfid;
    @FXML
    private Button btnannuler;
    String s ;
    @FXML
    private Button btnGestEvent;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       public void setData(String titre, String description, int idd) {
        
  
        
            
        tfDescriptionm.setText(description);
        tfNomRegionm.setText(titre);
        tfid.setText("" + idd);
    }

    @FXML
    private void savechanges(ActionEvent event) {
        
        String type = tfNomRegionm.getText();
        String desc = tfDescriptionm.getText();
        int id = Integer.parseInt(tfid.getText());
        TypeEvent r1 = new TypeEvent(id, type, desc);
        TypeEventCrud rc = new TypeEventCrud();
        rc.modifierTypeevent(r1);

     
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("TypeEventTable.fxml"));
        try {

            Parent root = Loader.load();

            btnModifier.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
     FXMLLoader Loader = new FXMLLoader(getClass().getResource("TypeEventTable.fxml"));
        try {

            Parent root = Loader.load();

            btnModifier.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
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
    
}
