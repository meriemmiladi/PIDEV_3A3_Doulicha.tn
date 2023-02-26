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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
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
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.text.Document;
import net.fortuna.ical4j.model.property.ProdId;
//import net.fortuna.ical4j.model.Calendar;

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
private void ajouterParticipation(ActionEvent event) throws SQLException, DocumentException, IOException {
    participation_evenement part = new participation_evenement();
    ServiceParticipationEvenement SPE = new ServiceParticipationEvenement();
     ServiceEvenement ev = new ServiceEvenement();
     
    int nombre_participation = Integer.parseInt(TF_nombre.getText());
    String textI = id_part.getText();
    int valueI=0;
    try {
        valueI = Integer.parseInt(textI);
    } catch (NumberFormatException e) {
        System.out.println("erreur recuperation !");
    } 
        
    part.setNombre_participation(Integer.parseInt(TF_nombre.getText()));
    part.setID_event(valueI);
    part.setID_user(2);
     int totalParticipations = SPE.getNombreParticipations(valueI);
       System.out.println(totalParticipations + "ay haja");
       System.out.println(totalParticipations < ev.getCapacite(part.getID_event()));
       System.out.println(nombre_participation <= (ev.getCapacite(part.getID_event()-totalParticipations)));
       System.out.println(SPE.getUserByEvent(part.getID_event(), part.getID_user()) + "MESSAGE OK");
     if ((totalParticipations < ev.getCapacite(part.getID_event())) && (nombre_participation <= (ev.getCapacite(part.getID_event())-totalParticipations)) && (SPE.getUserByEvent(part.getID_event(), part.getID_user())==0))
     {
    SPE.ajouterParticipationEvenement(part);
    
    int idParticipation = SPE.getIdDerniereParticipation();
   
   
    // Générer le QR code
    String codeData = "ID_user: " + ev.getNomUser(part.getID_user()) + ", ID_participation: " + idParticipation + ", ID_event: " + ev.getNom(part.getID_event()) + ", dateDebut_event: " + ev.getDateDebut(part.getID_event()) + ", dateFin_event: " + ev.getDateFin(part.getID_event());
    String filePath = "src/qrcode/qrcode.pdf";
    int size = 250;
    String fileType = "png";
    File qrFile = new File(filePath);
    try {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(codeData, BarcodeFormat.QR_CODE, size, size);
        MatrixToImageWriter.writeToFile(bitMatrix, fileType, qrFile);
    } catch (WriterException e) {
        e.printStackTrace();
    }
    
    // Créer un lien iCalendar avec les dates de début et de fin de l'évènement
String icalData = "BEGIN:VCALENDAR\n" +
                  "VERSION:2.0\n" +
                  "BEGIN:VEVENT\n" +
                  "SUMMARY:" + ev.getNom(part.getID_event()) + "\n" +
                  "DTSTART:" + ev.getDateDebut(part.getID_event()).replace("-", "") + "\n" +
                  "DTEND:" + ev.getDateFin(part.getID_event()).replace("-", "") + "\n" +
                  "END:VEVENT\n" +
                  "END:VCALENDAR";

String icalLink = "data:text/calendar;base64," + icalData;

// Générer le QR code avec le lien iCalendar
String icalFilePath = "src/qrcode/ical_qrcode.png";
int icalSize = 250;
try {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(icalLink, BarcodeFormat.QR_CODE, icalSize, icalSize);
    MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(icalFilePath));
} catch (WriterException e) {
    e.printStackTrace();
} 
    
      // Exporter les informations de la participation en PDF avec le QR code
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
Date date = new Date(System.currentTimeMillis());
String fileName = "participation_" + idParticipation + "_" + dateFormat.format(date) + ".pdf";
String pdfPath = "src/PDF/" + fileName;


com.itextpdf.text.Document document = new com.itextpdf.text.Document();
PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
writer.setBoxSize("art", new Rectangle(36, 36, 550, 800));

document.open();



    // Ajouter un titre coloré
    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18,Font.BOLD | Font.UNDERLINE, new BaseColor(135, 206, 235));
    Paragraph title = new Paragraph("Details de votre participation", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);
   
//document.add(new Paragraph("Participation"));
document.add(new Paragraph("Votre nom : " + ev.getNomUser(part.getID_user())));
document.add(new Paragraph("Numero de votre participation : " + idParticipation));
document.add(new Paragraph("Nom de l'évènement : " + ev.getNom(part.getID_event()) ));
document.add(new Paragraph("Date de début : " + ev.getDateDebut(part.getID_event()) ));
document.add(new Paragraph("Date de fin : " + ev.getDateFin(part.getID_event()) ));



// Ajouter le QR code
Image qrImage = Image.getInstance(qrFile.getAbsolutePath());
qrImage.setAlignment(Element.ALIGN_CENTER);
qrImage.scaleAbsolute(200, 200);
document.add(qrImage);


// Dessiner le cadre autour de tout le PDF
Rectangle rect = writer.getBoxSize("art");
PdfContentByte cb = writer.getDirectContent();
cb.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
cb.stroke();

// Ajouter le QR code contenant le lien iCalendar au PDF
Image icalQrImage = Image.getInstance(icalFilePath);
icalQrImage.setAlignment(Element.ALIGN_CENTER);
icalQrImage.scaleAbsolute(200, 200);
document.add(icalQrImage);






document.close();
writer.close();

 
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
     }else
     {
       if (totalParticipations == ev.getCapacite(part.getID_event()))
       {
        Notifications notificationBuilder = Notifications.create()
            .title("Ajout PARTICIPATION")
            .text("Evenement COMPLET.")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.showError();
       }
       else if (SPE.getUserByEvent(part.getID_event(), part.getID_user())==1)
       {
        Notifications notificationBuilder = Notifications.create()
            .title("Ajout PARTICIPATION")
            .text("Vous avez déjà effectué votre participation pour cet évènement.")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.showError();   
       }
       else
       {
           Notifications notificationBuilder = Notifications.create()
            .title("Ajout PARTICIPATION")
            .text("Il reste " + String.valueOf(ev.getCapacite(part.getID_event())-totalParticipations) + " places pour cet évènement.")
            .graphic(null)
            .hideAfter(Duration.seconds(5))
            .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.showWarning();
       }
     }
}









 /*@FXML
    private void ajouterParticipation(ActionEvent event) throws SQLException, DocumentException, IOException  {
        
        
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
        //ExportPDF(5);
        Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/participationClient.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutParticipationController.class.getName()).log(Level.SEVERE, null, ex);
    }
   // }
    } */
    
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


 