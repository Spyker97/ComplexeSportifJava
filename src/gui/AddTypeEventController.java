/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.services.TypeEventCrud;
import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.TypeEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class AddTypeEventController implements Initializable {

    @FXML
    private TextField tfNomRegion;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button btnannuler;
    String s;
    @FXML
    private Button btnGestEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enregistrerRegion(ActionEvent event) {
            if(tfNomRegion.getText().isEmpty())
                    { 
                        tfNomRegion.setText("Ajouter le titre");
                        tfNomRegion.setFont(Font.font(20));
                        
                    }
          else if(tfDescription.getText().isEmpty()){
                tfDescription.setText("Ajouter une description");
                tfDescription.setFont(Font.font(20));
                
                }
                else if (!tfNomRegion.getText().isEmpty()&&!tfDescription.getText().isEmpty())
             
                {
        
            
        String type = tfNomRegion.getText();
        String desc = tfDescription.getText();        
        TypeEvent r1 = new TypeEvent(type, desc);
        TypeEventCrud rc = new TypeEventCrud();
        rc.ajouterTypeevent(r1);
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("TypeEventTable.fxml"));
            try {
          
            
                Parent root = Loader.load();
           
                tfNomRegion.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            
            
            
      
         }
    }

    @FXML
    private void annuler(ActionEvent event) {
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("TypeEventTable.fxml"));
        try {

            Parent root = Loader.load();

            tfNomRegion.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void listDesRegions(ActionEvent event) {
           FXMLLoader Loader = new FXMLLoader(getClass().getResource("TypeEventTable.fxml"));
            try {         
            
                Parent root = Loader.load();
 
                tfNomRegion.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
    }
    
}
