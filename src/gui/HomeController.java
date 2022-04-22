/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void consilterProfil(ActionEvent event) throws IOException {
        
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/ModifUser.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeUser(ActionEvent event) throws IOException {
        
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/afficherUser.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
    Parent root  = FXMLLoader.load(getClass().getResource("/gui/logiin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }
    
}
