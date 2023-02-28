/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.categorie_avis;
import codingbeasts.doulicha.services.serviceAvis;
import codingbeasts.doulicha.services.serviceCategorie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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
    }    
    

