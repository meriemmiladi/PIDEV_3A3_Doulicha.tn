/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.services.DiscussionCRUD;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ghass
 */


public class ClientDiscussionController implements Initializable {
    
    @FXML
    private TableView<Discussion> tableDiscussions;
    @FXML
    private TableColumn<Discussion, Integer> colID_Discussion;
     @FXML
    private TableColumn<Discussion, Integer> colID_User;
    @FXML
    private TableColumn<Discussion, String> colTitreDiscussion;
    
    @FXML
    private TableColumn<Discussion, String> colContenuDiscussion;
    
    @FXML
    private TableColumn<Discussion, Date> colDateDiscussion;
    
    @FXML
    private TextField titreDiscussionTextField;
    
    @FXML
    private TextArea contenuDiscussionTextArea;
    
    @FXML
    private Button ajouterDiscussionButton;
    
    @FXML
    private Button modifierDiscussionButton;
    
    @FXML
    private Button supprimerDiscussionButton;
    
    private ObservableList<Discussion> discussionsList;
    
    private DiscussionCRUD discussionCRUD;
    
    private final int idUtilisateur=1; // l'id de l'utilisateur actuellement connecté
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        discussionCRUD = new DiscussionCRUD();
        
     
        
        // Charge les discussions dans le tableau
        chargerDiscussions();
        
        // Permet la sélection d'une seule ligne dans le tableau
        tableDiscussions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        // Désactive les boutons modifier et supprimer au démarrage de l'application
     /**   modifierDiscussionButton.setDisable(true);
        supprimerDiscussionButton.setDisable(true);
        
        // Attache un listener à la sélection dans le tableau
        tableDiscussions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                modifierDiscussionButton.setDisable(false);
                supprimerDiscussionButton.setDisable(false);
                titreDiscussionTextField.setText(newSelection.getTitre_discussion());
                contenuDiscussionTextArea.setText(newSelection.getContenu_discussion());
            } else {
                modifierDiscussionButton.setDisable(true);
                supprimerDiscussionButton.setDisable(true);
            }
        });*/
    } 
    
    // Charge les discussions dans le tableau
    private void chargerDiscussions() {
        discussionsList = FXCollections.observableArrayList(discussionCRUD.rechercherDiscussions(1));
        tableDiscussions.setItems(discussionsList);
    }
    
    // Vide les champs du formulaire d'ajout de discussion
    private void viderChamps() {
        titreDiscussionTextField.setText("");
        contenuDiscussionTextArea.setText("");
    }
    
    @FXML
    private void ajouterDiscussion(ActionEvent event) {
        if (titreDiscussionTextField.getText().isEmpty() || contenuDiscussionTextArea.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Les champs Titre et Contenu ne peuvent pas être vides !");
            alert.showAndWait();
            
        } else {
            Discussion discussion = new Discussion(idUtilisateur, titreDiscussionTextField.getText(),
                    contenuDiscussionTextArea.getText(), new Date(2023,12,11));
            discussionCRUD.ajouterDiscussion(discussion);
            chargerDiscussions();
            viderChamps();
        }
    }
}