/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import codingbeasts.doulicha.entities.don;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class donCRUD {
    Connection cnx2;
    public donCRUD(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    public void ajouterdon () {
        try {
            String requete ="INSERT INTO don ( valeur_don,  date_don)"
                    + "VALUES (15,'02/58/2000')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("don ajoutée avec succés");
        } catch (SQLException ex) {
              System.err.println(ex.getMessage()); 
        }
        
    }
    
    public void ajouterdon2(don d){
           java.util.Date act = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(act);
        String dc = date;
        try {
            String requete2 ="INSERT INTO don(ID_user,ID_projet,valeur_don,date_don)"
                    + "VALUES (?,?,?,'"+date+"')";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, d.getID_user());
            pst.setInt(2,d.getID_projet());
            pst.setDouble(3, d.getValeur_don());
            
            
            
            
            
            
            pst.executeUpdate();
            System.out.println("votre don est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public List<don> afficherdon (){
        List<don> myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM don";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            don d = new don();
            d.setID_don(rs.getInt(1));
            d.setID_user(rs.getInt(2));
            d.setID_projet(rs.getInt(3));
            d.setValeur_don((float) rs.getDouble("Valeur_don"));
            d.setDate_don(rs.getDate("Date_don"));
          
            myList.add(d);
        }
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
    }

    private void While(boolean next) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public void deletedon() throws SQLException{
        try{
        String requete = "DELETE FROM don WHERE ID_don=15";
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted don"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        
        }
}

public void modifierdon() throws SQLException{
        try{
        String requete = "UPDATE don SET valeur_don = 1 WHERE id_don=16";
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted don"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }
}

