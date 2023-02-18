/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class GererEvenementController implements Initializable {
    
        
         ObservableList<String>types = FXCollections.observableArrayList("art","culture","fêtes","nature");

    @FXML
    private TextField TF_nomM;
    @FXML
    private TextField TF_lieuM;
    @FXML
    private TextField TF_capaciteM;
    @FXML
    private TextField TF_prixM;
    @FXML
    private TextField TF_descriptionM;
    @FXML
    private TextField TF_imageM;
    @FXML
    private TextField TF_nombreactuelM;
    @FXML
    private DatePicker dateDebutM;
    @FXML
    private DatePicker dateFinM;
    @FXML
    private ComboBox<String> comboTypeM;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TextField TF_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTypeM.setItems(types);
        
        
    }    

    @FXML
    private void modifierEvenement(ActionEvent event) {
         
         ServiceEvenement SE = new ServiceEvenement();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setContentText("Etes vous sur de vouloir modifier cet évènement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            evenement ev = new evenement();
            
            
            
            ev.setID_event(SE.getId(TF_id.getText()));
            ev.setNom_event(TF_nomM.getText());
            ev.setDescription_event(TF_descriptionM.getText());
            ev.setLieu_event(TF_lieuM.getText());
            ev.setType_event(comboTypeM.getValue());
            ev.setCapacite_event(Integer.parseInt(TF_capaciteM.getText()));
            ev.setImage_event(TF_imageM.getText());
            ev.setPrix_event(Double.parseDouble(TF_prixM.getText()));
            ev.setNombreActuel_event(Integer.parseInt(TF_nombreactuelM.getText()));
            LocalDate dateDebut_local = dateDebutM.getValue();
            LocalDate dateFin_local = dateFinM.getValue();
            ev.setDateDebut_event(java.sql.Date.valueOf(dateDebut_local));
            ev.setDateDebut_event(java.sql.Date.valueOf(dateFin_local));
     
    
            
           
            //System.out.println(tf_desc.getText());
            
//            System.out.println(d);
            SE.modifierEvenement(ev);
            Notifications notificationBuilder = Notifications.create()
            .title("Modification EVENEMENT")
               .text("votre évènement a bien été modifié.")
               .graphic(null)
               .hideAfter(Duration.seconds(5))
              .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
        }else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherEvenements.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setContentText("Etes vous sur de supprimer cette promotion?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceEvenement SE = new ServiceEvenement();
            
         SE.supprimerEvenement(SE.getId(TF_id.getText()));
            
            
      
        } else {
            System.out.println("Cancel");
        }
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/zero/views/Promotion.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

void selected_item(int ID_event, String nom_event, String description_event, String lieu_event, String type_event, Date dateDebut_event, Date dateFin_event, int capacite_event, int nombreActuel_event, String image_event, double prix_event) {
//       ServicePromotion P = new ServicePromotion();
//System.out.println(id+nompromotion+description+dateP);

        LocalDate dateDebut_local = dateDebut_event.toLocalDate();
    LocalDate dateFin_local = dateFin_event.toLocalDate();

    TF_id.setText(String.valueOf(ID_event));
    TF_descriptionM.setText(description_event);
    TF_nomM.setText(nom_event);
    TF_lieuM.setText(lieu_event);
    comboTypeM.setValue(type_event);
    TF_imageM.setText(image_event);
    TF_capaciteM.setText(String.valueOf(capacite_event));
    TF_prixM.setText(String.valueOf(prix_event));
    TF_nombreactuelM.setText(String.valueOf(nombreActuel_event));
    dateDebutM.setValue(dateDebut_local);
    dateFinM.setValue(dateFin_local);
               
        
//        P.setRemise(Integer.parseInt(com_remise.getValue()));

//       p.setDateP(Date.valueOf(d));
    } 
    
    
    
    
    
    
    
    
}
