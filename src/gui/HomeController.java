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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        
        SessionClient.cleanUserSession();
        
    Parent root  = FXMLLoader.load(getClass().getResource("/gui/logiin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void Cours(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/Cours.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void Abonnement(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/FXMLDocument.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeTerrain(ActionEvent event) throws IOException {
         Parent root  = FXMLLoader.load(getClass().getResource("/gui/terrain.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeReservation(ActionEvent event) throws IOException {
     Parent root  = FXMLLoader.load(getClass().getResource("/gui/reservation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeAcademie(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/GestionAcademie.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeEnfant(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/GestionEnfant.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeEvenement(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/TypeEventTable.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeCommande(ActionEvent event) throws IOException {
    Parent root  = FXMLLoader.load(getClass().getResource("/gui/commande.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void listeProduit(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/produit.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }
    
}
