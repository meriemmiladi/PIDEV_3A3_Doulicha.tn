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
public class AfficheravisController implements Initializable {

    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox avisListe;
    private serviceCategorie serviceCategorie = new serviceCategorie();
    @FXML
    private Button btnaffichercategorie;
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

            // créer une ImageView pour afficher l'image du projet
            Label note = new Label("note : " +avis.getNote_avis());
            
            Label contenu = new Label(avis.getContenu_avis());
            
            Label type = new Label(avis.getType_avis());
            
            Button replyButton = new Button("supprimer");
            replyButton.setOnAction((ActionEvent event) -> {
                dis.deleteavis(avis.getID_avis()); // appel de la méthode deleteprojet avec l'id du projet
                avisListe.getChildren().remove(contentBox); // supprime le contenu du projet de la VBox
                 
               
            });
            // créer un bouton pour modifier le projet
            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction((ActionEvent event) -> {
                try {
                    // charger la vue AjouterProjet.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajouteravis.fxml"));
                    Parent root;
                
                    root = loader.load();
               
                    AjouteravisController controller = loader.getController();

                    // pré-remplir les champs avec les valeurs du projet sélectionné
                    controller.setavis(avis);

                    // afficher la vue AjouterProjet.fxml
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AfficheravisController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(nomcategorie,iduser,idevent,idlogement,note,contenu,type, replyButton,modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            avisListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            avisListe.setSpacing(10);
        });
    }

    @FXML
    private void affichercategorie(ActionEvent event) {
                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/affichercategorieclient.fxml"));
        Parent root = loader.load();
        Scene scene = btnaffichercategorie.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btnaffichercategorie.getScene().getWindow();
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
