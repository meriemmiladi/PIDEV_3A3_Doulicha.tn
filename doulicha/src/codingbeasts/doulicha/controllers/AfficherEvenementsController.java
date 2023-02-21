/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
 * @author Admin
 */
public class AfficherEvenementsController implements Initializable {

    @FXML
    private TableView<evenement> tableEvents;
    @FXML
    private TableColumn<evenement, String> col_nom;
    @FXML
    private TableColumn<evenement, String> col_description;
    @FXML
    private TableColumn<evenement, String> col_type;
    @FXML
    private TableColumn<evenement, Date> col_datedebut;
    @FXML
    private TableColumn<evenement, Date> col_datefin;
    @FXML
    private TableColumn<evenement, Integer> col_capacite;
    @FXML
    private TableColumn<evenement, Integer> col_prix;
    @FXML
    private TableColumn<evenement, Integer> col_nombreactuel;
    @FXML
    private TableColumn<evenement, String> col_image;
    @FXML
    private TableColumn<evenement, String> col_lieu;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_gerer;
    @FXML
    private Button btn_gestionParticipations;
    @FXML
    private Button btn_retourhome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ServiceEvenement evenenement = new ServiceEvenement();
        ObservableList<evenement> events = evenenement.afficherEvents();
        afficherEvenement();
//       rechercheEvent();   

btn_retourhome.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/homeAdmin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 

btn_gestionParticipations.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherParticipations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
       
        btn_ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/ajoutEvenement.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
    }    
    
        private void afficherEvenement() {
            ServiceEvenement evenenement = new ServiceEvenement();
        ObservableList<evenement> events = evenenement.afficherEvents();



        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_event"));
        col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_event"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type_event"));
        col_datedebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut_event"));
        col_datefin.setCellValueFactory(new PropertyValueFactory<>("dateFin_event"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite_event"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_event"));
        col_nombreactuel.setCellValueFactory(new PropertyValueFactory<>("nombreActuel_event"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image_event"));

        tableEvents.setItems(events);
        
        //search_event();
    }

    @FXML
    private void gererEvenement(ActionEvent event)throws IOException {
        
         
    
        evenement ev= new evenement();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/views/gererEvenement.fxml"));
            Parent root = loader.load();
          GererEvenementController HomeScene = loader.getController();
          System.out.println("aaaaa");
          
          
            HomeScene.selected_item (
                    tableEvents.getSelectionModel().getSelectedItem().getID_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getNom_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getDescription_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getLieu_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getType_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getDateDebut_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getDateFin_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getCapacite_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getNombreActuel_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getImage_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getPrix_event()
                   );
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) btn_gerer.getScene().getWindow();
            window.setScene(new Scene(root));
    }
    
    
    
    
    
}
