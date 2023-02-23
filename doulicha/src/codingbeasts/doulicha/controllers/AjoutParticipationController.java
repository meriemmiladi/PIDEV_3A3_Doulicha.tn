/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.interfaces.MyListener;
import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.entities.participation_evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import codingbeasts.doulicha.services.ServiceParticipationEvenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjoutParticipationController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private TextField TF_nombre;
    private DatePicker dateP;
    
    
    private MyListener myListener;
    private evenement event;
    @FXML
    private TextField id_part;
    
    @FXML
    private Button btnParticiper;
    @FXML
    private ImageView icone_nb;
    @FXML
    private ImageView icone_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/evenementClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
       
    } 
    

    @FXML
    private void ajouterParticipation(ActionEvent event) {
        
        
    participation_evenement part = new participation_evenement();
   // if (testSaisie()){
    ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
     
    int nombre_participation = Integer.parseInt(TF_nombre.getText());
   // int ID_event = Integer.parseInt(id_part.getText());
    
     String textI = id_part.getText();
        int valueI=0;
        try {
             valueI = Integer.parseInt(textI);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation !");
            } 
        
    part.setNombre_participation(Integer.parseInt(TF_nombre.getText()));
   // part.setID_participation(Integer.parseInt(idparticipation.getText()));
    
   part.setID_event(valueI);
     part.setID_user(1);
    SPE.ajouterParticipationEvenement(part);
    

    Notifications notificationBuilder = Notifications.create()
            .title("Ajout PARTICIPATION")
            .text("Votre participation a bien été enregistrée.")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.showInformation();

    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/participationClient.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutParticipationController.class.getName()).log(Level.SEVERE, null, ex);
    }
   // }
    }
    
   /* private Boolean testSaisie() {
        String erreur = "";
      
        if (!testDatePart()) {
            erreur = erreur + ("Veuillez saisir une date valide \n");
        }
        return testDatePart();
    } */
    
   
    
    
    void selected_item2(int ID_event) {
    id_part.setText(String.valueOf(ID_event));
    //id_part.setText(String.valueOf(ID_participation));
    
    } 
    
   
     private boolean update;
     void setUpdate(boolean b) {
        this.update = b;

    }
     
      private int id;
     void recupererID(int id) {
        this.id = id;
       System.out.println("ID de l'événement sélectionné : " + id);

    }

  
}


 