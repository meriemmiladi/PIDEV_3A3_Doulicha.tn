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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Affichage3Controller implements Initializable {
    
    @FXML
    private VBox projetListe;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProjets();
        searchButton.setOnAction(this::rechercherProjet);
        
    }
    private void rechercherProjet(ActionEvent event) {
        try {
            String searchTerm = searchField.getText().trim();
            projetCRUD dis = new projetCRUD();
            List<projet> myList = dis.rechercherProjet(searchTerm);
            projetListe.getChildren().clear();
            myList.stream().map((projet projet) -> {
                VBox contentBox = createContentBox(projet);
                return contentBox;
            }).forEachOrdered((contentBox) -> {
                projetListe.getChildren().add(contentBox);
            });
        } catch (SQLException ex) {
            Logger.getLogger(Affichage3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private VBox createContentBox(projet projet) {
        VBox contentBox = new VBox();
        Label nomLabel = new Label(projet.getNom_projet());
        nomLabel.setStyle("-fx-font-weight: bold;");
        Label descriptionLabel = new Label(projet.getDescription_projet());
        Label objectifLabel = new Label("Objectif : " + projet.getObjectif_projet());
        Label etatLabel = new Label("État : " + projet.getEtat_projet());
        Label imageView = new Label(projet.getImage_projet());
        Button replyButton = new Button("supprimer");
        replyButton.setOnAction((ActionEvent event) -> {
            try {
                projetCRUD dis = new projetCRUD();
                dis.deleteprojet(projet.getId_projet());
                projetListe.getChildren().remove(contentBox);
            } catch (SQLException ex) {
                Logger.getLogger(Affichage3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button modifierButton = new Button("Modifier");
        modifierButton.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceProjet.fxml"));
                Parent root = loader.load();
                InterfaceProjetController controller = loader.getController();
                controller.setProjet(projet);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Affichage3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        contentBox.getChildren().addAll(nomLabel, descriptionLabel, objectifLabel, etatLabel, imageView, replyButton, modifierButton);
        contentBox.setSpacing(10);
        contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
        return contentBox;
        
    }
    
    private void afficherProjets() {
        projetCRUD dis = new projetCRUD();
        List<projet> myList = dis.afficherprojet();
        myList.stream().map((projet projet) -> {
            VBox contentBox = createContentBox(projet);
            return contentBox;
        }).forEachOrdered((contentBox) -> {
            projetListe.getChildren().add(contentBox);
        });
        projetListe.setSpacing(10);
    }
}

