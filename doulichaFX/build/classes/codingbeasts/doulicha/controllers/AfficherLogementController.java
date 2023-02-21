/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import codingbeasts.doulicha.entities.Logement;
import codingbeasts.doulicha.services.ServiceLogement;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import codingbeasts.doulicha.entities.Logement;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class AfficherLogementController implements Initializable {

    @FXML
    private Button ajouter_logement;
    @FXML
    private Button afficher_logement;
    @FXML
    private TableView<Logement> table_view;
    @FXML
    private TableColumn<Logement, Integer> ID_logement;
    @FXML
    private TableColumn<Logement, String> nom_logement;
    @FXML
    private TableColumn<Logement, String> description_logement;
    @FXML
    private TableColumn<Logement, String> adresse_logement;
    @FXML
    private TableColumn<Logement, Double > prixNuitee_logement;
    @FXML
    private TableColumn<Logement, Integer> capacite_logement;
    @FXML
    private TableColumn<Logement, String> type_logement;
    @FXML
    private TableColumn<Logement, Integer> etat_logement;
    @FXML
    private TableColumn<Logement, String> image_logement;
    @FXML
    private Button home;
    @FXML
    private TableColumn<Logement, String> editcol;
    
    Logement Logement = null;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherLogementList();
        
        home.setOnAction( event->{
        try{
            System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(HomeBackController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
         
        ajouter_logement.setOnAction( event->{
        try{
            System.out.println("ajouter_logement appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AjouterLogement.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
    }  
    
      ServiceLogement ServL = new ServiceLogement();
        //ObservableList<Logement> listL = (ObservableList < Logement >) ServL.afficherLogement();
        ObservableList<Logement> listL = FXCollections.observableList(ServL.afficherLogement());
        
        
    private void refreshTable() {
        try {
            //listL.clear();
            //ServL.afficherLogement();
            
             ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
             executor.scheduleAtFixedRate(() -> {
            // Update the data in the table
            listL.clear();
            afficherLogementList();
        }, 0, 2, TimeUnit.SECONDS);
            

                
            }            
         catch (Exception ex) {
            Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    
    private void afficherLogementList() {
        ServiceLogement ServL = new ServiceLogement();
        //ObservableList<Logement> listL = (ObservableList < Logement >) ServL.afficherLogement();
        ObservableList<Logement> listL = FXCollections.observableList(ServL.afficherLogement());
        
        

        
        ID_logement.setCellValueFactory(new PropertyValueFactory<>("ID_logement"));
        nom_logement.setCellValueFactory(new PropertyValueFactory<>("nom_logement"));
        description_logement.setCellValueFactory(new PropertyValueFactory<>("description_logement"));
        adresse_logement.setCellValueFactory(new PropertyValueFactory<>("adresse_logement"));
        prixNuitee_logement.setCellValueFactory(new PropertyValueFactory<>("prixNuitee_logement"));
        capacite_logement.setCellValueFactory(new PropertyValueFactory<>("capacite_logement"));
        type_logement.setCellValueFactory(new PropertyValueFactory<>("type_logement"));
        etat_logement.setCellValueFactory(new PropertyValueFactory<>("etat_logement"));
        image_logement.setCellValueFactory(new PropertyValueFactory<>("image_logement"));
        
        
        //add cell of button edit 
         Callback<TableColumn<Logement, String>, TableCell<Logement, String>> cellFoctory = (TableColumn<Logement, String> param) -> {
            // make cell containing buttons
            final TableCell<Logement, String> cell = new TableCell<Logement, String>() {
          @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                Logement = table_view.getSelectionModel().getSelectedItem();
                                ServL.supprimerLogement(Logement.getID_logement());
                                refreshTable();
                                
                                
                            } catch (Exception ex) {
                                Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Logement = table_view.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/ModifierLogement.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherLogementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierLogementController ModifierLogementController = loader.getController();
                            ModifierLogementController.setUpdate(true);
                            ModifierLogementController.recupererID(Logement.getID_logement());
                            
                                //ServL.supprimerLogement(Logement.getID_logement());
                            ModifierLogementController.setTextField(Logement.getID_logement(),Logement.getNom_logement(), 
                            Logement.getDescription_logement(),Logement.getAdresse_logement(), Logement.getPrixNuitee_logement(),Logement.getCapacite_logement(),Logement.getType_logement(),Logement.getImage_logement());
                            Parent parent = loader.getRoot();
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show(); 
                            //stage.initStyle(StageStyle.UTILITY);
                            //stage.show();
                            //ServL.modifierLogement(Logement);
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editcol.setCellFactory(cellFoctory);
       
       
        table_view.setItems(listL);

    }
    
     
}
