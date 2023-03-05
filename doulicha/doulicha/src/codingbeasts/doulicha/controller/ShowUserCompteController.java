/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.MySession;
import codingbeasts.doulicha.entities.PasswordUtils;
import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShowUserCompteController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField txtpassword;
    @FXML
    private Button tfmodcompte;
private Parent fxml;

    Connection cnx2;

    Statement ste;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Récupérer les informations de l'utilisateur connecté
        Utilisateur loggedInUser = MySession.getLoggedInUser();

        // Afficher les informations de l'utilisateur dans les champs du formulaire
        nomField.setText(loggedInUser.getNom_user());
        prenomField.setText(loggedInUser.getPrenom_user());
        emailField.setText(loggedInUser.getEmail_user());

        // Déchiffrer le mot de passe de l'utilisateur
        String decryptedPassword = PasswordUtils.decrypt(loggedInUser.getMdp_user());
        txtpassword.setText(decryptedPassword);
        // TODO
    }    

    @FXML
    private void modcompte(ActionEvent event) {
        // Récupérer les informations de l'utilisateur connecté
        Utilisateur loggedInUser = MySession.getLoggedInUser();

        // Récupérer les nouvelles informations de l'utilisateur à partir des champs de texte
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String password = PasswordUtils.encrypt(txtpassword.getText());

        // Afficher un dialogue de confirmation avant de mettre à jour les informations de l'utilisateur
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment mettre à jour les informations de votre compte ?");
        alert.setContentText("Appuyez sur OK pour confirmer la mise à jour ou sur Annuler pour annuler.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Mettre à jour les informations de l'utilisateur dans la base de données
                cnx2 = MyConnection.getInstance().getCnx();
                String query = "UPDATE utilisateur SET nom_user=?, prenom_user=?, email_user=?, mdp_user=? WHERE ID_user=?";
                PreparedStatement stmt = cnx2.prepareStatement(query);
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setString(3, email);
                stmt.setString(4, password);
                stmt.setInt(5, loggedInUser.getID_user());
                stmt.executeUpdate();
                stmt.close();
                cnx2.close();

                // Mettre à jour les informations de l'utilisateur dans la session
                loggedInUser.setNom_user(nom);
                loggedInUser.setPrenom_user(prenom);
                loggedInUser.setEmail_user(email);
                loggedInUser.setMdp_user(password);

                // Afficher un message de confirmation
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Modification réussie");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Les informations de votre compte ont été modifiées avec succès.");
                successAlert.showAndWait();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Afficher un message d'erreur
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Erreur");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Une erreur s'est produite lors de la modification de votre compte.");
                errorAlert.showAndWait();
            }

            nomField.clear();
            prenomField.clear();
            emailField.clear();
            txtpassword.clear();
        }
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/AccueilFront.fxml"));
        Parent root = loader.load();
        Scene scene = tfmodcompte.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
    
}
