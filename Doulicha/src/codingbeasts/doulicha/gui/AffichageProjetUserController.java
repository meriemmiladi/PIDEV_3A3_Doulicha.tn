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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AffichageProjetUserController implements Initializable {

    @FXML
    private VBox VBox1;
    @FXML
    private HBox HBox1;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox projetListe;
    private int ID_projet;

    /**
     * Initializes the controller class.
     */
    private int id_projet;
    @FXML
    private Button tfaffichage2;
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
        Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private VBox createContentBox(projet projet) {
     
        try {
            FileInputStream inputstream = null;
            VBox contentBox = new VBox();
            
            ImageView imageView = new ImageView();
            inputstream = new FileInputStream(projet.getImage_projet());
            Image imageS = new Image(inputstream);
            imageView.getStyleClass().add("imageView");
            imageView.setFitWidth(200);
            imageView.setFitHeight(120);
            imageView.setTranslateX(120);
            imageView.setImage(imageS);
            
            Label nomLabel = new Label("Nom_projet :" + projet.getNom_projet());
            nomLabel.getStyleClass().add("nomLabel");
            nomLabel.setStyle("-fx-font-weight: bold;");
            Label descriptionLabel = new Label("Description :" + projet.getDescription_projet());
            descriptionLabel.getStyleClass().add("descriptionLabel");
            Label obj = new Label("Objectif");
            obj.getStyleClass().add("objectifLabel");
            obj.getStyleClass().add("nomLabel");
            Label objectifLabel = new Label(obj.getText()+ ":" + projet.getObjectif_projet());
            
            
            Button savedon = new Button("Ajouterdon");
            savedon.setStyle("-fx-background-color: #3FC4ED;\n" +
                               "    -fx-text-fill: #FFFFFF;\n" +
                               "    -fx-border-color: #FFFFFF;\n" +
                               "    -fx-border-width: 2px;\n" +
                               "    -fx-border-radius : 5px;\n" +
                               "    -fx-padding: 10px;\n" +
                               "    -fx-border-insets: 10px;\n" +
                               "    -fx-background-insets: 10px;");
            savedon.setOnAction((ActionEvent event) -> {
                try {
                    // charger la vue AjouterProjet.fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDon.fxml"));
                    Parent root = loader.load();
                    AjouterDonController AjouterDonController = loader.getController();
                    
                    //AjouterDonController controller = loader.getController();
                    this.id_projet = projet.getId_projet();
                    AjouterDonController.recupererID(id_projet);

                    System.out.println(id_projet);
                    // afficher la vue AjouterProjet.fxml
                    
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    scene.getStylesheets().add("affichageprojetuser.css");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AjouterDonController.class.getName()).log(Level.SEVERE, null, ex);
                }
                donCRUD rep = new donCRUD();
               
            });
            VBox butonsBox = new VBox();
            butonsBox.getChildren().addAll(nomLabel,descriptionLabel, objectifLabel);
            butonsBox.setStyle(" -fx-padding: 40px 10; -fx-line-spacing: 30px;");
            HBox buttonsBox = new HBox();
            buttonsBox.getChildren().addAll(butonsBox, imageView);
            
            
            contentBox.getChildren().addAll( buttonsBox,  savedon);
            contentBox.setSpacing(10);
            contentBox.getStyleClass().add("vbox-style");
            return contentBox;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AffichageProjetUserController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void affichage2(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/ListAffichageDon.fxml"));
        Parent root = loader.load();
        Scene scene = tfaffichage2.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

   
}
