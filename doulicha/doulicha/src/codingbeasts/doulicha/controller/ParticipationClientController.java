/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.MySession;
import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.services.MyListener2;
import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.entities.participation_evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import codingbeasts.doulicha.services.ServiceParticipationEvenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ParticipationClientController implements Initializable {
    
     List<participation_evenement> part;
    private List<participation_evenement> parts = new ArrayList<>();
    private MyListener2 myListener2;
    ServiceEvenement SE = new ServiceEvenement();
    
   // int userConnecte = 2;
    
    @FXML
    private GridPane participation_grid;
    @FXML
    private Button btn_evenements;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          btn_evenements.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/evenementClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
          
           participation_grid.getChildren().clear();
        ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
        
        Utilisateur user = MySession.getLoggedInUser();
        int loggedInUserId = user.getID_user();
        System.out.println("Utilisateur connecté : " + loggedInUserId);
      

        List<participation_evenement> pr = SPE.afficherParticipations(loggedInUserId);
        
        
        System.out.println("participation " + pr.toString());
        int row = 1, cl =0;
            try{
                for(participation_evenement part : pr ){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/codingbeasts/doulicha/view/detailParticipation.fxml"));
                    
                    Node postbox = loader.load();
                    System.out.println("TEST PART");
                    DetailParticipationController partc = loader.getController();
                    partc.setData2(part, myListener2, SE);
                   
                    if(cl== 1){
                         cl= 0;
                         row++;
                    }
                    this.participation_grid.add(postbox, cl++, row);
                    GridPane.setMargin(postbox,new Insets(10));
                }
            }catch(IOException e){
                System.out.println("no load for event");
                   e.printStackTrace();
            }
      
    }

     
    
}
