/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.don;
import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.donCRUD;
import codingbeasts.doulicha.services.projetCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HistoriqueController implements Initializable {
 @FXML
    private VBox VBox1;
    @FXML
    private HBox HBox1;
    @FXML
    private ScrollPane ScrollPane1;
    @FXML
    private VBox donListe;
    private Button tfretourne3;
    @FXML
    private Label sommeDonsLabel;
    private donCRUD dis;
    @FXML
    private Button statistique;
    @FXML
    private Button tfAffichage;
    @FXML
    private Button home1;


    /**
     * Initializes the controller class.
     */
   
public double calculerSommeDons() {
    donCRUD dis = new donCRUD();
    List<don> dons = dis.afficherdon1();
    double somme = 0;
    for (don d : dons) {
        somme += d.getValeur_don();
        System.out.println(somme);
    }
    return somme;
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        donCRUD dis = new donCRUD();
    double somme = calculerSommeDons();
    sommeDonsLabel.setText("Total des dons " + somme + "£");
    sommeDonsLabel.setStyle("-fx-font-weight: bold;");
    List<don> dons = dis.afficherdon1();
   
    for (don d : dons) {
        VBox contentBox = new VBox();
        Label valeurLabel = new Label("Valeur : " + d.getValeur_don() + "€");
        valeurLabel.setStyle("-fx-font-weight: bold;");
        Label dateLabel = new Label("Date : " + d.getDate_don());
        

      
       
        contentBox.getChildren().addAll(valeurLabel, dateLabel);
        contentBox.setSpacing(10);
        contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
        contentBox.getStyleClass().add("vbox-style");
         
        donListe.getChildren().add(contentBox);
    }
    donListe.setSpacing(10);
}

    private void retourne3(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/AjouterDon.fxml"));
        Parent root = loader.load();
        Scene scene = tfretourne3.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
    public Map<String, Double> getDonneesDonsParObjectif() {
    donCRUD dis = new donCRUD();
    List<don> dons = dis.afficherdon1();
    projetCRUD diss = new projetCRUD();
    List<projet> projets = diss.afficherprojet();
    Map<String, Double> donnees = new HashMap<>();
    for (don d : dons) {
        for (projet p : projets) {
            if (p.getId_projet() == d.getID_projet()) {
                String objectif = p.getObjectif_projet()+"";
                double valeurDon = d.getValeur_don();
                if (donnees.containsKey(objectif)) {
                    donnees.put(objectif, donnees.get(objectif) + valeurDon);
                } else {
                    donnees.put(objectif, valeurDon);
                }
            }
        }
    }
    return donnees;
}

@FXML
   public void afficherGraphiqueDonsParObjectif(ActionEvent event) {
    Map<String, Double> donnees = getDonneesDonsParObjectif();

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    xAxis.setLabel("Objectifs");
    yAxis.setLabel("Montant total des dons");
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    ObservableList<XYChart.Data<String, Number>> donneesSeries = FXCollections.observableArrayList();

    for (Map.Entry<String, Double> entry : donnees.entrySet()) {
        String objectif = entry.getKey();
        double sommeDons = entry.getValue();
        XYChart.Data<String, Number> donneesBarre = new XYChart.Data<>(objectif, sommeDons);
        donneesSeries.add(donneesBarre);
        
    }
    series.getData().addAll(donneesSeries);
    barChart.getData().add(series);

    // Changer la couleur des barres
    for (XYChart.Data<String, Number> data : series.getData()) {
        Node barre = data.getNode();
        barre.setStyle("-fx-bar-fill: #00CED1;");
    }
    

    // Afficher le graphique dans une nouvelle fenêtre
    //Stage stage = new Stage();
   // stage.setScene(new Scene(barChart, 400, 400));
    //stage.show();
           Button but = new Button("retour");
            
            but.setOnAction((ActionEvent eventt) -> {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ListAffichageDon.fxml"));
            Parent root = loader.load();
            
            ListAffichageDonController ListAffichageDonController = loader.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("affichage2.css");
            Stage stage = (Stage)((Node) eventt.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HistoriqueController.class.getName()).log(Level.SEVERE, null, ex);
        }

                } );
VBox root = new VBox();
root.getChildren().add(but);
root.getChildren().add(barChart);

//but.setStyle("-fx-background-color: # #CCE3EA;");
Scene scene = new Scene(root, 1230, 750);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
}

    @FXML
    private void Affichage(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ListAffichageDon.fxml"));
        Parent root = loader.load();
        Scene scene = tfAffichage.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void home1(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/AccueilFront.fxml"));
        Parent root = loader.load();
        Scene scene = home1.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
   
    }    
    

