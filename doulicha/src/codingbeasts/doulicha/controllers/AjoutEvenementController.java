/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutEvenementController implements Initializable {

    ObservableList<String>types = FXCollections.observableArrayList("art","culture","fêtes","nature");
    
    private TextField TF_description;
    private TextField TF_nom;
    private TextField TF_lieu;
    private TextField TF_capacite;
    private TextField TF_prix;
    private TextField TF_nombreActuel;
    private DatePicker dateDebut;
    private TextField TF_image;
    private DatePicker dateFin;
    private ComboBox<String> comboType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboType.setItems(types);
    }    

    private void ajouterEvenement(ActionEvent event) {
       
     String nom_event = TF_nom.getText();
     String description_event = TF_description.getText();
     String lieu_event = TF_lieu.getText();
     String type_event = comboType.getValue();
     LocalDate dateDebut_local = dateDebut.getValue();
     LocalDate dateFin_local = dateFin.getValue();
     Date dateDebut_event = java.sql.Date.valueOf(dateDebut_local);
     Date dateFin_event = java.sql.Date.valueOf(dateFin_local);
     int capacite_event =  Integer.parseInt(TF_capacite.getText());
     int nombreActuel_event = Integer.parseInt(TF_nombreActuel.getText());
     String image_event = TF_image.getText();
     double prix_event = Double.parseDouble(TF_prix.getText());
     
     evenement ev = new evenement (nom_event,description_event,lieu_event,  type_event,  dateDebut_event,  dateFin_event,  capacite_event,  nombreActuel_event,  image_event,  prix_event);
        ServiceEvenement sevent = new ServiceEvenement();
        sevent.ajouterEvenement2(ev);
    }
    
}


