/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;


import codingbeasts.doulicha.entities.Logement;
import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.services.SmsSender;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
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
   
    private int id;
    private String nom;
    @FXML
    private TextField num_tel;
    private Label ID_logement;
    @FXML
    private Label labelDateA;
    @FXML
    private Label labelDateD;
    @FXML
    private AnchorPane page;
    @FXML
    private VBox formBox;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
      /*selectionnerDates.setOnAction(event -> {
      this.recupererID(id);
          System.out.println(id);
          labelDateA.setDisable(false);
          labelDateD.setDisable(false);
          dateArrivee_reservation.setDisable(false);
          dateDepart_reservation.setDisable(false);
           try {
            recupDateReservee(id);
        } catch (SQLException ex) {
            Logger.getLogger(ReserverLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
      });*/
      
      page.setOnMouseMoved(event -> {
        this.recupererID(id);
          System.out.println(id);
          /*labelDateA.setDisable(false);
          labelDateD.setDisable(false);
          dateArrivee_reservation.setDisable(false);
          dateDepart_reservation.setDisable(false);*/
           try {
            recupDateReservee(id);
        } catch (SQLException ex) {
            Logger.getLogger(ReserverLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }
      });
         
        
       
        
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
            System.out.println(id);
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

       
        
         
     
        /*String textI = ID_logement.getText();
        int valueI=0;
        try {
             valueI = Integer.parseInt(textI);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation capacite!");
            }*/
        ServiceLogement sl = new ServiceLogement();
         // calculez le montant total de la réservation
        double montantTotal = nbJoursReservation * (sl.recupPrixNuitee(id));
         r.setMontantTotal_reservation(montantTotal);
        //montantTotal_reservation.setText(Double.toString(montantTotal));
        r.setID_logement(id);
        
        
        
        
        
        
        //A CHANGER A L'INTEGRATION
        //*************************************
        r.setID_user(2);
         //*************************************
        

        sr.ajouterReservationLogement2(r);
       //API MESSAGE 
       
       /* String toNumber= r.getNum_tel();
    String messageBody = "Votre réservation de logement a été bien validée. Merci de votre confiance !";
    SmsSender.sendSms(toNumber, messageBody);*/ 
       
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
        } }
});
         System.out.println("test3");
    } 
    
     /*void setTextField( int ID_logement) {
        this.ID_logement.setText(Integer.toString(ID_logement));
    }*/
     
     private boolean update;
     void setUpdate(boolean b) {
        this.update = b;

    }
     
      
     public void recupererID(int id) {
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




public List<LocalDate> getDatesReservation(Reservation_logement reservation) {
    Date dateArrivee_reservation = reservation.getDateArrivee_reservation();
    Date dateDepart_reservation = reservation.getDateDepart_reservation();

    java.sql.Date sqlDateA = (java.sql.Date) dateArrivee_reservation;
    java.util.Date utilDateA = new java.util.Date(sqlDateA.getTime());
    java.time.LocalDate localDateArrivee = utilDateA.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    java.sql.Date sqlDateD = (java.sql.Date) dateDepart_reservation;
    java.util.Date utilDateD = new java.util.Date(sqlDateD.getTime());
    java.time.LocalDate localDateDepart = utilDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    List<LocalDate> listDates = new ArrayList<LocalDate>();
    listDates.add(0,localDateArrivee);
    listDates.add(1,localDateDepart);

    return listDates;
}


public void recupDateReservee(int ID_logement) throws SQLException {
    Connection cnx;
    cnx = MyConnection.getInstance().getCnx();
    List<Reservation_logement> ListReservation = new ArrayList<>();

    try {
        String requete = "SELECT * from reservation_logement where ID_logement= ? ";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, ID_logement);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
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
    }catch(Exception e){e.printStackTrace();}

    // Configurer le DayCellFactory une seule fois pour chaque DatePicker
    this.dateArrivee_reservation.setDayCellFactory(createDayCellFactory(ListReservation));
    this.dateDepart_reservation.setDayCellFactory(createDayCellFactory(ListReservation));
}

// Créer un DayCellFactory qui désactive les dates pour toutes les réservations
private Callback<DatePicker, DateCell> createDayCellFactory(List<Reservation_logement> ListReservation) {
    return new Callback<DatePicker, DateCell>() {
        @Override
        public DateCell call(DatePicker datePicker) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    // Désactiver les dates entre les deux dates de chaque réservation
                    for (Reservation_logement reservation : ListReservation) {
                        List<LocalDate> listDates = getDatesReservation(reservation);
                        if ((item.compareTo(listDates.get(0))>= 0 )&& (item.compareTo(listDates.get(1))<= 0 )) 
                        {
                            setDisable(true);
                            setStyle("-fx-opacity: 0.5;"); // pour afficher les dates désactivées en semi-transparent
                        }
                    }
                }
            };
        }
    };
}


}
    

