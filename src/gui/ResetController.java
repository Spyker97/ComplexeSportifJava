/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ComplexesportifJava.tools.MaConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IMNA
 */
public class ResetController implements Initializable {

    Connection con = null; 
    ResultSet rs= null ; 
    PreparedStatement pst = null ; 
    public String user ; 
    
    
    @FXML
    private TextField Newpasswd;

    @FXML
    private TextField verifypasswd;

    @FXML
    private TextField youremail;
    @FXML
    private Button Reset;

    /**
     * Initializes the controller class.
     */
//    public ResetController(String name)
//    {
//    this.user=name;
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        @FXML
    void ResetPass(MouseEvent event) throws IOException {
     if(Newpasswd.getText().equals(verifypasswd.getText())){
     try{
         String UpdateQuery="UPDATE `user` SET `password`=? WHERE email=? ";
         con =  con = MaConnexion.getInstance().getCnx();
         pst= con.prepareStatement(UpdateQuery);
         pst.setString(1, verifypasswd.getText());
         pst.setString(2, youremail.getText());
         pst.executeUpdate();
         JOptionPane.showMessageDialog(null, "Reset successfully");
         
                Node node = (Node) event.getSource();
                    Stage stage1 = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage1.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("logiin.fxml")));
                    stage1.setScene(scene);
                    stage1.show();
                
        
     }catch( Exception e){
        JOptionPane.showMessageDialog(null, "Oups");
        
     }
          
     }else{
     JOptionPane.showMessageDialog(null, "password do not match");
     Node node = (Node) event.getSource();
                    Stage stage1 = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage1.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("logiin.fxml")));
                    stage1.setScene(scene);
                    stage1.show();
     }
    }
}
