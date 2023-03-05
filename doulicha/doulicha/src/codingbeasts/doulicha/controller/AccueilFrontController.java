/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.Commande;
import codingbeasts.doulicha.entities.MySession;
import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.services.CommandeCrud;
import codingbeasts.doulicha.services.LigneCommandeCrud;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class AccueilFrontController implements Initializable {

    @FXML
    private Pane gestion;
    @FXML
    private Button utilisateur;
    @FXML
    private Button logements;
    @FXML
    private Button mesreservations;
    @FXML
    private Button btnavis;
    @FXML
    private Button btnreclamation;
    @FXML
    private Button deconnexion;
    @FXML
    private Button logements1;
    @FXML
    private Button mesreservations1;
    @FXML
    private Button commander;
    @FXML
    private Button DiscussionButton;
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
    private void gererCompte(ActionEvent event) {
         try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ShowUserCompte.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
    }


    @FXML
    private void consulter_logements(ActionEvent event) {
         try{
             System.out.println("Bouton logements appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource( "/codingbeasts/doulicha/view/LogementUser.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
       
    }

    @FXML
    private void mesreservations(ActionEvent event) {
         try{
            System.out.println("Bouton reservations appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ConsulterMesReservations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
               ex.getStackTrace();
    }
    }

    @FXML
    private void avis(ActionEvent event) {
        try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/affichercategorieclient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
    }

    @FXML
    private void reclamation(ActionEvent event) {
        try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ajouterreclamation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
    }

    @FXML
    private void deconnexion(ActionEvent event) {
          try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/SginIn.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
    }

    @FXML
    private void consulter_evenements(ActionEvent event) {
        
         try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/EvenementClient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
        
    }

    @FXML
    private void mesparticipations(ActionEvent event) {
        
        try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ParticipationClient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
    }

    private void commander(ActionEvent event) {
        
        try{
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
//                CommandeCrud commandeCrud=new CommandeCrud();
//            LigneCommandeCrud ligneCommandeCrud=new LigneCommandeCrud();
//            Commande commande=commandeCrud.retreiveOneOrder(20);
//            LocalDateTime dateTime = LocalDateTime.now();
//            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());
//                Commande c=new Commande(sqlDate,0);
//                CommandeCrud cc=new CommandeCrud();
//                 Utilisateur user = MySession.getLoggedInUser();
//        int loggedInUserId = user.getID_user();
//
//        c.setID_user(loggedInUserId);
//                commande=cc.ajouterCommande2(c,loggedInUserId);
//                int id_commande = commande.getID_commande();
        }
        catch (IOException ex) {
            System.out.println(ex);
    
    }
    
}

    @FXML
    private void comProd(ActionEvent event) throws IOException {
        try{
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
            
                CommandeCrud commandeCrud=new CommandeCrud();
            LigneCommandeCrud ligneCommandeCrud=new LigneCommandeCrud();
            Commande commande=commandeCrud.retreiveOneOrder(20);
            LocalDateTime dateTime = LocalDateTime.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());
                Commande c=new Commande(sqlDate,0);
                CommandeCrud cc=new CommandeCrud();
                 Utilisateur user = MySession.getLoggedInUser();
        int loggedInUserId = user.getID_user();

                c.setID_user(loggedInUserId);
                commande=cc.ajouterCommande2(c,loggedInUserId);
                int id_commande = commande.getID_commande();
        }
        catch (LoadException ex) {
            System.out.println(ex);
    
    }
    }
    @FXML
    private void discussion(ActionEvent event) {
        
        try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/DiscussionUser.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
    }

    @FXML
    private void projet1(ActionEvent event) {
                try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/AffichageProjetUser.fxml"));
        Parent root = loader.load();
        Scene scene = projet1.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
}