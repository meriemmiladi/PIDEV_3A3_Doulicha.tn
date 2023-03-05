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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marie
 */
public class ServiceReservationLogement implements IServiceReservationLogement {
    
     Connection cnx;
    public ServiceReservationLogement(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterReservationLogement(){
    String req = "INSERT INTO reservation_logement ( ID_user, ID_logement, dateArrivee_reservation, dateDepart_reservation, nbPersonnes_reservation, montantTotal_reservation,num_tel)"
            + "VALUES (2,3,'2022-03-22','2022-03-30',12,500.0,'29599189')";
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
        String req = "INSERT INTO reservation_logement ( ID_user, ID_logement, dateArrivee_reservation, dateDepart_reservation, nbPersonnes_reservation, montantTotal_reservation,num_tel) "
                + "VALUES (?,?,?,?,?,?,?)";
             //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(req);
             PreparedStatement pst = cnx.prepareStatement(req);
             pst.setInt(1, rl.getID_user());
             pst.setInt(2, rl.getID_logement());
             pst.setDate(3, (java.sql.Date) rl.getDateArrivee_reservation());
             pst.setDate(4, (java.sql.Date) rl.getDateDepart_reservation());
             pst.setInt(5, rl.getNbPersonnes_reservation());
             pst.setDouble(6, rl.getMontantTotal_reservation());
             pst.setString(7, rl.getNum_tel());
             
             pst.executeUpdate();
            
             System.out.println("Reservation de logement ajoutée avec succès");
        } catch (SQLException ex){
             System.err.println(ex.getMessage());
        }
        }
    
    public ObservableList<Reservation_logement> afficherReservationLogement(){
        
         ObservableList<Reservation_logement> ListReservation = FXCollections.observableArrayList();
        
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
             reservation.setNum_tel(rs.getString(8));
            
             
             
             ListReservation.add(reservation);
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListReservation;
    }
    
   
    
    
    
    public List<List<String>> afficherReservation(){
         Connection cnx;
             cnx = MyConnection.getInstance().getCnx();
             List<List<String>> ListReservation = new ArrayList<>();
             try{
         String requete = "SELECT r.ID_reservation, u.nom_user, u.prenom_user , l.nom_logement, r.dateArrivee_reservation, r.dateDepart_reservation, r.nbPersonnes_reservation,r.montantTotal_reservation,r.num_tel FROM reservation_logement r INNER JOIN utilisateur u ON r.ID_user = u.ID_user INNER JOIN logement l ON r.ID_logement = l.ID_logement";
        
         Statement st = cnx.createStatement();
         ResultSet rs= st.executeQuery(requete);
       /*  TableView table = new TableView();
ID_reservation= new TableColumn<>("ID_reservation");
nom_user = new TableColumn<>("nom_user");
prenom_user= new TableColumn<>("prenom_user");
nom_logement = new TableColumn<>("nom logement");
dateArrivee_reservation = new TableColumn<>("dateArrivee_reservation");
dateDepart_reservation= new TableColumn<>("dateDepart_reservation");
nbPersonnes_reservation= new TableColumn<>("nbPersonnes_reservation");
montantTotal_reservation = new TableColumn<>("montantTotal_reservation");
num_tel= new TableColumn<>("num_tel");
table_view.getColumns().addAll(ID_reservation, nom_user,prenom_user, nom_logement,dateArrivee_reservation,dateDepart_reservation,nbPersonnes_reservation,montantTotal_reservation,num_tel);*/

// Créez une liste de listes pour stocker les données de chaque ligne de résultat



// Ajoutez chaque ligne de résultat à la table view
while (rs.next()) {
    int reservation = rs.getInt("ID_reservation");
    String Nuser = rs.getString("nom_user");
    String Puser = rs.getString("prenom_user");
    String logement = rs.getString("nom_logement");
    Date DateA=rs.getDate("dateArrivee_reservation");
    Date DateD=rs.getDate("dateDepart_reservation");
    int nbPersonnes= rs.getInt("nbPersonnes_reservation");
    Double montantTotal=rs.getDouble("montantTotal_reservation");
    String num_tel= rs.getString("num_tel");
    //ListReservation.addAll(Integer.toString(reservation), Nuser,Puser, logement,DateA.toString(),DateD.toString(),Integer.toString(nbPersonnes),Double.toString(montantTotal),num_tel);
    List<String> row = new ArrayList<>(Arrays.asList(Integer.toString(reservation), Nuser,Puser, logement,DateA.toString(),DateD.toString(),Integer.toString(nbPersonnes),Double.toString(montantTotal),num_tel));
      ListReservation.add(row);
    }
    }catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
             return ListReservation;
    }
    
    public void modifierReservationLogement(Reservation_logement rl) {
    
      try {
      String req= "UPDATE reservation_logement SET ID_user=?, ID_logement=?, dateArrivee_reservation=?, dateDepart_reservation=?, nbPersonnes_reservation=?, montantTotal_reservation=? ,num_tel=? WHERE ID_reservation=?";

     PreparedStatement pst = cnx.prepareStatement(req);
             pst.setInt(1, rl.getID_user());
             pst.setInt(2, rl.getID_logement());
             pst.setDate(3, (java.sql.Date) rl.getDateArrivee_reservation());
             pst.setDate(4, (java.sql.Date) rl.getDateDepart_reservation());
             pst.setInt(5, rl.getNbPersonnes_reservation());
             pst.setDouble(6, rl.getMontantTotal_reservation());
             pst.setInt(8, rl.getID_reservation());
             pst.setString(7, rl.getNum_tel());
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
             reservation.setNum_tel(rs.getString(8));
            
             
             
             ListReservation.add(reservation);
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListReservation;
    }
    
    
}
