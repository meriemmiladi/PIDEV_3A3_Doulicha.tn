/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   // Stripe.apiKey="sk_test_51Mf1wLLusEKclnk8QPGDh0xRPSORk53EJvGWiiYcyPJY5KMHEziRmaJyjtDhiBTQVI3ddwWMZXIDVV1uuGw4w6fo00Bkad1CYS";
    
    @FXML
    private void Payer(ActionEvent event) throws StripeException {
        
       String mail= cmail.getText();
       String nom= cnom.getText();
       String num= cnum.getText();
       String cmois= month.getText();
       String cAnn= year.getText();
       String ccvc= cvc.getText();
       String cprix= prix.getText();
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
        
    }

     public void setData(int prixd) {
         
        // ... implémentez votre logique pour utiliser les données
        this.p=prixd;
         System.out.println("prix p cent =" +this.p);
         prix.setText(""+prixd);
        
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
}
