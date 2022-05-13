/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import mail.mail;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import complexesportifJava.entities.reservation;
import complexesportifJava.entities.terrain;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.swing.table.DefaultTableModel;
import org.controlsfx.control.Notifications;
import complexesportifJava.services.reservationService;
import complexesportifJava.services.terrainService;
import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.SessionClient;
/**
 * FXML Controller class
 *
 * @author 21629
 */
public class ReservationController implements Initializable {

    Parent parent;
    Stage stage;

    Connection connexion;
    public PreparedStatement st;
    public ResultSet result;
    @FXML
    private TableView<reservation> tableReservation;
    @FXML
    private TableColumn<reservation, Integer> cinID;
    @FXML
    private TableColumn<reservation, Integer> cinTerrain;
    @FXML
    private TableColumn<reservation, Integer> cinC;
    @FXML
    private TableColumn<reservation, String> cinD;
    public ObservableList<reservation> data = FXCollections.observableArrayList();
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfCIN;
    @FXML
    private TextField tfD;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnImprimer;
    @FXML
    private Button buttntrier;
    @FXML
    private Button btnmail;
    @FXML
    private TableView<terrain> tableTerrain;

    ObservableList<terrain> listP;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> chef;
    @FXML
    private TableColumn<?, ?> equi;

    public void showReservation() {
        tableReservation.getItems().clear();
        String sql = "select * from reservation";
        try {
            st = connexion.prepareStatement(sql);
            result = st.executeQuery();
            while (result.next()) {
                data.add(new reservation(result.getInt("id"), result.getInt("idterrain_id"), result.getInt("cin"), result.getString("discipline")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cinID.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id"));
        cinTerrain.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("idterrain_id"));
        cinC.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("cin"));
        cinD.setCellValueFactory(new PropertyValueFactory<reservation, String>("discipline"));
        tableReservation.setItems(data);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connexion = MaConnexion.getInstance().getCnx();
        showReservation();
        afficherListeTerrain();
        tfCIN.setText(SessionClient.cin+"");

        // TODO
    }

    public void afficherListeTerrain() {

        terrainService ps = new terrainService();
        List<terrain> terrain = ps.afficherr();
        listP = FXCollections.observableArrayList(terrain);
        tableTerrain.setItems(listP);
        id.setCellValueFactory(new PropertyValueFactory<>("idterrain_id"));

        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        chef.setCellValueFactory(new PropertyValueFactory<>("chef"));
        equi.setCellValueFactory(new PropertyValueFactory<>("equipement"));

    }

    @FXML
    private void tableViewReservation(MouseEvent event) {
        reservation reservation = tableReservation.getSelectionModel().getSelectedItem();
        String sql = "select * from reservation where id = ?";
        try {
            st = connexion.prepareStatement(sql);
            st.setInt(1, reservation.getId());
            result = st.executeQuery();
            if (result.next()) {

                tfID.setText(result.getString("idterrain_id"));
                tfCIN.setText(result.getString("cin"));
                tfD.setText(result.getString("discipline"));
                tfSearch.setText(result.getString("discipline"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void searchReservation(ActionEvent event) {
        String sql = "select * from reservation where idterrain_id= '" + tfSearch.getText() + "'";
        int m = 0;
        try {
            st = connexion.prepareStatement(sql);
            result = st.executeQuery();
            if (result.next()) {

                tfID.setText(result.getString("idterrain_id"));
                tfCIN.setText(result.getString("cin"));
                tfD.setText(result.getString("discipline"));

                m = 1;
                result.close();
                st.close();

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (m == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "aucune reservation trouvée avec ce id_terrain =" + tfID.getText() + "", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();

        }
    }

    @FXML
    private void AddReservation(ActionEvent event) {
        int id_terrain = Integer.parseInt(tfID.getText());
        int cin = Integer.parseInt(tfCIN.getText());
        String discipline = tfD.getText();

        String sql = "insert into reservation(idterrain_id,cin,discipline) values(?,?,?)";
        if (discipline.matches("^[A-Z a-z 0-9]+$") && tfCIN.getText().matches("^[0-9]+$") && (tfCIN.getText().length() == 8) && tfID.getText().matches("^[0-9]+$")) {
            try {
                st = connexion.prepareStatement(sql);// insert a row into the database.
                st.setInt(1, id_terrain);
                st.setInt(2, cin);
                st.setString(3, discipline);
                st.execute();
                addNotifications("reservation ajouteé avec succes", "reservation ajoutée");
                resetData();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "reservation ajoutée avec succé", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
                setReservationTableData();
                st.close();
                resetData();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "veuillez remplir tous les champs", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();

        }

    }

    @FXML
    private void ResetReservation(ActionEvent event) {
        showReservation();
        resetData();
    }

    @FXML
    private void UpdateReservation(ActionEvent event) {
        int id_terrain = Integer.parseInt(tfID.getText());
        int cin = Integer.parseInt(tfCIN.getText());
        String discipline = tfD.getText();
        setReservationTableData();

        if (discipline.matches("^[A-Z a-z 0-9]+$") && tfCIN.getText().matches("^[0-9]+$") && (tfCIN.getText().length() == 8) && tfID.getText().matches("^[0-9]+$")) {
            try {
                Statement smt = connexion.createStatement();//Mise à jour de données en base de données 
                smt.execute("update reservation set  idterrain_id=" + id_terrain + ",cin=" + cin + ",discipline='" + discipline + "' where idterrain_id='" + tfSearch.getText() + "'");
                resetData();
                addNotifications("reservation modifiée avec succes", "reservation modifiée");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "reservation modifiée avec succé", javafx.scene.control.ButtonType.OK);
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
    private void DeleteReservation(ActionEvent event) {
        String sql = "delete from reservation where idterrain_id='" + tfSearch.getText() + "'";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(stage);
        alert.initModality(Modality.WINDOW_MODAL);
        alert.setTitle("Delete \"" + tfSearch.getText() + "\"?");
        alert.setHeaderText("Delete \"" + tfSearch.getText() + "\"?");
        alert.setContentText("Are you sure you want to delete this reservation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent() || result.get() != ButtonType.OK) {
            System.out.println("false");
        } else {
            try {
                st = connexion.prepareStatement(sql);
                st.executeUpdate();
                resetData();
                alert = new Alert(Alert.AlertType.CONFIRMATION, "reservation supprimée avec succé", javafx.scene.control.ButtonType.OK);
                alert.showAndWait();
                showReservation();
            } catch (SQLException ex) {
                Logger.getLogger(TerrainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void resetData() {

        tfID.setText("");
        tfCIN.setText("");
        tfD.setText("");

    }

    private void setReservationTableData() {
        int rows = 0;
        int rowIndex = 0;
        try {
            Statement smt = connexion.createStatement();
            ResultSet rs2 = smt.executeQuery("select * from reservation order by idterrain_id desc");
            if (rs2.next()) {
                rs2.last();
                rows = rs2.getRow();
                rs2.beforeFirst();
            }
            String[][] data = new String[rows][4];
            while (rs2.next()) {
                data[rowIndex][0] = rs2.getInt(1) + "";
                data[rowIndex][1] = rs2.getInt(2) + "";
                data[rowIndex][2] = rs2.getInt(3) + "";
                data[rowIndex][3] = rs2.getString(4) + "";

                rowIndex++;
            }
            String[] cols = {"ID", "ID TERRAIN", "CIN", "DISCIPLINE"};
            DefaultTableModel model = new DefaultTableModel(data, cols);

            rs2.close();
            smt.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void imprime(ActionEvent event) {
        Document docu = new Document();
        try {
            PdfWriter.getInstance(docu, new FileOutputStream("facture.pdf"));
            docu.open();
            String format = "dd/m/yy hh:mm";

            SimpleDateFormat formater = new SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            docu.add(new Paragraph("Facture à : " + tfID.getText() + ""
                    + "\n cin:" + tfCIN.getText() + ""
                    + "\n descipline:" + tfD.getText() + ""
                    + "\n Fait à Tunis le :" + formater.format(date) + ""
                    + "\n signature :", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.NORMAL, BaseColor.BLACK)));
            docu.close();
            Desktop.getDesktop().open(new File("facture.pdf"));
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } /* catch (MalformedURIException e)
    {
        e.printStackTrace();}*/ catch (IOException e) {
            e.printStackTrace();
        }
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
    private void trierreservation(ActionEvent event) {
        reservationService s = new reservationService();
        // int price = Integer.parseInt(findAb.getText());
        List< reservation> R = s.trierreservation();

        ObservableList< reservation> datalist = FXCollections.observableArrayList(R);

        tableReservation.setItems(datalist);

    }

    @FXML
    private void Envoimail(ActionEvent event) throws MessagingException {
        mail.SendMail("selmamohsenabdelkader@gmail.com");

    }

    @FXML
    private void tableViewTerrain(MouseEvent event) {
        terrain terrain = tableTerrain.getSelectionModel().getSelectedItem();
        reservation reservation = tableReservation.getSelectionModel().getSelectedItem();
        String sql = "select * from terrain where id  = ?";
        try {
            st = connexion.prepareStatement(sql);
            st.setInt(1, terrain.getid());
            result = st.executeQuery();
            if (result.next()) {
                tfID.setText(result.getString("id"));
                String sql1 = "select * from reservation where idterrain_id  = ?";
                try {
                    st = connexion.prepareStatement(sql1);
                    st.setInt(1, Integer.parseInt(tfID.getText()));
                    result = st.executeQuery();
                    if (result.next()) {
                        tfCIN.setText(result.getString("cin"));
                        tfD.setText(result.getString("discipline"));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
    
       

    private void setTerrainTableData() {
        int rows = 0;
        int rowIndex = 0;
        try {
            Statement smt = connexion.createStatement();
            ResultSet rs2 = smt.executeQuery("select * from terrain order by id desc");
            if (rs2.next()) {
                rs2.last();
                rows = rs2.getRow();
                rs2.beforeFirst();
            }
            String[][] data = new String[rows][4];
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

    public void showTerrain() {
        tableTerrain.getItems().clear();
        String sql = "select * from terrain";
        try {
            st = connexion.prepareStatement(sql);
            result = st.executeQuery();
            while (result.next()) {

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cinID.setCellValueFactory(new PropertyValueFactory("id "));
        type.setCellValueFactory(new PropertyValueFactory("type"));
        chef.setCellValueFactory(new PropertyValueFactory("chef"));
        equi.setCellValueFactory(new PropertyValueFactory("equipement"));

    }

}
