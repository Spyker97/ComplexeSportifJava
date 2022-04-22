
package complexesportifJava.services;


import complexesportifJava.entities.Avis;
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

public class AvisService {
    Connection cnx;

    public AvisService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

public void ajouterAvis(Avis a){
    String query="insert into avis(object,description) values (?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, a.getObject());
            ste.setString(2, a.getDescription());
            ste.executeUpdate();
            System.out.println("Avis Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
} 
public List<Avis> afficherAvis(){
    List<Avis> avis = new ArrayList<>();
    String sql = "select * from avis";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                Avis a = new Avis();
                a.setObject(rs.getString("object"));
                a.setDescription(rs.getString("description"));
                avis.add(a);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return avis;
}
    public boolean modifer(Avis a,int id) {
         boolean modifier= true;
        String sql = "update avis set object='"+a.getObject()+"', description='"+a.getDescription()+"'"
                + "where id ='"+id+"'"; 
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

    
    public boolean supprimer(int id) {
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
}
