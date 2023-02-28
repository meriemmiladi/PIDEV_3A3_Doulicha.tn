/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.avis;
import codingbeasts.doulicha.services.serviceAvis;
import codingbeasts.doulicha.services.serviceCategorie;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficheravisadminController implements Initializable {

    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox avisListe;
    private serviceCategorie serviceCategorie = new serviceCategorie();
    @FXML
    private Button btn4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    serviceAvis dis = new serviceAvis();
        List<avis> aviss = dis.afficheravis();

        // boucle pour ajouter chaque projet à la VBox
        aviss.stream().map((avis avis) -> {
            VBox contentBox = new VBox();
            // créer un Label pour afficher le nom du projet
            Label nomcategorie = new Label("Nom de la catégorie : " + serviceCategorie.getNomCategorieById(avis.getID_categorie()));

            nomcategorie.setStyle("-fx-font-weight: bold;");

            // créer un Label pour afficher la description du projet
            Label  iduser = new Label("user: " + serviceCategorie.getNomuserById(avis.getID_user()));

            // créer un Label pour afficher l'objectif du projet
            Label idevent = new Label("event: " + serviceCategorie.getNomeventById(avis.getID_event()));

            // créer un Label pour afficher l'état du projet
            Label idlogement = new Label("logement: " + serviceCategorie.getNomlogementById(avis.getID_logement()));

            HBox ratingBox = new HBox(); // créer une boîte pour les cercles de notation
            ratingBox.setSpacing(5);
            ratingBox.setAlignment(Pos.CENTER);

            int noteAvis = avis.getNote_avis(); // obtenir la note de l'avis
                
            // créer cinq cercles de notation
            for (int i = 0; i < 5; i++) {
                Circle circle = new Circle(10); // créer un cercle avec un rayon de 10 pixels
                if (i < noteAvis) { // remplir les cercles selon la note de l'avis
                    circle.setFill(Color.web("#3FC4ED"));
                } else {
                    circle.setFill(Color.LIGHTGRAY);
                }
                ratingBox.getChildren().add(circle); // ajouter le cercle à la boîte
            }
            
            TextArea contenu = new TextArea(avis.getContenu_avis());

            contenu.setEditable(false);
            contenu.setWrapText(true);
            
            Label type = new Label("cet avis concerne un " +avis.getType_avis());
            
            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(nomcategorie,iduser,idevent,idlogement,ratingBox,contenu,type);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 1px; -fx-border-radius: 1px;");
            contentBox.setAlignment(Pos.CENTER);
            return contentBox;
        }).map((contentBox) -> {
            avisListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            avisListe.setSpacing(10);
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
