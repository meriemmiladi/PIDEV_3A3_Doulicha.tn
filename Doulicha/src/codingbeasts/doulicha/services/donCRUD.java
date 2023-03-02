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
            String requete2 ="INSERT INTO don(ID_user,ID_projet,valeur_don,etat_paiement,date_don)"
                    + "VALUES (?,?,?,?,'"+date+"')";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, d.getID_user());
            pst.setInt(2,d.getID_projet());
            pst.setDouble(3, d.getValeur_don());
            pst.setInt(4, 0);
            
            
            
            
            
            
            pst.executeUpdate();
            System.out.println("votre don est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public List<don> afficherdon (){
        List<don> myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM don where etat_paiement=0";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            don d = new don();
            d.setID_don(rs.getInt(1));
            d.setID_user(rs.getInt(2));
            d.setID_projet(rs.getInt(3));
            d.setValeur_don((float) rs.getDouble("Valeur_don"));
            d.setDate_don(rs.getDate("Date_don"));
            d.setEtat_paiement(rs.getInt("etat_paiement"));
            myList.add(d);
        }
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
    }
    
     public List<don> afficherdon1 (){
        List<don> myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM don where etat_paiement=1";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            don d = new don();
            d.setID_don(rs.getInt(1));
            d.setID_user(rs.getInt(2));
            d.setID_projet(rs.getInt(3));
            d.setValeur_don((float) rs.getDouble("Valeur_don"));
            d.setDate_don(rs.getDate("Date_don"));
            d.setEtat_paiement(rs.getInt("etat_paiement"));
            myList.add(d);
        }
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
    }
    public don getDonById(int Id_don) throws SQLException {
        String req3 = "SELECT * FROM don WHERE ID_don = ?";
        Statement pc = cnx2.createStatement();
        ResultSet rs = pc.executeQuery(req3);
       
      
        if (rs.next()) {
            don donp = new don();
            donp.setID_don(rs.getInt(1));
            donp.setID_user(rs.getInt(2));
            donp.setID_projet(rs.getInt(3));
            donp.setValeur_don((float) rs.getDouble("Valeur_don"));
            donp.setDate_don(rs.getDate("Date_don"));
            donp.setEtat_paiement(rs.getInt("etat_paiement"));
            return donp;
        }
        return null;
    }
    
    /*
public void updatePaiement(int Id_don, int etat_Paiement) throws SQLException {
    String query = "UPDATE don SET etat_paiement = ? WHERE ID_don = ?";
    PreparedStatement preparedStatement = cnx2.prepareStatement(query);
    preparedStatement.setInt(1, etat_Paiement);
    preparedStatement.setInt(2, Id_don);
    preparedStatement.executeUpdate();
}

public void updateEtatPaiement(int Id_don, int etat_Paiement) throws SQLException {
    Connection cnx2 = null;
    PreparedStatement stmt = null;

    try {
        Statement pc = cnx2.createStatement();
        
        String query = "UPDATE don SET etat_paiement = ? WHERE ID_don = ?";
        stmt = cnx2.prepareStatement(query);
        stmt.setInt(1, etat_Paiement);
        stmt.setInt(2, Id_don);
        stmt.executeUpdate();
    } finally {
        if (stmt != null) {
            stmt.close();
        }
        if (cnx2 != null) {
            cnx2.close();
        }
    }
}*/
    
    
    
    private void While(boolean next) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public void deletedon(int Id_don) throws SQLException{
        try{
        String requete = "DELETE FROM don WHERE ID_don="+ Id_don;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted don"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        
        }
}

public void modifierdon(don d ,int id_don) throws SQLException{
        try{
        String requete = "UPDATE don SET valeur_don = '" + d.getValeur_don() + "'  WHERE ID_don=" + id_don;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted don"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }
public void modifieretatdon(don d ,int id_don) throws SQLException{
        try{
        String requete = "UPDATE don SET etat_paiement = 1 WHERE ID_don=" + id_don;
        
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted don"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }
    public List<don> afficherdon(int id_don) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public double calculerSommeDons() {
    donCRUD dis = new donCRUD();
    List<don> dons = dis.afficherdon();
    double somme = 0;
    for (don d : dons) {
        somme += d.getValeur_don();
        System.out.println(somme);
    }
    return somme;
}

    public void deletedon(String donationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEtat_paiement(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

