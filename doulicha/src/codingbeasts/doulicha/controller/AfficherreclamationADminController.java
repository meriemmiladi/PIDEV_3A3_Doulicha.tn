/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.reclamation;
import codingbeasts.doulicha.services.serviceEmail;
import codingbeasts.doulicha.services.serviceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherreclamationADminController implements Initializable {

    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox reclamationListe;
    @FXML
    private Button btn4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serviceReclamation dis = new serviceReclamation();
        List<reclamation> reclamations = dis.afficherreclamation();
        System.out.println(reclamations);

        // boucle pour ajouter chaque projet à la VBox
        reclamations.stream().map((reclamation reclamation) -> {
            VBox contentBox = new VBox();
            // créer un Label pour afficher le contenu de la réclamation
            TextArea contenuReclamation = new TextArea(reclamation.getContenu_reclamation());
            contenuReclamation.setEditable(false);
            contenuReclamation.setWrapText(true);
            contenuReclamation.setStyle("-fx-font-weight: bold;");

            // créer un Label pour afficher l'état de la réclamation
            Label etatReclamation = new Label("État de la réclamation : " + reclamation.getEtat_reclamation());
            System.out.println(etatReclamation);

            // créer un Label pour afficher la date de la réclamation
            Label dateReclamation = new Label("Date de la réclamation : " + reclamation.getDate_reclamation());

int idrec = reclamation.getID_reclamation();

Button modifierButton = new Button();
// Initialiser le bouton avec l'état actuel de la réclamation
if (reclamation.getEtat_reclamation() == 1) {
    modifierButton.setText("Résolue");
    modifierButton.setDisable(true); // désactiver le bouton si la réclamation est résolue
} else {
    modifierButton.setText("Envoyer email");
}

modifierButton.setOnAction((ActionEvent event) -> {
    // mettre à jour l'état de la réclamation
    int newEtat = (reclamation.getEtat_reclamation() == 1) ? 0 : 1;
    reclamation.setEtat_reclamation(newEtat);
    dis.modifierreclamation2(reclamation);
    String recipientt = dis.getEmailByReclamationId(idrec); // adresse e-mail du destinataire
    System.out.println("l'adresse est " +recipientt);

    // envoyer un e-mail si l'état de la réclamation était 0
    if (reclamation.getEtat_reclamation() == 1) {
        String recipient = dis.getEmailByReclamationId(idrec); // adresse e-mail du destinataire
        System.out.println(recipient);
        String content = "Réclamation résolue"; // contenu du message
        String subject = "Nous sommes heureux de vous informer que votre réclamation a été résolue avec succès. Nous avons pris en compte votre demande et avons travaillé pour trouver une solution qui répond à vos besoins."; // sujet du message
        serviceEmail diss = new serviceEmail();
        diss.sendMail(recipient, content, subject);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Envoi de mail");
        alert.setHeaderText("Le mail a été envoyé avec succès !");
        alert.setContentText("Le destinataire a été notifié de la résolution de sa réclamation.");
        alert.showAndWait();
    }

    // changer le texte du bouton en conséquence et le désactiver si la réclamation est résolue
    if (reclamation.getEtat_reclamation() == 1) {
        modifierButton.setText("Résolue");
        modifierButton.setDisable(true);
    } else {
        modifierButton.setText("Envoyer email");
    }

    reclamationListe.getChildren().clear();
    initialize(url, rb);
});

            // ajouter les Labels et l'ImageView à la VBox
            contentBox.getChildren().addAll(contenuReclamation,etatReclamation,dateReclamation, modifierButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            reclamationListe.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            reclamationListe.setSpacing(10);
        });
    }  

    @FXML
    private void action4(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/AffichageUser.fxml"));
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
