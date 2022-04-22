
package enfantacademie.services;


import enfantacademie.entities.Enfant;

import enfantacademie.tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EnfantService implements enfantinterface {
     private static EnfantService instance;
    public static EnfantService getInstance(){
        	if(instance == null) {
			instance = new EnfantService();
		}
			
		return instance;	
    }
    
    Connection cnx;

    public EnfantService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

public void ajouterEnfant(Enfant a){
    String query="insert into enfant(name , age , poids , taille ,cin_parent ) values (?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
                ste.setString(1, a.getName());
                ste.setInt(2, a.getAge());
                ste.setInt(3, a.getPoids());
                ste.setInt(4, a.getTaille());
                ste.setInt(5, a.getCin_parent());
              


        
             
          ste.executeUpdate();
            System.out.println("Enfant Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
}
public List<Enfant> afficherEnfant(){
    List<Enfant> enfant = new ArrayList<>();
    String sql = "select * from enfant";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while(rs.next()){
                Enfant a = new Enfant();
              a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setAge(rs.getInt("age"));
                a.setPoids(rs.getInt("poids"));
                a.setTaille(rs.getInt("taille"));
                a.setCin_parent(rs.getInt("cin_parent"));
                a.setAcademie_id(rs.getInt("academie_id"));




                enfant.add(a);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return enfant;
}
    public boolean modifierEnfant(Enfant a) {
         boolean modifier= true;
        String sql = "update enfant set name='"+a.getName()+"', age='"+a.getAge()+"', poids='"+a.getPoids()+"', taille='"+a.getTaille()+"', cin_parent='"+a.getCin_parent()+"'"
                + "where id ='"+a.getId()+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("enfant modifiée!!");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }

   
    public boolean supprimerEnfant(int id) {
        boolean supp = true;
        try {
            String sql = "delete from enfant where id ='"+id+"'";    
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("enfant supprimée");
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }  
        return supp;
    } 
    
     public Enfant rechercherParId(Enfant t) {
        
        List<Enfant> enfant = new ArrayList<>();
        String sql ="SELECT name , age , poids , taille ,cin_parent , academie_id FROM enfant WHERE id ='"+t.getId()+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                t.setName(rs.getString(1));
                t.setAge(rs.getInt(2));
                 t.setPoids(rs.getInt(3));
                  t.setTaille(rs.getInt(4));
                   t.setCin_parent(rs.getInt(5));
                    t.setAcademie_id(rs.getInt(6));
                enfant.add(t);
                System.out.println(t.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    } 
     public List<Enfant> trierAge(){
        List<Enfant> Enfants = new ArrayList<>();
        String sql="select * from Enfant ORDER BY dateeven DESC";
        try { 
            PreparedStatement ps = cnx.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 Enfant e = new Enfant();
                e.setName(rs.getString(1));
                e.setAge(rs.getInt(2));
                 e.setPoids(rs.getInt(3));
                  e.setTaille(rs.getInt(4));
                   e.setCin_parent(rs.getInt(5));
                    e.setAcademie_id(rs.getInt(6));
               
               Enfants.add(e);
               System.out.println(e.toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Enfants;
    } 
    
     
     public List<Enfant> Tri() {
        Comparator<Enfant> comparator = Comparator.comparing(Enfant::getAge);
        List<Enfant> prd = afficherEnfant();
        return prd.stream().sorted(comparator).collect(Collectors.toList());
    }

}

