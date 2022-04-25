
package complexesportifJava.services;


import complexesportifJava.entities.User;
import ComplexesportifJava.tools.MaConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.event.ActionEvent;

public class UserService {
    Connection cnx;

    public UserService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

public void ajouterUser(User u){
    String query="insert into user(cin,username,prenom,genre,email,password,adresse,date_naissance,roles) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, u.getCin());
            ste.setString(2, u.getUsername());
            
            ste.setString(3, u.getPrenom());
            ste.setString(4, u.getGenre());
            
            ste.setString(5, u.getEmail());
            //ste.setDate(2, (Date) u.getBirth_date());
            ste.setString(6, u.getPassword());
            ste.setString(7, u.getAdresse());
            ste.setString(8, u.getDate_naissance());
            String ROLE_USER = "ROLE_USER";
            ste.setString(9,"["+ROLE_USER+"]");
            ste.executeUpdate();
            System.out.println("Utilisateur Ajoutée!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
} 
public List<User> afficherUser(){
    List<User> user = new ArrayList<>();
    String sql = "select * from user";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setCin(rs.getInt("cin"));
                u.setUsername(rs.getString("username"));
                u.setPrenom(rs.getString("prenom"));
                u.setAdresse(rs.getString("adresse"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setGenre(rs.getString("genre"));
                u.setDate_naissance(rs.getString("date_naissance"));
                user.add(u);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return user;
}


public Boolean afficherUserLogin(String emailId, String password){
    User user = new User();
    String sql = "SELECT * FROM user WHERE email = ? and password = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

           
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return false;
}

public User afficherUserSession(String emailId, String password){
    User u = new User();
    String sql = "SELECT * FROM user WHERE email = ? and password = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

           
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                
                u.setId(rs.getInt("id"));
                u.setCin(rs.getInt("cin"));
                u.setUsername(rs.getString("username"));
                u.setPrenom(rs.getString("prenom"));
                u.setAdresse(rs.getString("adresse"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setGenre(rs.getString("genre"));
                u.setGenre(rs.getString("date_naissance"));
                return u;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return u;
}
public User userRemember(){
    User u = new User();
    String sql = "SELECT * FROM user WHERE RememberMe = 1";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
   
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRememberMe(rs.getInt("RememberMe"));
                
                return u;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return u;
}
   

public boolean modifer(User u,int id) {
         boolean modifier= true;
        String sql = "update user set cin='"+u.getCin()+"', username='"+u.getUsername()+"',prenom='"+u.getPrenom()+"',"
                + " adresse='"+u.getAdresse()+"', email='"+u.getEmail()+"', password='"+u.getPassword()+"'"
                + "where id ='"+id+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Utilisateur modifiée!!");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }
public boolean modifToRemember(User u,int id) {
         boolean modifier= true;
        String sql = "update user set RememberMe = '"+ u.getRememberMe() +"'"  +" where id ='"+id+"'"; 
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("Utilisateur modifiée!!");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            modifier = false;
        }
        return modifier;
    }

    
    public boolean supprimer(int id) {
        boolean supp = true;
        try {
            String sql = "delete from user where cin ='"+id+"'";     
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
            System.out.println("utilisateur supprimée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            supp = false;
        }   
        return supp;
    }
    
}
