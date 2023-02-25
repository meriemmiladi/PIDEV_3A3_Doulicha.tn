/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.reclamation;
import codingbeasts.doulicha.services.serviceReclamation;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherreclamationADminController implements Initializable {

    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox reclamationListe;
    @FXML
    private Button btn4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceReclamation dis = new serviceReclamation();
        List<reclamation> reclamations = dis.afficherreclamation();
        System.out.println(reclamations);

        // boucle pour ajouter chaque projet à la VBox
        reclamations.stream().map((reclamation reclamation) -> {
            VBox contentBox = new VBox();
            // créer un Label pour afficher le contenu de la réclamation
            Label contenuReclamation = new Label("Contenu de la réclamation : " + reclamation.getContenu_reclamation());
            contenuReclamation.setStyle("-fx-font-weight: bold;");

            // créer un Label pour afficher l'état de la réclamation
            Label etatReclamation = new Label("État de la réclamation : " + reclamation.getEtat_reclamation());
            System.out.println(etatReclamation);

            // créer un Label pour afficher la date de la réclamation
            Label dateReclamation = new Label("Date de la réclamation : " + reclamation.getDate_reclamation());



            Button modifierButton = new Button();
            // Initialiser le bouton avec l'état actuel de la réclamation
            if (reclamation.getEtat_reclamation() == 1) {
                modifierButton.setText("En attente");
            } else {
                modifierButton.setText("Résolue");
            }

            modifierButton.setOnAction((ActionEvent event) -> {
                // mettre à jour l'état de la réclamation
                int newEtat = (reclamation.getEtat_reclamation() == 1) ? 0 : 1;
                reclamation.setEtat_reclamation(newEtat);
                dis.modifierreclamation2(reclamation);

                // changer le texte du bouton en conséquence
                if (reclamation.getEtat_reclamation() == 1) {
                    modifierButton.setText("En attente");
                } else {
                    modifierButton.setText("Résolue");
                }
                reclamationListe.getChildren().clear();
                initialize(url, rb);
            });

            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(contenuReclamation,etatReclamation,dateReclamation, modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            reclamationListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            reclamationListe.setSpacing(10);
        });
    }  

    @FXML
    private void action4(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/Pageadmin.fxml"));
        Parent root = loader.load();
        Scene scene = btn4.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn4.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }


    
}
