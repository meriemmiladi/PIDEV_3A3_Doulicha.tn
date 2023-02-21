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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AfficherParticipationsController implements Initializable {

    @FXML
    private TableView<participation_evenement> tableParticipations;
    @FXML
    private TableColumn<participation_evenement, Integer> col_nbparticipants;
    @FXML
    private TableColumn<participation_evenement, Date> col_dateparticipation;
    @FXML
    private TableColumn<participation_evenement, Integer> col_iduser;
    @FXML
    private TableColumn<participation_evenement, Integer> col_idevent;
    @FXML
    private Button btn_gestionEvenements;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         btn_gestionEvenements.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherEvenements.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
       
      /*ServiceParticipationEvenement participation = new ServiceParticipationEvenement();
        ObservableList<participation_evenement> parts = participation.afficherParts();*/
        afficherPart();
//       rechercheEvent();   
      
    }

    private void afficherPart() {
            ServiceParticipationEvenement participation = new ServiceParticipationEvenement();
       ObservableList<participation_evenement> parts = participation.afficherParts();

col_iduser.setCellValueFactory(new PropertyValueFactory<>("ID_user"));
col_idevent.setCellValueFactory(new PropertyValueFactory<>("ID_event"));
col_dateparticipation.setCellValueFactory(new PropertyValueFactory<>("date_participation"));
col_nbparticipants.setCellValueFactory(new PropertyValueFactory<>("nombre_participation"));


        tableParticipations.setItems(parts);
        
        //search_event();
    }    
    
}
