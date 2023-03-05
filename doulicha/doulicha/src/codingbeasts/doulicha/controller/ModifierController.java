/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

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
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ModifierController implements Initializable {

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
    private Button modifierbtn;
    @FXML
    private SplitMenuButton categorie;
    @FXML
    private Button btnretour;
    @FXML
    private ImageView nolibelle;
    @FXML
    private ImageView img;
    @FXML
    private Button importerbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          MenuItem ac1 = new MenuItem("vêtements");
    ac1.setOnAction(event -> categorie.setText("vêtements"));

    MenuItem ac2 = new MenuItem("bijoux");
    ac2.setOnAction(event -> categorie.setText("bijoux"));
        
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
    private Produit produitToModify;
    private int idproduit=0;
    public void setProduitToModify(Produit produit) {
        this.produitToModify = produit;
        idproduit=produit.getID_produit();
        libelle.setText(produit.getLibelle_produit());
        quantite.setText(String.valueOf(produit.getQuantite_produit()));
        prixachat.setText(String.valueOf(produit.getPrixUachat_produit()));
        prixvente.setText(String.valueOf(produit.getPrixUvente_produit()));
        categorie.setText(produit.getCategorie_produit());
        image.setText(produit.getImage_produit());
        // TODO : afficher l'image du produit dans l'ImageView
    }

    @FXML
    private void modifierProd(ActionEvent event) {
        try {
            Produit produit=new Produit();
            
            produit.setID_produit(idproduit);
            produit.setLibelle_produit(libelle.getText());
            produit.setQuantite_produit(Integer.parseInt(quantite.getText()));
            produit.setPrixUachat_produit(Double.parseDouble(prixachat.getText()));
            produit.setPrixUvente_produit(Double.parseDouble(prixvente.getText()));
            produit.setCategorie_produit(categorie.getText());
            produit.setImage_produit(image.getText());
            
            
            ProduitCrud produitCrud = new ProduitCrud();
            produitCrud.modifierProduit(produit.getID_produit(), produit);
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/Accprod.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    String xamppFolderPath = "C:/xampp/htdocs/img/";
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