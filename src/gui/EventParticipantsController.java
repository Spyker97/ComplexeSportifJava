/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.Evenement;
import complexesportifJava.entities.Participant;
import complexesportifJava.entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author seif
 */
public class EventParticipantsController implements Initializable {

    @FXML
    private Label txteventnom;
    @FXML
    private Button btnGestEvent;
    @FXML
    private Label txteventnom1;
    @FXML
    private TextField eveid;
  String query = null;
    Connection cnxx = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    Evenement eve = null;

    ObservableList<User> eveList = FXCollections.observableArrayList();
    @FXML
    private TableView<User> tableparticipants;
    @FXML
    private TableColumn<User, String> cl_nom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }    
        void setdata(int id) {
        eveid.setText("" + id);

    }
          public void refreshETable(int idd) {
        try {
            eveList.clear();

        eveid.setText("" + idd);

            query = "SELECT nom from user WHERE id = (SELECT user_id from participant  WHERE evenement_id='" + idd + "' )";
            preparedStatement = cnxx.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
//                eveList.add(new User(rs.getString("nom")));
                tableparticipants.setItems(eveList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EventsListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
          
              public void loadData() {

        cnxx = MaConnexion.getInstance().getCnx();
        int ii =  Integer.parseInt(eveid.getText());
       // refreshETable( ii);

         
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       
        tableparticipants.setItems(eveList);

    }

    @FXML
    private void listDesRegions(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("FRONTevents.fxml"));
            try {         
            
                Parent root = Loader.load();
 
                btnGestEvent.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
    }
    
}
