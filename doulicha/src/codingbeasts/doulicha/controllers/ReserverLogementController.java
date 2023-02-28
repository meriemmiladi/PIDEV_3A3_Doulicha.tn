/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;


import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.services.SmsSender;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
public class ReserverLogementController implements Initializable {

    @FXML
    private Button consulter_logement;
    @FXML
    private Button consulter_reservation;
    @FXML
    private Button home;
    private TextField prixNuitee_logement;
    @FXML
    private Button reserver;
    @FXML
    private Button annuler;
    @FXML
    private TextField nbPersonnes_reservation;
    @FXML
    private DatePicker dateArrivee_reservation;
    @FXML
    private DatePicker dateDepart_reservation;
    private TextField ID_logement;
    private int id;
    private String nom;
    @FXML
    private TextField num_tel;

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
        
        
        
        reserver.setOnAction(event -> {  
            System.out.println("reserver appuyé");
            if(testNbPersonnes()){
        ServiceReservationLogement sr = new ServiceReservationLogement();
        if(testSaisie()){ 
            System.out.println("test1");
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
       r.setNum_tel(num_tel.getText());
       
        // calculez le nombre de jours de la réservation
        long nbJoursReservation = ChronoUnit.DAYS.between(dateArrivee_local, dateDepart_local);

        // calculez le montant total de la réservation
         this.recupererID(id);
         
        /*String textI = ID_logement.getText();
        int valueI=0;
        try {
             valueI = Integer.parseInt(textI);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation capacite!");
            }*/
        ServiceLogement sl = new ServiceLogement();
        double montantTotal = nbJoursReservation * (sl.recupPrixNuitee(id));
         r.setMontantTotal_reservation(montantTotal);
        //montantTotal_reservation.setText(Double.toString(montantTotal));
        r.setID_logement(id);
        
        
        
        
        
        //A CHANGER A L'INTEGRATION
        //*************************************
        r.setID_user(2);
         //*************************************
        

        sr.ajouterReservationLogement2(r);
        //handleReservationValidation();
        //*********************
        //String toNumber = "+21629599189";
        String toNumber= r.getNum_tel();
    String messageBody = "Votre réservation de logement a été bien validée. Merci de votre confiance !";
    SmsSender.sendSms(toNumber, messageBody);
        //*********************
         System.out.println("test2");
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
                .title("Reservation validée")
                .text("La réservation a été bien enregistrer  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
        } } });
         System.out.println("test3");
    } 
    
     void setTextField( int ID_logement) {
        this.ID_logement.setText(Integer.toString(ID_logement));
    }
     
     private boolean update;
     void setUpdate(boolean b) {
        this.update = b;

    }
     
      
     void recupererID(int id) {
        this.id = id;

    }
     
      void recupererNom(String nom) {
        this.nom = nom;

    }
      
       private Boolean testSaisie() {
        String erreur = "";
      
        if (!testNbPersonnes()&&(!(nbPersonnes_reservation.getText().isEmpty()))) {
            erreur = erreur + ("Veuillez entrer le nombre de personnes correctement \n");
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
        try {
        Integer.parseInt(nbPersonnes_reservation.getText()) ;
       
       return true;}
        
     catch (NumberFormatException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer le nombre de personnes correctement !");
        alert.showAndWait();
         return false;
        
    }
       
       }
      
        
      
      
      
      private Boolean testDateArrivee() {
        LocalDate now = LocalDate.now();
        if (( dateArrivee_reservation.getValue().compareTo(now) > 0)&& (!(dateArrivee_reservation.getValue().toString().trim().isEmpty()) )) {
                  return true;
            } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier la date d'arrivée !");
            alert.showAndWait();
              return false;
            }
              
    } 
     
      private Boolean testDateDepart() {
        LocalDate now = LocalDate.now();
        if ((dateDepart_reservation.getValue().compareTo(now) > 0) && (dateDepart_reservation.getValue().isAfter(dateArrivee_reservation.getValue()))&& (!(dateDepart_reservation.getValue().toString().trim().isEmpty()) )) {
               
                return true;
            } else {
                  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier la date d'arrivée !");
            alert.showAndWait();
              return false;
            }
               
    } 
      
private void handleReservationValidation() {
    // code pour valider la réservation
    String toNumber = "+21629599189";
    String messageBody = "Votre réservation de logement a été bien validée. Merci de votre confiance !";
    SmsSender.sendSms(toNumber, messageBody);
}


     
    }    
    

