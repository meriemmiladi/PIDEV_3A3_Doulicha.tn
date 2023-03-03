package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Commande;
import codingbeasts.doulicha.entities.LigneCommande;
import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.services.CommandeCrud;
import codingbeasts.doulicha.services.LigneCommandeCrud;
import codingbeasts.doulicha.services.ProduitCrud;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import javax.swing.text.Document;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import java.awt.Desktop;
//import java.io.File;
import java.io.OutputStream;
import java.util.Collections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import com.itextpdf.text.BadElementException;
import static com.itextpdf.text.pdf.BidiOrder.PDF;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import static java.time.Instant.now;
import static java.time.Instant.now;
import static java.time.LocalDate.now;
import static java.time.LocalDateTime.now;
import static java.time.MonthDay.now;
import static java.time.Year.now;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.auth.oauth2.GoogleCredentials;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.auth.http.HttpCredentialsAdapter;
import java.util.Collections;
import javafx.collections.FXCollections;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import codingbeasts.doulicha.services.AEmail;
public class PanierController implements Initializable {

    static Map<Produit, Integer> produitMap;

    @FXML
    private TableColumn<Entry<Produit, Integer>, String> c1;

    @FXML
    private TableColumn<Entry<Produit, Integer>, Integer> c2;

    @FXML
    private TableColumn<Entry<Produit, Integer>, Double> c3;

