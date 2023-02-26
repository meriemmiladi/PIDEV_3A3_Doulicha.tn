/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Reservation_logement;
import codingbeasts.doulicha.services.ServiceLogement;
import codingbeasts.doulicha.services.ServiceReservationLogement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
//import com.itextpdf.text.DocumentException;
import com.itextpdf.text.*;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class ConsulterMesReservationsController implements Initializable {

    @FXML
    private Button consulter_logement;
    @FXML
    private Button consulter_reservations;
    @FXML
    private Button home;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox ReservationBox;
    String xamppFolderPath = "C:/xampp/htdocs/img/";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherMesReservations();
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
            System.out.println("Bouton home appuyé");
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
            System.out.println("Bouton home appuyé");
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
    }
        
        
        public void afficherMesReservations(){
            ServiceReservationLogement sr = new ServiceReservationLogement();
            ServiceLogement sl = new ServiceLogement();
        List<Reservation_logement> mesReservations = sr.afficherMesReservations(2);
        mesReservations.stream().map((Reservation_logement MaReservation) -> {
            VBox contentBox = new VBox();
            //sl.recupNomLogement(MaReservation.getID_logement());
            Label nomLabel = new Label("Nom du logement réservé: "+ sl.recupNomLogement(MaReservation.getID_logement()));
            nomLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            Label dateArriveeLabel = new Label("Date Arrivée: "+MaReservation.getDateArrivee_reservation().toString());
            dateArriveeLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            Label dateDepartLabel = new Label("Date Départ: "+ MaReservation.getDateDepart_reservation().toString());
            dateDepartLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            
            Label nbPersonnesLabel = new Label( "Nombre de personnes: "+ Integer.toString(MaReservation.getNbPersonnes_reservation()));
            nbPersonnesLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            Label montantTotalLabel = new Label("Montant total à payer: "+ Double.toString(MaReservation.getMontantTotal_reservation())+ " DT");
            montantTotalLabel.setStyle( "-fx-text-fill: #3D6A78 ; -fx-font: 20px GothamMedium; -fx-font-weight: bold; ");
            
            Button modifierButton = new Button("Modifier ma réservation") ;
            Button supprimerButton = new Button("Supprimer ma réservation") ;
            Button PdfButton=new Button("Télecharger ma réservation");
            
            if(MaReservation.editPossible(MaReservation.getDateArrivee_reservation()))
            {
//************* SUPPRIMER RESERVATION *********************
            supprimerButton.setOnAction((ActionEvent event) -> {
                    try{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Annulation de réservation");
        alert.setContentText("Etes vous sur de vouloir annuler votre réservation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("Bouton supprimerButton appuyé");
            sr.supprimerReservationLogement(MaReservation.getID_reservation());
            Notifications notificationBuilder = Notifications.create()
                .title("Reservation Annulée")
                .text("La réservation a été bien annulée  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
            refreshTable();
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/ConsulterMesReservations.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }}
        catch (Exception ex) {
                Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
    }
                    });
            
            
 //************* MODIFIER RESERVATION *********************          
                        modifierButton.setOnAction((ActionEvent event) -> {
                            System.out.println("Bouton modifierButton appuyé");
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/ModifierReservation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierReservationController ModifierReservationController = loader.getController();
                            ModifierReservationController.setUpdate(true);
                            ModifierReservationController.recupererIDs(MaReservation.getID_reservation(),MaReservation.getID_logement(),MaReservation.getID_user());
                            
                            
                            
                                //ServL.supprimerLogement(Logement.getID_logement());
                            //ModifierReservationController.setTextField(MaReservation.getID_reservation(),MaReservation.getID_logement(),MaReservation.getID_user(),MaReservation.getDateArrivee_reservation(),MaReservation.getDateDepart_reservation(),MaReservation.getNbPersonnes_reservation(),MaReservation.getMontantTotal_reservation());
                            ModifierReservationController.setTextField(MaReservation.getDateArrivee_reservation(),MaReservation.getDateDepart_reservation(),MaReservation.getNbPersonnes_reservation());
                            Parent parent = loader.getRoot();
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show(); 
            });
            
            
            
            }
            
             if(!(MaReservation.editPossible(MaReservation.getDateArrivee_reservation())))
             {
                          supprimerButton.setDisable(true);
                          modifierButton.setDisable(true);
             }
             
             PdfButton.setOnAction((event) -> {
                 
       //**************
      /*  String message = "Voici votre réservation \n\n";

        String file_name = "src/PDF/Réservation.pdf";
        Document document = new Document();
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(file_name));
                } catch (Exception ex) {ex.getStackTrace();}
              
        document.open();
        try{
         document.add(new Paragraph("Nom du logement réservé : " + sl.recupNomLogement(MaReservation.getID_logement())));
            document.add(new Paragraph("Date d'arrivée : " + MaReservation.getDateArrivee_reservation()));
            document.add(new Paragraph("Date de départ : " + MaReservation.getDateDepart_reservation()));
            document.add(new Paragraph("Nombre de personnes : " + MaReservation.getNbPersonnes_reservation()));
            document.add(new Paragraph("Montant total : " + MaReservation.getMontantTotal_reservation()));
        }catch(Exception e)
        {
           e.getStackTrace();
        }
       
        document.close();

    }*/
      

String fileName = "Résercation N°" + MaReservation.getID_reservation() + ".pdf";
String pdfPath = "src/PDF/" + fileName;


com.itextpdf.text.Document document = new com.itextpdf.text.Document();
PdfWriter writer;
                try {
                    writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
document.open();
                try {
                    document.add(new Paragraph("Détails de votre réservation"));
                } catch (DocumentException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    document.add(new Paragraph("Nom du logement : " +sl.recupNomLogement(MaReservation.getID_logement())));
                } catch (DocumentException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    document.add(new Paragraph("Date d'arrivée : " + MaReservation.getDateArrivee_reservation()));
                } catch (DocumentException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    document.add(new Paragraph(" Date de départ : " + MaReservation.getDateDepart_reservation() ));
                } catch (DocumentException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    document.add(new Paragraph(" Nombre de personnes : " + MaReservation.getNbPersonnes_reservation() ));
                } catch (DocumentException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    document.add(new Paragraph(" *******************************Montant total à payer : " + MaReservation.getMontantTotal_reservation()+" DT *******************************" ));
                } catch (DocumentException ex) {
                    Logger.getLogger(ConsulterMesReservationsController.class.getName()).log(Level.SEVERE, null, ex);
                }


document.close();
//writer.close();
                     //********************
             });
        

            contentBox.getChildren().addAll(nomLabel, dateArriveeLabel,dateDepartLabel,nbPersonnesLabel, montantTotalLabel,modifierButton, supprimerButton,PdfButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #BFDCE4  ; -fx-border-color: #BFDCE4  ; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            ReservationBox.getChildren().add(contentBox);
            return contentBox;
        })  .forEachOrdered((_item) -> {
            ReservationBox.setSpacing(10);
        });

        scrollPane.setFitToWidth(false);
    }    

    ServiceReservationLogement sr = new ServiceReservationLogement();
    List<Reservation_logement> mesReservations = sr.afficherMesReservations(2);
    private void refreshTable() {
        try {
            //listL.clear();
            //ServL.afficherLogement();
            
             ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
             executor.scheduleAtFixedRate(() -> {
            // Update the data in the table
            mesReservations.clear();
            afficherMesReservations();
        }, 0, 2, TimeUnit.SECONDS);
            

                
            }            
         catch (Exception ex) {
            Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
}
