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
public class ListAffichageDonController implements Initializable {

    @FXML
    private VBox VBox1;
    @FXML
    private HBox HBox1;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox donListe;
    @FXML
    private Button tfretourne3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        donCRUD dis = new donCRUD();
        List<don> dons = dis.afficherdon();

        // boucle pour ajouter chaque projet à la VBox
        dons.stream().map((don don) -> {
            VBox contentBox = new VBox();
            // créer un Label pour afficher le nom du projet
            Label valeurLabel = new Label("valeur  : " + don.getValeur_don());
            valeurLabel.setStyle("-fx-font-weight: bold;");

            // créer un Label pour afficher la description du projet
            Label dateLabel = new Label("date  : " + don.getDate_don());

            // créer un Label pour afficher l'objectif du projet
            
            
            
            Button replyButton = new Button("supprimer");
            replyButton.setOnAction((ActionEvent event) -> {
                try {
                    dis.deletedon(don.getID_don()); 
                    donListe.getChildren().remove(contentBox); // supprime le contenu du projet de la VBox
                } catch (SQLException ex) {
                    Logger.getLogger(ListAffichageDonController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction((ActionEvent event) -> {
                try {
                    // charger la vue AjouterProjet.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDon.fxml"));
                    Parent root = loader.load();
                    AjouterDonController controller = loader.getController();

                    // pré-remplir les champs avec les valeurs du projet sélectionné
                    controller.setdon(don);

                    // afficher la vue AjouterProjet.fxml
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            contentBox.getChildren().addAll(valeurLabel, dateLabel, replyButton,modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            contentBox.getStyleClass().add("vbox-style");
            replyButton.getStyleClass().add("button-style");
            modifierButton.getStyleClass().addAll("button-style", "modify");
            return contentBox;
        }).map((contentBox) -> {
            donListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            donListe.setSpacing(10);
        });
    }

    @FXML
    private void retourne3(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/AffichageProjetUser.fxml"));
        Parent root = loader.load();
        Scene scene = tfretourne3.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
                }

             
    
