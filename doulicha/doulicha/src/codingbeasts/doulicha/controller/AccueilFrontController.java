/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void gererCompte(ActionEvent event) {
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

   
    
}
