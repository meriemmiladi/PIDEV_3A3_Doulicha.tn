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
               part.setDate_participation(rs.getDate(4));
                part.setNombre_participation(rs.getInt(5));
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
        
       
      String requete =  "INSERT INTO `participation_evenement`(`ID_user`, `ID_event`, `date_participation`, `nombre_participation`)"
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
        
        String requete2 = " INSERT INTO  `participation_evenement`(`ID_user`, `ID_event`, `date_participation`, `nombre_participation`)"
       + "VALUES (?,?,?,?)";
        
        try {
            //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            PreparedStatement pst = cnx.prepareStatement(requete2);
            
           
            pst.setInt(1, participation.getID_user());
            pst.setInt(2, participation.getID_event());
            pst.setDate(3, (java.sql.Date) participation.getDate_participation());
            pst.setInt(4, participation.getNombre_participation());
            
            pst.executeUpdate();
            System.out.println("Participation à l'évènement ajouté ");
           
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    
    
    }  
    
    
    public List<participation_evenement> afficherParticipations(){
        List<participation_evenement> ListParticipation = new ArrayList<>();
        
        try {
         String requete3 = "SELECT * FROM participation_evenement";
         
         Statement st = cnx.createStatement();
         ResultSet rs= st.executeQuery(requete3);
         while(rs.next()){
             participation_evenement participation = new participation_evenement();
             
             participation.setID_participation(rs.getInt(1));
             participation.setID_user(rs.getInt(2));
             participation.setID_event(rs.getInt(3));
             participation.setDate_participation(rs.getDate(4));
             participation.setNombre_participation(rs.getInt(5));
            
             
             
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
            //nb: on ne peut pas modifier la date
            String requete4 = "UPDATE  participation_evenement SET ID_participation  = '" + participation.getID_participation() + "', ID_user = '" + participation.getID_user() + "', ID_event = '" + participation.getID_event() + "' , date_participation = '" + participation.getDate_participation() + "', nombre_participation = '" + participation.getNombre_participation() + "' WHERE ID_participation = '" + participation.getID_participation()+ "'";
            st.executeUpdate(requete4);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
            System.out.println(ex);
        }
    }
    /*public boolean modifierParticipationEvenement (int ID_participation,int ID_user, int ID_event, Date date_participation, int nombre_participation) {
        
         boolean partModification = true;
        String requete = null;
        
        try {
     String requete5= "UPDATE `participation_evenement` SET `ID_user`=?,`ID_event`=?,`date_participation`=?,`nombre_participation`=? WHERE ID_participation=?";
     PreparedStatement pst = cnx.prepareStatement(requete5);
     
         
            pst.setDate(3, (java.sql.Date) date_participation);
            pst.setInt(1, ID_user);
            pst.setInt(2, ID_event);
            pst.setDate(3, (java.sql.Date) date_participation);
            pst.setInt(4, nombre_participation);
            pst.setInt(5,ID_participation);

            pst.executeUpdate();
            partModification = true;
             System.out.println("Votre participation est modifiée");
             
        } catch (SQLException ex) {
            partModification = false;
             System.err.println(ex.getMessage());
        }
        return partModification;
    }*/
    
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
    
    
    
    
}
