/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class AccueilBackController implements Initializable {

    @FXML
    private Pane gestion;
    @FXML
    private Button utilisateur;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button gestionlogement;
    @FXML
    private Button consulter_reservation;
    @FXML
    private Pane design;
    @FXML
    private Label countLabel;
    @FXML
    private Button deconnexion;
    @FXML
    private Button gestionEvenement;
    @FXML
    private Button consulter_participation;
    @FXML
    private Button produit;
    @FXML
    private Button consulter_DiscussionsReponses;
    @FXML
    private Button projet1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void action1(ActionEvent event) {

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ajoutercategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        /*  try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajoutercategorie.fxml"));
        Parent root = loader.load();
        Scene scene = btn1.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn1.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }*/
    }

    @FXML
    private void action2(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/afficheravisadmin.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }

        /*   try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficheravisadmin.fxml"));
        Parent root = loader.load();
        Scene scene = btn2.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn2.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }*/
    }

    @FXML
    private void action3(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/afficherreclamationADmin.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        /*                           try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficherreclamationADmin.fxml"));
        
        Parent root = loader.load();
        Scene scene = btn3.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn3.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }*/
    }

    @FXML
    private void btnuser(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AffichageUser.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        /*try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/AffichageUser.fxml"));
        
        Parent root = loader.load();
        Scene scene = btn3.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn3.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }*/
    }

    @FXML
    private void gestionLogements(ActionEvent event) {
        try {
            System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AfficherLogement.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    private void consulter_reservation(ActionEvent event) {
        try {
            System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AfficherReservation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    private void deconnexion(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/SginIn.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    private void gestionEvenements(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AfficherEvenements.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    private void consulter_participations(ActionEvent event) {

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AfficherParticipations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    @FXML
    private void gestionproduit(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/accprod.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void gestiondiscussion(ActionEvent event) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/GestionDiscussionReponse.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getStackTrace();
        }

    }

    @FXML
    private void projet1(ActionEvent event) {
                        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/InterfaceProjet.fxml"));
        Parent root = loader.load();
        Scene scene = projet1.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
}
