/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.MySession;
import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    @FXML
    private TextField num_tel;
    @FXML
    private AnchorPane page;
    int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AccueilFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                ex.getStackTrace();
    }
    });
        
    consulter_reservation.setOnAction( event->{
        try{
            System.out.println("Bouton consulter_reservations appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ConsulterMesReservations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
          ex.getStackTrace();
    }
    });
    
    
    consulter_logement.setOnAction( event->{
        try{
            System.out.println("Bouton consulter_logement appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/LogementUser.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
              ex.getStackTrace();
    }
    });
        annuler.setOnAction( event->{
        try{
            System.out.println("annuler appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AccueilFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                ex.getStackTrace();
    }
    });
        
        
        
        modifier.setOnAction(event -> { 
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setContentText("Etes vous sur de vouloir modifier votre réservation ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
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
        r.setNum_tel(num_tel.getText());
     
       

       
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
        
        
        
        
        
        //RECUPERATION DU CURRENT USER
        //*************************************
         Utilisateur user = MySession.getLoggedInUser();
        int loggedInUserId = user.getID_user();
        System.out.println("Utilisateur connecté : " + loggedInUserId);
        r.setID_user(loggedInUserId);
         //*************************************
        

        sr.modifierReservationLogement(r);
        
        try{
        Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ConsulterMesReservations.fxml"));
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
        }} }});
      
    }    
    
  //  void setTextField( int ID_reservation,int ID_logement,int ID_user,Date dateArrivee_reservation,Date dateDepart_reservation,int nbPersonnes_reservations,Double montantTotal_reservation ) {
        void setTextField( Date dateArrivee_reservation,Date dateDepart_reservation,int nbPersonnes_reservations ,String num_tel) {
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
        this.num_tel.setText(num_tel);
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
     
       public void recupererID(int id) {
        this.id = id;

    }
     
      private Boolean testSaisie() {
        String erreur = "";
      
        if (!testNbPersonnes()&&(!(nbPersonnes_reservation.getText().isEmpty()))) {
            erreur = erreur + ("Veuillez entrer le nombre de personnes correctement \n");
        }
       
        if (!testDateArrivee()) {
            erreur = erreur + ("Veuillez vérifier la date d'arrivée: \n");
        }
         if (!testDateDepart()) {
            erreur = erreur + ("Veuillez vérifier la date de départ \n");
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
