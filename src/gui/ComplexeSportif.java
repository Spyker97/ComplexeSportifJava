/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.entities.Mail;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ahmed
 */
public class ComplexeSportif extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("logiin.fxml"));
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Ajouter personne!");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
//            Mail.send(
//    "ahmedbhd1997@gmail.com",
//    "Sej+^e3hzB@7cd(saddi",
//    "ahmedbhd97@gmail.com",
//    "Bienvenu sur WayToLearnX",
//    "sdsd"
//  );
        } catch (IOException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
