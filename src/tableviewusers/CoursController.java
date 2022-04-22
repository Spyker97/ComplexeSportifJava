package tableviewusers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class CoursController implements Initializable {
    

    @FXML
    private TableView<Cours> table_Cours;

    @FXML
    private TableColumn<Cours, Integer> col_id;

    @FXML
    private TableColumn<Cours, String> col_Coach;

    @FXML
    private TableColumn<Cours, String> col_Num_salle;

 

    @FXML
    private TableColumn<Cours, String> col_type;
    
     @FXML
    private TextField txt_Coach;

    @FXML
    private TextField txt_Num_salle;



    @FXML
    private TextField txt_type;
        
    @FXML
    private TextField txt_id;
    
    @FXML
    private TextField filterField;
    
       
    ObservableList<Cours> listM;
    ObservableList<Cours> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
     
    public void Add_Cours (){    
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into Cours (Coach,Num_salle,type)values(?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_Coach.getText());
            pst.setString(2, txt_Num_salle.getText());
          
            pst.setString(3, txt_type.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Cours Add succes");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    //////// methode select Cours ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_Cours.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_id.setText(col_id.getCellData(index).toString());
    txt_Coach.setText(col_Coach.getCellData(index).toString());
    txt_Num_salle.setText(col_Num_salle.getCellData(index).toString());
 
    txt_type.setText(col_type.getCellData(index).toString());
    
    }

    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_Coach.getText();
            String value3 = txt_Num_salle.getText();
       
            String value4 = txt_type.getText();
            String sql = "update Cours set id= '"+value1+"',Coach= '"+value2+"',Num_salle= '"+
                    value3+"',type= '"+value4+"' where id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void Delete(){
    conn = mysqlconnect.ConnectDb();
    String sql = "delete from Cours where id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Cours,Integer>("id"));
        col_Coach.setCellValueFactory(new PropertyValueFactory<Cours,String>("Coach"));
        col_Num_salle.setCellValueFactory(new PropertyValueFactory<Cours,String>("Num_salle"));

        col_type.setCellValueFactory(new PropertyValueFactory<Cours,String>("type"));
        
        listM = mysqlconnect.getDataCours();
        table_Cours.setItems(listM);
    }
    
    

    
 @FXML
    void search_user() {          
        col_id.setCellValueFactory(new PropertyValueFactory<Cours,Integer>("id"));
        col_Coach.setCellValueFactory(new PropertyValueFactory<Cours,String>("Coach"));
        col_Num_salle.setCellValueFactory(new PropertyValueFactory<Cours,String>("Num_salle"));
        
        col_type.setCellValueFactory(new PropertyValueFactory<Cours,String>("type"));
        
        dataList = mysqlconnect.getDataCours();
        table_Cours.setItems(dataList);
        FilteredList<Cours> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getCoach().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches Coach
    }else if (person.getNum_salle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches Num_salle
    }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) 
     return true; // Filter matches Num_salle
                     
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Cours> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table_Cours.comparatorProperty());  
  table_Cours.setItems(sortedData);      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
    search_user();
    // Code Source in description
    } 
}

