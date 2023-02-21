/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Logement;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class LogementUserController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox LogementBox;
    
    @FXML
    private Button home;
    @FXML
    private Button consulter_logement;
    @FXML
    private Button consulter_reservations;
    String xamppFolderPath = "C:/xampp/htdocs/img/";

  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    home.setOnAction( event->{
        try{
            System.out.println("Bouton home appuyé");
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
        
    consulter_reservations.setOnAction( event->{
        try{
            System.out.println("Bouton consulter_reservations appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/ConsulterMesReservations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
    
    
    consulter_logement.setOnAction( event->{
        try{
            System.out.println("Bouton consulter_logement appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/LogementUser.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
        
        ServiceLogement sl = new ServiceLogement();
        List<Logement> logements = sl.afficherLogement();

        logements.stream().map((Logement logement) -> {
            VBox contentBox = new VBox();
            Label nomLabel = new Label(logement.getNom_logement());
            nomLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            Label descriptionLabel = new Label("Description: "+ logement.getDescription_logement());
            descriptionLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            Label adresseLabel = new Label("Adresse: "+ logement.getAdresse_logement());
            adresseLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            Label typeLabel = new Label(logement.getType_logement());
            typeLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            Label etatLabel = new Label() ;
            Button reserverButton = new Button("Réserver") ;
            if(logement.getEtat_logement()== 0)
            {
                int ID_logement=logement.getID_logement();
                String nom_logement= logement.getNom_logement();
                String s = "Disponible";
                etatLabel = new Label ("Disponibilité: " +s);
                etatLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
                 //reserverButton = new Button("Réserver");
            
                 
                 
//***********    RESERVATION D'UN LOGEMENT *****************
                            reserverButton.setOnAction((ActionEvent event) -> {
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/ReserverLogement.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ReserverLogementController ReserverLogementController = loader.getController();
                            ReserverLogementController.setUpdate(true);
                            ReserverLogementController.recupererID(ID_logement);
                            //ReserverLogementController.recupererNom(nom_logement);
                            
                                //ServL.supprimerLogement(Logement.getID_logement());
                            //ReserverLogementController.setTextField(ID_logement);
                            Parent parent = loader.getRoot();
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show(); 
              
              
              
              
         
            });
            }
             if(logement.getEtat_logement()== 1)
            {
                String s = "Réservé";
                 etatLabel = new Label ("Disponibilité: "+ s);
                 etatLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
                 reserverButton.setDisable(true);
            }
        
            Label prixNuiteeLabel = new Label("Prix de la nuitée: " + Double.toString(logement.getPrixNuitee_logement())+ " DT/Nuitée");
            prixNuiteeLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            Label capaciteLabel = new Label("Capacité: "+ Integer.toString(logement.getCapacite_logement())+ " personnes");
            capaciteLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            Label imageLabel = new Label(logement.getImage_logement());
            imageLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            ImageView imageLog = new ImageView();
            
            
            String test = xamppFolderPath + logement.getImage_logement();
    try {
            BufferedImage bufferedImage = ImageIO.read(new File(test));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
           // this.image_detail.setImage(image);
             imageLog = new ImageView(image);
             imageLog.setPreserveRatio(true);
             imageLog.setFitWidth(100); 
             imageLog.setFitHeight(100); 
        } catch (IOException ex) {
            System.out.println("could not get the image");
        } 
            
            
            contentBox.getChildren().addAll(imageLog,nomLabel, typeLabel,descriptionLabel, adresseLabel,prixNuiteeLabel, capaciteLabel, etatLabel,reserverButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #BFDCE4  ; -fx-border-color: #BFDCE4 ; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            LogementBox.getChildren().add(contentBox);
            return contentBox;
        })  .forEachOrdered((_item) -> {
            LogementBox.setSpacing(10);
        });

        scrollPane.setFitToWidth(false);
        /**
         * newDiscussionButton.setOnAction(event -> { // Ajouter le code de
         * création d'une nouvelle discussion ici });
         */
    }
    
}