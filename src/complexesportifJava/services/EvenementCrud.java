/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.services;

import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.Evenement;
import complexesportifJava.entities.TypeEvent;
import static com.mysql.jdbc.Messages.getString;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static com.mysql.jdbc.Messages.getString;
import static com.mysql.jdbc.Messages.getString;
import static com.mysql.jdbc.Messages.getString;
import static com.mysql.jdbc.Messages.getString;
import static com.mysql.jdbc.Messages.getString;
import java.sql.Date;

/**
 *
 * @author Nidhal
 */
public class EvenementCrud {

    Connection cnxx;

    public EvenementCrud() {
        cnxx = MaConnexion.getInstance().getCnx();

    }
//*ajouter evenement

    public void ajouterEvenement(Evenement e) {

        String req = "INSERT INTO Evenement (type_id_id,  titre,  date_start,  date_end,  nombre_par,  description) VALUES (?,?,?,?,?,?)";
        
        PreparedStatement pst;
        try {
            pst = cnxx.prepareStatement(req);

            pst.setInt(1, e.getType_id_id());
            pst.setString(2, e.getTitre());
            pst.setDate(3, e.getDate_start());
            pst.setDate(4, e.getDate_end());
            pst.setInt(5, e.getNombre_par());
            pst.setString(6, e.getDescription());
            //pst.setString(7, e.getImage());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
/*
    public void modifierEvenemenet(Evenement e) {
        String req = "UPDATE Evenement SET titre=?,date_start=?,date_end=?, nombre_par=?,description=? WHERE id=?";
        PreparedStatement pst;
        try {
            pst = cnxx.prepareStatement(req);
          
            pst.setString(1, e.getTitre());
            pst.setDate(2, e.getDate_start());
            pst.setDate(3, e.getDate_end());
            pst.setInt(4, e.getNombre_par());
            pst.setString(5, e.getDescription());
             pst.setInt(6, e.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }*/
        public void modifierEvenemenet(Evenement e) {
        String req = "UPDATE Evenement SET titre=?,nombre_par=?,description=? WHERE id=?";
        PreparedStatement pst;
        try {
            pst = cnxx.prepareStatement(req);
          
            pst.setString(1, e.getTitre());
         
            pst.setInt(2, e.getNombre_par());
            pst.setString(3, e.getDescription());
             pst.setInt(4, e.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
/*
    public String countEvenement() {

        String req = "SELECT COUNT(*) FROM Evenement";
        PreparedStatement pst;
        try {
            pst = cnxx.prepareStatement(req);
            pst.executeQuery(req);
            ResultSet rs = pst.getResultSet();
            rs.next();
            return ("  " + rs.getInt("count(*)") + " evenement");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }
*/
    public void supprimerEvenement(Evenement e) {

        String reqdelete = "DELETE FROM Evenement WHERE id=?";
        PreparedStatement pst;
        try {
            pst = cnxx.prepareStatement(reqdelete);
            pst.setInt(1, e.getId());
            pst.executeUpdate();
            System.out.println("evenement  supprimé");

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());

        }

    }
 // afficher les evenements par type
    
       public ArrayList<Evenement> consulterListeEndroits(TypeEvent t) {
        ArrayList<Evenement> listevent = new ArrayList<Evenement>();

        String req =   "SELECT * FROM Evenement INNER JOIN type_event  ON type_id_id='" + t.getId() + "'   ";              
        Statement st;
        try {

            st = cnxx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
             
                Evenement rr = new Evenement(rs.getInt("id"),rs.getInt("type_id_id"),rs.getString("titre"), rs.getDate("date_start"),rs.getDate("date_end"),rs.getInt("nombre_par"),rs.getString("description"));
                listevent.add(rr);
            }
            return listevent;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listevent;
    }
     
     
}
