/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piAHMED;

import entities.reservation;
import entities.terrain;
import java.sql.SQLException;
import service.reservationService;
import service.terrainService;

/**
 *
 * @author LENOVO
 */
public class main {
    
    
       
    public static void main(String[] args) throws SQLException {
     reservation avv=new reservation(16,66,666,"eeee");
  
        reservationService av = new reservationService();
        
        
   
                        try {
           av.ajouter(avv);
            System.out.println("ajout avec succes");
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
          
      /*try {
            av.supprimer(new reservation(16));
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
      
         try {
            av.modifier(new reservation(1, 222222222, 3333,"dhiaa"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       /* 
        try {
            System.out.println(av.afficher());
             System.out.println("afficher avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        
   
        
      

    }}