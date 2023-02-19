/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.services.ProduitCrud;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AjoutController implements Initializable {

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
    @FXML
    private TableView<Produit> TABLEPRODUIT;
    @FXML
    private TableColumn<Produit, String> ID;
    @FXML
    private TableColumn<Produit, String> LIBELLE;
    @FXML
    private TableColumn<Produit, String> QUANTITE;
    @FXML
    private TableColumn<Produit, String> PTIXACHAT;
    @FXML
    private TableColumn<Produit, String> PRIXVENTE;
    @FXML
    private TableColumn<Produit, String> CATEGORIE;
    @FXML
    private TableColumn<Produit, String> IMAGE;
    @FXML
    private RadioButton btnretour;


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
        ProduitCrud pc = new ProduitCrud();
        pc.ajouterProduit2(p);
        
        
        
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
}
    
        
//    }    Notifications notificationBuilder = Notifications.create()
//            .title("Ajout EVENEMENT")
//               .text("votre évènement a bien été ajouté.")
//               .graphic(null)
//               .hideAfter(Duration.seconds(5))
//              .position(Pos.BOTTOM_RIGHT);
//       notificationBuilder.showInformation();
//        
        
    
    
    
    
    

