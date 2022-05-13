/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.Commande;
import java.sql.ResultSet;

/**
 *
 * @author FK Info
 */
public class ServiceCommande {
    Connection c = MaConnexion.getInstance().getCnx();
    
     public void ajouter_com(Commande p)
     {
         PreparedStatement req;
        try {
            req = c.prepareStatement("insert into commande(adresse,payement,produits)values(?,?,?)");
        
         req.setString(1, p.getAdresse());
         req.setString(2, p.getPayement());
         req.setString(3, p.getProduits());
        
     req.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
     
}
      public ResultSet getall_com() {
         
        try {
            PreparedStatement req = c.prepareStatement("SELECT * FROM commande");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      return null;
    }
    public boolean supprimer_com(Commande p) {
         boolean ok=false;
        try {
            
            PreparedStatement req = c.prepareStatement("delete from commande where adresse = ? ");
            req.setString(1, p.getAdresse());
            req.executeUpdate();
ok=true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ok;
    }
    public void modifier_com(Commande p ,String nom) {
        try {
            PreparedStatement req = c.prepareStatement("update commande set adresse=?,payment=? ,produits=? where adresse=?");
            
            req.setString(1, p.getAdresse());
            req.setString(2, p.getPayement());
    
            req.setString(3, p.getProduits());
            req.setString(4,nom);
          
            req.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }/*
    public projection recherche_projection(String nom) {
        projection p = new projection();
        try {
            PreparedStatement req = c.prepareStatement("select * from projection where nom=?  ");
            req.setString(1, nom);
            ResultSet rs = req.executeQuery();
            rs.next();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setGenre(rs.getString("genre"));
            p.setAge_recommande(rs.getInt("age_recommande"));
            p.setDuree(rs.getString("duree"));
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }
    public ResultSet tri_projection() {
         
        try {
            PreparedStatement req = c.prepareStatement("SELECT * FROM projection ORDER BY duree");
            ResultSet rs = req.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
}*/

}