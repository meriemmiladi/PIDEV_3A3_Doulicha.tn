/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Logement;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.MapService;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.teamdev.jxmaps.javafx.MapView;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeFunction.function;
import okhttp3.Authenticator;
import org.slf4j.*;
import com.teamdev.jxmaps.ch.*;
import java.security.GeneralSecurityException;
import com.google.gson.FieldNamingPolicy;
import com.sun.org.apache.xerces.internal.impl.io.Latin1Reader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import javafx.embed.swing.SwingNode;
import org.json.simple.parser.ParseException;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;



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
     private int ID_logement;
    
  
  
    
    
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
            
            //**********************
            // Créer un objet JMapViewer
          /*  VBox vbox = new VBox();
JMapViewer mapViewer = new JMapViewer();

// Initialiser la carte
mapViewer.setZoomContolsVisible(true); // Afficher les contrôles de zoom
mapViewer.setDisplayPosition(new Coordinate(51.5, -0.12), 10); // Définir les coordonnées du centre et le niveau de zoom

// Charger les tuiles OpenStreetMap
mapViewer.setTileSource(new OsmTileSource.Mapnik());

// Ajouter un marqueur à la carte
mapViewer.addMapMarker(new MapMarkerDot(51.5, -0.12));

// Ajouter le composant de carte à votre interface utilisateur JavaFX
SwingNode mapNode = new SwingNode();
mapNode.setContent(mapViewer);
vbox.getChildren().add(mapNode); */// Ajouter le noeud à votre VBox
//*****************
// Créer un objet JMapViewer
            VBox vbox = new VBox();
JMapViewer mapViewer = new JMapViewer();

// Initialiser la carte
mapViewer.setZoomContolsVisible(true); // Afficher les contrôles de zoom
// Récupération des coordonnées géographiques
        double[] coords = new double[2];
        try {
            coords = MapService.getCoordinates(logement.getAdresse_logement());
            System.out.println("Latitude: " + coords[0] + ", Longitude: " + coords[1]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Affichage de la carte OpenStreetMap
        //JMapViewer mapViewer = new JMapViewer();
        Coordinate coordinate = new Coordinate(coords[0], coords[1]);
mapViewer.setDisplayPosition(coordinate, 10); // Définir les coordonnées du centre et le niveau de zoom

// Charger les tuiles OpenStreetMap
mapViewer.setTileSource(new OsmTileSource.CycleMap());

// Ajouter un marqueur à la carte
mapViewer.addMapMarker(new MapMarkerDot(coordinate));

// Ajouter le composant de carte à votre interface utilisateur JavaFX
SwingNode mapNode = new SwingNode();
mapNode.setContent(mapViewer);
vbox.getChildren().add(mapNode); 
// Ajouter le noeud à votre VBox
            
            
            //************
            /*
            // Create a VBox to hold the map view
            VBox vbox = new VBox();
            
            // Geocode an address
            GeocodingResult[] results =null;
            try {
                System.out.println("testttt00");
                results = MapService.geocode("303 Peachtree St NE, Atlanta, GA 30308");
                System.out.println("testttt11");
                return results;
            } catch (Exception ex) {
              ex.getStackTrace();
            }
             System.out.println("test1"+ results);
             LatLng location = results[0].geometry.location;
             System.out.println(location);
            
        try {
            // Display the place on a map
            MapService.displayPlace(location);
        } catch (Exception ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            // Make a request to the API endpoint
            String response =null;
        try {
            response = MapService.makeRequest("https://maps.googleapis.com/maps/api/geocode/json");
            return response;
        } catch (IOException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            // Add the response to the VBox
            vbox.getChildren().add(new javafx.scene.control.Label(response));*/
            
            // Create a Scene and show the Stage
            /*Scene scene = new Scene(vbox);
            primaryStage.setScene(scene);
            primaryStage.show();*/
            
            //**************************
            
            Label typeLabel = new Label(logement.getType_logement());
            typeLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            Label etatLabel = new Label() ;
            Button reserverButton = new Button("Réserver") ;
            //*******************************
            /*Button mapButton = new Button("map") ;
            mapButton.setOnAction((ActionEvent event) -> {
                try{
                    System.out.println("Bouton map appuyé");
                    Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/MapCord.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });*/
            
            //**********************************
            //if(logement.getEtat_logement()== 0)
            //{
                //int ID_logement=logement.getID_logement();
                //ID_logement=logement.getID_logement();
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
    
    ReserverLogementController reserverLogementController = loader.getController();
    reserverLogementController.setUpdate(true);
    ID_logement=logement.getID_logement();
    reserverLogementController.recupererID(ID_logement);
    

    
    
    //ReserverLogementController.recupererNom(nom_logement);
    
    //ServL.supprimerLogement(Logement.getID_logement());
    //ReserverLogementController.setTextField(ID_logement);
    Parent parent = loader.getRoot();
    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    
    
    
    
    
});

            //}
           /* if(logement.getEtat_logement()== 1)
            {
                String s = "Réservé";
                etatLabel = new Label ("Disponibilité: "+ s);
                etatLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
                reserverButton.setDisable(true);
            }*/
        
            Label prixNuiteeLabel = new Label("Prix de la nuitée: " + Double.toString(logement.getPrixNuitee_logement())+ " DT/Nuitée");
            prixNuiteeLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            Label capaciteLabel = new Label("Capacité: "+ Integer.toString(logement.getCapacite_logement())+ " personnes");
            capaciteLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            Label imageLabel = new Label(logement.getImage_logement());
            imageLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium ; -fx-font-weight: bold; ");
            ImageView imageLog = new ImageView();
            
       

