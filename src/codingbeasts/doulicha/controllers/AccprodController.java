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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AccprodController implements Initializable {

    @FXML
    private ImageView a;
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
    private Button btnajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
       ProduitCrud p = new ProduitCrud();
       List<Produit> produits = p.afficherProduit();
       afficherprod();
       
       
       btnajout.setOnAction(event ->{
           try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ajout.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               System.out.println("errooooor"); 
            }
       });
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
