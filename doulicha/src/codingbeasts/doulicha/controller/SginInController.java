/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class SginInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Parent fxml;

    @FXML
    private TextField txtnom;

    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button btnopenhome;

    @FXML
    private Button btnSignIn;

    @FXML
    void onsave(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/SignUp.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root, 300, 250);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
//        Stage home = (Stage)((Node) event.getSource()).getScene().getWindow();
//         home.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void openhome(ActionEvent event) {
 Connection cnx2;
        cnx2 = MyConnection.getInstance().getCnx();
        String nom = txtnom.getText();
        //  String pass = txtpassword.getText();
        String pass = hashMotDePasse(txtpassword.getText());
        if (nom.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
        } else {
            String query = "SELECT * FROM Utilisateur WHERE nom_user = ? AND mdp_user = ?";

            try {
                PreparedStatement statement = cnx2.prepareStatement(query);
                statement.setString(1, nom);
                statement.setString(2, pass);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    // L'utilisateur existe, vérifier le statut
                    String statut = rs.getString("status_user");
                    if (statut.equals("inactif")) {
                        // L'utilisateur est désactivé
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de connexion");
                        alert.setHeaderText(null);
                        alert.setContentText("Votre compte a été désactivé par l'administrateur. Veuillez contacter l'administrateur pour plus d'informations.");
                        alert.showAndWait();
                    } else {
                        // L'utilisateur est actif, vérifier le rôle
                        String role = rs.getString("role_user");
                        if (role.equals("Admin")) {
                            // L'utilisateur a le rôle d'administrateur, ouvrir la fenêtre d'accueil
                            System.out.println("bien!");
                            Stage home = new Stage();
                            try {
                                fxml = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AffichageUser.fxml"));
                                Scene scene = new Scene(fxml);
                                
                                home.setScene(scene);
                                home.show();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (role.equals("Utilisateur")) {
                            // L'utilisateur a le rôle d'utilisateur, ouvrir la fenêtre d'accueil utilisateur
                            Stage home = new Stage();
                            try {
                                fxml = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/Dashboard.fxml"));
                                Scene scene = new Scene(fxml);
                                home.setScene(scene);
                                home.show();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
//else {
//                        // L'utilisateur n'a pas le rôle d'administrateur
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Erreur de connexion");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Vous n'êtes pas autorisé à accéder à cette page.");
//                        alert.showAndWait();
//                    }
                    }
                } else {
                    // L'utilisateur n'existe pas ou le mot de passe est incorrect
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setHeaderText(null);
                    alert.setContentText("Nom d'utilisateur ou mot de passe incorrect.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String hashMotDePasse(String motDePasse) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(motDePasse.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
