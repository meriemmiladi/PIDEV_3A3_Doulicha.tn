/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.categorie_avis;
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
public class AffichercategorieController implements Initializable {

    @FXML
    private ScrollPane scrollpane1;
    @FXML
    private VBox categorieListe;
    @FXML
    private Button btnajouterr;
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

 
            
            Button replyButton = new Button("supprimer");
            replyButton.setOnAction((ActionEvent event) -> {
                dis.deletecategorie(categorieavis.getID_categorie()); // appel de la méthode deleteprojet avec l'id du projet
                categorieListe.getChildren().remove(contentBox); // supprime le contenu du projet de la VBox
                 
               
            });
            // créer un bouton pour modifier le projet
            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction((ActionEvent event) -> {
                try {
                    // charger la vue AjouterProjet.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajoutercategorie.fxml"));
                    Parent root;
                
                    root = loader.load();
               
                    AjoutercategorieController controller = loader.getController();

                    // pré-remplir les champs avec les valeurs du projet sélectionné
                    controller.setcategorie(categorieavis);

                    // afficher la vue AjouterProjet.fxml
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AfficheravisController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(namecategorie, replyButton,modifierButton);
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
/*
    private void pageajouter(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajoutercategorie.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) idajouterpage.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
*/

    @FXML
    private void ajouterr(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajoutercategorie.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) btnajouterr.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
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
