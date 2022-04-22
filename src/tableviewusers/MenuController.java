package tableviewusers;


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
    private Button Abonnement;
    @FXML
    private Button Cours;
    @FXML
    void Cours(javafx.event.ActionEvent mouseEvent) {
        if (mouseEvent.getSource() == Cours) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Coursdoc.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Cours");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(Cours.class
.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
@FXML
    void Abonnement(javafx.event.ActionEvent mouseEvent) {
         if (mouseEvent.getSource() == Abonnement) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Abonnement");
                stage.show();

            

} catch (IOException ex) {
                Logger.getLogger(users.class
.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
    
    
    

}
