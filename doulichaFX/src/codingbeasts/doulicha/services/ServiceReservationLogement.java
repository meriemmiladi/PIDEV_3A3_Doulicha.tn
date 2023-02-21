/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Logement;
import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marie
 */
public class ServiceReservationLogement {
    
     Connection cnx;
    public ServiceReservationLogement(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterReservationLogement(){
    String req = "INSERT INTO reservation_logement ( ID_user, ID_logement, dateArrivee_reservation, dateDepart_reservation, nbPersonnes_reservation, montantTotal_reservation)"
            + "VALUES (2,3,'2022-03-22','2022-03-30',12,500.0)";
        try {
            //Statement st = new MyConnection().getCnx().createStatement();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            
            System.out.println("Réservation ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   
    }
    
    public void ajouterReservationLogement2(Reservation_logement rl){
         try {
        String req = "INSERT INTO reservation_logement ( ID_user, ID_logement, dateArrivee_reservation, dateDepart_reservation, nbPersonnes_reservation, montantTotal_reservation) "
                + "VALUES (?,?,?,?,?,?)";
             //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(req);
             PreparedStatement pst = cnx.prepareStatement(req);
             pst.setInt(1, rl.getID_user());
             pst.setInt(2, rl.getID_logement());
             pst.setDate(3, (java.sql.Date) rl.getDateArrivee_reservation());
             pst.setDate(4, (java.sql.Date) rl.getDateDepart_reservation());
             pst.setInt(5, rl.getNbPersonnes_reservation());
             pst.setDouble(6, rl.getMontantTotal_reservation());
             
             pst.executeUpdate();
            
             System.out.println("Reservation de logement ajoutée avec succès");
        } catch (SQLException ex){
             System.err.println(ex.getMessage());
        }
        }
    
    public List<Reservation_logement> afficherReservationLogement(){
        List<Reservation_logement> ListReservation = new ArrayList<>();
        
        try {
         String requete3 = "SELECT * FROM reservation_logement";
         
         Statement st = cnx.createStatement();
         ResultSet rs= st.executeQuery(requete3);
         while(rs.next()){
             Reservation_logement reservation = new Reservation_logement();
             reservation.setID_reservation(rs.getInt(1));
             reservation.setID_user(rs.getInt(2));
             reservation.setID_logement(rs.getInt(3));
             reservation.setDateArrivee_reservation(rs.getDate(4));
             reservation.setDateDepart_reservation(rs.getDate(5));
             reservation.setNbPersonnes_reservation(rs.getInt(6));
             reservation.setMontantTotal_reservation(rs.getDouble(7));
            
             
             
             ListReservation.add(reservation);
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListReservation;
    }
    
    public void modifierReservationLogement(Reservation_logement rl) {
    
      try {
      String req= "UPDATE reservation_logement SET ID_user=?, ID_logement=?, dateArrivee_reservation=?, dateDepart_reservation=?, nbPersonnes_reservation=?, montantTotal_reservation=? WHERE ID_reservation=?";

     PreparedStatement pst = cnx.prepareStatement(req);
             pst.setInt(1, rl.getID_user());
             pst.setInt(2, rl.getID_logement());
             pst.setDate(3, (java.sql.Date) rl.getDateArrivee_reservation());
             pst.setDate(4, (java.sql.Date) rl.getDateDepart_reservation());
             pst.setInt(5, rl.getNbPersonnes_reservation());
             pst.setDouble(6, rl.getMontantTotal_reservation());
             pst.setInt(7, rl.getID_reservation());
             pst.executeUpdate();
            
             System.out.println("Réservation de logement modifiée avec succès");
        } catch (SQLException ex){
             System.err.println(ex.getMessage());
        }
      }
  
    public void supprimerReservationLogement(int id) {
    try {
        String req = "DELETE FROM reservation_logement WHERE ID_reservation = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Reservation de logement supprimée avec succès");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    public List<Reservation_logement> afficherMesReservations(int id){
        List<Reservation_logement> ListReservation = new ArrayList<>();
        
        try {
     
         String requete3 = "SELECT * FROM reservation_logement WHERE ID_user = ? ";
         
         PreparedStatement pst = cnx.prepareStatement(requete3);
         pst.setInt(1, id);
         ResultSet rs= pst.executeQuery();
         while(rs.next()){
             Reservation_logement reservation = new Reservation_logement();
             reservation.setID_reservation(rs.getInt(1));
             reservation.setID_user(rs.getInt(2));
             reservation.setID_logement(rs.getInt(3));
             reservation.setDateArrivee_reservation(rs.getDate(4));
             reservation.setDateDepart_reservation(rs.getDate(5));
             reservation.setNbPersonnes_reservation(rs.getInt(6));
             reservation.setMontantTotal_reservation(rs.getDouble(7));
            
             
             
             ListReservation.add(reservation);
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListReservation;
    }
    
    
}
