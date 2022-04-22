/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.reservation;
import entities.terrain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author LENOVO
 */
public class reservationService {
    
     Connection connexion;
    Statement stm;

    public reservationService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    
    public void ajouter(reservation t) throws SQLException {
        String req = "INSERT INTO `reservation` (`id`,`id_terrain`,`cin`,`discipline`) "
                + "VALUES ( ?, ?, ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.setInt(2, t.getId_terrain());
        ps.setInt(3, t.getCin());
        ps.setString(4, t.getDiscipline());

        ps.executeUpdate();
    }

    
    public void supprimer(reservation t) throws SQLException {
        String requete = "DELETE FROM reservation WHERE id =?";
        PreparedStatement pst = connexion.prepareStatement(requete);
        pst.setInt(1, t.getId());

        pst.executeUpdate();
        System.out.println("reservation supprimé !");
    }

    public void modifier(reservation t) throws SQLException {
        String requete = "UPDATE reservation SET id_terrain=?,cin=? , discipline=? WHERE id=?";
        PreparedStatement pst = connexion.prepareStatement(requete);

          pst.setInt(4, t.getId());
        pst.setInt(1, t.getId_terrain());
        pst.setInt(2, t.getCin());
        pst.setString(3, t.getDiscipline());
      

        pst.executeUpdate();
        System.out.println("reservation modifiée !");
    }

    
    public List<reservation> afficher() throws SQLException {
        List<reservation> reservation = new ArrayList<>();
        String req = "SELECT * FROM `reservation` WHERE id=1";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

            reservation a = new reservation(
                      rst.getInt("id"),
                    rst.getInt("id_terrain"),
                    rst.getInt("cin"),
                
                    rst.getString("discipline")
            );

            reservation.add(a);
        }
        return reservation;

    }

   
    public List<terrain> trienumero() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
