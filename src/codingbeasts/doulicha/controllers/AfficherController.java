/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.services.ProduitCrud;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AfficherController implements Initializable {

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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ID.setCellValueFactory(new PropertyValueFactory<>("ID_produit"));
       LIBELLE.setCellValueFactory(new PropertyValueFactory<>("libelle_produit"));
       QUANTITE.setCellValueFactory(new PropertyValueFactory<>("quantite_produit"));
       PTIXACHAT.setCellValueFactory(new PropertyValueFactory<>("prixUachat_produit"));
       PRIXVENTE.setCellValueFactory(new PropertyValueFactory<>("prixUvente_produit"));
       CATEGORIE.setCellValueFactory(new PropertyValueFactory<>("categorie_produit"));
       IMAGE.setCellValueFactory(new PropertyValueFactory<>("image_produit"));
       
       ProduitCrud p = new ProduitCrud();
       List<Produit> produits = p.afficherProduit();
       TABLEPRODUIT.getItems().addAll(produits);
       
    }    
    
}
