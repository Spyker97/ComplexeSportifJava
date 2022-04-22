
package enfantacademie.services;


import enfantacademie.entities.Academie;

import enfantacademie.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcademieService implements academieinterface {
   private static AcademieService instance;
    public static AcademieService getInstance(){
        	if(instance == null) {
			instance = new AcademieService();
		}
			
		return instance;	
    }
    
    Connection cnx;

    public AcademieService() {
        cnx=MaConnexion.getInstance().getCnx();
    }


public void ajouterAcademie(Academie a){
    String query="insert into academie(name , nbr_seances) values (?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
                ste.setString(1, a.getName());
                ste.setInt(2, a.getNbr_seance());
        
             
          ste.executeUpdate();
            System.out.println("Academie Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
}
public List<Academie> afficherAcademie(){
    List<Academie> academie = new ArrayList<>();
    String sql = "select * from academie";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while(rs.next()){
                Academie a = new Academie();
              a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setNbr_seance(rs.getInt("nbr_seances"));
                academie.add(a);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return academie;
}
    public boolean modifierAcademie(Academie a) {
         boolean modifier= true;
        String sql = "update academie set name='"+a.getName()+"', nbr_seances='"+a.getNbr_seance()+""
                + "where id ='"+a.getId()+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Academie modifiée!!");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }

   
    public boolean supprimerAcademie(int id) {
        boolean supp = true;
        try {
            String sql = "delete from academie where id ='"+id+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Academie supprimée");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    }

     public Academie rechercherParId(Academie t) {
        
        List<Academie> enfant = new ArrayList<>();
        String sql ="SELECT name , nbr_seances FROM academie WHERE id ='"+t.getId()+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setName(rs.getString(1));
                t.setNbr_seance(rs.getInt(2));
                
                enfant.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }
}

