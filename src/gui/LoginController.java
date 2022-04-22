/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tt_email;
    @FXML
    private TextField tt_password;
    @FXML
    private Button button_signup;
    @FXML
    private Label lblErrors;

    /**
     * Initializes the controller class.
     */
    
      Connection con= null;
      PreparedStatement preparedStatement =null;
      ResultSet resultSet =null; 
    @Override
     public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logIn();
    }    

    @FXML
    private void buttonLogin(ActionEvent event) {
         //login here
        
    }
    
   

    //we gonna use string to check for status
    private String logIn() {
        String status = "Success";
        String email = tt_email.getText();
        String password = tt_password.getText();
        if(email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM user Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        
        return status;
    }
    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    
    
    
    
//    public void logIn(){
//      Connection con= null;
//      PreparedStatement preparedStatement =null;
//      ResultSet resultSet =null;  
//      
//      String email = tt_email.getText();
//      String password = tt_password.getText();
//       
//       //query
//       String sql = "SELECT * FROM  user where email = ? and password = ?";
//        try {
//            preparedStatement= con.prepareStatement(sql);
//            preparedStatement.setString(1,email);
//            preparedStatement.setString(2,password);
//            resultSet= preparedStatement.executeQuery();
//            if (!resultSet.next()){
//                lblErrors.setTextFill(Color.TOMATO);
//                lblErrors.setText("Enter Correct Email/password");
//            }else{
//                lblErrors.setTextFill(Color.GREEN);
//                lblErrors.setText("Login Successful...Redirectiong..");
////                showDialog("Login Successful", null, "Successful");
//            }
//
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//    }
}
