
package complexesportifJava.services;


import complexesportifJava.entities.Avis;
import complexesportifJava.entities.Reclamation;
import complexesportifJava.entities.User;
import ComplexesportifJava.tools.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService {
    Connection cnx;

    public ReclamationService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

public void ajouterReclamation(Reclamation r){
    String query="insert into reclamation(object,description) values (?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, r.getObject());
            ste.setString(2, r.getDescription());
            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println("");
        }
    
} 
public List<Reclamation> afficherReclamation(){
    List<Reclamation> reclamation = new ArrayList<>();
    String sql = "select * from reclamation";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setObject(rs.getString("object"));
                r.setDescription(rs.getString("description"));
                reclamation.add(r);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return reclamation;
}
     public boolean modifer(Reclamation a,int id) {
         boolean modifier= true;
        String sql = "update reclamation set object='"+a.getObject()+"', description='"+a.getDescription()+"'"
                + "where id ='"+id+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("reclamation modifiée!!");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }

    
    public boolean supprimer(int id) {
        boolean supp = true;
        try {
            String sql = "delete from reclamation where id ='"+id+"'";     
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("reclamation supprimée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }   
        return supp;
    }   
}
