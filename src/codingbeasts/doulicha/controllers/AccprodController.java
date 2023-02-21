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
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

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
        
       addUpdateButtonToTable();
       addDleteButtonToTable();
        
    }

    

    
 private void addUpdateButtonToTable() {
        TableColumn<Produit, Void> column= new TableColumn<>();

        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = col -> {
    final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {
        private final Button updateButton = new Button("update");
        {
            updateButton.setOnAction(event ->{
                Produit produit = getTableView().getItems().get(getIndex());
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/modifier.fxml"));
                Parent parent = loader.load();
                ModifierController controller = loader.getController();
                System.out.println("produit"+produit);
                controller.setProduitToModify(produit);
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               System.out.println("errooooor"); 
            }
       });
        }
        @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(updateButton);
            }
        }
    };
    return cell;
};

        
        column.setCellFactory(cellFactory);
        TABLEPRODUIT.getColumns().add(column);
    }
    
    
    
    
        private void addDleteButtonToTable() {
        TableColumn<Produit, Void> column= new TableColumn<>();

        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = col -> {
    final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {
        private final Button deleteButton = new Button("Delete");
        {
            deleteButton.setOnAction(event -> {
                Produit data = getTableView().getItems().get(getIndex());
                ProduitCrud c=new ProduitCrud();
                c.deleteProduit(data.getID_produit());
                getTableView().getItems().remove(data);
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
        TABLEPRODUIT.getColumns().add(column);
    }
    
    
    
    
    
}
