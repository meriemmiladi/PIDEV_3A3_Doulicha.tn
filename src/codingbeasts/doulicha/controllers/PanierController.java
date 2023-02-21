package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Commande;
import codingbeasts.doulicha.entities.LigneCommande;
import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.services.CommandeCrud;
import codingbeasts.doulicha.services.LigneCommandeCrud;
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


public class PanierController implements Initializable {

    Map<Produit, Integer> produitMap;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        confirme.setOnAction(event ->{
            try{
            LocalDateTime dateTime = LocalDateTime.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateTime.toLocalDate());
                Commande c=new Commande(sqlDate,1);
                CommandeCrud cc=new CommandeCrud();
                commande=cc.ajouterCommande2(c, 1);
                produitMap.forEach((key,value)->{
                    LigneCommandeCrud lcc=new LigneCommandeCrud();
                    LigneCommande lc=new LigneCommande(commande.getID_commande(), key.getID_produit(), value);
                    lcc.ajouterLigneCommande2(lc);
                });
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
             System.out.println("errrrrrrrrrrr");
            }
        
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
    
}
