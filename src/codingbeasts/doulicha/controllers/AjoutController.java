/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.services.ProduitCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;


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
    
}
