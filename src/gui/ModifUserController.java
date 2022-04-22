/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    }    

    @FXML
    private void ModiferClient(ActionEvent event) {
        UserService us = new UserService();
    }
    
}
