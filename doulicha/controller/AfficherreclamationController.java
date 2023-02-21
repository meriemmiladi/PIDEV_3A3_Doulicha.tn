/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.reclamation;
import codingbeasts.doulicha.services.serviceReclamation;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherreclamationController implements Initializable {

    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox reclamationListe;
    @FXML
    private Button ajouterrec;
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
            // créer un Label pour afficher le nom du projet
            Label contenureclamation = new Label("contenu reclamation : " + reclamation.getContenu_reclamation());
            contenureclamation.setStyle("-fx-font-weight: bold;");

            // créer un Label pour afficher la description du projet
            Label  etatreclamation = new Label("etat reclamation: " + reclamation.getEtat_reclamation());
            System.out.println(etatreclamation);

            // créer un Label pour afficher l'objectif du projet
            Label datereclamation = new Label("date reclamation : " + reclamation.getDate_reclamation());

            
            Button replyButton = new Button("supprimer");
            replyButton.setOnAction((ActionEvent event) -> {
                dis.deletereclamation(reclamation.getID_reclamation()); // appel de la méthode deleteprojet avec l'id du projet
                reclamationListe.getChildren().remove(contentBox); // supprime le contenu du projet de la VBox
                 
               
            });
            // créer un bouton pour modifier le projet
            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction((ActionEvent event) -> {
                try {
                    // charger la vue AjouterProjet.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajouterreclamation.fxml"));
                    Parent root;
                
                    root = loader.load();
               
                    AjouterreclamationController controller = loader.getController();

                    // pré-remplir les champs avec les valeurs du projet sélectionné
                    controller.setreclamation(reclamation);

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AfficherreclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(contenureclamation,etatreclamation,datereclamation, replyButton,modifierButton);
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
    private void ajouterreclamation(ActionEvent event) {
                        try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajouterreclamation.fxml"));
        Parent root = loader.load();
        Scene scene = ajouterrec.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) ajouterrec.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void action4(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/Pageuser.fxml"));
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
