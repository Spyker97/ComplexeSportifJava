/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class terrainService {
    
     private static terrainService instance;
    public static terrainService getInstance(){
        	if(instance == null) {
			instance = new terrainService();
		}
			
		return instance;	
    }
    
    Connection cnx;

    public terrainService() {
        cnx=MyDB.getInstance().getConnexion();
    }



    public void ajouter(terrain a) throws SQLException {
        String req = "INSERT INTO `terrain` (type,chef,equipement) "
                + "VALUES ( ?, ?, ?) ";
        PreparedStatement ste = cnx.prepareStatement(req);
            
            ste.setString(1, a.getType());
            ste.setString(2, a.getChef());
            ste.setString(3, a.getEquipement());

        ste.executeUpdate();
    }
    
    /**
     *
     * @return
     */
    public List<terrain> afficherr(){
    List<terrain> user = new ArrayList<>();
    String sql = "select * from terrain";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                terrain u = new terrain();
                u.setId_terrain(rs.getInt("id_terrain"));
                
                u.setType(rs.getString("type"));
                u.setChef(rs.getString("chef"));
               
                u.setEquipement(rs.getString("equipement"));
             
                user.add(u);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return user;
}
   
    

/*@Override
public List<Avis> afficherAvis(){
    List<Avis> avis = new ArrayList<>();
    String sql = "select * from avis";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Avis a = new Avis();
                a.setId(rs.getInt("id"));
                a.setObject(rs.getString("object"));
                a.setDescription(rs.getString("description"));
                avis.add(a);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return avis;
}
@Override
    public boolean modifierAvis(Avis a) {
         boolean modifier= true;
        String sql = "update avis set object='"+a.getObject()+"', description='"+a.getDescription()+"'"
                + "where id ='"+a.getId()+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("avis modifiée!!");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }

    @Override
    public boolean supprimerAvis(int id) {
        boolean supp = true;
        try {
            String sql = "delete from avis where id ='"+id+"'";     
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("avis supprimée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }   
        return supp;
    }

  public Avis rechercherParId(Avis t) {
        
        List<Avis> avis = new ArrayList<>();
        String sql ="SELECT object,description FROM avis WHERE id ='"+t.getId()+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setObject(rs.getString(1));
                t.setDescription(rs.getString(2));
                avis.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
*/

   
}
