/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.services.LigneCommandeCrud;
import codingbeasts.doulicha.services.ProduitCrud;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException ;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author aziz
 */
public class MainView extends Application {
    public static final String CURRENCY = "$";
    
    @Override
    public void start(Stage primaryStage) {
        
       
        try {

//            Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/magasin.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/accprod.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/Accueil.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/panier.fxml"));
            
            Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
            int width = (int)size.getWidth();
            int height = (int)size.getHeight();
            Scene scene = new Scene(root, width,height);
            primaryStage.setTitle("gestion produit");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }


       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        LigneCommandeCrud LCC =new LigneCommandeCrud();
        List<Integer> lista = LCC.retourDesIdsProduits(40);
        List<Integer> Quantities = LCC.retourDesIdsQuantite(45);
        
        ProduitCrud Pcc = new ProduitCrud();
       // System.out.println(Pcc.getSelectedProducts(lista).stream().findFirst().get().getID_produit());
//        System.out.println(Pcc.getSelectedProducts(lista).stream().findFirst().get().getLibelle_produit());
//       
//        System.out.println(Quantities.get(0));


        launch(args);
    }
    
}
