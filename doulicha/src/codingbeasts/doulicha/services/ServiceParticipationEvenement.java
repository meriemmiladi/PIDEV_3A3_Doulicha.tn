/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.entities.participation_evenement;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class ServiceParticipationEvenement {
    
    Connection cnx;
    public ServiceParticipationEvenement(){
        cnx = MyConnection.getInstance().getCnx();  
}
    
        public ObservableList<participation_evenement> afficherParts()
 { 
       ObservableList<participation_evenement> participation_evenement = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `participation_evenement`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                participation_evenement part = new participation_evenement();
                
               part.setID_participation(rs.getInt(1));
               part.setID_user(rs.getInt(2));
               part.setID_event(rs.getInt(3));
                part.setNombre_participation(rs.getInt(4));
                part.setNum_tel(rs.getString(5));
                System.out.println(part);
           participation_evenement.add(part);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
        return participation_evenement;
    }
    
   /* public void ajouterParticipationEvenement(){
        
       
      String requete =  "INSERT INTO `participation_evenement`(`ID_user`, `ID_event`, `nombre_participation`)"
       + "VALUES (1,9,'[value-4]','[value-5]')";
     
        try {
            //Statement st = new MyConnection().getCnx().createStatement();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Evènement ajouté avec succès ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    } */
    
    public void ajouterParticipationEvenement(participation_evenement participation){
        
        String requete2 = " INSERT INTO  `participation_evenement`(`ID_user`, `ID_event`, `nombre_participation`, `num_tel`)"
       + "VALUES (?,?,?,?)";
        
        try {
            //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            PreparedStatement pst = cnx.prepareStatement(requete2);
            
           
            pst.setInt(1, participation.getID_user());
            pst.setInt(2, participation.getID_event());
            pst.setInt(3, participation.getNombre_participation());
            pst.setString(4, participation.getNum_tel());
            
            pst.executeUpdate();
            System.out.println("Participation à l'évènement ajouté ");
           
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    
    
    }  
    
    
    public List<participation_evenement> afficherParticipations(int iduser){
        List<participation_evenement> ListParticipation = new ArrayList<>();
        
        try {
         String requete3 = "SELECT * FROM participation_evenement where ID_user LIKE '" + iduser + "'";
         
         Statement st = cnx.createStatement();
         ResultSet rs= st.executeQuery(requete3);
         while(rs.next()){
             participation_evenement participation = new participation_evenement();
             
             participation.setID_participation(rs.getInt(1));
             participation.setID_user(rs.getInt(2));
             participation.setID_event(rs.getInt(3));
             participation.setNombre_participation(rs.getInt(4));
            participation.setNum_tel(rs.getString(5));
             
             
             ListParticipation.add(participation);
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListParticipation;
    }
    
    
    public boolean supprimerParticipation(int ID_participation) {
        boolean PartSuppression = true;
        
        try {
           String requete4 = "DELETE FROM participation_evenement WHERE ID_participation = ?";
             PreparedStatement pst = cnx.prepareStatement(requete4);
             
            pst.setInt(1, ID_participation);
            int nbSuppression = pst.executeUpdate();
            
            if (nbSuppression == 0) {
                System.out.println("Pas de suppression de participation effectuée");
            } else {
               System.out.println("\"La participation avec l'ID " + ID_participation + " a été supprimée.\"");

            }

        } catch (SQLException ex) {
            PartSuppression = false;
             System.err.println(ex.getMessage());
            
        }
        return PartSuppression;
    }
    
    
      public void modifierParticipationEvenement(participation_evenement participation) {
try {
            Statement st = cnx.createStatement();
            String requete4 = "UPDATE  participation_evenement SET ID_participation  = '" + participation.getID_participation() + "', ID_user = '" + participation.getID_user() + "', ID_event = '" + participation.getID_event() + "', nombre_participation = '" + participation.getNombre_participation() + "', num_tel = '" + participation.getNum_tel() + "' WHERE ID_participation = '" + participation.getID_participation()+ "'";
            st.executeUpdate(requete4);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
            System.out.println(ex);
        }
    }
  
    
    public int getId2(String id) {
try {
            Statement st = cnx.createStatement();
            
            String req = "select ID_participation from `participation_evenement` WHERE  ID_participation LIKE '" + id + "'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return 0;
    }
    
   public int getIdDerniereParticipation() throws SQLException {
    int idParticipation = 0;
    String req = "SELECT MAX(ID_participation) FROM participation_evenement";
     Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    if (rs.next()) {
        idParticipation = rs.getInt(1);
    }
    return idParticipation;
}
   
   public int getNombreParticipations(int idEvent) {
    int totalParticipations = 0;
    try {
        Statement st = cnx.createStatement();
        String req = "SELECT SUM(nombre_participation) AS total_participations FROM participation_evenement WHERE ID_event=" + idEvent;
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            totalParticipations = rs.getInt("total_participations");
        }
    } catch (SQLException ex) {
        System.out.println("erreur");
        System.out.println(ex);
    }
    return totalParticipations;
}
   
  
 public int getUserByEvent(int idEvent, int idUser) {
    int total = 0;
    try {
        Statement st = cnx.createStatement();
        String req = " SELECT COUNT(*) AS total FROM participation_evenement WHERE ID_user = " + idUser  + " AND ID_event = "  + idEvent ;
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            total = rs.getInt("total");
        }
    } catch (SQLException ex) {
        System.out.println("erreur");
        System.out.println(ex);
    }
    return total;
}
 
 
 public List<String> getNumTelsByEvent(int idEvent) throws SQLException {
    List<String> numTels = new ArrayList<>();
    String req = "SELECT num_tel FROM participation_evenement WHERE ID_event = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, idEvent);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        String numTel = rs.getString("num_tel");
        numTels.add(numTel);
    }
    return numTels;
}
 
    
    
}
