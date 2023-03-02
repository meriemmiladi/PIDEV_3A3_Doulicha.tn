/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.services.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UpdateUtilisateurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtpassword;
    
    @FXML
    private ChoiceBox<String> statusChoiceBox;
    
    private  String[] choixstatus = {"actif","inactif"};

    int id;
    String U_nom;
    String U_Prenom;
    String U_email;
    String mot_de_pass;

    @FXML
    void miseajour(ActionEvent event) {

        String nom = txtnom.getText();
        String prenom = txtprenom.getText();
        String email = txtemail.getText();
        String mot_passe = txtpassword.getText();
       String statut = statusChoiceBox.getValue();

// Vérifier que tous les champs sont remplis
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        Utilisateur u = new Utilisateur(id, nom, prenom, email, mot_passe, "Utilisateur",statut);
        UtilisateurCRUD uc = new UtilisateurCRUD();
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Modifier l'utilisateur ?");
        alert.setContentText("Êtes-vous sûr(e) de vouloir modifier cet utilisateur ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // code pour mettre à jour l'utilisateur
            // ...
              uc.modifierutilisateur(u);
            // afficher une alerte de succès
            Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
            alertSuccess.setTitle("Succès");
            alertSuccess.setHeaderText(null);
            alertSuccess.setContentText("L'utilisateur a été modifié avec succès !");
            alertSuccess.showAndWait();
        } else {
            // l'utilisateur a annulé l'opération
            // ...
        }
       

        txtnom.clear();
        txtprenom.clear();
        txtemail.clear();
        txtpassword.clear();

      
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Modification réussie");
//        alert.setHeaderText(null);
//        alert.setContentText("L'utilisateur a été modifié avec succès.");
//        alert.showAndWait();
//        String nom = txtnom.getText();
//        String Prenom = txtprenom.getText();
//        String email = txtemail.getText();
//        String mot_passe = txtpassword.getText();
//
//        Utilisateur u = new Utilisateur(id, nom, Prenom, email, mot_passe, "Utilisateur");
//        UtilisateurCRUD uc = new UtilisateurCRUD();
//        uc.modifierutilisateur(u);
////        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
////        stage.close();
//        txtnom.clear();
//        txtprenom.clear();
//        txtemail.clear();
//        txtpassword.clear();
//
////                
//        System.out.println("Utilisateur Update");
    }

    public void setData(int id, String nom, String prenom, String email, String mot_de_pass) {
        this.id = id;
        this.U_nom = nom;
        this.U_Prenom = prenom;
        this.U_email = email;
        this.mot_de_pass = mot_de_pass;

        txtnom.setText(nom);
        txtprenom.setText(prenom);
        txtemail.setText(email);
        txtpassword.setText(mot_de_pass);

        System.out.println("nom:" + nom + "prenom:" + prenom + "email:" + email + "mot de paasse:" + mot_de_pass);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          statusChoiceBox.getItems().addAll(choixstatus);
        // TODO
    }

    @FXML
    void retourhome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AffichageUser.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root, 300, 250);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("AffichageUser");
            stage.resizableProperty().setValue(false);
            stage.show();

//        primaryStage.setTitle("Inscription");
//        primaryStage.setScene(scene);
//        primaryStage.setMaximized(true);
//      
//        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
