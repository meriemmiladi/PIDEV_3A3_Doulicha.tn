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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ListAffichageDonController implements Initializable {

    @FXML
    private VBox VBox1;
    @FXML
    private HBox HBox1;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox donListe;
    @FXML
    private Button tfretourne3;
    @FXML
    private Label sommeDonsLabel;
    private donCRUD dis;


    /**
     * Initializes the controller class.
     */
   public double calculerSommeDons() {
    donCRUD dis = new donCRUD();
    List<don> dons = dis.afficherdon();
    double somme = 0;
    for (don d : dons) {
        somme += d.getValeur_don();
        System.out.println(somme);
    }
    return somme;
}

@Override
public void initialize(URL url, ResourceBundle rb) {
    donCRUD dis = new donCRUD();
    List<don> dons = dis.afficherdon();
    double somme = calculerSommeDons();
    sommeDonsLabel.setText("Total des dons " + somme + "£");
    sommeDonsLabel.setStyle("-fx-font-weight: bold;");
    for (don d : dons) {
        VBox contentBox = new VBox();
        Label valeurLabel = new Label("Valeur : " + d.getValeur_don() + "€");
        valeurLabel.setStyle("-fx-font-weight: bold;");
        Label dateLabel = new Label("Date : " + d.getDate_don());
        Button replyButton = new Button("Supprimer");
        replyButton.getStyleClass().add("replyButton");
        replyButton.setOnAction((ActionEvent event) -> {
            try {
                dis.deletedon(d.getID_don()); 
                donListe.getChildren().remove(contentBox); 
            } catch (SQLException ex) {
                Logger.getLogger(ListAffichageDonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button modifierButton = new Button("Modifier");
        modifierButton.getStyleClass().add("modifierButton");
        modifierButton.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDon.fxml"));
                Parent root = loader.load();
                AjouterDonController controller = loader.getController();
                controller.setdon(d);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                scene.getStylesheets().add("listaffichagedon.css");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Affichage2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(replyButton, modifierButton);
        contentBox.getChildren().addAll(valeurLabel, dateLabel,buttonsBox);
        contentBox.setSpacing(10);
        contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
        contentBox.getStyleClass().add("vbox-style");
        replyButton.getStyleClass().add("button-style");
        modifierButton.getStyleClass().addAll("button-style", "modify");
        donListe.getChildren().add(contentBox);
    }
    donListe.setSpacing(10);
}


    @FXML
    private void retourne3(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/AffichageProjetUser.fxml"));
        Parent root = loader.load();
        Scene scene = tfretourne3.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
                }

             
    
