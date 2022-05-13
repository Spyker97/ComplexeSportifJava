package gui;

//import com.lowagie.text.BadElementException;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Paragraph;
import ComplexesportifJava.tools.MaConnexion;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfTable;
//import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import gui.Cours;



import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author khalil
 */
public class FXMLDocumentController implements Initializable {
    

    @FXML
    private TableView<users> table_users;

//    @FXML
//    private TableColumn<users, Integer> col_id;

    @FXML
    private TableColumn<users, String> col_username;

    @FXML
    private TableColumn<users, String> col_Number;

    @FXML
    private TableColumn<users, String> col_email;

    @FXML
    private TableColumn<users, String> col_type;
    
     @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_Number;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_type;
        
    private TextField txt_id;
    
    @FXML
    private TextField filterField;
    
       
    ObservableList<users> listM;
    ObservableList<users> dataList;
    
    @FXML
    private ComboBox<Cours> combo;
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
  
    @FXML
    private TableColumn<users, Integer> col_id;
    @FXML
    private Button btnPDF;
    
    
     
    @FXML
    public void Add_users (){    
        conn = MaConnexion.getInstance().getCnx();
        String sql = "insert into users (username,Number,email,type)values(?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_Number.getText());
            pst.setString(3, txt_email.getText());
            pst.setString(4, txt_type.getText());
//            pst.setInt(5, combo.getSelectionModel().getSelectedItem().getId());
//            pst.setObject(5, combo.getSelectionModel().getSelectedItem());
            pst.execute();
             
            JOptionPane.showMessageDialog(null, "Users Add succes");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    


    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
    index = table_users.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
//    txt_id.setText(col_id.getCellData(index).toString());
    txt_username.setText(col_username.getCellData(index).toString());
    txt_Number.setText(col_Number.getCellData(index).toString());
    txt_email.setText(col_email.getCellData(index).toString());
    txt_type.setText(col_type.getCellData(index).toString());
    
    }

    @FXML
    public void Edit (){   
        try {
            conn = MaConnexion.getInstance().getCnx();
//            String value1 = txt_id.getText();
            String value2 = txt_username.getText();
            String value3 = txt_Number.getText();
            String value4 = txt_email.getText();
            String value5 = txt_type.getText();
            String sql = "update users set username= '"+value2+"',Number= '"+
                    value3+"',email= '"+value4+"',type= '"+value5+"' where username='"+value2+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    @FXML
    public void Delete(){
    conn = MaConnexion.getInstance().getCnx();
    String sql = "delete from users where username = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable(){
//        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_Number.setCellValueFactory(new PropertyValueFactory<users,String>("Number"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));
        
        listM = MaConnexion.getDatausers();
        table_users.setItems(listM);
    }
    
    

    
 @FXML
    void search_user() {          
//        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_Number.setCellValueFactory(new PropertyValueFactory<users,String>("Number"));
        col_email.setCellValueFactory(new PropertyValueFactory<users,String>("email"));
        col_type.setCellValueFactory(new PropertyValueFactory<users,String>("type"));
        
        dataList = MaConnexion.getDatausers();
        table_users.setItems(dataList);
        FilteredList<users> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches Number
    }else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches Number
    }
    else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<users> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
  table_users.setItems(sortedData);      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
    search_user();
    // Code Source in description
    } 

    


    
    public class Pdf {
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
     
        
        document.add(new Paragraph("La liste des Abonnements :"));
        document.add(new Paragraph("     "));
         for(users l:listM)
        {
            
     document.add(new Paragraph("Username:"+l.getUsername()));
     document.add(new Paragraph("Number:"+l.getNumber()));
       document.add(new Paragraph("Email:"+l.getEmail()));
        document.add(new Paragraph("Type:"+l.getType()));

          
        
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }    
    
}
    
    @FXML
    private void pdf(ActionEvent event) {
        Pdf pd = new Pdf();
        try {
            pd.GeneratePdf("liste of voyage");
            System.out.println("impression done");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    

    
    

    

}

