/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.entities.User;
import complexesportifJava.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class GestionUserController implements Initializable {
     @FXML
    private TextField textNom;
     @FXML
     private TextField textPrenom;
     @FXML
     private DatePicker dateNaiss;
     @FXML
     private TextField email;
      @FXML
    private TextField password;
     @FXML
      private TextField adresse;
     @FXML
     private TextField textCin;
     @FXML
     private RadioButton g1;
    @FXML
    private ToggleGroup gn;
     @FXML
    private RadioButton g2;
    @FXML
    private Button button_signup;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public String c ;
    

    @FXML
    private void addClient(ActionEvent event) {
        UserService us = new UserService();
        
        if (g1.isSelected()){
           c = g1.getText();
        }else {
            c = g2.getText();
        }
            
            
        if(ValidCin(textCin)&&Validchamp(textNom)&&Validchamp(textPrenom)&&Validchamp(email)&&Validchamp(password)&&Validchamp(adresse)&&ValidEmail(email.getText())){
            try {
                MessageDigest msg = MessageDigest.getInstance("MD5");
                byte[] hash = msg.digest(password.getText().getBytes(StandardCharsets.UTF_8));
                // convertir bytes en hexadécimal
                StringBuilder s = new StringBuilder();
                for (byte b : hash) {
                    s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                }
               
                User u = new User(Integer.parseInt(textCin.getText()), textNom.getText(),textPrenom.getText(), c ,email.getText(),
                        s.toString(),adresse.getText(),dateNaiss.getValue().toString());
                us.ajouterUser(u);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(GestionUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
 
    
    
    private boolean ValidEmail(String email){
                String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email);
        if (controler.matches()){
//Ok : la saisie est bonne
 return true;
}else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut corriger le email!!");
            alert.showAndWait();
            
        return false;
        }
    }
    
    private boolean Validchamp(TextField T){
         if(T.getText().isEmpty() | T.getLength() <=1 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs!");
            alert.showAndWait();
      return false;
    }
         return true;
}
     private boolean ValidCin(TextField T){
         if(T.getText().isEmpty() | T.getLength() <8 | T.getLength()> 8 ){ //verifier nom o deesc vide 
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Il faut lenth cin egale 8!");
            alert.showAndWait();
      return false;
    }
         return true;
}

    @FXML
    private void back(ActionEvent event) throws IOException {
        
         Parent root  = FXMLLoader.load(getClass().getResource("/gui/logiin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }
    
}
