/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class ModifierReservationController implements Initializable {

    @FXML
    private Button consulter_logement;
    @FXML
    private Button consulter_reservation;
    @FXML
    private Button home;
    @FXML
    private TextField nbPersonnes_reservation;
    @FXML
    private DatePicker dateArrivee_reservation;
    @FXML
    private DatePicker dateDepart_reservation;
    @FXML
    private Button modifier;
    @FXML
    private Button annuler;
    private TextField ID_user;
    private TextField ID_logement;
    private TextField ID_reservation;
    private TextField montantTotal_reservation;
    private int id_logement,id_reservation,id_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         home.setOnAction( event->{
        try{
            System.out.println("Bouton home appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
        
    consulter_reservation.setOnAction( event->{
        try{
            System.out.println("Bouton consulter_reservations appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/ConsulterMesReservations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
    
    
    consulter_logement.setOnAction( event->{
        try{
            System.out.println("Bouton consulter_logement appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/LogementUser.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
        annuler.setOnAction( event->{
        try{
            System.out.println("annuler appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(ReserverLogementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
        
        
        
        modifier.setOnAction(event -> { 
            if (testNbPersonnes())
            {
        ServiceReservationLogement sr = new ServiceReservationLogement();
        if(testSaisie()){ 

        Reservation_logement r = new Reservation_logement();
        LocalDate dateArrivee_local = dateArrivee_reservation.getValue();
        LocalDate dateDepart_local = dateDepart_reservation.getValue();
        Date dateArrivee_reservation = java.sql.Date.valueOf(dateArrivee_local);
        Date dateDepart_reservation = java.sql.Date.valueOf(dateDepart_local);
        
        r.setDateArrivee_reservation(dateArrivee_reservation);
        r.setDateDepart_reservation(dateDepart_reservation);
       
        String textC = nbPersonnes_reservation.getText();
        try {
            int valueC = Integer.parseInt(textC);
            r.setNbPersonnes_reservation(valueC);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation capacite!");
            }
     
       

       
         this.recupererIDs(id_reservation,id_logement,id_user);
         
         
        /*String textI = ID_logement.getText();
        int valueI=0;
        try {
             valueI = Integer.parseInt(textI);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation capacite!");
            }*/
        ServiceLogement sl = new ServiceLogement();
         // calculez le nombre de jours de la réservation
        long nbJoursReservation = ChronoUnit.DAYS.between(dateArrivee_local, dateDepart_local);
         // calculez le montant total de la réservation
        double montantTotal = nbJoursReservation * (sl.recupPrixNuitee(id_logement));
         r.setMontantTotal_reservation(montantTotal);
        
        r.setID_reservation(id_reservation);
        r.setID_logement(id_logement);
        r.setID_user(id_user);
        
        
        
        
        
        //A CHANGER A L'INTEGRATION
        //*************************************
        //r.setID_user(2);
         //*************************************
        

        sr.modifierReservationLogement(r);
        
        try{
        Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/ConsulterMesReservations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
        } catch(IOException ex)
        {
           Logger.getLogger(ModifierLogementController.class.getName()).log(Level.SEVERE, null, ex); 
        }
        //Resof.addReservationoffre(Res);
        Notifications notificationBuilder = Notifications.create()
                .title("Reservation Modifiée")
                .text("La réservation a été bien modifiée  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
        } }});
      
    }    
    
  //  void setTextField( int ID_reservation,int ID_logement,int ID_user,Date dateArrivee_reservation,Date dateDepart_reservation,int nbPersonnes_reservations,Double montantTotal_reservation ) {
        void setTextField( Date dateArrivee_reservation,Date dateDepart_reservation,int nbPersonnes_reservations ) {
        /*this.ID_reservation.setText(Integer.toString(ID_reservation));
        this.ID_logement.setText(Integer.toString(ID_logement));
        this.ID_user.setText(Integer.toString(ID_user));*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDateArrivee = formatter.format(dateArrivee_reservation);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateA = LocalDate.parse(strDateArrivee, formatter1);
        this.dateArrivee_reservation.setValue(dateA);
        String strDateDepart = formatter.format(dateDepart_reservation);
        LocalDate dateD = LocalDate.parse(strDateDepart, formatter1);
        this.dateDepart_reservation.setValue(dateD);
        this.nbPersonnes_reservation.setText(Integer.toString(nbPersonnes_reservations));
        //this.montantTotal_reservation.setText(Double.toString(montantTotal_reservation));
        
    }
    
    private boolean update;
     void setUpdate(boolean b) {
        this.update = b;

    }
     
      
     void recupererIDs(int idr,int idl,int idu) {
        this.id_reservation = idr;
        this.id_logement = idl;
        this.id_user = idu;

    }
     
      private Boolean testSaisie() {
        String erreur = "";
      
        if (!testNbPersonnes()) {
            erreur = erreur + ("Veuillez verifier le nombre de personnes correctement \n");
        }
       
        if (!testDateArrivee()) {
            erreur = erreur + ("Veuillez verifier l'adresse: \n");
        }
         if (!testDateDepart()) {
            erreur = erreur + ("Veuillez saisir la capacité correctement \n");
        }
          
         
      
        return  testNbPersonnes() && testDateArrivee() && testDateDepart() ;
    }
      
      
      private Boolean testNbPersonnes() {
          try{
          Double.parseDouble(nbPersonnes_reservation.getText());
        //checkprixnuitee.setImage(new Image("/codingbeats/doulicha/images/checkmark.png"));
        return true;
    } catch (NumberFormatException e) {
       // checkprixnuitee.setImage(new Image("/codingbeats/doulicha/images/erreurcheckmark.png"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier le nombre de personnes inséré à la réservation !");
            alert.showAndWait();
        return false;
    }
      }
     
     
       
        private Boolean testDateArrivee() {
        LocalDate now = LocalDate.now();
        if ( dateArrivee_reservation.getValue().compareTo(now) > 0) {
                  return true;
            } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier la date d'arrivée insérée !");
            alert.showAndWait();
              return false;
            }
              
    } 
     
      private Boolean testDateDepart() {
        LocalDate now = LocalDate.now();
        if ((dateDepart_reservation.getValue().compareTo(now) > 0) && (dateDepart_reservation.getValue().isAfter(dateArrivee_reservation.getValue())) ) {
               
                return true;
            } else {
                  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier la date d'arrivée insérée !");
            alert.showAndWait();
              return false;
            }
               
    } 
    
}
