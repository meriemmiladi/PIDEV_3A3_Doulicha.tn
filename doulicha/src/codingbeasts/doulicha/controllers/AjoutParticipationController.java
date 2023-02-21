/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.entities.participation_evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import codingbeasts.doulicha.services.ServiceParticipationEvenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutParticipationController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private TextField TF_nombre;
    @FXML
    private DatePicker dateP;
    
    
    private MyListener myListener;
    private evenement event;
    @FXML
    private TextField id_part;
    
    @FXML
    private Button btnParticiper;
    @FXML
    private ImageView icone_nb;
    @FXML
    private ImageView icone_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       /* FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/detailEvenement.fxml"));
                    
        try {
            Node postbox = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AjoutParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    System.out.println("TEST TEST");
                    DetailEvenementController evc = loader.getController();
                   // evc.setData(event, myListener, this.evenement_choisi); */
                  
        
       btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/evenementClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
       
       
       
     
    } 
    
    
     
    


    @FXML
    private void ajouterParticipation(ActionEvent event) {
        
        
    participation_evenement part = new participation_evenement();
    if (testSaisie()){
    ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
        
    LocalDate dateParticipation_local = dateP.getValue();
    java.sql.Date dateParticipation_event = java.sql.Date.valueOf(dateParticipation_local);
    int nombre_participation = Integer.parseInt(TF_nombre.getText());
   // int ID_event = Integer.parseInt(id_part.getText());
    
     String textI = id_part.getText();
        int valueI=0;
        try {
             valueI = Integer.parseInt(textI);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation !");
            } 
        
    part.setDate_participation(dateParticipation_event);
    part.setNombre_participation(Integer.parseInt(TF_nombre.getText()));
   // part.setID_participation(Integer.parseInt(idparticipation.getText()));
    
   part.setID_event(valueI);
     part.setID_user(1);
    SPE.ajouterParticipationEvenement(part);
    

    Notifications notificationBuilder = Notifications.create()
            .title("Ajout PARTICIPATION")
            .text("Votre participation a bien été enregistrée.")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.showInformation();

    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/evenementClient.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutParticipationController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }
    
    private Boolean testSaisie() {
        String erreur = "";
      
        if (!testDatePart()) {
            erreur = erreur + ("Veuillez saisir une date valide \n");
        }
        return testDatePart();
    }
    private Boolean testDatePart() {
        LocalDate now = LocalDate.now();
        if ( dateP.getValue().compareTo(now) > 0) {
                icone_date.setImage(new Image("images/yes.png"));
                return true;
            } else {
                icone_date.setImage(new Image("images/no.png"));
            }
                return false;
    } 
    
    
    void selected_item2(int ID_event) {
    id_part.setText(String.valueOf(ID_event));
    //id_part.setText(String.valueOf(ID_participation));
    
    } 
    
    
   /* void setTextField( int ID_event) {
        this.ID_event.setText(Integer.toString(ID_event));
    } */
     
     private boolean update;
     void setUpdate(boolean b) {
        this.update = b;

    }
     
      private int id;
     void recupererID(int id) {
        this.id = id;
       System.out.println("ID de l'événement sélectionné : " + id);

    }

    
    
   
}


 /* public void initialize(URL url, ResourceBundle rb) {
        
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
        ServiceReservationLogement sr = new ServiceReservationLogement();
        //if(testSaisie()){ 

        Reservation_logement r = new Reservation_logement();
        LocalDate dateArrivee_local = dateArrivee_reservation.getValue();
        LocalDate dateDepart_local = dateDepart_reservation.getValue();
        Date dateDebut_event = java.sql.Date.valueOf(dateArrivee_local);
        Date dateFin_event = java.sql.Date.valueOf(dateDepart_local);
        
        r.setDateArrivee_reservation(dateDebut_event);
        r.setDateDepart_reservation(dateFin_event);
       
        String textC = nbPersonnes_reservation.getText();
        try {
            int valueC = Integer.parseInt(textC);
            r.setNbPersonnes_reservation(valueC);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation capacite!");
            }
     
        // calculez le nombre de jours de la réservation
        long nbJoursReservation = ChronoUnit.DAYS.between(dateArrivee_local, dateDepart_local);

        // calculez le montant total de la réservation
        String textI = ID_logement.getText();
        int valueI=0;
        try {
             valueI = Integer.parseInt(textI);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation capacite!");
            }
        ServiceLogement sl = new ServiceLogement();
        double montantTotal = nbJoursReservation * (sl.recupPrixNuitee(valueI));
        r.setMontantTotal_reservation(montantTotal);
        r.setID_logement(valueI);
        
        
        //A CHANGER A L'INTEGRATION
        //*************************************
        r.setID_user(2);
         //*************************************
        

        sr.ajouterReservationLogement2(r);
        
       
    } 
    
     
     
     
    }*/
