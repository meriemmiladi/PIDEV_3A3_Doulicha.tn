/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.don;
import codingbeasts.doulicha.services.donCRUD;
import codingbeasts.doulicha.utils.MyConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.TokenCreateParams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField year;
    @FXML
    private TextField prix;
    @FXML
    private TextField month;
    @FXML
    private TextField cvc;
    @FXML
    private Button valider;
    @FXML
    private TextField cnom;
    @FXML
    private TextField cnum;
    @FXML
    private TextField cmail;
    private double p;
    @FXML
    private Button Annulerr;
    @FXML
    private Button Annulerr1;
    
    
    private boolean paiementValide = false;
    private int donId;

    @FXML
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   // Stripe.apiKey="sk_test_51Mf1wLLusEKclnk8QPGDh0xRPSORk53EJvGWiiYcyPJY5KMHEziRmaJyjtDhiBTQVI3ddwWMZXIDVV1uuGw4w6fo00Bkad1CYS";
    
    @FXML
    private void Payer(ActionEvent event) throws StripeException, SQLException {
        
       String mail= cmail.getText();
       String nom= cnom.getText();
       String num= cnum.getText();
       String cmois= month.getText();
       String cAnn= year.getText();
       String ccvc= cvc.getText();
       String cprix= prix.getText();
       
       // Vérification que tous les champs sont remplis
if (mail.isEmpty() || nom.isEmpty() || num.isEmpty() || cmois.isEmpty() || cAnn.isEmpty() || ccvc.isEmpty() || cprix.isEmpty()) {
Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Attention");
alert.setHeaderText("Certains champs sont vides");
alert.setContentText("Veuillez remplir tous les champs");
alert.showAndWait();
return;
}

if (!mail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Attention");
    alert.setHeaderText("Format de l'adresse email invalide");
    alert.setContentText("Veuillez saisir une adresse email valide");
    alert.showAndWait();
    return;
}

if (!num.matches("\\d{16}")) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Attention");
    alert.setHeaderText("Format du numéro de carte invalide");
    alert.setContentText("Le numéro de carte doit contenir 16 chiffres");
    alert.showAndWait();
    return;
}
int mois = Integer.parseInt(cmois);
int annee = Integer.parseInt(cAnn);

// Vérification que la date d'expiration est future
if (annee < Calendar.getInstance().get(Calendar.YEAR) || (annee == Calendar.getInstance().get(Calendar.YEAR) && mois < (Calendar.getInstance().get(Calendar.MONTH) + 1))) {
Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Attention");
alert.setHeaderText("Date d'expiration de la carte invalide");
alert.setContentText("La date d'expiration de la carte doit être future");
alert.showAndWait();
return;
}

       int p = Integer.parseInt(cprix)*100 ;
       
       
        
                Stripe.apiKey = "sk_test_51Mf1wLLusEKclnk8QPGDh0xRPSORk53EJvGWiiYcyPJY5KMHEziRmaJyjtDhiBTQVI3ddwWMZXIDVV1uuGw4w6fo00Bkad1CYS";
        CustomerCreateParams.Builder customerCreateParamsBuilder = new CustomerCreateParams.Builder()
                .setEmail(mail)
                .setName(nom);
        
        Customer customer = Customer.create(customerCreateParamsBuilder.build());
        
        // Ajout d'une carte bancaire au client
        TokenCreateParams.Builder tokenCreateParamsBuilder = new TokenCreateParams.Builder()
                .setCard(new TokenCreateParams.Card.Builder()
                        .setNumber(num)
                        .setExpMonth(cmois)
                        .setExpYear(cAnn)
                        .setCvc(ccvc)
                        .build());
        Token token = Token.create(tokenCreateParamsBuilder.build());
        CustomerUpdateParams.Builder customerUpdateParamsBuilder = new CustomerUpdateParams.Builder()
                .setSource(token.getId());
        customer.update(customerUpdateParamsBuilder.build());
        
        // Paiement d'un montant donné en utilisant le client et la carte bancaire
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", p); 
        chargeParams.put("currency", "eur");
        chargeParams.put("description", "Achat sur notre site web");
        chargeParams.put("customer", customer.getId());
        //CustomerCharge.create(chargeParams);
        
        Charge charge = Charge.create(chargeParams);

        System.out.println("Charge created: " + charge.getId());
        
         
        pdfPayment(cprix,charge.getId(),nom,mail,num);
        
        // donCRUD d = new donCRUD();
        // d.setEtat_paiement(1); 
        
         //paiementValide = true;
        // Fermer la fenêtre de paiement
        //((Stage) valider.getScene().getWindow()).close();
     
        updateDonPaymentStatus(donId);
        System.out.println(donId);
    }  
        
        
    

     public void setData(int prixd, int Id_don) {
         
        // ... implémentez votre logique pour utiliser les données
        this.p=prixd;
         System.out.println("prix p cent =" +this.p);
         prix.setText(""+prixd);
         this.donId = Id_don;
        
    }
     Connection cnx2;
   
        
    
public void updateDonPaymentStatus(int donId) throws SQLException {
    
    String updateQuery = "UPDATE don SET etat_paiement = 1 WHERE ID_don = ?";
    cnx2 = MyConnection.getInstance().getCnx();
    try (PreparedStatement pstmt = cnx2.prepareStatement(updateQuery)) {
        pstmt.setInt(1, donId);
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        System.err.println("Error updating payment status for donation " + donId + ": " + ex.getMessage());
        throw ex;
    }
    System.out.println(donId);
}
    @FXML
    private void Anunuler(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/ListAffichageDon.fxml"));
        Parent root = loader.load();
        Scene scene = Annulerr.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
  

    @FXML
     public void pdfPayment(String cprix, String id, String nom, String mail, String num) {
    LocalDateTime now = LocalDateTime.now();

    try {
        //Create Document instance.
        Document document = new Document();

        //Create OutputStream instance.
        OutputStream outputStream =
                new FileOutputStream(new File("C:\\xampp\\htdocs\\" + "Facture de don " + id + ".pdf"));

        //Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);

        //Open the document.
        document.open();

        //Create and add title paragraph to the document.
        Paragraph title = new Paragraph("Facture de paiement", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        //Create and add customer info paragraph to the document.
        Paragraph customerInfo = new Paragraph("Nom : " + nom + "\nE-mail : " + mail + "\nNuméro de carte : " + num);
        customerInfo.setAlignment(Element.ALIGN_LEFT);
        document.add(customerInfo);

        //Create and add content paragraph to the document.
        Paragraph content = new Paragraph("Vous avez effectué un paiement d'un montant de " + cprix + " euros.");
        content.setAlignment(Element.ALIGN_LEFT);
        document.add(content);

        Paragraph date = new Paragraph("Date de paiement : " + now + ".");
        date.setAlignment(Element.ALIGN_LEFT);
        document.add(date);

        //Close document and outputStream.
        document.close();
        outputStream.close();

        System.out.println("Pdf created successfully.");
    } catch (Exception e) {
        e.printStackTrace();
    }

}


    public boolean isPaiementValide() {
        System.out.println(paiementValide);
        return paiementValide;
    }
   
     
    
     
}
