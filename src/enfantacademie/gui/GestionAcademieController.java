/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfantacademie.gui;

import enfantacademie.entities.Academie;
import enfantacademie.entities.Enfant;
import enfantacademie.services.AcademieService;
import enfantacademie.services.EnfantService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author wided
 */
public class GestionAcademieController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextField Nbr;
    private TextField searchAcademie;
    
    @FXML
    private ListView<Academie> list;
    private Text alerteName;
    private Text alerteNbr;
    /**
     * Initializes the controller class.
     */
    
    AcademieService academieService = AcademieService.getInstance();
    private TextField id;
    @FXML
    private Button gu;
    @FXML
    private Button ga;
    @FXML
    private TextField searchVoyage;
    
            @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        fill();
        
        
    }    


    
    private void afficher() {
        
        List<Academie> academie = academieService.afficherAcademie();
        ObservableList<Academie> observableArrayListAcademie = 
        FXCollections.observableArrayList(academie);
        list.setItems(observableArrayListAcademie);
        
    } 
     private void fill(){
        list.setOnMouseClicked(e->{
            Academie academie = academieService.rechercherParId(list.getSelectionModel().getSelectedItem());
            Name.setText(academie.getName());
            Nbr.setText(String.valueOf(academie.getNbr_seance()));
           
               
        });
    } 

    
 
     @FXML
    private void modifier(ActionEvent event) {
        Academie academie = new Academie();
        Academie a = academieService.rechercherParId(list.getSelectionModel().getSelectedItem());
        academie.setId(a.getId()); 
         Academie v = new Academie();
        
        
          Boolean verif = true;
        
        
     
         if (Name.getText().equals("")) {
            alerteName.setText("Remplir le champs !!");
            verif = false;
        } 
        // Control Nombre Places
        if (Nbr.getText().equals("")) {
            alerteNbr.setText("Remplir le champs !!");
            verif = false;
        } else if (!Nbr.getText().matches("[\\d\\.]+")) {
            alerteNbr.setText("Nombres doit être un entier !!");
            verif = false;
         
         
               
       if (verif == true) {
            v = new Academie (Name.getText(),Integer.parseInt(Nbr.getText()));

            AcademieService vs = new AcademieService();
           
                
                   
       academie.setName(Name.getText());
        academie.setNbr_seance(Integer.parseInt(Nbr.getText()));
        
      
       academieService.modifierAcademie(academie);
        
       
                      Nbr.setText("");
                    Name.setText("");
       
                       afficher();
       }
        }
    }

    @FXML
    private void Supprimer(ActionEvent event){
   Academie u = new Academie();
        Academie a = academieService.rechercherParId(list.getSelectionModel().getSelectedItem());
        u.setId(a.getId());
       academieService.supprimerAcademie(u.getId());
       
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

  
    private void search(){
         AcademieService sc = new AcademieService();
        ObservableList<Academie> listVoyagee = FXCollections.observableArrayList(sc.afficherAcademie());
        FilteredList<Academie> filteredData = new FilteredList<>(listVoyagee,p -> true);

    //Set the filter Predicate whenever the filter changes.
    searchAcademie.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(Academie ->{
            // If filter text is empty, display all persons.
        if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Academie.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (String.valueOf(Academie.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if (String.valueOf(Academie.getNbr_seance()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

    //Wrap the FilteredList in a SortedList.
        SortedList<Academie> sortedData = new SortedList<>(filteredData);

    //put the sorted list into the listview
    list.setItems(sortedData);
    }

    @FXML
    private void Ajouter1(ActionEvent event) {
        String nameu=Name.getText();
        int nbr=Integer.parseInt(Nbr.getText());
        Academie u=new Academie(nameu,nbr);
        AcademieService us=new AcademieService();
        us.ajouterAcademie(u);
        
        afficher();
    }

    
    
}
