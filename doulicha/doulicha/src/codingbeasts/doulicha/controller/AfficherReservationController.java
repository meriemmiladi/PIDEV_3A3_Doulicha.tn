/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;


import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class AfficherReservationController implements Initializable {

    @FXML
    private Button home;
    private TableView table;
    @FXML
    private TableColumn<Reservation_logement, String> ID_reservation;
    @FXML
    private TableColumn<Reservation_logement, String> nom_user;
    @FXML
    private TableColumn<Reservation_logement, String> prenom_user;
    @FXML
    private TableColumn<Reservation_logement, String> nom_logement;
    @FXML
    private TableColumn<Reservation_logement, Date> dateArrivee_reservation;
    @FXML
    private TableColumn<Reservation_logement, Date> dateDepart_reservation;
    @FXML
    private TableColumn<Reservation_logement, Integer> nbPersonnes_reservation;
    @FXML
    private TableColumn<Reservation_logement, Double> montantTotal_reservation;
    @FXML
    private TableColumn<Reservation_logement, String> num_tel;
    @FXML
    private TableView<Reservation_logement> table_view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherReservation();
        Reservation_logement reservation = null;
    
    ServiceReservationLogement ServL = new ServiceReservationLogement();
     ObservableList<Reservation_logement> reservations = FXCollections.observableArrayList(ServL.afficherReservationLogement());
     
        home.setOnAction( event->{
        try{
            System.out.println("home appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AccueilBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(ModifierLogementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
    }

  
      ServiceReservationLogement ServR = new ServiceReservationLogement();
        //ObservableList<Logement> listL = (ObservableList < Logement >) ServL.afficherLogement();
        ObservableList<Reservation_logement> listR = FXCollections.observableList(ServR.afficherReservationLogement());
        
        
    private void refreshTable() {
        try {
            //listL.clear();
            //ServL.afficherLogement();
            
             ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
             executor.scheduleAtFixedRate(() -> {
            // Update the data in the table
            listR.clear();
            afficherReservationList();
        }, 0, 2, TimeUnit.SECONDS);
            

                
            }            
         catch (Exception ex) {
            Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
private void afficherReservation() {
    
        ServiceLogement sl = new ServiceLogement();
        ServiceReservationLogement servR = new ServiceReservationLogement();
        ObservableList<Reservation_logement> reservations = servR.afficherReservationLogement();

nom_user.setCellValueFactory(cellData -> {
    int ID_user = cellData.getValue().getID_user();
    String nom = sl.getNomUser(ID_user);
    return new SimpleStringProperty(nom);
});

prenom_user.setCellValueFactory(cellData -> {
    int ID_user = cellData.getValue().getID_user();
    String nom = sl.getPrenomUser(ID_user);
    return new SimpleStringProperty(nom);
});

nom_logement.setCellValueFactory(cellData -> {
    int ID_logement = cellData.getValue().getID_logement();
    String nom = sl.recupNomLogement(ID_logement);
    return new SimpleStringProperty(nom);
});




dateArrivee_reservation.setCellValueFactory(new PropertyValueFactory<>("dateArrivee_reservation"));
dateDepart_reservation.setCellValueFactory(new PropertyValueFactory<>("dateDepart_reservation"));
nbPersonnes_reservation.setCellValueFactory(new PropertyValueFactory<>("nbPersonnes_reservation"));
montantTotal_reservation.setCellValueFactory(new PropertyValueFactory<>("montantTotal_reservation"));

num_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));


        table_view.setItems(reservations);
        
        
    }
    
    private void afficherReservationList() {
        
        ServiceReservationLogement ServR = new ServiceReservationLogement();
        //ObservableList<Logement> listL = (ObservableList < Logement >) ServL.afficherLogement();
        ObservableList<List<String>> listR = FXCollections.observableList(ServR.afficherReservation());
        System.out.println(listR);
      
        
        // Créez une table view et définissez les colonnes
//TableView table = new TableView();
TableColumn<Integer, Integer> reservationCol = new TableColumn<>("ID_reservation");

TableColumn<String, String> nomUserCol = new TableColumn<>("Nom_user");
TableColumn<String, String> prenomUserCol = new TableColumn<>("Prenom_user");
TableColumn<String, String> logementCol = new TableColumn<>("Nom_logement");
TableColumn<Date, Date> dateArrivee_reservation = new TableColumn<>("Date d'arrivee");
TableColumn<String, String>  dateDepart_reservation= new TableColumn<>("Date de depart");
TableColumn<Integer, Integer>  nbPersonnes_reservation= new TableColumn<>("Nombre de personnes");
TableColumn<Double, Double>  montantTotal_reservation = new TableColumn<>("montantTotal");
TableColumn<String, String>  num_tel= new TableColumn<>("Téléphone");


table.getColumns().addAll(reservationCol, nomUserCol,prenomUserCol, logementCol,dateArrivee_reservation,dateDepart_reservation,nbPersonnes_reservation,montantTotal_reservation,num_tel);
 
        table.setItems(listR);


        
        
    }
        
       
    
}
