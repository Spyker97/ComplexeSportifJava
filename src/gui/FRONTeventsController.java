/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.Evenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Oumaima
 */
public class FRONTeventsController implements Initializable {

    @FXML
    private Button btnGestEvent;
     @FXML
    private TableView<Evenement> event_table;
    @FXML
    private TableColumn<Evenement, Integer> cl_id;
    @FXML
    private TableColumn<Evenement, String> cl_nom;
    @FXML
    private TableColumn<Evenement, Date> cl_dateS;
    @FXML
    private TableColumn<Evenement, Date> cl_dateE;

    @FXML
    private TableColumn<Evenement, String> cl_desc;
    @FXML
    private TableColumn<Evenement, String> cl_action;
    String query = null;
    Connection cnxx = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    Evenement eve = null;
     ObservableList<Evenement> eveList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> cl_cap;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }    
    
  public void refreshETable() {
        try {
            eveList.clear();

           

            query = "SELECT * FROM evenement ";
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
        refreshETable();

        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("titre"));
        cl_dateS.setCellValueFactory(new PropertyValueFactory<>("date_start"));
        cl_dateE.setCellValueFactory(new PropertyValueFactory<>("date_end"));
       
        cl_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        //add cell of button participate 
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
                        
                         
                        Label lbl = new Label(">");
                        Button participerIcon = new Button("Participer");
                      
                      

                        participerIcon.setStyle(
                                 " -fx-cursor: hand ;"
                                + " -fx-background-color: #ffffff;"
                                + "-fx-background-radius: 5px;"
                                + " -fx-border-color: #bf3131;"
                                + "-fx-border-radius: 5px;"
                                + "  -fx-border-width: 2px;"
                                + "-fx-text-fill: #bf3131;"
                        );
                              participerIcon.setStyle(
                                 " -fx-cursor: hand ;"
                                + " -fx-background-color: #ffffff;"
                                + "-fx-background-radius: 5px;"
                                + " -fx-border-color: #bf3131;"
                                + "-fx-border-radius: 5px;"
                                + "  -fx-border-width: 2px;"
                                + "-fx-text-fill: #bf3131;"
                        );
                        
                        
                      

                        participerIcon.setOnMouseClicked((MouseEvent event) -> {
                  cnxx = MaConnexion.getInstance().getCnx();
                  eve = event_table.getSelectionModel().getSelectedItem();
                 
                            try {
                              
                                eve = event_table.getSelectionModel().getSelectedItem();
                                
                 query = "INSERT INTO participant (evenement_id,user_id) VALUES (?,?)";
                
                                preparedStatement = cnxx.prepareStatement(query);                                 
                                preparedStatement.setInt(1, eve.getId());
                                preparedStatement.setInt(2, 1);
                                preparedStatement.executeUpdate();
                              
            
                

                                refreshETable();

                            } catch (SQLException ex) {
                                Logger.getLogger(TypeEventTableController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                             

                        });
    event_table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
               FXMLLoader Loader = new FXMLLoader(getClass().getResource("EventParticipants.fxml"));
            try {         
            
                Parent root = Loader.load();
 
                event_table.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            
            EventParticipantsController el = Loader.getController();
                el.refreshETable(event_table.getSelectionModel().getSelectedItem().getId());
                Parent p = Loader.getRoot();
                
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(p));
                stage.show();
                
                                      
        }
    }
});  
                      

                        HBox managebtn = new HBox(participerIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(participerIcon, new Insets(2, 2, 0, 3));
                     

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
    private void listDesEvenements(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("FRONTevents.fxml"));
            try {         
            
                Parent root = Loader.load();
 
                event_table.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
    }
    
}
