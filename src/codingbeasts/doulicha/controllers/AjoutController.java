/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.services.ProduitCrud;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AjoutController implements Initializable {
    String xamppFolderPath = "C:/xampp/htdocs/img/";
    @FXML
    private TextField prixachat;
    @FXML
    private TextField prixvente;
    @FXML
    private TextField image;
    @FXML
    private TextField quantite;
    @FXML
    private TextField libelle;
    @FXML
    private SplitMenuButton categorie;
    @FXML
    private Button ajouterprod;
    private TableView<Produit> TABLEPRODUIT;
    private TableColumn<Produit, String> ID;
    private TableColumn<Produit, String> LIBELLE;
    private TableColumn<Produit, String> QUANTITE;
    private TableColumn<Produit, String> PTIXACHAT;
    private TableColumn<Produit, String> PRIXVENTE;
    private TableColumn<Produit, String> CATEGORIE;
    private TableColumn<Produit, String> IMAGE;
    @FXML
    private Button btnretour;
    @FXML
    private ImageView nolibelle;
    @FXML
    private Button importerbtn;
    @FXML
    private ImageView img;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    MenuItem ac1 = new MenuItem("vêtements");
    ac1.setOnAction(event -> categorie.setText("vêtements"));

    MenuItem ac2 = new MenuItem("bijoux");
    ac2.setOnAction(event -> categorie.setText("bijoux"));

    categorie.getItems().addAll(ac1, ac2);
    
    btnretour.setOnAction(event ->{
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/Accprod.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             System.out.println("errrrrrrrrrrr");
            }
    
    
    
    });
    }  
    @FXML
    private void ajouterProd(ActionEvent event) {
        
        String libelle_produit=libelle.getText();
        int quantite_produit=Integer.parseInt(quantite.getText());
        double prixUachat_produit=Double.parseDouble(prixachat.getText());
        double prixUvente_produit=Double.parseDouble(prixvente.getText());
        String categorie_produit=categorie.getText();
        String image_produit=image.getText();
        
        Produit p = new Produit(libelle_produit,quantite_produit,prixUachat_produit,prixUvente_produit,categorie_produit,image_produit);
        if(testSaisie()) {
        ProduitCrud pc = new ProduitCrud();       
        pc.ajouterProduit2(p);     
        Notifications notificationBuilder = Notifications.create().title("Ajout du produit").text("votre produit a bien été ajouté.").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.BOTTOM_RIGHT);
       notificationBuilder.showInformation();
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/Accprod.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("codingbeasts.doulicha.controllers.AjoutController.ajouterProd()");
        }
  
        
    }
    }
    public void afficherprod(){
        ProduitCrud c = new ProduitCrud();
       List<Produit> produits = c.afficherProduit();
       ID.setCellValueFactory(new PropertyValueFactory<>("ID_produit"));
       LIBELLE.setCellValueFactory(new PropertyValueFactory<>("libelle_produit"));
       QUANTITE.setCellValueFactory(new PropertyValueFactory<>("quantite_produit"));
       PTIXACHAT.setCellValueFactory(new PropertyValueFactory<>("prixUachat_produit"));
       PRIXVENTE.setCellValueFactory(new PropertyValueFactory<>("prixUvente_produit"));
       CATEGORIE.setCellValueFactory(new PropertyValueFactory<>("categorie_produit"));
       IMAGE.setCellValueFactory(new PropertyValueFactory<>("image_produit"));
        TABLEPRODUIT.getItems().addAll(produits);
    }
    
    private Boolean testSaisie() {
        String erreur = "";
      
        if (!testlibelle()) {
            erreur = erreur + ("le libellé doit contenir que des caractéres et sa longeur =>3 \n");
        }
          return testlibelle();
    }
    private Boolean testlibelle() {
        int nbNonChar = 0;
        for (int i = 1; i < libelle.getText().trim().length(); i++) {
            char ch = libelle.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && libelle.getText().trim().length() >= 5) {
            return true;
        } else {
            nolibelle.setImage(new Image("/codingbeasts/doulicha/images/faux.png"));
            return false;

        }
    }

    @FXML
    private void importer(ActionEvent event) {
        
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("file !");

            Stage stage = new Stage();
            fileChooser.getExtensionFilters().addAll(
                    //les extensions
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
                );
            File file = fileChooser.showOpenDialog(stage);
            // le fichier va se copie dans les xampp
            Path source = file.toPath();
            String fileName = file.getName();
            Path destination = Paths.get(xamppFolderPath + fileName);
            try {
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    BufferedImage bufferedImage = ImageIO.read(file);
                    Image imagee = SwingFXUtils.toFXImage(bufferedImage, null);
                    image.setText(fileName);
                    img.setImage(imagee);
                } catch (IOException ex) {
                    System.out.println("could not get the image");
                }
            String imagePath = "images/" + fileName;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
        
//    }    Notifications notificationBuilder = Notifications.create()
//            .title("Ajout EVENEMENT")
//               .text("votre évènement a bien été ajouté.")
//               .graphic(null)
//               .hideAfter(Duration.seconds(5))
//              .position(Pos.BOTTOM_RIGHT);
//       notificationBuilder.showInformation();
//        
        
    
    
    
    
    