    @FXML
    private TableView<Entry<Produit, Integer>> tableView;
    @FXML
    private Button confirme;
    @FXML
    private TextField sommePrixTextField;
    @FXML
    private Button btnretour;
    Commande commande;
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final GsonFactory JSON_FACTORY = new GsonFactory();
    private static final String SERVICE_ACCOUNT_EMAIL = "mohamedazyz-belaidi@doulicha-379102.iam.gserviceaccount.com";
    private static final String SERVICE_ACCOUNT_PKCS12_FILE_PATH = "C:\\Users\\scola\\Desktop\\hamza2.p12";
    @FXML
    private TextField mail;
    
    
  


//    private static final String API_URL = "https://invoice-generator.com";
////    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";
//    private static final String POST_DATA = "{\n" +
//"    \"from\":\"doulicha\",\n" +
//"    \"number\":1,\n" +
//"    \"items\":[{\n" +
//"      \"name\": \"Gizmo\",\n" +
//"      \"quantity\": 10,\n" +
//"      \"unit_cost\": 99.99,\n" +
//"      \"description\": \"The best gizmos there are around.\"\n" +
//"    },{\n" +
//"      \"name\": \"Gizmo\",\n" +
//"      \"quantity\": 10,\n" +
//"      \"unit_cost\": 99.99,\n" +
//"      \"description\": \"The best gizmos there are around.\"\n" +
//"    }]\n" +
//"}";

    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url1, ResourceBundle rb) {
        
        String rec ="mohamedazyz.belaidi@esprit.tn";
         String rec1 ="azyzbel";
          String rec2 ="azyzbel";
               
        confirme.setOnAction(event ->{
        String message = "Bonjour  Merci de vous être inscrit!";
        AEmail emailsend = new AEmail("mohamedazyz.belaidi@esprit.tn", "ucjenrtgssrqxkxy", "azyzbel@gmail.com", "Confirmation d'inscription", message);
            try {
                emailsend.sendEmail();
                
                
                //String a="mohamedazyz.belaidi@esprit.tn";
//            try {
//                envoyer(a);
//            } catch (Exception ex) {
//                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            } catch (MessagingException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
                
                
            try {
                
                
                
                
                CommandeCrud commandeCrud=new CommandeCrud();
                LigneCommandeCrud ligneCommandeCrud=new LigneCommandeCrud();
                
                Commande commande=commandeCrud.lastCommand();
                List<LigneCommande> ligneCommandeListe=ligneCommandeCrud.retreiveLigneCommandeByIdCommande(commande.getID_commande());
                /*
                
                CommandeCrud commandeCrud=new CommandeCrud();
                Commande commande=commandeCrud.lastCommand();
                int id_commande = commande.getID_commande();
                
                */
                
                int id_commande = commande.getID_commande();
                commande.setEtat_commande(1);
                commandeCrud.modifierCommande(id_commande, commande);
                
                System.out.println(id_commande);
                produitMap.forEach((key,value)->{
                    
                        LigneCommandeCrud lcc=new LigneCommandeCrud();
                        //   LigneCommande lc = new LigneCommande(id_commande, key.getID_produit(), value);
                        // LigneCommande lc=new LigneCommande(commande.getID_commande(), key.getID_produit(), value);
                        //    lcc.ajouterLigneCommande2(lc);
                        
                        Notifications notificationBuilder = Notifications.create().title("Validation commande").text("Commande validé").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.BOTTOM_RIGHT);
                        notificationBuilder.showInformation();
                        
                    
                });
                
                 Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
                        Scene scene = new Scene(page1);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                /////////////////APIIIIIIII/////////////////////
               
                HttpTransport transport = new NetHttpTransport();
                GoogleCredential credential;
                try {
                    credential = new GoogleCredential.Builder()
                            .setTransport(HTTP_TRANSPORT)
                            .setJsonFactory(JSON_FACTORY)
                            .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
                            .setServiceAccountPrivateKeyFromP12File(new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE_PATH))
                            .build();
                    Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                            .setApplicationName("doulicha")
                            .build();
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                pdfPayment("aziz","azyzbel@gmail.com ","ajdjzed25czx");
                
                
                
                ////////////////////////////////////////////////////////////////////////
//            try {
//            URL url = new URL(API_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json; utf-8");
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setDoOutput(true);
//
//            // Send the request body
//            byte[] postData = POST_DATA.getBytes();
//            conn.getOutputStream().write(postData);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//             String responseData = response.toString();
//        System.out.println(responseData);
//
//        // Parse the JSON response and extract the data you need
//        JSONObject jsonObject = new JSONObject(responseData);
//        String invoiceNumber = jsonObject.getString("number");
//        String invoiceFrom = jsonObject.getString("from");
//        JSONArray items = jsonObject.getJSONArray("items");
//
//        // Create a new PDF document
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("post.pdf"));
//        document.open();
//
//        // Add the invoice number and from fields to the document
//        document.add(new Paragraph("Invoice Number: " + invoiceNumber));
//        document.add(new Paragraph("From: " + invoiceFrom));
//
//        // Add the items to the document
//        for (int i = 0; i < items.length(); i++) {
//            JSONObject item = items.getJSONObject(i);
//            String name = item.getString("name");
//            int quantity = item.getInt("quantity");
//            double unitCost = item.getDouble("unit_cost");
//            String description = item.getString("description");
//
//            document.add(new Paragraph(name + " x " + quantity + " @ $" + unitCost + " = $" + (quantity * unitCost)));
//            document.add(new Paragraph(description));
//            document.add(new Paragraph(""));
//        }
//
//        document.close();
//
//        // Open the PDF file with the default PDF viewer on your system
//        File pdfFile = new File("post.pdf");
//        Desktop.getDesktop().open(pdfFile);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
////////////////////////////////////////////////////////////////////////

//             try{
//            LocalDateTime dateTime = LocalDateTime.now();
//            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());
//                Commande c=new Commande(sqlDate,1);
//                CommandeCrud cc=new CommandeCrud();
//                commande=cc.ajouterCommande2(c, 1);
//                int id_commande = commande.getID_commande();
//                produitMap.forEach((key,value)->{
//                    LigneCommandeCrud lcc=new LigneCommandeCrud();
//                LigneCommande lc = new LigneCommande(id_commande, key.getID_produit(), value);
//                    LigneCommande lc=new LigneCommande(commande.getID_commande(), key.getID_produit(), value);
//                    lcc.ajouterLigneCommande2(lc);
//                    Notifications notificationBuilder = Notifications.create().title("Validation commande").text("Commande validé").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.BOTTOM_RIGHT);
//        notificationBuilder.showInformation();
//                });
//                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
//                Scene scene = new Scene(page1);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//             System.out.println("errrrrrrrrrrr");
//            }
//            });
//            });
//
//});
//add to pdf document


//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("post.pdf"));
//            document.open();
//
//            // Ouvre le fichier PDF avec l'application PDF par défaut installée sur votre système
////        File pdfFile = new File("post.pdf");
////        Desktop.getDesktop().open(pdfFile);
////        } catch (IOException e) {
////        e.printStackTrace();
//
//            String responseData = response.toString();
//            System.out.println(responseData);
////            //String title = responseData.split("\"title\":")[1].split(",")[0];
////            String id = responseData.split("\"id\":")[1].split(",")[0];
////            System.out.println("Title: " + title + ", ID: " + id);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//


//            try{
//            LocalDateTime dateTime = LocalDateTime.now();
//            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());
//                Commande c=new Commande(sqlDate,1);
//                CommandeCrud cc=new CommandeCrud();
//                commande=cc.ajouterCommande2(c, 1);
//                produitMap.forEach((key,value)->{
//                    LigneCommandeCrud lcc=new LigneCommandeCrud();
//                    LigneCommande lc=new LigneCommande(commande.getID_commande(), key.getID_produit(), value);
//                    lcc.ajouterLigneCommande2(lc);
//                    Notifications notificationBuilder = Notifications.create().title("Validation commande").text("Commande validé").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.BOTTOM_RIGHT);
//        notificationBuilder.showInformation();
//                });
//                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
//                Scene scene = new Scene(page1);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//             System.out.println("errrrrrrrrrrr");
//            }


            } catch (DocumentException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                
                
                catch (GeneralSecurityException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
                
                
                
                ////////////////////////////////////////////////////////////////////////
//            try {
//            URL url = new URL(API_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json; utf-8");
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setDoOutput(true);
//
//            // Send the request body
//            byte[] postData = POST_DATA.getBytes();
//            conn.getOutputStream().write(postData);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            
//             String responseData = response.toString();
//        System.out.println(responseData);
//
//        // Parse the JSON response and extract the data you need
//        JSONObject jsonObject = new JSONObject(responseData);
//        String invoiceNumber = jsonObject.getString("number");
//        String invoiceFrom = jsonObject.getString("from");
//        JSONArray items = jsonObject.getJSONArray("items");
//
//        // Create a new PDF document
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("post.pdf"));
//        document.open();
//
//        // Add the invoice number and from fields to the document
//        document.add(new Paragraph("Invoice Number: " + invoiceNumber));
//        document.add(new Paragraph("From: " + invoiceFrom));
//
//        // Add the items to the document
//        for (int i = 0; i < items.length(); i++) {
//            JSONObject item = items.getJSONObject(i);
//            String name = item.getString("name");
//            int quantity = item.getInt("quantity");
//            double unitCost = item.getDouble("unit_cost");
//            String description = item.getString("description");
//
//            document.add(new Paragraph(name + " x " + quantity + " @ $" + unitCost + " = $" + (quantity * unitCost)));
//            document.add(new Paragraph(description));
//            document.add(new Paragraph(""));
//        }
//
//        document.close();
//
//        // Open the PDF file with the default PDF viewer on your system
//        File pdfFile = new File("post.pdf");
//        Desktop.getDesktop().open(pdfFile);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
////////////////////////////////////////////////////////////////////////
            
//             try{
//            LocalDateTime dateTime = LocalDateTime.now();
//            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());
//                Commande c=new Commande(sqlDate,1);
//                CommandeCrud cc=new CommandeCrud();
//                commande=cc.ajouterCommande2(c, 1);
//                int id_commande = commande.getID_commande();
//                produitMap.forEach((key,value)->{
//                    LigneCommandeCrud lcc=new LigneCommandeCrud();
//                LigneCommande lc = new LigneCommande(id_commande, key.getID_produit(), value);
//                    LigneCommande lc=new LigneCommande(commande.getID_commande(), key.getID_produit(), value);
//                    lcc.ajouterLigneCommande2(lc);
//                    Notifications notificationBuilder = Notifications.create().title("Validation commande").text("Commande validé").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.BOTTOM_RIGHT);
//        notificationBuilder.showInformation();
//                });
//                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
//                Scene scene = new Scene(page1);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//             System.out.println("errrrrrrrrrrr");
//            }
//            });
//            });
//            
//});
//add to pdf document


//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("post.pdf"));
//            document.open();
//            
//            // Ouvre le fichier PDF avec l'application PDF par défaut installée sur votre système
////        File pdfFile = new File("post.pdf");
////        Desktop.getDesktop().open(pdfFile);
////        } catch (IOException e) {
////        e.printStackTrace();
//            
//            String responseData = response.toString();
//            System.out.println(responseData);
////            //String title = responseData.split("\"title\":")[1].split(",")[0];
////            String id = responseData.split("\"id\":")[1].split(",")[0];
////            System.out.println("Title: " + title + ", ID: " + id);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//


//            try{
//            LocalDateTime dateTime = LocalDateTime.now();
//            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());
//                Commande c=new Commande(sqlDate,1);
//                CommandeCrud cc=new CommandeCrud();
//                commande=cc.ajouterCommande2(c, 1);
//                produitMap.forEach((key,value)->{
//                    LigneCommandeCrud lcc=new LigneCommandeCrud();
//                    LigneCommande lc=new LigneCommande(commande.getID_commande(), key.getID_produit(), value);
//                    lcc.ajouterLigneCommande2(lc);
//                    Notifications notificationBuilder = Notifications.create().title("Validation commande").text("Commande validé").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.BOTTOM_RIGHT);
//        notificationBuilder.showInformation();
//                });
//                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
//                Scene scene = new Scene(page1);
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//             System.out.println("errrrrrrrrrrr");
//            }
            
            
       });
        


        btnretour.setOnAction(event ->{
        try {
              
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             System.out.println("errrrrrrrrrrr");
            }
    
    
    
    });
        c1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entry<Produit, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Entry<Produit, Integer>, String> p) {
                // retourne la clé de l'entrée du Map
                return new SimpleStringProperty(p.getValue().getKey().getLibelle_produit());
            }
        });

        c2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entry<Produit, Integer>, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Entry<Produit, Integer>, Integer> p) {
                // retourne la valeur pour chaque entrée du Map
                return new SimpleObjectProperty<Integer>(p.getValue().getValue());
            }
        });

        c3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entry<Produit, Integer>, Double>, ObservableValue<Double>>() {
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Entry<Produit, Integer>, Double> p) {
                // calculer le prix total pour chaque entrée du Map
                double prixTotal = p.getValue().getKey().getPrixUvente_produit()* p.getValue().getValue();
                return new SimpleDoubleProperty(prixTotal).asObject();
            }
        });

        if (produitMap != null) {
            tableView.getItems().addAll(produitMap.entrySet());
            
        }
    }
    
    private double calculateTotalPrice() {
    double total = 0.0;
    if (produitMap != null) {
        for (Map.Entry<Produit, Integer> entry : produitMap.entrySet()) {
            double prixTotal = entry.getKey().getPrixUvente_produit() * entry.getValue();
            total += prixTotal;
        }
    }
    return total;
}

    
    public void setProduitToModify(Map<Produit, Integer> mapProduit) {
        produitMap = mapProduit;
        System.out.println(produitMap);
        if (tableView != null) {
            tableView.getItems().addAll(produitMap.entrySet());
        }
        double total = calculateTotalPrice();
    sommePrixTextField.setText(String.format("%.2f", total));
    }
    
    private void afficherProduitSpec(){
        
        LigneCommandeCrud LCC =new LigneCommandeCrud();
        List<Integer> lista = LCC.retourDesIdsProduits(40);
        List<Integer> Quantities = LCC.retourDesIdsQuantite(45);
        
        ProduitCrud Pcc = new ProduitCrud();
        System.out.println(Pcc.getSelectedProducts(lista).stream().findFirst().get().getID_produit());
        System.out.println(Pcc.getSelectedProducts(lista).stream().findFirst().get().getLibelle_produit());
       
        System.out.println(Quantities.get(0));
        
        ObservableList<Produit> productsSelected = (ObservableList) lista ;
        
        
        c1.setCellValueFactory((new PropertyValueFactory<>("a")));

        /*
        ProductName || Quantite || ProductPrice
        => 3elements differents mawjoudin fi 3 listes (telque kol liste est de meme type que l'element hedheka)
        
        je veux faire un tableView nhot fih 3listes fi kol colonne (tel que les listes CONSECULTIVEMENT)
        
        */
       // tableView.setItems(productsSelected);
    }
    
    private void addDeleteButtonToTable() {
    TableColumn<Entry<Produit, Integer>, Void> column = new TableColumn<>();
    Callback<TableColumn<Entry<Produit, Integer>, Void>, TableCell<Entry<Produit, Integer>, Void>> cellFactory = col -> {
        final TableCell<Entry<Produit, Integer>, Void> cell = new TableCell<Entry<Produit, Integer>, Void>() {
            private final Button deleteButton = new Button("Delete");
            {
                deleteButton.setOnAction(event -> {
                    Entry<Produit, Integer> data = getTableView().getItems().get(getIndex());
//                    ProduitCrud c = new ProduitCrud();
//                    c.deleteProduit(data.getKey().getID_produit());
//                    getTableView().getItems().remove(data);
                        System.out.println("yasmiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiine");
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        };
        return cell;
    };
    column.setCellFactory(cellFactory);
    tableView.getColumns().add(column);
}
//    public void pdfPayment(int id ,String libelle ,int quantite,double  prix ,double somme){
//         LocalDateTime now = LocalDateTime.now();
//         
//       try {
//           
//           String fileName = ".pdf";
//            String pdfPath = "src/PDF/" + fileName;
//            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
//            writer.setBoxSize("art", new Rectangle(36, 36, 550, 800));
//            document.open();
//        
//            
//             // Ajouter le logo au document
//            Image logo = Image.getInstance("C:\\Users\\scola\\Documents\\NetBeansProjects\\JDBC\\PIDEV_3A3_Doulicha.tn\\src\\img\\logo.png");
//            logo.setAlignment(Element.ALIGN_CENTER);
//            logo.scaleToFit(200, 200);
//            document.add(logo);
//        
////        //Create and add title paragraph to the document.
////        Paragraph title = new Paragraph("Facture de paiement", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
////        title.setAlignment(Element.ALIGN_CENTER);
////        title.setSpacingAfter(20);
////        document.add(title);
//        
//         // Ajouter un titre coloré
//    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18,Font.BOLD | Font.UNDERLINE, new BaseColor(135, 206, 235));
//    Paragraph title = new Paragraph("Facture de paiement", titleFont);
//    title.setAlignment(Element.ALIGN_CENTER);
//    document.add(title);
//
//        //Create and add customer info paragraph to the document.
//        Paragraph customerInfo = new Paragraph("Nom : " + id + "\nE-mail : " + libelle + "\nNuméro de carte : " + quantite);
//        customerInfo.setAlignment(Element.ALIGN_LEFT);
//        document.add(customerInfo);
//
//        //Create and add content paragraph to the document.
//        Paragraph content = new Paragraph("Vous avez effectué un paiement d'un montant de " + prix + " pound .");
//        content.setAlignment(Element.ALIGN_LEFT);
//        document.add(content);
//        
//        Paragraph date = new Paragraph("Date de paiment : "  + now + ".");
//        date.setAlignment(Element.ALIGN_LEFT);
//        document.add(date);
//
//        //Close document and outputStream.
//        document.close();
//        writer.close();
//
//        Notifications notificationBuilder = Notifications.create()
//            .title("Ajout PARTICIPATION")
//            .text("Votre facture a bien été enregistrée.")
//            .graphic(null)
//            .hideAfter(Duration.seconds(5))
//            .position(Pos.BOTTOM_RIGHT);
//    notificationBuilder.showInformation();
//    
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    
//     }
//
    
    public void pdfPayment(String nom,String mail,String numc) throws FileNotFoundException, DocumentException, BadElementException, IOException, GeneralSecurityException{
            
    // Create the PDF file and save it on the computer
    String fileName = nom + ".pdf"; // Use the customer's name as the file name
    String pdfPath = "src/PDF/" + fileName; // Save the file in the Invoices folder on the computer
    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
    writer.setBoxSize("art", new Rectangle(36, 36, 550, 800));
    document.open();

     //Add the logo to the document
    Image logo = Image.getInstance("C:\\Users\\scola\\Documents\\NetBeansProjects\\JDBC\\PIDEV_3A3_Doulicha.tn\\src\\img\\logo.png"); // Replace with the path to your logo image
    logo.setAlignment(Element.ALIGN_CENTER);
    logo.scaleToFit(200, 200);
    document.add(logo);

    // Add a colored title
    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD | Font.UNDERLINE, new BaseColor(135, 206, 235));
    Paragraph title = new Paragraph("Facture de paiement", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);

     //Add customer information
    Paragraph customerInfo = new Paragraph("Nom : " + nom + "\nE-mail : " + mail + "\nNuméro de carte : " + numc);
    customerInfo.setAlignment(Element.ALIGN_LEFT);
    document.add(customerInfo);
      
//    // Add content
//    Paragraph content = new Paragraph("Vous avez effectué un paiement d'un montant de " + amount + " pound .");
//    content.setAlignment(Element.ALIGN_LEFT);
//    document.add(content);

    
     Iterable<Entry<Produit, Integer>> data = produitMap.entrySet();
    for (Entry<Produit, Integer> entry : data) {
    Produit produit = entry.getKey();
    Integer quantite = entry.getValue();
    Paragraph paragraph = new Paragraph(produit.getLibelle_produit()+ "      quantité  " + quantite +"      prix       "+ produit.getPrixUvente_produit()*quantite+"DT");
    document.add(paragraph);
    }
    // Add date
//    Paragraph date = new Paragraph("Date de paiment : "  + now + ".");
//    date.setAlignment(Element.ALIGN_LEFT);
//    document.add(date);

    // Close document and outputStream.
    document.close();
    writer.close();

     //Upload the file to Google Drive
    Drive service = getDriveService(); // Get an instance of the Google Drive service
   
    File fileMetadata = new File();
    
    fileMetadata.setName("aaa");
    fileMetadata.setParents(Collections.singletonList("18Hxr0pTa_3_5aqPJ49ru-_pC_HY160TN")); // Set the folder ID of the Invoices folder on your Google Drive
    java.io.File pdfFile = new java.io.File(pdfPath);
    FileContent mediaContent = new FileContent("application/pdf", pdfFile);
    File file = service.files().create(fileMetadata, mediaContent)
            .setFields("id")
            .execute();
    System.out.println("File ID: " + file.getId());

    // Show a notification to the user
    Notifications notificationBuilder = Notifications.create()
            .title("Ajout PARTICIPATION")
            .text("Votre facture a bien été enregistrée.");
            

      
      
      
            }
      
     private Drive getDriveService() throws IOException , GeneralSecurityException{
    GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\scola\\Desktop\\hamza.json"))
            .createScoped(Collections.singleton(DriveScopes.DRIVE));
                
    HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
    return new Drive.Builder(
            new NetHttpTransport(),
            new JacksonFactory(),
            null)
            .setApplicationName("doulicha")
            .build();
}          

    
 
//      public void pdfPayment(String monton ,String amount,String nom,String mail,String numc) throws FileNotFoundException, DocumentException, BadElementException, IOException, GeneralSecurityException{
//            
//    // Create the PDF file and save it on the computer
//    String fileName = nom + ".pdf"; // Use the customer's name as the file name
//    String pdfPath = "src/PDF/" + fileName; // Save the file in the Invoices folder on the computer
//    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
//    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
//    writer.setBoxSize("art", new Rectangle(36, 36, 550, 800));
//    document.open();
//
//     //Add the logo to the document
//    Image logo = Image.getInstance("C:\\Users\\scola\\Documents\\NetBeansProjects\\JDBC\\PIDEV_3A3_Doulicha.tn\\src\\img\\logo.png"); // Replace with the path to your logo image
//    logo.setAlignment(Element.ALIGN_CENTER);
//    logo.scaleToFit(200, 200);
//    document.add(logo);
//
//    // Add a colored title
//    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD | Font.UNDERLINE, new BaseColor(135, 206, 235));
//    Paragraph title = new Paragraph("Facture de paiement", titleFont);
//    title.setAlignment(Element.ALIGN_CENTER);
//    document.add(title);
//
//     //Add customer information
//    Paragraph customerInfo = new Paragraph("Nom : " + nom + "\nE-mail : " + mail + "\nNuméro de carte : " + numc);
//    customerInfo.setAlignment(Element.ALIGN_LEFT);
//    document.add(customerInfo);
//
//    // Add content
//    Paragraph content = new Paragraph("Vous avez effectué un paiement d'un montant de " + amount + " pound .");
//    content.setAlignment(Element.ALIGN_LEFT);
//    document.add(content);
//
//    // Add date
////    Paragraph date = new Paragraph("Date de paiment : "  + now + ".");
////    date.setAlignment(Element.ALIGN_LEFT);
////    document.add(date);
//
//    // Close document and outputStream.
//    document.close();
//    writer.close();
//
//     //Upload the file to Google Drive
//    Drive service = getDriveService(); // Get an instance of the Google Drive service
//   
//    File fileMetadata = new File();
//    
//    fileMetadata.setName("aaa");
//    fileMetadata.setParents(Collections.singletonList("Invoices")); // Set the folder ID of the Invoices folder on your Google Drive
//    java.io.File pdfFile = new java.io.File(pdfPath);
//    FileContent mediaContent = new FileContent("application/pdf", pdfFile);
//    File file = service.files().create(fileMetadata, mediaContent)
//            .setFields("id")
//            .execute();
//    System.out.println("File ID: " + file.getId());
//
//    // Show a notification to the user
//    Notifications notificationBuilder = Notifications.create()
//            .title("Ajout PARTICIPATION")
//            .text("Votre facture a bien été enregistrée.");
//            
//
//      
//      
//      
//            }
//      
//      private Drive getDriveService() throws IOException , GeneralSecurityException{
//    GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\scola\\Desktop\\sas.json"))
//            .createScoped(Collections.singleton(DriveScopes.DRIVE));
//                
//    HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
//    return new Drive.Builder(
//            new NetHttpTransport(),
//            new JacksonFactory(),
//            null)
//            .setApplicationName("doulicha")
//            .build();
//}
          
   

    public static void envoyer(String email) throws Exception{
         String username = "mohamedazyz.belaidi@esprit.tn";
             String password = "201JMT3416";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
              
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = prepareMessage(session, email);
        Transport.send(message);
        System.out.println("Message envoyé avec succès.");
    }
    
    private static Message prepareMessage(Session session, String email){ 
        try {
            String username = "mohamedazyz.belaidi@esprit.tn";
             String password = "201JMT3416";
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Confirmation de l'identification du compte");                
            message.setText("Bonjour,\n\nConfirmation de l'identification du compte.\n\nCordialement,\nL'équipe de support");
            return message;
        } catch (MessagingException e) {
            System.out.println(e);
        }
        return null;
    }

   
}


            
