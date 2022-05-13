/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.Evenement;
import complexesportifJava.entities.TypeEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
 * @author Oumaima
 */
public class EventsListController implements Initializable {

    @FXML
    private TableColumn<Evenement, Integer> cl_id;
    @FXML
    private TableColumn<Evenement, String> cl_nom;
    @FXML
    private TableColumn<Evenement, Date> cl_dateS;
    @FXML
    private TableColumn<Evenement, Date> cl_dateE;
    @FXML
    private TableColumn<Evenement, Integer> cl_nmb;
    @FXML
    private TableColumn<Evenement, String> cl_desc;
    @FXML
    private TableColumn<Evenement, String> cl_action;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnGestEvent;
    String query = null;
    Connection cnxx = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    Evenement eve = null;

    ObservableList<Evenement> eveList = FXCollections.observableArrayList();
    @FXML
    private TextField txtregionid;
    @FXML
    private TableView<Evenement> event_table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        loadData();
    }

    void setData(int id) {
        txtregionid.setText("" + id);

    }

    public void refreshETable(int idd) {
        try {
            eveList.clear();

            txtregionid.setText("" + idd);

            query = "SELECT * FROM evenement WHERE type_id_id = '" + idd + "' ";
            preparedStatement = cnxx.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                eveList.add(new Evenement(rs.getInt("id"), rs.getString("titre"), rs.getDate("date_start"), rs.getDate("date_end"), rs.getInt("nombre_par"), rs.getString("description")));
                event_table.setItems(eveList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EventsListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadData() {

        cnxx = MaConnexion.getInstance().getCnx();
        //refreshETable( idd);

        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("titre"));
        cl_dateS.setCellValueFactory(new PropertyValueFactory<>("date_start"));
        cl_dateE.setCellValueFactory(new PropertyValueFactory<>("date_end"));
        cl_nmb.setCellValueFactory(new PropertyValueFactory<>("nombre_par"));
        cl_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        //add cell of button edit 
        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = (TableColumn<Evenement, String> param) -> {
            // make cell containing buttons
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteIcon = new Button("Supprimer");
                        Button editIcon = new Button("Modifier");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + " -fx-background-color: #ffffff;"
                                + "-fx-background-radius: 5px;"
                                + " -fx-border-color: #bf3131;"
                                + "-fx-border-radius: 5px;"
                                + "  -fx-border-width: 2px;"
                                + "-fx-text-fill: #bf3131;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + " -fx-background-color:  #ffffff;"
                                + "-fx-background-radius: 5px;"
                                + " -fx-border-color: #186b41;"
                                + "-fx-border-radius: 5px;"
                                + "  -fx-border-width: 2px;"
                                + "-fx-text-fill: #186b41;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                eve = event_table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM evenement WHERE id  ='" + eve.getId() + "' ";
                                cnxx = MaConnexion.getInstance().getCnx();
                                preparedStatement = cnxx.prepareStatement(query);
                                preparedStatement.execute();
                                int iddtype = Integer.parseInt(txtregionid.getText());

                                refreshETable(iddtype);

                            } catch (SQLException ex) {
                                Logger.getLogger(TypeEventTableController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        editIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent event) {

                                FXMLLoader Loader = new FXMLLoader(getClass().getResource("EditEvent.fxml"));
                                try {

                                    Parent root = Loader.load();

                                    btnaj.getScene().setRoot(root);
                                } catch (IOException ex) {
                                    System.out.println("Error: " + ex.getMessage());
                                }

                                EditEventController eec = Loader.getController();
                                eec.setData(event_table.getSelectionModel().getSelectedItem().getTitre(), event_table.getSelectionModel().getSelectedItem().getNombre_par(), event_table.getSelectionModel().getSelectedItem().getDescription(), event_table.getSelectionModel().getSelectedItem().getId());
                                int ss = Integer.parseInt(txtregionid.getText());
                                eec.setidData(ss);
                                Parent p = Loader.getRoot();
                                Stage stage = new Stage();
                                stage.initStyle(StageStyle.TRANSPARENT);
                                stage.setScene(new Scene(p));
                                stage.show();

                            }

                        });

                        
                        
                        
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        cl_action.setCellFactory(cellFoctory);
        event_table.setItems(eveList);

    }

    @FXML
    private void listDesRegions(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("TypeEventTable.fxml"));
        try {

            Parent root = Loader.load();

            btnaj.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @FXML
    private void ajouterEvent(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));

        try {

            Parent root = Loader.load();

            btnaj.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        AddEventController el = Loader.getController();
        int ss = Integer.parseInt(txtregionid.getText());
        el.setData(ss);
        Parent p = Loader.getRoot();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(p));
        stage.show();

    }

}
