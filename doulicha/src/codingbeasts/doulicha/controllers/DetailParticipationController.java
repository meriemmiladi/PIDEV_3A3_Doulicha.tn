/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.entities.participation_evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import codingbeasts.doulicha.services.ServiceParticipationEvenement;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DetailParticipationController implements Initializable {

    private participation_evenement part;
    private evenement event;
    
    ServiceEvenement SE = new ServiceEvenement();
      
    private MyListener2 myListener2;
    private Label idpart_detail;
    @FXML
    private Label nbpart_detail;
    @FXML
    private Label datepart_detail;
    @FXML
    private Button btn_modifierpart;
    @FXML
    private Label id_event_part;
    @FXML
    private Label id_participationM;
    @FXML
    private Label iduser_detail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_modifierpart.setOnAction(event -> {

           /* try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/gererParticipation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }*/
           
            participation_evenement part= new participation_evenement();
            
           FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/gererParticipation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EvenementClientController.class.getName()).log(Level.SEVERE, null, ex);
                            }
          GererParticipationController HomeScene = loader.getController();
          System.out.println("aaaaa");
          HomeScene.selected_item3(Integer.parseInt(id_participationM.getText()),Integer.parseInt(iduser_detail.getText()), Integer.parseInt(id_event_part.getText()),java.sql.Date.valueOf(datepart_detail.getText()), Integer.parseInt(nbpart_detail.getText()));
           GererParticipationController GererParticipationController = loader.getController();
                            GererParticipationController.setUpdate(true);
                          // AjoutParticipationController.recupererID(ID_event);
                            
//                           AjoutParticipationController.setTextField(ev.getID_event());
          
          
          System.out.println(part.getID_participation());
          
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) btn_modifierpart.getScene().getWindow();
            Parent parent = loader.getRoot();
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
        }); 
       
        
        
        // TODO
    }    
    
    public void setData2(participation_evenement part, MyListener2 myListener2, ServiceEvenement SE) {
        this.part =part;
        this.myListener2 = myListener2;
       // this.event = event;
        this.SE=SE;
        
       // this.id_event_part.setText(SE.getNom(part.getID_event()));
       this.id_event_part.setText(String.valueOf(part.getID_event()));
        this.nbpart_detail.setText(String.valueOf(part.getNombre_participation()));
        this.datepart_detail.setText(String.valueOf(part.getDate_participation()));
       this.iduser_detail.setText(String.valueOf(1));
        this.id_participationM .setText(String.valueOf(part.getID_participation()));
        
    }  

    @FXML
    private void gererPart(ActionEvent event) {
        
       /* participation_evenement part= new participation_evenement();
            
           FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/gererParticipation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EvenementClientController.class.getName()).log(Level.SEVERE, null, ex);
                            }
          GererParticipationController HomeScene = loader.getController();
          System.out.println("aaaaa");
          HomeScene.selected_item3(Integer.parseInt(id_event_part.getText()),java.sql.Date.valueOf(datepart_detail.getText()), Integer.parseInt(nbpart_detail.getText()));
           GererParticipationController GererParticipationController = loader.getController();
                            GererParticipationController.setUpdate(true);
                          // AjoutParticipationController.recupererID(ID_event);
                            
//                           AjoutParticipationController.setTextField(ev.getID_event());
          
          
          System.out.println(part.getID_participation());
          
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) btn_modifierpart.getScene().getWindow();
            Parent parent = loader.getRoot();
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
    }*/


    

    }    
}
