/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;
import javafx.util.Callback;

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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
    private TableColumn<participation_evenement, Date> col_dateparticipation;
    @FXML
    private TableColumn<participation_evenement, String> col_iduser;
    @FXML
    private TableColumn<participation_evenement, String> col_idevent;
    @FXML
    private Button btn_gestionEvenements;
    @FXML
    private TableColumn<participation_evenement, String> col_numtel;
    @FXML
    private TextField recherche;
    
     participation_evenement particip = null;
    
    ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
     ObservableList<participation_evenement> parts = FXCollections.observableArrayList(SPE.afficherParts());
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         btn_gestionEvenements.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AfficherEvenements.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
       
        afficherPart();
//       rechercheEvent();   
      
    }

    private void afficherPart() {
        ServiceEvenement ev = new ServiceEvenement();
            ServiceParticipationEvenement participation = new ServiceParticipationEvenement();
       ObservableList<participation_evenement> parts = participation.afficherParts();

col_iduser.setCellValueFactory(cellData -> {
    int ID_user = cellData.getValue().getID_user();
    String nom = ev.getNomUser(ID_user);
    return new SimpleStringProperty(nom);
});

col_idevent.setCellValueFactory(cellData -> {
    int ID_event = cellData.getValue().getID_event();
    String nom = ev.getNom(ID_event);
    return new SimpleStringProperty(nom);
});



//col_iduser.setCellValueFactory(new PropertyValueFactory<>("ID_user"));
//col_idevent.setCellValueFactory(new PropertyValueFactory<>("ID_event"));
col_nbparticipants.setCellValueFactory(new PropertyValueFactory<>("nombre_participation"));
col_numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));


        tableParticipations.setItems(parts);
        
        search_event();
    }


 void search_event() {
        ServiceParticipationEvenement participation = new ServiceParticipationEvenement();
         ServiceEvenement ev = new ServiceEvenement();
           ObservableList<participation_evenement> parts = participation.afficherParts();
        
      col_iduser.setCellValueFactory(cellData -> {
    int ID_user = cellData.getValue().getID_user();
    String nom = ev.getNomUser(ID_user);
    return new SimpleStringProperty(nom);
});

col_idevent.setCellValueFactory(cellData -> {
    int ID_event = cellData.getValue().getID_event();
    String nom = ev.getNom(ID_event);
    return new SimpleStringProperty(nom);
});



//col_iduser.setCellValueFactory(new PropertyValueFactory<>("ID_user"));
//col_idevent.setCellValueFactory(new PropertyValueFactory<>("ID_event"));
col_nbparticipants.setCellValueFactory(new PropertyValueFactory<>("nombre_participation"));
col_numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));

        tableParticipations.setItems(parts);
        
       
        FilteredList<participation_evenement> filteredData = new FilteredList<>(parts, b -> true);
       
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((participation_evenement event) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (ev.getNomUser(event.getID_user()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (ev.getNom(event.getID_event()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                               
                                
                               
				     else  
				    	 return false; // Does not match.
			});
		});
        SortedList<participation_evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableParticipations.comparatorProperty());
        tableParticipations.setItems(sortedData);
   }     
    
}
