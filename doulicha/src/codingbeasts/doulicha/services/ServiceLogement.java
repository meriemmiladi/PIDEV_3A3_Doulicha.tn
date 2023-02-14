/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Logement;
import codingbeasts.doulicha.utils.MyConnection; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author marie
 */
public class ServiceLogement {
    
    Connection cnx;
    public ServiceLogement(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterLogement(){
    String req = "INSERT INTO logement ( nom_logement,  description_logement,  adresse_logement,  prixNuitee_logement,  capacite_logement, type_logement,  etat_logement,  image_logement) "
            + "VALUES ('n','d','a',125.5,5,'t',0,'i')";
        try {
            //Statement st = new MyConnection().getCnx().createStatement();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            st.close();
            cnx.close();
            System.out.println("Logement ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   
    }

    
    public void ajouterLogement2(Logement l){
         try {
        String req = "INSERT INTO logement ( nom_logement,  description_logement,  adresse_logement,  prixNuitee_logement,  capacite_logement, type_logement,  etat_logement,  image_logement) "
                + "VALUES (?,?,?,?,?,?,?,?)";
             //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(req);
             PreparedStatement pst = cnx.prepareStatement(req);
             pst.setString(1, l.getNom_logement());
             pst.setString(2, l.getDescription_logement());
             pst.setString(3, l.getAdresse_logement());
             pst.setDouble(4, l.getPrixNuitee_logement());
             pst.setInt(5, l.getCapacite_logement());
             pst.setString(6, l.getType_logement());
             pst.setInt(7, l.getEtat_logement());
             pst.setString(8, l.getImage_logement());
             pst.executeUpdate();
             pst.close();
             cnx.close();
             System.out.println("Logement ajouté avec succès");
        } catch (SQLException ex){
             System.err.println(ex.getMessage());
        }
        }
    
    public List<Logement> afficherLogement(){
        List <Logement> ListLogement = new ArrayList<>();
        try {
         String req = "SELECT * FROM logement";
         //Statement st = new MyConnection().getCnx().createStatement();
         Statement st = cnx.createStatement();
         ResultSet rs= st.executeQuery(req);
         while(rs.next()){
             Logement L = new Logement();
             L.setID_logement(rs.getInt(1));
             L.setNom_logement(rs.getString(2));
             L.setDescription_logement(rs.getString(3));
             L.setAdresse_logement(rs.getString(4));
             L.setPrixNuitee_logement(rs.getDouble(5));
             L.setCapacite_logement(rs.getInt(6));
             L.setType_logement(rs.getString(7));
             L.setEtat_logement(rs.getInt(8));
             L.setImage_logement(rs.getString(9));
             
            /* L.setID_logement(rs.getInt("ID_logement"));
             L.setNom_logement(rs.getString("nom_logement"));
             L.setDescription_logement(rs.getString("description_logement"));
             L.setAdresse_logement(rs.getString("adresse_logement"));
             L.setPrixNuitee_logement(rs.getDouble("prixNuitee_logement"));
             L.setCapacite_logement(rs.getInt("capacite_logement"));
             L.setType_logement(rs.getString("type_logement"));
             L.setEtat_logement(rs.getInt("etat_logement"));
             L.setImage_logement(rs.getString("image_logement")); */
             
             ListLogement.add(L);
             st.close();
             cnx.close();
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListLogement;
    }
    
 
  public void modifierLogement(Logement l) {
    
      try {
      String req= "UPDATE logement SET nom_logement=?, description_logement=?, adresse_logement=?, prixNuitee_logement=?, capacite_logement=?, type_logement=?, etat_logement=?, image_logement=? WHERE ID_logement=?";
     
     PreparedStatement pst = cnx.prepareStatement(req);
             pst.setString(1, l.getNom_logement());
             pst.setString(2, l.getDescription_logement());
             pst.setString(3, l.getAdresse_logement());
             pst.setDouble(4, l.getPrixNuitee_logement());
             pst.setInt(5, l.getCapacite_logement());
             pst.setString(6, l.getType_logement());
             pst.setInt(7, l.getEtat_logement());
             pst.setString(8, l.getImage_logement());
             pst.setInt(9, l.getID_logement());
             pst.executeUpdate();
             pst.close();
             cnx.close();
             System.out.println("Logement modifié avec succès");
        } catch (SQLException ex){
             System.err.println(ex.getMessage());
        }
      }
  
  public void supprimerLogement(int id) {
    try {
        String req = "DELETE FROM logement WHERE ID_logement = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id);
        pst.executeUpdate();
        pst.close();
        cnx.close();
        System.out.println("Logement supprimé avec succès");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
}
