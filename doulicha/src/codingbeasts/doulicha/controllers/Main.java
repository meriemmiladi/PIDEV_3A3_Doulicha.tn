/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/evenementClient.fxml"));
           //Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherEvenements.fxml"));
           //Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherParticipations.fxml"));
            
            //Scene scene = new Scene(root, 700, 400);
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Ajout Evènement");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
