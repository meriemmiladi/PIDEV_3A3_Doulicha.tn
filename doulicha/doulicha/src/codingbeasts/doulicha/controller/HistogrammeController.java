/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.categorie_avis;
import codingbeasts.doulicha.services.serviceAvis;
import codingbeasts.doulicha.services.serviceCategorie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HistogrammeController implements Initializable {

    @FXML
    private BarChart<?, ?> histogramme;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // Récupération des catégories
        List<categorie_avis> categories = new serviceCategorie().affichercategorie();

        // Configuration de l'axe X
        x.setLabel("Catégorie");

        // Configuration de l'axe Y
        y.setLabel("Moyenne des notes");

        // Configuration du graphique
        histogramme.setTitle("Moyenne des notes par catégorie");
        histogramme.setLegendVisible(false);

        // Ajout des données
        XYChart.Series series = new XYChart.Series();
        for (categorie_avis categorie : categories) {
            double moyenneNotes = new serviceAvis().calculerMoyenneNotesParCategorie(categorie.getID_categorie());
            series.getData().add(new XYChart.Data(categorie.getNom_categorie(), moyenneNotes));
            
            
        }
        histogramme.getData().addAll(series);
        
    }

    @FXML
    private void retour(ActionEvent event) {
         try{
            
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/affichercategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
           ex.getStackTrace();
    }
    }
    }    
    

