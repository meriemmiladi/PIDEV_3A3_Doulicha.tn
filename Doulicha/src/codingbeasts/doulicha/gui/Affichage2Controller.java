/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.projetCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Affichage2Controller implements Initializable {

    @FXML
    private Button btn_valider;
    @FXML
    private VBox VBox1;
    @FXML
    private HBox HBox1;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox projetListe;
    @FXML
    private Button tfretourne;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   projetCRUD dis = new projetCRUD();
        List<projet> projets = dis.afficherprojet();

        // boucle pour ajouter chaque projet à la VBox
        projets.stream().map((projet projet) -> {
            VBox contentBox = new VBox();
            // créer un Label pour afficher le nom du projet
            Label nomLabel = new Label("Nom_Projet : " + projet.getNom_projet());
            nomLabel.setStyle("-fx-font-weight: bold;");

            // créer un Label pour afficher la description du projet
            Label descriptionLabel = new Label("Description : " + projet.getDescription_projet());
            nomLabel.setStyle("-fx-font-weight: bold;");
            // créer un Label pour afficher l'objectif du projet
            Label objectifLabel = new Label("Objectif : " + projet.getObjectif_projet());
            nomLabel.setStyle("-fx-font-weight: bold;");
            // créer un Label pour afficher l'état du projet
            Label etatLabel = new Label("État : " + projet.getEtat_projet());
            nomLabel.setStyle("-fx-font-weight: bold;");
            // créer une ImageView pour afficher l'image du projet
            Label imageView = new Label("Path d Image : " + projet.getImage_projet());
            nomLabel.setStyle("-fx-font-weight: bold;");
            
            Button replyButton = new Button("supprimer");
            replyButton.setOnAction((ActionEvent event) -> {
                try {
                    dis.deleteprojet(projet.getId_projet()); // appel de la méthode deleteprojet avec l'id du projet
                    projetListe.getChildren().remove(contentBox); // supprime le contenu du projet de la VBox
                } catch (SQLException ex) {
                    Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            replyButton.setStyle("-fx-font-weight: bold;");
            // créer un bouton pour modifier le projet
            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction((ActionEvent event) -> {
                try {
                    // charger la vue AjouterProjet.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceProjet.fxml"));
                    Parent root = loader.load();
                    InterfaceProjetController controller = loader.getController();

                    // pré-remplir les champs avec les valeurs du projet sélectionné
                    controller.setProjet(projet);

                    // afficher la vue AjouterProjet.fxml
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            modifierButton.setStyle("-fx-font-weight: bold;");
            modifierButton.getLayoutX();
            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(nomLabel, descriptionLabel, objectifLabel, etatLabel, imageView, replyButton,modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            contentBox.getStyleClass().add("vbox-style");
            replyButton.getStyleClass().add("button-style");
            modifierButton.getStyleClass().addAll("button-style", "modify");
            return contentBox;
            
        }).map((contentBox) -> {
            projetListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            projetListe.setSpacing(10);
        });
    }
    @FXML
    private void retourne(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/InterfaceProjet.fxml"));
        Parent root = loader.load();
        Scene scene = tfretourne.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
}
    
}
