/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Commande;
import codingbeasts.doulicha.entities.LigneCommande;
import codingbeasts.doulicha.services.CommandeCrud;
import codingbeasts.doulicha.services.LigneCommandeCrud;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AccueilController implements Initializable {

    @FXML
    private Button admin;
    @FXML
    private Button user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin.setOnAction(event ->{
           try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/accprod.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               System.out.println(ex); 
            }
       });
        user.setOnAction(event ->{
           try {
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
                commande=cc.ajouterCommande2(c, 1);
                int id_commande = commande.getID_commande();
                
            } catch (IOException ex) {
               System.out.println("errooooor"); 
               
            
            }
       });
    }    
    }    
    
