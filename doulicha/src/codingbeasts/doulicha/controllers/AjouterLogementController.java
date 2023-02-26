package codingbeasts.doulicha.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.entities.Logement;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.stage.FileChooser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import java.net.URL;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * FXML Controller class
 *
 * @author marie
 */
public class AjouterLogementController implements Initializable {

    @FXML
    private TextField nom_logement;
    @FXML
    private TextField adresse_logement;
    @FXML
    private TextField prixNuitee_logement;
    @FXML
    private TextField capacite_logement;
    @FXML
    private ComboBox<String> type_logement;
    @FXML
    private Button ajouter;
    @FXML
    private Button annuler;
    @FXML
    private TextField description_logement;
    @FXML
    private Button ajouter_logement;
    @FXML
    private Button afficher_logement;
    @FXML
    private Button home;
    @FXML
    private TextField image_logement;
    @FXML
    private Button importer_image;
    
    String xamppFolderPath = "C:/xampp/htdocs/img/";
    @FXML
    private ImageView imageLog;
    @FXML
    private ImageView checknom;
    @FXML
    private ImageView checkadresse;
    @FXML
    private ImageView checkprixnuitee;
    @FXML
    private ImageView checkcapacite;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    type_logement.setItems(FXCollections.observableArrayList());
    type_logement.getItems().add("Villa");
    type_logement.getItems().add("Appartement");
    type_logement.getItems().add("Chambre privée");
    type_logement.getItems().add("Maison d'hôtes");

       /* ObservableList<String> listType = FXCollections.observableArrayList("Maison d'hôte", "Villa", "Appartement");

        type_logement.setItems(listType);
        */
       
         home.setOnAction( event->{
        try{
            System.out.println("home appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(AjouterLogementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
         
        afficher_logement.setOnAction( event->{
        try{
            System.out.println("home appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherLogement.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(AjouterLogementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
        
        annuler.setOnAction( event->{
        try{
            System.out.println("annuler appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(AjouterLogementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
        
        
        importer_image.setOnAction( event ->{
    
        
          FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file !");
        fileChooser.setInitialDirectory(new File("\\C:\\3A3\\doulichaFX\\images"));
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
                image_logement.setText(fileName);
                imageLog.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
        String imagePath = "images/" + fileName;
    });
         
         
         ajouter.setOnAction(event -> {        
        ServiceLogement sl = new ServiceLogement();
        if(testSaisie()){ 

        Logement l = new Logement();
        l.setNom_logement(nom_logement.getText());
        l.setDescription_logement(description_logement.getText());
        l.setAdresse_logement(adresse_logement.getText());
        String textP = prixNuitee_logement.getText();
        double valueP = Double.parseDouble(textP);
        l.setPrixNuitee_logement(valueP);
        String textC = capacite_logement.getText();
        try {
            int valueC = Integer.parseInt(textC);
            l.setCapacite_logement(valueC);
            } catch (NumberFormatException e) {
              System.out.println("erreur recuperation capacite!");
            }
       
        l.setImage_logement(image_logement.getText());
        l.setEtat_logement(0);
        l.setType_logement(type_logement.getValue());
      

        sl.ajouterLogement2(l);
        try{
        Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherLogement.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
        } catch(IOException ex)
        {
           Logger.getLogger(ModifierLogementController.class.getName()).log(Level.SEVERE, null, ex); 
        }
        //Resof.addReservationoffre(Res);
        Notifications notificationBuilder = Notifications.create()
                .title("AJOUT LOGEMENT")
                .text("Le logemenet a etait bien enregistrer  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
        }});
    } 
                 
                 
    
     void setTextField( String nom_logement,String description_logement,String adresse_logement,Double prixNuitee_logement,int capacite_logement,String type_logement,String image_logement) {

        this.nom_logement.setText(nom_logement);
        this.description_logement .setText(description_logement);
        this.adresse_logement .setText(adresse_logement);
        this.prixNuitee_logement.setText(Double.toString(prixNuitee_logement));
        this.capacite_logement.setText(Integer.toString(capacite_logement));
        //this.type_logement.setText(type_logement);
        this.image_logement.setText(image_logement);

    }
     private boolean update;
     void setUpdate(boolean b) {
        this.update = b;

    }
     
     
///////////////////////////         CONTROLE DE SAISIE             //////////////////////////////////////////
      private Boolean testSaisie() {
        String erreur = "";
      
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier le nom du logement correctement \n");
        }
       
        if (!testAdresse()) {
            erreur = erreur + ("Veuillez verifier l'adresse: \n");
        }
         if ((!testCapacite())&&(!(capacite_logement.getText().isEmpty()))) {
            erreur = erreur + ("Veuillez saisir la capacité correctement \n");
        }
          if ((!testPrixNuitee(prixNuitee_logement))&&(!(prixNuitee_logement.getText().isEmpty()))) {
            erreur = erreur + ("Veuillez saisir le prix correctement \n");
        }
         
      
        return  testNom() && testAdresse() && testCapacite() && testPrixNuitee(prixNuitee_logement);
    }
       
     
     
     
     private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom_logement.getText().trim().length(); i++) {
            char ch = nom_logement.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if ((nbNonChar == 0 )&& (nom_logement.getText().trim().length() >= 3)&&(!(nom_logement.getText().isEmpty()))) {
            //checknom.setImage(new Image("/codingbeats/doulicha/images/checkmark.png"));
            return true;
        } else {
            //checknom.setImage(new Image("/codingbeats/doulicha/images/erreurcheckmark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier le nom du logement !");
            alert.showAndWait();
            return false;

        }
    }
     
     private Boolean testAdresse() {
        int nbNonChar = 0;
        for (int i = 1; i < adresse_logement.getText().trim().length(); i++) {
            char ch = adresse_logement.getText().charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                nbNonChar++;
            }
        }

        if ((nbNonChar == 0 )&& (adresse_logement.getText().trim().length() >= 3) && (!(adresse_logement.getText().isEmpty()))){
            //checkadresse.setImage(new Image("/codingbeats/doulicha/images/checkmark.png"));
            return true;
        } else {
            //checkadresse.setImage(new Image("/codingbeats/doulicha/images/erreurcheckmark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier l'adresse du logement !");
            alert.showAndWait();
            return false;

        }
    }
     
      private Boolean testCapacite() {
          try{
          Double.parseDouble(capacite_logement.getText());
        //checkprixnuitee.setImage(new Image("/codingbeats/doulicha/images/checkmark.png"));
        return true;
    } catch (NumberFormatException e) {
       // checkprixnuitee.setImage(new Image("/codingbeats/doulicha/images/erreurcheckmark.png"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier la capacité du logement !");
            alert.showAndWait();
        return false;
    }
      }
        
    
      
      
      private Boolean testPrixNuitee(TextField textfield) {
      try {
        Double.parseDouble(textfield.getText());
        //checkprixnuitee.setImage(new Image("/codingbeats/doulicha/images/checkmark.png"));
        return true;
    } catch (NumberFormatException e) {
       // checkprixnuitee.setImage(new Image("/codingbeats/doulicha/images/erreurcheckmark.png"));
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez verifier le prix du logement !");
            alert.showAndWait();
        return false;
    }
      }
     
     
       
       
       
       
       
       
       
       
       
    }    
    

