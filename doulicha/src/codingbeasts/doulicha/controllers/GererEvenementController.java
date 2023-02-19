/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

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
    
    String xamppFolderPath = "C:/xampp/htdocs/images/";
    
    @FXML
    private Button btn_importer;
    @FXML
    private ImageView imageevenement;
    @FXML
    private ImageView iconeNom;
    @FXML
    private ImageView iconeLieu;
    @FXML
    private ImageView iconeCapacite;
    @FXML
    private ImageView iconecPrix;
    @FXML
    private ImageView iconeDescription;
    @FXML
    private ImageView iconeImage;
    @FXML
    private ImageView iconeType;
    @FXML
    private ImageView iconeDateDebut;
    @FXML
    private ImageView iconeDateFin;
    @FXML
    private ImageView iconeNombreActuel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTypeM.setItems(types);
        
         btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherEvenements.fxml"));
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
            ev.setDateFin_event(java.sql.Date.valueOf(dateFin_local));
            SE.modifierEvenement(ev);
            if (testSaisie()){
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
}

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setContentText("Etes vous sur de supprimer cet évènement ?");

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
        ServiceEvenement SE = new ServiceEvenement();
            
         SE.supprimerEvenement(SE.getId(TF_id.getText()));
            
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherEvenements.fxml"));
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
    
   // imageevenement.setImage(new Image (xamppFolderPath + image_event));
   System.out.println(xamppFolderPath + image_event);
   String test = xamppFolderPath + image_event;
   
  /* InputStream inputStream = getClass().getResourceAsStream(test);
Image image = new Image(inputStream);
imageevenement.setImage(image); */
   
    try {
            BufferedImage bufferedImage = ImageIO.read(new File(test));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageevenement.setImage(image);
        } catch (IOException ex) {
            System.out.println("could not get the image");
        } 
   
   /* File file = new File(test.replace('/' , '\\'));
        System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         
         this.imageevenement.setImage(im); */
 
//        P.setRemise(Integer.parseInt(com_remise.getValue()));

//       p.setDateP(Date.valueOf(d));
    } 

    @FXML
    private void importerImage(ActionEvent event) {
        
     FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file !");
       // fileChooser.setInitialDirectory(new File("\\Mehdi\\ESPRIT\\pi java\\zero\\src\\Images"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);

// Copier le fichier dans le dossier XAMPP
Path source = file.toPath();
String fileName = file.getName();
Path destination = Paths.get(xamppFolderPath + fileName);



        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                TF_imageM.setText(fileName);
                System.err.println("1");
                imageevenement.setImage(image);
                System.err.println("2");
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
        String imagePath = "images/" + fileName;
    }
    
      private Boolean testSaisie() {
        String erreur = "";
      
        if (!testNomEvent()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
       
          if (!testDateDebut()) {
            erreur = erreur + ("Veuillez saisir une date valide \n");
        }
          if (!testDateFin()) {
            erreur = erreur + ("Veuillez saisir une date valide \n");
        }
          if (!testLieuEvent()) {
            erreur = erreur + ("Veuillez verifier votre Lieu: seulement des caractères et de nombre >= 3 \n");
        }
          if (!testCapaciteEvent()) {
            erreur = erreur + ("Veuillez verifier votre Capacité: seulement des nombres >= 10 \n");
        }
          if (!testPrixEvent()) {
            erreur = erreur + ("Veuillez verifier votre Prix: seulement des nombres >= 10 \n");
        }
          if (!testImageEvent()) {
            erreur = erreur + ("Veuillez insérer votre Image \n");
        }
       /* if (!testDesc()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
         if (!testRemise()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        } */
        //return  testNomProm() || testDate() || testDesc() || testRemise() ;
        return  testNomEvent() && testDateDebut() && testLieuEvent() && testDateFin() && testCapaciteEvent() && testPrixEvent() && testImageEvent() ;
    }
    
    private Boolean testNomEvent() {
        int nbNonChar = 0;
        for (int i = 1; i < TF_nomM.getText().trim().length(); i++) {
            char ch = TF_nomM.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && TF_nomM.getText().trim().length() >= 3) {
            iconeNom.setImage(new Image("images/yes.png"));
            return true;
        } else {
            iconeNom.setImage(new Image("images/no.png"));
            return false;

        }
    }
    
     private Boolean testImageEvent() {
         int nbNonChar = 0;
        for (int i = 1; i < TF_imageM.getText().trim().length(); i++) {
            char ch = TF_imageM.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0) {
            iconeImage.setImage(new Image("images/yes.png"));
            return true;
        } else {
            iconeImage.setImage(new Image("images/no.png"));
            return false;

        }
    }
    
    private Boolean testPrixEvent() {
        if (Integer.parseInt(TF_prixM.getText()) >= 10.0) {
            iconecPrix.setImage(new Image("images/yes.png"));
            return true;
        } else {
            iconecPrix.setImage(new Image("images/no.png"));
            return false;

        }
    }
    
     private Boolean testCapaciteEvent() {
        
        if (Integer.parseInt(TF_capaciteM.getText()) >= 10) {
            iconeCapacite.setImage(new Image("images/yes.png"));
            return true;
        } else {
            iconeCapacite.setImage(new Image("images/no.png"));
            return false;

        }
    }
        private Boolean testLieuEvent() {
        int nbNonChar = 0;
        for (int i = 1; i < TF_lieuM.getText().trim().length(); i++) {
            char ch = TF_lieuM.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && TF_lieuM.getText().trim().length() >= 3) {
            iconeLieu.setImage(new Image("images/yes.png"));
            return true;
        } else {
            iconeLieu.setImage(new Image("images/no.png"));
            return false;

        }
    }
    
     private Boolean testDateDebut() {
        LocalDate now = LocalDate.now();
        if ( dateDebutM.getValue().compareTo(now) > 0) {
                iconeDateDebut.setImage(new Image("images/yes.png"));
                return true;
            } else {
                iconeDateDebut.setImage(new Image("images/no.png"));
            }
                return false;
    } 
     
      private Boolean testDateFin() {
        LocalDate now = LocalDate.now();
        if ((dateFinM.getValue().compareTo(now) > 0) && ((dateFinM.getValue().isAfter(dateDebutM.getValue())) || (dateFinM.getValue().isEqual(dateDebutM.getValue())))) {
                iconeDateFin.setImage(new Image("images/yes.png"));
                return true;
            } else {
                iconeDateFin.setImage(new Image("images/no.png"));
            }
                return false;
    } 
}
