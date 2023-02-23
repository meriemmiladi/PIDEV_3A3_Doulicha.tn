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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtnom_user;

    @FXML
    private TextField txtprenom_user;

    @FXML
    private TextField txtemail_user;

    @FXML
    private PasswordField txtmdp_user;

    @FXML
    private Button btnajouter;

    @FXML
    void retouresignin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/SginIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root, 300, 250);
            Scene scene = new Scene(root);
            stage.setScene(scene);
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

    @FXML
    void ajouter(ActionEvent event) {
        String nom_user = txtnom_user.getText();
        String prenom_user = txtprenom_user.getText();
        String email_user = txtemail_user.getText();
        String mdp_user = txtmdp_user.getText();
        // String role_user = txtrole_user.getText();

        Utilisateur p = new Utilisateur(nom_user, prenom_user, email_user, mdp_user);

        UtilisateurCRUD pc = new UtilisateurCRUD();
        pc.ajouterUtlisateur3(p);
        txtnom_user.clear();
        txtprenom_user.clear();
        txtemail_user.clear();
        txtmdp_user.clear();

        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
