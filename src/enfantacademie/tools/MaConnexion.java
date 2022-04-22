package enfantacademie.tools;

import java.sql.*;

public class MaConnexion {
    public final String  url ="jdbc:mysql://127.0.0.1:3306/complexesportif";
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
   
   
}
