/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.terrain;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ReservationTerrainController implements Initializable {
    
    Parent parent;
    Stage stage;

    Connection connexion;
    public PreparedStatement st;
    
    public ResultSet result;

    @FXML
    private TextField tfD;
    @FXML
    private TextField tfCIN;
    @FXML
    private TextField tfID;
    @FXML
    private Button btnADD;
    @FXML
    private TableView<terrain> tableTerrain;
    @FXML
    private TableColumn<terrain, String> cinType;
    @FXML
    private TableColumn<terrain, String> cinChef;
    @FXML
    private TableColumn<terrain, String> cinEquipement;
    
    public ObservableList<terrain> data = FXCollections.observableArrayList();
    
    public void showTerrain() {
        tableTerrain.getItems().clear();
        String sql = "select * from terrain";
        try {
            st = connexion.prepareStatement(sql);
            result = st.executeQuery();
            while (result.next()) {
                data.add(new terrain(result.getString("type"), result.getString("chef"), result.getString("equipement")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        cinType.setCellValueFactory(new PropertyValueFactory<terrain, String>("type"));
        cinChef.setCellValueFactory(new PropertyValueFactory<terrain, String>("chef"));
        cinEquipement.setCellValueFactory(new PropertyValueFactory<terrain, String>("equipement"));
        tableTerrain.setItems(data);

    } 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connexion = MaConnexion.getInstance().getCnx();
        showTerrain();
        // TODO
    }    

    @FXML
    private void AddReservation(ActionEvent event) {
    }

    @FXML
    private void tableViewTerrain(MouseEvent event) {
    
    
    }
    
}
