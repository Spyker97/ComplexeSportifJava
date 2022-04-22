/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfantacademie.gui;


import enfantacademie.entities.Enfant;

import enfantacademie.services.EnfantService;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author wided
 */
public class GestionEnfantController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextField Age;
    @FXML
    private TextField Taille;
    @FXML
    private TextField Poids;
    @FXML
    private TextField cin_parent;
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
        afficher();
        fill();
        
        
    }    


    
    private void afficher() {
        
        List<Enfant> enfant = enfantService.afficherEnfant();
        ObservableList<Enfant> observableArrayListEnfant = 
        FXCollections.observableArrayList(enfant);
        list.setItems(observableArrayListEnfant);
        
    } 
     private void fill(){}
        @FXML
    private void Ajouter(ActionEvent event){
        String nameu=Name.getText();
        int ageu=Integer.parseInt(Age.getText());
        int poid=Integer.parseInt(Poids.getText());
        int tailles=Integer.parseInt(Taille.getText());
        int cinp=Integer.parseInt(cin_parent.getText());
        Enfant u=new Enfant(nameu,ageu,poid,tailles,cinp);
        EnfantService us=new EnfantService();
        us.ajouterEnfant(u);
        
        afficher();
        
      
       
    
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
      enfant.setCin_parent(Integer.parseInt(cin_parent.getText()));
      
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
    
    
}
