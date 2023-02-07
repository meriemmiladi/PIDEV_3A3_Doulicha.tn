/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */
public class JDBC {
    
static String url = "jdbc:mysql://localhost:3306/esprit";
static String user = "root";
static String pwd = "";
static Connection conn;
static Statement ste;

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
       try {
        
        Personne p1 = new Personne("mariouma","mimi");
        Connection conn=DriverManager.getConnection(url,user,pwd); 
        System.out.println("Connexion établie");
       ste= conn.createStatement();
        
        String req = "INSERT INTO `personne` (`nom`, `prenom`) values('"+p1.nom+"','"+p1.prenom+"')";
        
        /*String req2 = "SELECT * FROM `personne` ";
        ResultSet result = ste.executeQuery(req2);
        List <Personne> pers =new ArrayList <Personne>();
        while(result.next())
        {
            Personne resultPersonne=new Personne(result.getInt("id"), result.getString(2), result.getString("prenom"));
        }*/
        
        ste.executeUpdate(req);
        //ste.executeQuery(req2);
        ste.close();
        conn.close();
        
        
        
       }catch
               (SQLException e){ System.out.println("Problème de connexion ");}
      
               
    }
    
}
