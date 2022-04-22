package com.esprit.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button activite;

    @FXML
    private Button reglement;
       @FXML
    private Button etablissement;

    @FXML
    private Button commande;

    @FXML
    void activite(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == activite) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("manuactivite.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("GameOfDrone");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(TerrainController.class
.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    void reglement(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == reglement) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("menureglement.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("GameOfDrone");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(TerrainController.class
.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
    
    
    @FXML
    void commande(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == commande) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("commande.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("GameOfDrone");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(TerrainController.class
.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    void etablissement(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == etablissement) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("etablissement.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("GameOfDronetablissemente");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(TerrainController.class
.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}
