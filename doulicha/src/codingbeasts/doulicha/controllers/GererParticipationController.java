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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class GererParticipationController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private Button btnModifierPart;
    @FXML
    private Button btnSupprimerPart;
    @FXML
    private TextField TF_nombreM;
    @FXML
    private DatePicker datePM;
    @FXML
    private TextField id_partM;
    @FXML
    private TextField TF_idM;
    @FXML
    private TextField TF_iduserM;
    @FXML
    private ImageView icone_nb;
    @FXML
    private ImageView icone_date;
    @FXML
    private Button btn_events;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btn_events.setOnAction(event -> {

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
        // TODO
    }    

    @FXML
    private void modifierPart(ActionEvent event) {
        
         ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
         if(testSaisie()){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setContentText("Etes vous sur de vouloir modifier cet évènement ?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK){
            
            participation_evenement evp = new participation_evenement();
            evp.setID_participation(SPE.getId2(id_partM.getText()));
           
            evp.setID_event(Integer.parseInt(TF_idM.getText()));
            evp.setID_user(Integer.parseInt(TF_iduserM .getText()));
            evp.setNombre_participation(Integer.parseInt(TF_nombreM.getText()));
            LocalDate date_local = datePM.getValue();
            evp.setDate_participation(java.sql.Date.valueOf(date_local));
            SPE.modifierParticipationEvenement(evp);
         
            Notifications notificationBuilder = Notifications.create()
            .title("Modification EVENEMENT")
               .text("votre évènement a bien été modifié.")
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
            Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        }
    }
    
    private Boolean testSaisie() {
        String erreur = "";
      
        if (!testDatePart()) {
            erreur = erreur + ("Veuillez saisir une date valide \n");
        }
        return testDatePart();
    }
    private Boolean testDatePart() {
        LocalDate now = LocalDate.now();
        if ( datePM.getValue().compareTo(now) > 0) {
                icone_date.setImage(new Image("images/yes.png"));
                return true;
            } else {
                icone_date.setImage(new Image("images/no.png"));
            }
                return false;
    } 

    @FXML
    private void supprimerPart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setContentText("Etes vous sur de supprimer cet évènement ?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
        ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
            
         SPE.supprimerParticipation(SPE.getId2(id_partM.getText()));
            
            Notifications notificationBuilder = Notifications.create()
            .title("Suppression EVENEMENT")
               .text("votre évènement a bien été supprimé.")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
      
        } else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/participationClient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    void selected_item3(int ID_participation, int ID_user, int ID_event, Date date_participation, int nombre_participation) {

        LocalDate date_local = date_participation.toLocalDate();

   // TF_idM.setText(String.valueOf(ID_particiation));
    id_partM.setText(String.valueOf(ID_participation));
     TF_iduserM.setText(String.valueOf(ID_user));
    TF_idM.setText(String.valueOf(ID_event));
    TF_nombreM.setText(String.valueOf(nombre_participation));
    datePM.setValue(date_local);
   
    
   
    }
    
    private boolean update;
     void setUpdate(boolean b) {
        this.update = b;

    }
    
}
