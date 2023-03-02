/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.categorie_avis;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AffichercategorieclientController implements Initializable {

    @FXML
    private ScrollPane scrollpane1;
    @FXML
    private VBox categorieListe;
    private int ID_categorie;
    @FXML
    private Button btnafficher;
    @FXML
    private Button btn4;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    serviceCategorie dis = new serviceCategorie();
        List<categorie_avis> categories = dis.affichercategorie();

        // boucle pour ajouter chaque projet à la VBox
        categories.stream().map((categorie_avis categorieavis) -> {
            VBox contentBox = new VBox();
            // créer un Label pour afficher le nom du projet
            Label namecategorie = new Label(categorieavis.getNom_categorie());
            namecategorie.setStyle("-fx-font-weight: bold;");
            
            // Créer une barre de progression pour afficher la moyenne des notes de la catégorie correspondante
            double moyenneNotes = new serviceAvis().calculerMoyenneNotesParCategorie(categorieavis.getID_categorie());
            ProgressBar moyenneBar = new ProgressBar(moyenneNotes / 10.0); // on divise par 10 pour avoir une valeur entre 0 et 1
            moyenneBar.setPrefWidth(200);
            moyenneBar.setStyle("-fx-accent: #3FC4ED;");// ajuster la largeur de la barre de progression


            // Créer un label pour afficher la valeur de la moyenne des notes
            Label moyenneLabel = new Label(String.format("%.1f", moyenneNotes));
            
            

 
            
            Button ajouteravisButton = new Button("Ajouter un avis");
            ajouteravisButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajouteravis.fxml"));
                    Parent root = loader.load();
                    AjouteravisController controller = loader.getController();
                    this.ID_categorie = categorieavis.getID_categorie();
                    controller.recupererID(ID_categorie);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            Button afficherAvisButton = new Button("Afficher les avis");
            afficherAvisButton.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficheravis.fxml"));
                    Parent root = loader.load();
                    AfficheravisController controller = loader.getController();
                    controller.afficherAvisParCategorie(categorieavis.getID_categorie());
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.showAndWait();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(namecategorie,moyenneBar,ajouteravisButton,afficherAvisButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            categorieListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            categorieListe.setSpacing(10);
        });
    }  

    @FXML
    private void afficheravis(ActionEvent event) {
                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficheravis.fxml"));
        Parent root = loader.load();
        AfficheravisController controller = loader.getController();
        controller.afficherAvis();
        Scene scene = btnafficher.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btnafficher.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void action4(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/Dashboard.fxml"));
        Parent root = loader.load();
        Scene scene = btn4.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn4.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    private void afficherHistogramme(ActionEvent event) {
            try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/histogramme.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    } catch (IOException ex) {
        Logger.getLogger(HistogrammeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
