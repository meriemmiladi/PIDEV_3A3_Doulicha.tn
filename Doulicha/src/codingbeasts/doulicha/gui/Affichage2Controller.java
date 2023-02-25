/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.projetCRUD;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Affichage2Controller implements Initializable {

    @FXML
    private VBox VBox1;
    @FXML
    private HBox HBox1;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox projetListe;
    @FXML
    private Button searchButton;
    @FXML
    private Button tfretourne;
    @FXML
    private TextField searchField;
    

    /**
     * Initializes the controller class.
     */
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
            Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private VBox createContentBox(projet projet) {
        VBox contentBox = new VBox();
        Label nomLabel = new Label(projet.getNom_projet());
       nomLabel.getStyleClass().add("nomLabel");
        Label descriptionLabel = new Label(projet.getDescription_projet());
        descriptionLabel.getStyleClass().add("descriptionLabel");
        Label obj = new Label("Objectif");
            obj.getStyleClass().add("objectifLabel");
            obj.getStyleClass().add("nomLabel");
            Label objectifLabel = new Label(obj.getText()+ ":" + projet.getObjectif_projet());
        
        
        
        Label imageView = new Label(projet.getImage_projet());
        imageView.getStyleClass().add("imageView");
        
        Button replyButton = new Button("supprimer");
        replyButton.getStyleClass().add("replyButton");
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
        modifierButton.getStyleClass().add("modifierButton");
        modifierButton.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceProjet.fxml"));
                Parent root = loader.load();
                InterfaceProjetController controller = loader.getController();
                controller.setProjet(projet);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                scene.getStylesheets().add("Affichage2.css");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Affichage3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(replyButton, modifierButton);
        buttonsBox.getStyleClass().add("buttonsBox");
        contentBox.getChildren().addAll(nomLabel, descriptionLabel, objectifLabel,imageView,buttonsBox);
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

    

    @FXML
    private void retourne(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/InterfaceProjet.fxml"));
        Parent root = loader.load();
        Scene scene = tfretourne.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
}
    
}
