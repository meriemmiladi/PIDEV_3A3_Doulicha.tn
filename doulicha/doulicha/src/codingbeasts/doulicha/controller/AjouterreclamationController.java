/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;


import codingbeasts.doulicha.entities.MySession;
import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.entities.reclamation;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterreclamationController implements Initializable {

    @FXML
    private Button idafficherreclamation;
    @FXML
    private TextField idcontecureclamation;
    @FXML
    private Button idajouterreclamation;
    private TextField idetat;
    @FXML
    private ComboBox<String> idnomutilisateur;
    @FXML
    private Button idajouterreclamation1;
    @FXML
    private Button btn4;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    
    }    

    @FXML
    private void handleAjouterReclamation(ActionEvent event) throws IOException {

        Utilisateur user = MySession.getLoggedInUser();
        int loggedInUserId = user.getID_user();
        System.out.println("Utilisateur connecté : " + loggedInUserId);

                        
    String contenuReclamation = idcontecureclamation.getText();
    if (contenuReclamation.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Le contenu de la réclamation ne peut pas être vide !");
        alert.showAndWait();
        return;
    }


    
    try {
        Connection cnx = MyConnection.getInstance().getCnx();
        

        
        // Insérer la nouvelle réclamation dans la table 'reclamation'
        String queryInsert = "INSERT INTO reclamation (id_user, contenu_reclamation, etat_reclamation, date_reclamation) VALUES (?, ?, 0, ?)";
        PreparedStatement pstInsert = cnx.prepareStatement(queryInsert);
        pstInsert.setInt(1, loggedInUserId);
        pstInsert.setString(2, contenuReclamation);
        pstInsert.setDate(3, new java.sql.Date(new java.util.Date().getTime())); // Utiliser la date actuelle
        pstInsert.executeUpdate();
        
        System.out.println("La réclamation a été ajoutée avec succès !");
        
        // Charger la nouvelle vue
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficherreclamation.fxml"));
        Parent root = loader.load();

        // Obtenir la scène courante et la remplacer par la nouvelle scène
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    }
        private int id_reclamation;
        public void setreclamation(reclamation reclamation) {
        if (reclamation != null) {
            // affecter les propriétés de l'objet projet aux champs de texte correspondants dans la vue d'ajout/modification de projet

            idcontecureclamation.setText(reclamation.getContenu_reclamation());
        this.id_reclamation = reclamation.getID_reclamation();



        }
    }

    @FXML
    private void handleAfficherReclamation(ActionEvent event) {
          try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/afficherreclamation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
               ex.getStackTrace();
    }
        /*try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficherreclamation.fxml"));
        Parent root = loader.load();
        Scene scene = idafficherreclamation.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) idafficherreclamation.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }*/
    }

    @FXML
    private void savereclamation2(ActionEvent event) throws IOException {
    String contenuReclamation = idcontecureclamation.getText();
    
    try {
        Connection cnx = MyConnection.getInstance().getCnx();
        
        // Mettre à jour la réclamation dans la table 'reclamation'
        String queryUpdate = "UPDATE reclamation SET contenu_reclamation = ? WHERE id_reclamation = ?";
        PreparedStatement pstUpdate = cnx.prepareStatement(queryUpdate);
        pstUpdate.setString(1, contenuReclamation);
        pstUpdate.setInt(2, id_reclamation);
        pstUpdate.executeUpdate();
        
        System.out.println("La réclamation a été mise à jour avec succès !");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficherreclamation.fxml"));
        Parent root = loader.load();

        // Obtenir la scène courante et la remplacer par la nouvelle scène
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    @FXML
    private void action4(ActionEvent event) {
          try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AccueilFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
               ex.getStackTrace();
    }
                                               /* try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/Dashboard.fxml"));
        Parent root = loader.load();
        Scene scene = btn4.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn4.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }*/
    }

    
}
