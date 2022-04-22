/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.services;

import complexesportifJava.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ahmed
 */
public class LoginService {
         Connection con= null;
      PreparedStatement preparedStatement =null;
      ResultSet resultSet =null;  
    public boolean login(User u){
        boolean login = false ;
        String sql = "SELECT email, password from user WHERE email='" +u.getEmail()+ "' and password='" + u.getPassword()+ "'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();  
            if(rs.next()){
                if((rs.getString("email").equals(u.getEmail()))
                        && (rs.getString("password").equals(u.getPassword()))){
                    System.out.println("Vous etes connecté(e)" );
                    
//                    PasswordUtils.setCurrentUser(u);
                    
                    login = true ;
                    
                }else
                    System.err.println("Veuillez saisir correctement l'adresse ou le mot de passe" );
              }
          
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return login;
    }
}
