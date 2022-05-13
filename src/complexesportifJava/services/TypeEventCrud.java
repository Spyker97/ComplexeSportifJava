/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexesportifJava.services;



import ComplexesportifJava.tools.MaConnexion;
import complexesportifJava.entities.Evenement;
import complexesportifJava.entities.TypeEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nidhal
 */
public class TypeEventCrud {

    Connection cnxx;

    public TypeEventCrud() {
        cnxx = MaConnexion.getInstance().getCnx();

    }
 

    public TypeEventCrud(String sDate7, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
        
        
//*ajouter type

    public void ajouterTypeevent(TypeEvent t) {

        String req = "INSERT INTO Type_event (type,description) VALUES (?,?)";
        PreparedStatement pst;
        try {
            pst = cnxx.prepareStatement(req);

          
            pst.setString(1, t.getType());
            
            pst.setString(2, t.getDescription());
            //pst.setString(7, e.getImage());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierTypeevent(TypeEvent e) {
        String req = "UPDATE type_event SET type=?,description=? WHERE id=?";
        PreparedStatement pst;
        try {
            pst = cnxx.prepareStatement(req);
          
            pst.setString(1, e.getType());
            
            pst.setString(2, e.getDescription());
            pst.setInt(3, e.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
 
    public void supprimerTypeevent(TypeEvent e) {

        String reqdelete = "DELETE FROM type_event WHERE id=?";
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
    
       public ArrayList<TypeEvent> consulterListeTypeevent() {
        ArrayList<TypeEvent> listtype = new ArrayList<TypeEvent>();

        String req =   "SELECT * FROM type_event  ";              
        Statement st;
        try {

            st = cnxx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
             
                TypeEvent rr = new TypeEvent(rs.getInt("id"),rs.getString("type"),rs.getString("description"));
                listtype.add(rr);
            }
            return listtype;

        } catch (SQLException ex) {
            Logger.getLogger(EvenementCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listtype;
    }
        
  }
       
       
       
       
       
       
       
       
       
       
       