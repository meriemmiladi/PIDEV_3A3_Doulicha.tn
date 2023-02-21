/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.don;
import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.donCRUD;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AffichageProjetUserController implements Initializable {

    @FXML
    private VBox VBox1;
    @FXML
    private HBox HBox1;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox projetListe;
    private int ID_projet;

    /**
     * Initializes the controller class.
     */
    private int id_projet;
    @FXML
    private Button tfaffichage2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            
            
            Button savedon = new Button("Ajouterdon");
            savedon.setOnAction((ActionEvent event) -> {
                try {
                    // charger la vue AjouterProjet.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDon.fxml"));
                    Parent root = loader.load();
                   AjouterDonController AjouterDonController = loader.getController();
                   
                    //AjouterDonController controller = loader.getController();
                    this.id_projet = projet.getId_projet();
                    AjouterDonController.recupererID(id_projet);

                    System.out.println(id_projet);
                    // afficher la vue AjouterProjet.fxml
                    
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AjouterDonController.class.getName()).log(Level.SEVERE, null, ex);
                }
                donCRUD rep = new donCRUD();
               
            });
            
            
            
        contentBox.getChildren().addAll(nomLabel, descriptionLabel, objectifLabel, etatLabel, imageView,savedon);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            contentBox.getStyleClass().add("vbox-style");
            savedon.getStyleClass().add("button-style");
            
            return contentBox;
        }).map((contentBox) -> {
            projetListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            projetListe.setSpacing(10);
        });
    }

    @FXML
    private void affichage2(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/ListAffichageDon.fxml"));
        Parent root = loader.load();
        Scene scene = tfaffichage2.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

   
}
