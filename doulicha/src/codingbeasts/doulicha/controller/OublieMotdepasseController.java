/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.Email;
import codingbeasts.doulicha.entities.PasswordUtils;
import codingbeasts.doulicha.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class OublieMotdepasseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtemaill;

    @FXML
    void buttonemail(ActionEvent event) {
        
           Connection cnx2;
        cnx2 = MyConnection.getInstance().getCnx();
        
         String email = txtemaill.getText();
         
        String requete = "SELECT mdp_user FROM Utilisateur WHERE email_user = ?";
        try {
            PreparedStatement statement = cnx2.prepareStatement(requete);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String mdp = resultSet.getString("mdp_user");
                String message = "Votre mot de passe est : " + PasswordUtils.decrypt(mdp);
                Email emailsend = new Email("skanderbedwi1@gmail.com", "vaqyvsdjobpxpudo", email, "Récupération de mot de passe", message);
                emailsend.sendEmail();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Récupération de mot de passe");
                alert.setHeaderText("Mot de passe envoyé");
                alert.setContentText("Votre mot de passe a été envoyé à l'adresse e-mail " + email);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Récupération de mot de passe");
                alert.setHeaderText("E-mail invalide");
                alert.setContentText("L'adresse e-mail " + email + " n'est pas associée à un compte utilisateur.");
                alert.showAndWait();
            }
        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Récupération de mot de passe");
            alert.setHeaderText("Erreur lors de l'envoi de l'e-mail");
            alert.setContentText("Une erreur est survenue lors de l'envoi de l'e-mail. Veuillez réessayer plus tard.");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
