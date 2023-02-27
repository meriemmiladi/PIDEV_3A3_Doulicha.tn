/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.avis;
import codingbeasts.doulicha.entities.translator;
import codingbeasts.doulicha.services.LanguageDetection;
import codingbeasts.doulicha.services.serviceAvis;
import codingbeasts.doulicha.services.serviceCategorie;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField searchField;
    
    


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

            
            Label contenu = new Label(avis.getContenu_avis());
            Button translateButton = new Button("Traduire");
            translateButton.setOnAction((ActionEvent event) -> {
            try {
                String originalText = contenu.getText();
                String detectedLang = LanguageDetection.detectLanguage(originalText);
                System.out.println(detectedLang);

                String targetLang = "";
                if (detectedLang.equals("fr")) {
                    targetLang = "en";
                } else if (detectedLang.equals("en")) {
                    targetLang = "fr";
                } else {
                    System.err.println("Unable to detect language of text: " + originalText);
                    return;
                }

                String translatedText = translate(detectedLang, targetLang, originalText);
                System.out.println("Original text: " + originalText);
                System.out.println("Detected language: " + detectedLang);
                System.out.println("Target language: " + targetLang);
                System.out.println("Translated text: " + translatedText);

                contenu.setText(translatedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
            });
            Label type = new Label("cet avis concerne un " +avis.getType_avis());
            
            Button replyButton = new Button("supprimer");
            replyButton.setOnAction((ActionEvent event) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");
                alert.setContentText("Appuyez sur OK pour confirmer.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    dis.deleteavis(avis.getID_avis());
                    avisListe.getChildren().remove(contentBox);
                } else {
                    // l'utilisateur a appuyé sur Annuler ou fermé la fenêtre de confirmation
                }
            });
            // créer un bouton pour modifier le projet
            Button modifierButton = new Button("Modifier");
            modifierButton.setOnAction((ActionEvent event) -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de modification");
                alert.setHeaderText("Voulez-vous vraiment modifier cet avis");
                alert.setContentText("Cette action peut affecter les données existantes !");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajouteravis.fxml"));
                    Parent root = loader.load();
                    AjouteravisController controller = loader.getController();
                    controller.setavis(avis);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException ex) {
                Logger.getLogger(AfficheravisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(nomcategorie,idevent,idlogement,ratingBox,contenu,translateButton ,type, replyButton,modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
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
    
        @FXML
    private void filtrer(ActionEvent event) {
        serviceAvis dis = new serviceAvis();
        List<avis> aviss = dis.afficheravis();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
    // Effacer la liste actuelle d'avis affichés
    avisListe.getChildren().clear();
    
    // Boucler à travers la liste des avis et ajouter les avis contenant le mot-clé
    for (avis avis : aviss) {
        String contenu = avis.getContenu_avis();
        String titre = serviceCategorie.getNomCategorieById(avis.getID_categorie());

        if (contenu.contains(newValue) || titre.contains(newValue)) {
            // Créer et ajouter le contenu de l'avis à la liste des avis
            VBox contentBox = createAvisContentBox(avis);
            avisListe.getChildren().add(contentBox);
        }
    }
});
    }
    
    
    
    private VBox createAvisContentBox(avis avis) {
        serviceAvis serviceAvis = new serviceAvis();
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

    Label contenu = new Label(avis.getContenu_avis());
            Button translateButton = new Button("Traduire");
        translateButton.setOnAction((ActionEvent event) -> {
            try {
                String originalText = contenu.getText();
                String translatedText = translate("en", "fr", originalText);
                System.out.println("Original text: " + originalText);
                System.out.println("Translated text: " + translatedText);

                contenu.setText(translatedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    Label type = new Label("cet avis concerne un " +avis.getType_avis());

    Button replyButton = new Button("supprimer");
    replyButton.setOnAction((ActionEvent event) -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");
        alert.setContentText("Appuyez sur OK pour confirmer.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            serviceAvis.deleteavis(avis.getID_avis());
            avisListe.getChildren().remove(contentBox);
        } else {
            // l'utilisateur a appuyé sur Annuler ou fermé la fenêtre de confirmation
        }
    });
    // créer un bouton pour modifier le projet
    Button modifierButton = new Button("Modifier");
    modifierButton.setOnAction((ActionEvent event) -> {
    try {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText("Voulez-vous vraiment modifier cet avis");
        alert.setContentText("Cette action peut affecter les données existantes !");
        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajouteravis.fxml"));
                    Parent root = loader.load();
                    AjouteravisController controller = loader.getController();
                    controller.setavis(avis);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException ex) {
                Logger.getLogger(AfficheravisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(nomcategorie,idevent,idlogement,ratingBox,contenu,translateButton,type, replyButton,modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            contentBox.setAlignment(Pos.CENTER);
            return contentBox;
       
        
        
    }
    public static String translate(String fromLang, String toLang, String text) throws Exception {
    String translatedText = "";

    try {
        // Appeler la méthode de traduction de l'API WhatsMate Translation
        translatedText = translator.translate(fromLang, toLang, text);
    } catch (Exception e) {
        // Gérer les erreurs de connexion à l'API
        e.printStackTrace();
    }

    return translatedText;
    }
    
    
    
}



