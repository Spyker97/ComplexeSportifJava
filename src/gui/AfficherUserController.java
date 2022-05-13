/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.User;
import complexesportifJava.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AfficherUserController implements Initializable {

    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> CCin;
    @FXML
    private TableColumn<User, String> CNom;
    @FXML
    private TableColumn<User, String> CPrenom;
    @FXML
    private TableColumn<User, String> CDateNaiss;
    @FXML
    private TableColumn<User, String> CGenre;
    @FXML
    private TableColumn<User, String> CEmail;
    @FXML
    private TableColumn<User, ?> CAdresse;
   
    Button bt = new Button("Supprimer");
        @FXML
        private TableColumn<User, String> CAction;
    @FXML
    private TextField filterField;

    ObservableList<User> listM;
    ObservableList<User> dataList;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        search();
    }    
    
     ObservableList<User>  ActList = FXCollections.observableArrayList();
     UserService us = new UserService();
      private void refreshTable() {
        
         ActList.addAll(us.afficherUser());
      
         tableUser.setItems(ActList); 
       
    }
      
      private void loadDate() {
        CCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        CNom.setCellValueFactory(new PropertyValueFactory<>("username"));
        CPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        CDateNaiss.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        CGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        CEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        CAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//        CAction.setCellValueFactory(new Button<>("Supprimer"));
        
        refreshTable();
   
     }

    @FXML
    private void Delete(ActionEvent event) {
        loadDate();
         UserService us = new UserService();
            User a = tableUser.getSelectionModel().getSelectedItem();
            us.supprimer(a.getCin());
            
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         Parent root  = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }
    
//    @FXML
//    void search_user() {          
//        CCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
//        CNom.setCellValueFactory(new PropertyValueFactory<>("username"));
//        CPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        
//         UserService us = new UserService();
//     
//        
//        dataList = (ObservableList<User>) us.searchUser();
//        tableUser.setItems(dataList);
//        FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);  
// filterField.textProperty().addListener((observable, oldValue, newValue) -> {
// filteredData.setPredicate(person -> {
//    if (newValue == null || newValue.isEmpty()) {
//     return true;
//    }    
//    String lowerCaseFilter = newValue.toLowerCase();
//    
//    if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//     return true; // Filter matches Coach
//    }else  
//          return false; // Does not match.
//   });
//  });  
//  SortedList<User> sortedData = new SortedList<>(filteredData);  
//  sortedData.comparatorProperty().bind(tableUser.comparatorProperty());  
//  tableUser.setItems(sortedData);      
//    }
    
    private void search(){
        UserService s = new UserService();
        ObservableList<User> listUser = FXCollections.observableArrayList(s.searchUser());
        FilteredList<User> filteredData = new FilteredList<>(listUser,p -> true);

    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(user ->{
            if(newValue == null || newValue.isEmpty()){
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();
            if(String.valueOf(user.getCin()).toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else if(user.getUsername().toLowerCase().contains(lowerCaseFilter)){
                return true; 
            }else if(user.getPrenom().toLowerCase().contains(lowerCaseFilter)){
                return true; 
            }
            return false;
        });
    });

        SortedList<User> sortedData = new SortedList<>(filteredData);

    tableUser.setItems(sortedData);
    }
    
}