String imagePath = "http://"+logement.getImage_logement(); // chemin de l'image
        Image image = new Image(imagePath);
        imageLog.setImage(image);
        imageLog.setPreserveRatio(true);
                imageLog.setFitWidth(150);
                imageLog.setFitHeight(150);
//**************
/*
// URL de l'image sur le serveur
String imageUrl = "http://"+logement.getImage_logement();

// Créer une connexion à l'URL
URL urlImage;
        try {
            urlImage = new URL(imageUrl);
        } catch (MalformedURLException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
InputStream in = null;
        try {
            in = url.openStream();
        } catch (IOException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

// Créer un fichier temporaire pour enregistrer l'image
File tempFile = null;
        try {
            tempFile = File.createTempFile("image", ".jpg");
        } catch (IOException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
tempFile.deleteOnExit();
FileOutputStream out = null;
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

// Copier le contenu de l'image dans le fichier temporaire
byte[] buffer = new byte[4096];
int bytesRead;
        try {
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }       } catch (IOException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Fermer les flux d'entrée et de sortie
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

// Charger l'image à partir du fichier temporaire
Image image= new Image(in);
        try {
            
            image = SwingFXUtils.toFXImage(ImageIO.read(tempFile), null);
        } catch (IOException ex) {
            Logger.getLogger(LogementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

// Afficher l'image dans un ImageView
ImageView imageLogement = new ImageView(image); */
//**************

            
           /* String test = xamppFolderPath + logement.getImage_logement();
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
            } */
            
            
            contentBox.getChildren().addAll(imageLog,nomLabel, typeLabel,descriptionLabel, adresseLabel,vbox,prixNuiteeLabel, capaciteLabel, etatLabel,reserverButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #BFDCE4  ; -fx-border-color: #BFDCE4 ; -fx-border-width: 2px; -fx-border-radius: 5px;");
                        return contentBox;

        
        }).map((contentBox) -> {
            LogementBox.getChildren().add((Node)contentBox);
            
            return contentBox;
        })  .forEachOrdered((_item) -> {
            LogementBox.setSpacing(10);
        });
 
        scrollPane.setFitToWidth(false);
       
    }
      
    
}