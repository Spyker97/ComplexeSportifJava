/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import complexesportifJava.entities.SessionClient;
import complexesportifJava.entities.User;
import complexesportifJava.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class LogiinController implements Initializable {
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
    
      
    @FXML
    private Button loginButton;
    @FXML
    private CheckBox rememberMe;
    
       @Override
    
     public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserService retUser = new UserService();
        if (retUser.userRemember().getRememberMe() == 1){
            tt_email.setText(retUser.userRemember().getEmail());
            tt_password.setText(retUser.userRemember().getPassword());
            
        }else{
                tt_email.setText("");
                tt_password.setText("");
        }
        
    } 

     
     
     
     
//     private void login(ActionEvent event) throws IOException, NoSuchAlgorithmException {
//        User user = new User();
//        UserService userService = new UserService();
//        user.setEmail(tt_email.getText());
//        user.setPassword(tt_password.getText());
////        User u = userService.rechercherParEmail(user);
////        MessageDigest msg = MessageDigest.getInstance("MD5");
////        byte[] hash = msg.digest(tt_password.getText().getBytes(StandardCharsets.UTF_8));
//        // convertir bytes en hexadécimal
////        StringBuilder s = new StringBuilder();
////        for (byte b : hash) {
////            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
////        }
//        if (tt_email.getText().equals(user.getEmail()) && s.toString().equals(u.getPassword())) {
//            UserService.login(u);
//
//            if (u.getEmail().toString().equals("gharbi.wided@esprit.tn")) {
////                FXMLLoader loader = new FXMLLoader();
////                loader.setLocation(getClass().getResource("GestionUser.fxml"));
////                Parent root = loader.load();
////                tt_email.getScene().setRoot(root);
////                Notifications notificationBuilder = Notifications.create()
////                        .title("Succées").text("Bienvenu").graphic(null).position(Pos.CENTER);
////                Platform.runLater(() -> notificationBuilder.showInformation());
//                   lblErrors.setText("Veuillez saisir l'email ou le mot de passe correctement");
//            } else {
////                FXMLLoader loader = new FXMLLoader();
////                loader.setLocation(getClass().getResource("avis.fxml"));
////                Parent root = loader.load();
////                tt_email.getScene().setRoot(root);
////                Notifications notificationBuilder = Notifications.create()
////                        .title("Succées").text("Bienvenu").graphic(null).position(Pos.CENTER);
////                Platform.runLater(() -> notificationBuilder.showInformation());
//            }
//        } else {
//            lblErrors.setText("Veuillez saisir l'email ou le mot de passe correctement");
//            
//        }
//    }
     
     
     
     
    @FXML
    private void buttonLogin(ActionEvent event) throws IOException, NoSuchAlgorithmException {
    try {
        //login here
        Window owner = loginButton.getScene().getWindow();
        
        System.out.println(tt_email.getText());
        System.out.println(tt_password.getText());

        if (tt_email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (tt_password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String emailId = tt_email.getText();
        
        String password = tt_password.getText();
        
        
        UserService retUser = new UserService();
        boolean flag = retUser.afficherUserLogin(emailId, password);
        User user = new User();
        UserService userService = new UserService();
        user.setEmail(tt_email.getText());
        user.setPassword(tt_password.getText());
        User u = userService.rechercherParEmail(user);
        MessageDigest msg = MessageDigest.getInstance("MD5");
        byte[] hash = msg.digest(tt_password.getText().getBytes(StandardCharsets.UTF_8));
        // convertir bytes en hexadécimal
        StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        
        if (tt_email.getText().equals(u.getEmail()) && s.toString().equals(u.getPassword())) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            
            infoBox("Login Successful!", null, "Failed");
            
            User userSession = retUser.afficherUserSession(emailId, password);
            SessionClient.id=userSession.getId();
            SessionClient.cin=userSession.getCin();
            SessionClient.username=userSession.getUsername();
            SessionClient.prenom=userSession.getPrenom();
            SessionClient.genre=userSession.getGenre();
            SessionClient.email=userSession.getEmail();
            SessionClient.password=userSession.getPassword();
            SessionClient.date_naissance=userSession.getDate_naissance();
            SessionClient.adresse=userSession.getAdresse();
            
            if(rememberMe.isSelected()){
                userSession.setRememberMe(1);
                retUser.modifToRemember(userSession, userSession.getId());
            }else{
                userSession.setRememberMe(0);
                retUser.modifToRemember(userSession, userSession.getId());
            }
            System.out.println(userSession); 
            Parent root  = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene   scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(LogiinController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

//    @FXML
//    private void ajouteUser(ActionEvent event) {
//    }
//    }
    
   

    //we gonna use string to check for status
//    private String logIn() {
//        String status = "Success";
//        String email = tt_email.getText();
//        String password = tt_password.getText();
//        if(email.isEmpty() || password.isEmpty()) {
//            setLblError(Color.TOMATO, "Empty credentials");
//            status = "Error";
//        } else {
//            //query
//            String sql = "SELECT * FROM user Where email = ? and password = ?";
//            try {
//                preparedStatement = con.prepareStatement(sql);
//                preparedStatement.setString(1, email);
//                preparedStatement.setString(2, password);
//                resultSet = preparedStatement.executeQuery();
//                if (!resultSet.next()) {
//                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
//                    status = "Error";
//                    return "echec";
//                } else {
//                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
//                    return "succes";
//                }
//            } catch (SQLException ex) {
//                System.err.println(ex.getMessage());
//                status = "Exception";
//            }
//        }
//        
//        return status;
//    }
//    
//    private void setLblError(Color color, String text) {
//        lblErrors.setTextFill(color);
//        lblErrors.setText(text);
//        System.out.println(text);
//    }
//    
    
    
    
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

    @FXML
    private void ajouteUser(ActionEvent event) throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("/gui/gestionUser.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }

    @FXML
    private void ForgetPassword(ActionEvent event) throws IOException {
         Parent root  = FXMLLoader.load(getClass().getResource("/gui/ForgotPass.fxml"));
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              Scene   scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();
    }
    
}
