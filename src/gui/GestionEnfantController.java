/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import complexesportifJava.entities.Academie;
import complexesportifJava.entities.Enfant;
import complexesportifJava.entities.SessionClient;
import complexesportifJava.services.EnfantService;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import mail.mail;



/**
 * FXML Controller class
 *
 * @author wided
 */
public class GestionEnfantController implements Initializable {

    @FXML
    private Button btnmail;

    @FXML
    private TextField Name;
    @FXML
    private TextField Age;
    @FXML
    private TextField Taille;
    @FXML
    private TextField Poids;
    @FXML
    private TextField cinparent;
    @FXML
       private ListView<Enfant> list;
    private Text alerteName;
    private Text alerteAge;
    private Text alertePoid;
    private Text alerteTaille;
    private Text alerteCinparent;

    /**
     * Initializes the controller class.
     */
    
    EnfantService enfantService = EnfantService.getInstance();
    private TextField id;
    @FXML
    private Button gu;
    @FXML
    private Button ga;
    
            @Override
    public void initialize(URL url, ResourceBundle rb) {
        fill();
        afficher();
        cinparent.setText(SessionClient.cin+"");
        
        
    }    


    
    private void afficher() {
        
        List<Enfant> enfant = enfantService.afficherEnfant();
        ObservableList<Enfant> observableArrayListEnfant = 
        FXCollections.observableArrayList(enfant);
        list.setItems(observableArrayListEnfant);
        
    } 
     private void fill(){
     list.setOnMouseClicked(e->{
            Enfant e1 = enfantService.rechercherParId(list.getSelectionModel().getSelectedItem());
            Name.setText(e1.getName());
            Age.setText(String.valueOf(e1.getAge()));
             Poids.setText(String.valueOf(e1.getPoids()));
             Taille.setText(String.valueOf(e1.getTaille()));
             cinparent.setText(String.valueOf(e1.getCinparent()));
           });
             }
        @FXML
    private void Ajouter(ActionEvent event){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        if ((null == Age.getText()) && (Age.getText().length() <= 8)) {
            alert.setTitle("Mot de passe incorrect");
                        alert.showAndWait();
}
            else{
        String nameu=Name.getText();
        int ageu=Integer.parseInt(Age.getText());
        int poid=Integer.parseInt(Poids.getText());
        int tailles=Integer.parseInt(Taille.getText());
        int cinp=Integer.parseInt(cinparent.getText());
        Enfant u=new Enfant(nameu,ageu,poid,tailles,cinp);
        EnfantService us=new EnfantService();
        addNotifications("Enfant Ajouté avec succes", "Enfant Ajouté");

        us.ajouterEnfant(u);

        
        afficher();
        }
        
      
       
    
}
   
    
     
     @FXML
    private void modifier(ActionEvent event) {
        Enfant enfant = new Enfant();
        Enfant a = enfantService.rechercherParId(list.getSelectionModel().getSelectedItem());
        enfant.setId(a.getId());
        enfant.setName(Name.getText());
        enfant.setAge(Integer.parseInt(Age.getText()));
        enfant.setPoids(Integer.parseInt(Poids.getText()));
        enfant.setTaille(Integer.parseInt(Taille.getText()));
      enfant.setCinparent(Integer.parseInt(cinparent.getText()));
      
        enfantService.modifierEnfant(enfant);
        
        afficher();
    
    }

    @FXML
    private void Supprimer(ActionEvent event){
   Enfant u = new Enfant();
        Enfant a = enfantService.rechercherParId(list.getSelectionModel().getSelectedItem());
        u.setId(a.getId());
        enfantService.supprimerEnfant(u.getId());
       
        afficher();
    }

    @FXML
    private void gu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GestionEnfant.fxml"));
        Parent root = loader.load();
        gu.getScene().setRoot(root);
    }

    @FXML
    private void ga(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GestionAcademie.fxml"));
        Parent root = loader.load();
        ga.getScene().setRoot(root);
    }

   @FXML
    private void tri(ActionEvent event) {
         EnfantService ca = new EnfantService();
        List<Enfant> trie = ca.Tri();
        list.getItems().clear();
        list.getItems().addAll(trie);
    }

    @FXML
    private void aaa(ActionEvent event) {
        afficher();
        fill();
    }
    private void addNotifications(String title, String content) {

        if (null != content) {
            if (content.length() > 50) {
                content = content.substring(0, 49) + "......";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
                .hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }
    @FXML
    private void  Envoimail (ActionEvent event) throws MessagingException {
      mail.SendMail("selmamohsenabdelkader@gmail.com");

}
    
}
