/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.entities;

/**
 *
 * @author Ahmed
 */
public class SessionClient {
    public static int id,cin;
    public static String username, prenom, genre,email,password,adresse, date_naissance;
    
    
    public static void cleanUserSession() {
        id= 0;
        cin= 0;        
        username = "";// or null
         prenom = "";
         genre = "";
         email = "";
         password = "";
         adresse = "";
         date_naissance = "";
         
    }
    
}
