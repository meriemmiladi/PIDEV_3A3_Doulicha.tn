/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctutoclasse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
        

/**
 *
 * @author marie
 */
public class JDBCtutoclasse {
    
static String url = "jdbc:mysql://localhost:3306/esprit";
static String user = "root";
static String pwd = "";
static Connection conn;
static Statement ste;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        personne P1 =new personne("Mariem","Miladi");
      
        conn=DriverManager.getConnection(url,user,pwd); 
          System.out.println("Connexion etablie");
        ste=conn.createStatement();
        
        /*String req2 = "SELECT * from `personne`";
        ResultSet result = ste.executeQuery(req2);
        List <personne> pers = new ArrayList <personne>() ;
        while(result.next())
        {
            personne resultPersonne= new personne(result.getInt("id"), result.getString(2), result.getString("prenom"));
        }*/
        String req1 ="INSERT INTO `personne`(`nom`, `prenom`) VALUES ('"+P1.nom+"','"+P1.prenom+"')";
       
        ste.executeUpdate(req1);
        ste.close();
        conn.close();
        
    } catch (SQLException e){
            System.out.println("Probleme de connexion!! ");
    e.printStackTrace();}
        
        
    }
    
}
