/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;


import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @FXML
    private TableView<Reservation_logement> table_view;
    @FXML
    private TableColumn<Reservation_logement, Integer> ID_reservation;
    @FXML
    private TableColumn<Reservation_logement, Integer> ID_user;
    @FXML
    private TableColumn<Reservation_logement, Integer> ID_logement;
    @FXML
    private TableColumn<Reservation_logement, Date > dateArrivee_reservation;
    @FXML
    private TableColumn<Reservation_logement, Date> dateDepart_reservation;
    @FXML
    private TableColumn<Reservation_logement, Integer> nbPersonnes_reservation;
    @FXML
    private TableColumn<Reservation_logement, Double > montantTotal_reservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherReservationList();
        home.setOnAction( event->{
        try{
            System.out.println("home appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeBack.fxml"));
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

    
    private void afficherReservationList() {
        ServiceReservationLogement ServR = new ServiceReservationLogement();
        //ObservableList<Logement> listL = (ObservableList < Logement >) ServL.afficherLogement();
        ObservableList<Reservation_logement> listR = FXCollections.observableList(ServR.afficherReservationLogement());
        
        
        ID_reservation.setCellValueFactory(new PropertyValueFactory<>("ID_reservation"));
        ID_user.setCellValueFactory(new PropertyValueFactory<>("ID_user"));
        ID_logement.setCellValueFactory(new PropertyValueFactory<>("ID_logement"));
        dateArrivee_reservation.setCellValueFactory(new PropertyValueFactory<>("dateArrivee_reservation"));
        dateDepart_reservation.setCellValueFactory(new PropertyValueFactory<>("dateDepart_reservation"));
        nbPersonnes_reservation.setCellValueFactory(new PropertyValueFactory<>("nbPersonnes_reservation"));
        montantTotal_reservation.setCellValueFactory(new PropertyValueFactory<>("montantTotal_reservation"));
        table_view.setItems(listR);}
           
    
}
