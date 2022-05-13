
package ComplexesportifJava.tools;

import complexesportifJava.entities.User;
import gui.Cours;

import gui.users;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MaConnexion {
    public final String  url ="jdbc:mysql://127.0.0.1:3306/ComplexeSportif";
    public final String  user ="root";
    public final String pwd ="";
    public static MaConnexion ct;
    
    private Connection cnx;

    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connection etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public static MaConnexion getInstance(){
        if(ct==null)
            ct = new MaConnexion();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
//    public static ObservableList<User> getDataCours(){
//        MaConnexion conn = MaConnexion.getInstance();
//        ObservableList<User> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select * from users");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()){   
//                list.add(new User(Integer.parseInt(rs.getString("cin")), rs.getString("nom"), rs.getString("prenom")));               
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }

    
    
    public static ObservableList<users> getDatausers(){
        Connection conn = MaConnexion.getInstance().getCnx();
        ObservableList<users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new users(Integer.parseInt(rs.getString("user_id")), rs.getString("username"), rs.getString("Number"), rs.getString("email"),rs.getString("type")));               
            }
        } catch (Exception e) {
        }
        return list;
    }


       public static ObservableList<Cours> getDataCours(){
        Connection conn = MaConnexion.getInstance().getCnx();
        ObservableList<Cours> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Cour");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Cours(Integer.parseInt(rs.getString("id")),rs.getString("Coach"), rs.getString("Num_salle"),rs.getString("type")));               
            }
        } catch (Exception e) {
        }
        return list;
    }

       


}
