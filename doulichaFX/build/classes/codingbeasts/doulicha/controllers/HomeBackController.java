package codingbeasts.doulicha.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class HomeBackController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private AnchorPane HomeBack;
    @FXML
    private Button gestionlogement;
    @FXML
    private Button consulter_reservation;
    @FXML
    private Button homeFront;
    
  
    /**
     * Initializes the controller class.
     */
    
    /*@FXML
    private void gestionLogement (ActionEvent event) throws IOException{
     
             System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/MyView.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
       
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
     homeFront.setOnAction( event->{
        try{
            System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
     
     
     
    gestionlogement.setOnAction( event->{
        try{
            System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherLogement.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
    
    
     consulter_reservation.setOnAction( event->{
        try{
            System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherReservation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
    
        
    
        
    
        
    
        
    
        
    
        
    
        
    
    
    
    
    }
    
    
   

    
  
    
}
