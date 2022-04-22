/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import entities.terrain;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.table.DefaultTableModel;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author 21629
 */
public class TerrainController implements Initializable {

    Parent parent;
    Stage stage;

    Connection connexion;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private TableView<terrain> tableTerrain;
    @FXML
    private TableColumn<terrain, Integer> cinID;
    @FXML
    private TableColumn<terrain, String> cinType;
    @FXML
    private TableColumn<terrain, String> cinChef;
    @FXML
    private TableColumn<terrain, String> cinEquipement;

    public ObservableList<terrain> data = FXCollections.observableArrayList();
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfChef;
    @FXML
    private TextField tfEquipement;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    public void showTerrain() {
        tableTerrain.getItems().clear();
        String sql = "select * from terrain";
        try {
            st = connexion.prepareStatement(sql);
            result = st.executeQuery();
            while (result.next()) {
                data.add(new terrain(result.getInt("id_terrain"), result.getString("type"), result.getString("chef"), result.getString("equipement")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cinID.setCellValueFactory(new PropertyValueFactory<terrain, Integer>("id_terrain"));
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
        connexion = MyDB.getInstance().getConnexion();
        showTerrain();
        // TODO
    }

    @FXML
    private void tableViewTerrain(MouseEvent event) {
        terrain terrain = tableTerrain.getSelectionModel().getSelectedItem();
        String sql = "select * from terrain where id_terrain = ?";
        try {
            st = connexion.prepareStatement(sql);
            st.setInt(1, terrain.getId_terrain());
            result = st.executeQuery();
            if (result.next()) {

                tfType.setText(result.getString("type"));
                tfChef.setText(result.getString("chef"));
                tfEquipement.setText(result.getString("equipement"));
                tfSearch.setText(result.getString("chef"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void searchTerrain(ActionEvent event) {
        String sql = "select * from terrain where chef= '" + tfSearch.getText() + "'";
        int m = 0;
        try {
            st = connexion.prepareStatement(sql);
            result = st.executeQuery();
            if (result.next()) {

                
                tfType.setText(result.getString("type"));
                tfChef.setText(result.getString("chef"));
                tfEquipement.setText(result.getString("equipement"));
                
                m = 1;
                result.close();
                st.close();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (m == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "aucun terrain trouvé avec ce chef =" + tfChef.getText() + "", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();

        }
    }

    @FXML
    private void AddTerrain(ActionEvent event) {

        String type = tfType.getText();
        String chef = tfChef.getText();
        String equipement = tfEquipement.getText();

        String sql = "insert into terrain(type,chef,equipement) values(?,?,?)";
        /*if (equipement.matches("^[A-Z a-z 0-9]+$") && type.matches("^[a-zA-Z]+$") && !chef.equals("")) {
         try {
                st = connexion.prepareStatement(sql);
                st.setString(1, type);
                st.setString(2, chef);
                st.setString(3, equipement);
                st.execute();
                resetData();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "terrain ajouté avec succé", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
                setTerrainTableData();
                st.close();
                resetData();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();

        }*/
        try {
            st = connexion.prepareStatement(sql);
            st.setString(1, type);
            st.setString(2, chef);
            st.setString(3, equipement);
            st.execute();
            resetData();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "terrain ajouté avec succé", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            setTerrainTableData();
            st.close();
            resetData();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ResetTerrain(ActionEvent event) {
        showTerrain();
        resetData();
    }

    @FXML
    private void UpdateTerrain(ActionEvent event) {

        String type = tfType.getText();
        String chef = tfChef.getText();
        String equipement = tfEquipement.getText();
        setTerrainTableData();

        if (equipement.matches("^[A-Z a-z 0-9]+$") && type.matches("^[a-zA-Z]+$") && !chef.equals("")) {
            try {
                Statement smt = connexion.createStatement();
                smt.execute("update terrain set  type='" + type + "',chef='" + chef + "',equipement='" + equipement + "' where chef='" + tfSearch.getText() + "'");
                resetData();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "terrain modifié avec succé", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();

        }

    }

    @FXML
    private void DeleteTerrain(ActionEvent event) {
        String sql = "delete from terrain where chef='" + tfSearch.getText() + "'";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setTitle("Delete \"" + tfSearch.getText() + "\"?");
        alert.setHeaderText("Delete \"" + tfSearch.getText() + "\"?");
        alert.setContentText("Are you sure you want to delete this terrain ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent() || result.get() != ButtonType.OK) {
            System.out.println("false");
        } else {
            try {
                st = connexion.prepareStatement(sql);
                st.executeUpdate();
                resetData();
                alert = new Alert(Alert.AlertType.CONFIRMATION, "terrain supprimé avec succé", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
                showTerrain();
            } catch (SQLException ex) {
                Logger.getLogger(TerrainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void resetData() {

        tfType.setText("");
        tfChef.setText("");
        tfEquipement.setText("");

    }

    private void setTerrainTableData() {
        int rows = 0;
        int rowIndex = 0;
        try {
            Statement smt = connexion.createStatement();
            ResultSet rs2 = smt.executeQuery("select * from terrain order by id_terrain desc");
            if (rs2.next()) {
                rs2.last();
                rows = rs2.getRow();
                rs2.beforeFirst();
            }
            String[][] data = new String[rows][8];
            while (rs2.next()) {
                data[rowIndex][0] = rs2.getInt(1) + "";
                data[rowIndex][1] = rs2.getString(2) + "";
                data[rowIndex][2] = rs2.getString(3) + "";
                data[rowIndex][3] = rs2.getString(4) + "";

                rowIndex++;
            }
            String[] cols = {"ID", "TYPE", "CHEF", "EQUIPEMENT"};
            DefaultTableModel model = new DefaultTableModel(data, cols);

            rs2.close();
            smt.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
