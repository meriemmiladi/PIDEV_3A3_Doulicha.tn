/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.projetCRUD;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
        try {
            FileInputStream inputstream = null;
            VBox contentBox = new VBox();
      
            ImageView imageView = new ImageView();
           
            Image imageS = new Image(new URL("http://localhost"+projet.getImage_projet()).toString());
            //Image imageS = new Image(new URL(projet.getImage_projet()).toString());
            
            imageView.getStyleClass().add("imageView");
            imageView.setFitWidth(200);
            imageView.setFitHeight(100);
            imageView.setTranslateX(100);
            imageView.setImage(imageS);
            TextFlow nomLabel = new TextFlow();
            nomLabel.getChildren().add(new Text("Projet : " + projet.getNom_projet()));
            nomLabel.getStyleClass().add("nomLabel");
            TextFlow descriptionLabel = new TextFlow();
            Text descriptionPrefix = new Text("Description :: ");
            descriptionPrefix.getStyleClass().add("description-prefix");
            descriptionPrefix.setStyle("-fx-fill: blue;");
            descriptionLabel.getChildren().addAll(descriptionPrefix, new Text(projet.getDescription_projet()));
            descriptionLabel.getStyleClass().add("description-label");
            descriptionLabel.getStyleClass().add("descriptionLabel");
            Text obj = new Text("Objectif");
            obj.setStyle("-fx-fill: blue;");
            obj.getStyleClass().add("objectifLabel");
            obj.getStyleClass().add("nomLabel");
            Label objectifLabel = new Label(obj.getText()+ "::" + projet.getObjectif_projet());
            Button replyButton = new Button("supprimer");
            replyButton.getStyleClass().add("replyButton");
            replyButton.setOnAction((ActionEvent event) -> {
                // Vérifier si un projet est sélectionné avant de supprimer
                if (projetListe.getChildren().contains(contentBox)) {
                    // Demander confirmation avant de supprimer
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation de suppression");
                    alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce projet ?");
                    alert.setContentText(projet.getNom_projet());
                    
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        projetCRUD dis = new projetCRUD();
                        try {
                            dis.deleteprojet(projet.getId_projet()); // appel de la méthode deleteprojet avec l'id du projet
                            projetListe.getChildren().remove(contentBox); // supprime le contenu du projet de la VBox
                        } catch (SQLException ex) {
                            Logger.getLogger(Affichage3Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    // Afficher un message d'erreur si aucun projet n'est sélectionné
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Aucun projet sélectionné");
                    alert.setContentText("Veuillez sélectionner un projet avant de cliquer sur le bouton Supprimer.");
                    
                    alert.showAndWait();
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
            VBox butonsBox = new VBox();
            butonsBox.getChildren().addAll(nomLabel,descriptionLabel, objectifLabel);
            butonsBox.setStyle(" -fx-line-spacing: 30px;");
            HBox buttonsBox = new HBox();
            buttonsBox.getChildren().addAll(butonsBox, imageView);
            buttonsBox.setStyle("-fx-padding: 10px 120px 10px 10px");
            HBox butttonsBox = new HBox();
            butttonsBox.getChildren().addAll(replyButton, modifierButton);
            butttonsBox.setStyle("-fx-padding: 10px 10px 10px 100px");
            contentBox.getChildren().addAll( buttonsBox, butttonsBox);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
            /*try {
            inputstream.close();
            } catch (IOException ex) {
            Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
            */
        } catch (MalformedURLException ex) {
            Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }     
    return null;
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
