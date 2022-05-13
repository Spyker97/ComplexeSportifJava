/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.TypeEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
public class TypeEventTableController implements Initializable {

     
    @FXML
    private TableColumn<TypeEvent, Integer> cl_id;
    @FXML
    private TableColumn<TypeEvent, String> cl_nom;
    @FXML
    private TableColumn<TypeEvent, String> cl_desc;
    @FXML
    private TableColumn<TypeEvent, String> cl_action;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnGestEvent;
    
    String query = null;
    Connection cnxx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    TypeEvent typeve = null;

    ObservableList<TypeEvent> typeList = FXCollections.observableArrayList();
    @FXML
    private TableView<TypeEvent> type_table;
    @FXML
    private TextField searchBox1;
    @FXML
    private Button searchbtnclose;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           loadData();
           
       
    FilteredList<TypeEvent> filteredData = new FilteredList<>(FXCollections.observableList(typeList));
        type_table.setItems(filteredData);

        type_table.setRowFactory(tableView -> {
            TableRow<TypeEvent> row = new TableRow<>();
            row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), false);
            row.itemProperty().addListener((obs, oldOrder, newOrder) -> {
                boolean assignClass = filteredData.contains(newOrder) &&
                        (newOrder.getType().equals("maraton") ||
                                newOrder.getType().equals("gala"));

                row.pseudoClassStateChanged(PseudoClass.getPseudoClass("highlighted"), assignClass);
            });
            return row;
        });

        searchBox1.textProperty().addListener((observable, oldValue, newValue) ->
                type_table.setItems(filterList(typeList, newValue.toLowerCase()))
        );
    }

    
    
    
    
    private Predicate<TypeEvent> createPredicate(String searchText){
        return order -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchFindsOrder(order, searchText);
        };
    }

    private ObservableList<TypeEvent> filterList(List<TypeEvent> list, String searchText){
        List<TypeEvent> filteredList = new ArrayList<>();

        for (TypeEvent e : list){
            if(searchFindsOrder(e, searchText)){
                filteredList.add(e);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    private boolean searchFindsOrder(TypeEvent e, String searchText){
        return (e.getType().toLowerCase().contains(searchText))  ;
    }
    
    
    private void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

   
    
    
      private void refreshTable() {
        try {
            typeList.clear();

            query = "SELECT * FROM type_event";
            preparedStatement = cnxx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                typeList.add(new TypeEvent(
                        resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getString("description")));
                type_table.setItems(typeList);

            }

        } catch (SQLException ex) {
            Logger.getLogger(TypeEventTableController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadData() {

        cnxx = MaConnexion.getInstance().getCnx();
        refreshTable();

        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("type"));
        cl_desc.setCellValueFactory(new PropertyValueFactory<>("description"));

        //add cell of button edit 
        Callback<TableColumn<TypeEvent, String>, TableCell<TypeEvent, String>> cellFoctory = (TableColumn<TypeEvent, String> param) -> {
            // make cell containing buttons
            final TableCell<TypeEvent, String> cell = new TableCell<TypeEvent, String>() {
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
                                +"-fx-text-fill: #bf3131;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + " -fx-background-color:  #ffffff;"
                                + "-fx-background-radius: 5px;"
                                + " -fx-border-color: #186b41;"
                                + "-fx-border-radius: 5px;"
                                + "  -fx-border-width: 2px;"
                                +"-fx-text-fill: #186b41;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                typeve = type_table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM type_event WHERE id  ='" + typeve.getId() + "' ";
                                cnxx = MaConnexion.getInstance().getCnx();
                                preparedStatement = cnxx.prepareStatement(query);
                                preparedStatement.execute();

                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(TypeEventTableController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        
                    editIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent event) {

                                FXMLLoader Loader = new FXMLLoader(getClass().getResource("EditTypeEvent.fxml"));
                                try {

                                    Parent root = Loader.load();

                                    btnaj.getScene().setRoot(root);
                                } catch (IOException ex) {
                                    System.out.println("Error: " + ex.getMessage());
                                }

                                EditTypeEventController alertBoxController = Loader.getController();
                                alertBoxController.setData(type_table.getSelectionModel().getSelectedItem().getType(),
                                        type_table.getSelectionModel().getSelectedItem().getDescription(),
                                        type_table.getSelectionModel().getSelectedItem().getId());
                                Parent p = Loader.getRoot();
                                Stage stage = new Stage();
                                stage.initStyle(StageStyle.TRANSPARENT);
                                stage.setScene(new Scene(p));
                                stage.show();

                            }
                  

                        }); 
                   
      type_table.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
               FXMLLoader Loader = new FXMLLoader(getClass().getResource("EventsList.fxml"));
            try {         
            
                Parent root = Loader.load();
 
                btnaj.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            
            EventsListController el = Loader.getController();
                el.refreshETable( type_table.getSelectionModel().getSelectedItem().getId());
                Parent p = Loader.getRoot();
                
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(p));
                stage.show();
                
                                      
        }
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
        type_table.setItems(typeList);

    }

    @FXML
    private void ajouterRegion(ActionEvent event) {
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("AddTypeEvent.fxml"));
            try {         
            
                Parent root = Loader.load();
 
                btnaj.getScene().setRoot(root);  
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
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
    private void handleClearSearchText(ActionEvent event) {
    }

    
}
