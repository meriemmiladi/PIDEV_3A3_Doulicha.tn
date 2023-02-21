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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Affichage3Controller implements Initializable {

    @FXML
    private VBox projetListe;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // code pour initialiser le contrôleur

        // récupérer la liste de projets à partir de la base de données avec la méthode afficherprojet()

        projetCRUD dis = new projetCRUD();
        List<projet> projets = dis.afficherprojet();

        // boucle pour ajouter chaque projet à la VBox
        projets.stream().map((projet projet) -> {
            VBox contentBox = new VBox();
            // créer un Label pour afficher le nom du projet
            Label nomLabel = new Label(projet.getNom_projet());
            nomLabel.setStyle("-fx-font-weight: bold;");

            // créer un Label pour afficher la description du projet
            Label descriptionLabel = new Label(projet.getDescription_projet());

            // créer un Label pour afficher l'objectif du projet
            Label objectifLabel = new Label("Objectif : " + projet.getObjectif_projet());

            // créer un Label pour afficher l'état du projet
            Label etatLabel = new Label("État : " + projet.getEtat_projet());

            // créer une ImageView pour afficher l'image du projet
            Label imageView = new Label(projet.getImage_projet());
            
            Button replyButton = new Button("supprimer");
            replyButton.setOnAction((ActionEvent event) -> {
                try {
                    dis.deleteprojet(projet.getId_projet()); // appel de la méthode deleteprojet avec l'id du projet
                    projetListe.getChildren().remove(contentBox); // supprime le contenu du projet de la VBox
                } catch (SQLException ex) {
                    Logger.getLogger(Affichage3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
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
                    Logger.getLogger(Affichage3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(nomLabel, descriptionLabel, objectifLabel, etatLabel, imageView,replyButton,modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            projetListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            projetListe.setSpacing(10);
        });
    }
}
