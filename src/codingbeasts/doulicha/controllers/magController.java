/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.Commande;
import codingbeasts.doulicha.entities.LigneCommande;
import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.services.CommandeCrud;
import codingbeasts.doulicha.services.LigneCommandeCrud;
import codingbeasts.doulicha.services.ProduitCrud;
import codingbeasts.doulicha.tests.MyListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class magController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private VBox chosenCard;
    @FXML
    private Label NameLable;
    @FXML
    private Label PriceLabel;
    @FXML
    private ImageView Img;
    @FXML
    private Button ajouterpanier;
    @FXML
    private Spinner<Integer> spinnerId;
    @FXML
    private Button panier;       
    private Image image;
    private MyListener myListener;
    ProduitCrud p = new ProduitCrud();
    List<Produit> produits = new ArrayList<>();
    Produit produitActuel=new Produit();
    int qttProduit=0;
    static Map<Produit,Integer> produitMap=new HashMap<Produit,Integer>();
    @FXML
    private ComboBox<?> sort;
    @FXML
    private Button mesachats;
    private void setChosen(Produit produit) {
        produitActuel=produit;
        SpinnerValueFactory<Integer> valueFactory =new SpinnerValueFactory.IntegerSpinnerValueFactory(1, produit.getQuantite_produit(), 1);
        spinnerId.setValueFactory(valueFactory);
        NameLable.setText(produit.getLibelle_produit());
        PriceLabel.setText(MainView.CURRENCY + produit.getPrixUvente_produit());
        image = new Image(getClass().getResourceAsStream(produit.getImage_produit()));
        Img.setImage(image);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ajouterpanier.setOnAction(event ->{
           produitMap.put(produitActuel, spinnerId.getValue());
          
             LigneCommandeCrud ligneCommandeCrud=new LigneCommandeCrud();
             LigneCommandeCrud lcc=new LigneCommandeCrud();
             
             CommandeCrud commandeCrud=new CommandeCrud();
             Commande commande=commandeCrud.lastCommand();
             int id_commande = commande.getID_commande();
             
                LigneCommande lc = new LigneCommande(id_commande, (int) produitActuel.getID_produit(), spinnerId.getValue() );
            
              lcc.ajouterLigneCommande2(lc);
            
       });
        
        
        

        panier.setOnAction(event ->{
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/panier.fxml"));
                Parent parent = loader.load();
                PanierController controller = loader.getController();
                controller.setProduitToModify(produitMap);
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
               System.out.println(ex); 
            }
       });

        
        ProduitCrud pc=new ProduitCrud();
        produits.addAll(pc.afficherProduit());
        if (produits.size() > 0) {
            setChosen(produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosen(produit);
                }
            };
        }
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < produits.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/codingbeasts/doulicha/view/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(produits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
        }
        
        //////////////////recherche//////////
        
        ///////////////////////////////////////
        
    }

    }  
    
