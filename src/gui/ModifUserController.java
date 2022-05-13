/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.entities.SessionClient;
import complexesportifJava.entities.User;
import complexesportifJava.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
public class ModifUserController implements Initializable {

    @FXML
    private TextField ModifertextNom;
    @FXML
    private TextField ModifertextPrenom;
    @FXML
    private TextField Modiferemail;
    @FXML
    private TextField Modiferadresse;
    @FXML
    private TextField ModifertextCin;
    @FXML
    private Button button_Modifer;
    @FXML
    private PasswordField Modiferpassword;
    @FXML
    private RadioButton Modiferg1;
    @FXML
    private ToggleGroup gn;
    @FXML
    private RadioButton Modiferg2;
    @FXML
    private DatePicker dateNaiss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//     (ModifertextNom.setText(SessionClient.),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin),ModifertextNom.setText(SessionClient.cin));
    
        
        ModifertextCin.setText(SessionClient.cin+"");
        ModifertextNom.setText(SessionClient.username+"");
        ModifertextPrenom.setText(SessionClient.prenom+"");
        Modiferemail.setText(SessionClient.email+"");
        
        Modiferpassword.setText(SessionClient.password+"");
        Modiferadresse.setText(SessionClient.adresse+"");
        
    }    

    @FXML
    private void ModiferClient(ActionEvent event) {
        try {
            UserService us = new UserService();
            User u = new User();
            u.setId(SessionClient.id);
            u.setCin(Integer.parseInt(ModifertextCin.getText()));
            u.setUsername(ModifertextNom.getText());
            u.setPrenom(ModifertextPrenom.getText());
            u.setEmail(Modiferemail.getText());
                MessageDigest msg = MessageDigest.getInstance("MD5");
                byte[] hash = msg.digest(Modiferpassword.getText().getBytes(StandardCharsets.UTF_8));
                // convertir bytes en hexadécimal
                StringBuilder s = new StringBuilder();
                for (byte b : hash) {
                    s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
                }
                u.setPassword(s.toString());
            u.setAdresse(Modiferadresse.getText());
            
            us.modifer(u, SessionClient.id);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ModifUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }
    
}
