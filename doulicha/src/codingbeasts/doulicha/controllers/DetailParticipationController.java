/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.interfaces.MyListener2;
import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.entities.participation_evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import codingbeasts.doulicha.services.ServiceParticipationEvenement;
import java.awt.Desktop;
import java.io.File;
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
    private Label id_event_part;
    @FXML
    private Label id_participationM;
    @FXML
    private Label iduser_detail;
    @FXML
    private Label id_participation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void setData2(participation_evenement part, MyListener2 myListener2, ServiceEvenement SE) {
        this.part =part;
        this.myListener2 = myListener2;
       // this.event = event;
        this.SE=SE;
        
        this.id_event_part.setText(SE.getNom(part.getID_event()));
      this.id_participation.setText(String.valueOf(part.getID_event()));
        this.nbpart_detail.setText(String.valueOf(part.getNombre_participation()));
       this.iduser_detail.setText(String.valueOf(part.getID_user()));
        this.id_participationM .setText(String.valueOf(part.getID_participation()));
        
    }  

    

    @FXML
    private void gererPart(MouseEvent event) {
        
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
          HomeScene.selected_item3(Integer.parseInt(id_participationM.getText()),Integer.parseInt(iduser_detail.getText()), Integer.parseInt(id_participation.getText()), Integer.parseInt(nbpart_detail.getText()));
           GererParticipationController GererParticipationController = loader.getController();
                            GererParticipationController.setUpdate(true);
                          // AjoutParticipationController.recupererID(ID_event);
                            
//                           AjoutParticipationController.setTextField(ev.getID_event());
          
          
          System.out.println(part.getID_participation());
          
                 System.out.println("bbbbbbbbbbbbb");
           
//            Stage window = (Stage) btn_modifierpart.getScene().getWindow();
            Parent parent = loader.getRoot();
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
        
    }
    
    

    @FXML
    private void afficherPass(MouseEvent event) {
        
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir afficher votre PASS ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            
            // Chemin vers le fichier PDF
        String filePath = "C:/xampp/htdocs/myservice/pdfs/participation_" + id_participationM.getText() + ".pdf";
            System.out.println(filePath);
        
        // Créer un objet File à partir du chemin
        File file = new File(filePath);
        
        // Vérifier si le fichier existe et peut être lu
        if (file.exists() && file.canRead()) {
            // Ouvrir le fichier PDF avec l'application par défaut
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Le fichier PDF n'existe pas ou ne peut pas être lu.");
        } 
            
            
            
            
            
        }
    }

    @FXML
    private void supprimerPart(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setContentText("Etes vous sur de supprimer cet évènement ?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
        ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
            
         SPE.supprimerParticipation(SPE.getId2(id_participationM.getText()));
            
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
    
    
    
}
