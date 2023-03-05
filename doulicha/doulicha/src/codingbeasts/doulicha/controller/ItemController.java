/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import codingbeasts.doulicha.services.MyListener1;
import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.view.MainClass;
import javafx.scene.image.Image;
import sun.tools.jar.Main;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private Produit produit ;
    private MyListener1 myListener;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produit);
    }
     public void setData(Produit produit, MyListener1 myListener) {
        this.produit = produit;
        this.myListener = myListener;
        nameLabel.setText(produit.getLibelle_produit());
        priceLable.setText(MainClass.CURRENCY + produit.getPrixUvente_produit());
        Image image = new Image(getClass().getResourceAsStream(produit.getImage_produit()));
        img.setImage(image);
    }
    
    
   
}
