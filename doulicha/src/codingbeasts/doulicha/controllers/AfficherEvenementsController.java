/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AfficherEvenementsController implements Initializable {

    @FXML
    private TableView<evenement> tableEvents;
    @FXML
    private TableColumn<evenement, String> col_nom;
    @FXML
    private TableColumn<evenement, String> col_description;
    @FXML
    private TableColumn<evenement, String> col_type;
    @FXML
    private TableColumn<evenement, Date> col_datedebut;
    @FXML
    private TableColumn<evenement, Date> col_datefin;
    @FXML
    private TableColumn<evenement, Integer> col_capacite;
    @FXML
    private TableColumn<evenement, Integer> col_prix;
    @FXML
    private TableColumn<evenement, String> col_image;
    @FXML
    private TableColumn<evenement, String> col_lieu;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_gerer;
    @FXML
    private Button btn_gestionParticipations;
    @FXML
    private Button btn_retourhome;
    
     evenement evenement = null;
    @FXML
    private TableColumn<evenement, String> col_gerer;
    @FXML
    private TextField recherche;
 ServiceEvenement EV = new ServiceEvenement();
    ObservableList<evenement> events = FXCollections.observableArrayList(EV.afficherEvents());
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ServiceEvenement evenement = new ServiceEvenement();
        ObservableList<evenement> events = evenement.afficherEvents();
        afficherEvenement();
      search_event();   

btn_retourhome.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/homeAdmin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 

btn_gestionParticipations.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/AfficherParticipations.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
       
        btn_ajout.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/ajoutEvenement.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
    }    
    
       private void afficherEvenements() {
            ServiceEvenement evenenement = new ServiceEvenement();
        ObservableList<evenement> events = evenenement.afficherEvents();



        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_event"));
        col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_event"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type_event"));
        col_datedebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut_event"));
        col_datefin.setCellValueFactory(new PropertyValueFactory<>("dateFin_event"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite_event"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_event"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image_event"));

        tableEvents.setItems(events);
        
        search_event();
        
    }     
    
  
   @FXML
    private void gererEvenement(ActionEvent event)throws IOException {
        
         
    
        evenement ev= new evenement();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/views/gererEvenement.fxml"));
            Parent root = loader.load();
          GererEvenementController HomeScene = loader.getController();
          System.out.println("aaaaa");
          
          
            HomeScene.selected_item (
                    tableEvents.getSelectionModel().getSelectedItem().getID_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getNom_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getDescription_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getLieu_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getType_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getDateDebut_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getDateFin_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getCapacite_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getImage_event(),
                    tableEvents.getSelectionModel().getSelectedItem().getPrix_event()
                   );
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) btn_gerer.getScene().getWindow();
            window.setScene(new Scene(root));
            
             
    } 
    
    ServiceEvenement SE = new ServiceEvenement();
      //  ObservableList<evenement> events = FXCollections.observableList(SE.afficherEvenements());
    
         private void refreshTable() {
        try {
            //listL.clear();
            //ServL.afficherLogement();
            
             ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
             executor.scheduleAtFixedRate(() -> {
            // Update the data in the table
            events.clear();
            afficherEvenement();
        }, 0, 2, TimeUnit.SECONDS);
            

                
            }            
         catch (Exception ex) {
            Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
   private void afficherEvenement() {
          ServiceEvenement SE = new ServiceEvenement();
        ObservableList<evenement> events = SE.afficherEvents();

       // ObservableList<evenement> events = FXCollections.observableList(SE.afficherEvents());


        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_event"));
        col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_event"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type_event"));
        col_datedebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut_event"));
        col_datefin.setCellValueFactory(new PropertyValueFactory<>("dateFin_event"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite_event"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_event"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image_event"));
         tableEvents.setItems(events);
        // search_event();
        
        //add cell of button edit 
         Callback<TableColumn<evenement, String>, TableCell<evenement, String>> cellFoctory = (TableColumn<evenement, String> param) -> {
            // make cell containing buttons
            final TableCell<evenement, String> cell = new TableCell<evenement, String>() {
          @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        FontAwesomeIconView supprimerIcone = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT  );
                        FontAwesomeIconView modifierIcone = new FontAwesomeIconView(FontAwesomeIcon.EDIT );
                        FontAwesomeIconView listeIcone = new FontAwesomeIconView(FontAwesomeIcon.LIST );
                        
                        
                        supprimerIcone.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        modifierIcone.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                        listeIcone.setOnMouseClicked((MouseEvent event) -> {
                            
                           
                            
                           refreshTable();
                        });
                        
                        supprimerIcone.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                evenement = tableEvents.getSelectionModel().getSelectedItem();
                                SE.supprimerEvenement(evenement.getID_event());
                                
                                
                            } catch (Exception ex) {
                                Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                            
                           refreshTable();
                        });
                        
                        modifierIcone.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                
                                evenement ev= new evenement();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/views/gererEvenement.fxml"));
                                Parent root = loader.load();
                                GererEvenementController HomeScene = loader.getController();
                                System.out.println("aaaaa");
                                
                                
                                HomeScene.selected_item (
                                        tableEvents.getSelectionModel().getSelectedItem().getID_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getNom_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getDescription_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getLieu_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getType_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getDateDebut_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getDateFin_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getCapacite_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getImage_event(),
                                        tableEvents.getSelectionModel().getSelectedItem().getPrix_event()
                                );
                                System.out.println("bbbbbbbbbbbbb");
                                
                                Stage window = (Stage) btn_gerer.getScene().getWindow();
                                window.setScene(new Scene(root));
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            

                        });
                        HBox managebtn = new HBox(modifierIcone, supprimerIcone,listeIcone);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(supprimerIcone, new Insets(2, 2, 0, 3));
                        HBox.setMargin(modifierIcone, new Insets(2, 3, 0, 2));
                        HBox.setMargin(listeIcone, new Insets(2, 1, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         col_gerer.setCellFactory(cellFoctory);
        tableEvents.setItems(events);

    } 
   
   void search_event() {
        evenement ev = new evenement();
        
      
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description_event"));
        col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu_event"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type_event"));
        col_datedebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut_event"));
        col_datefin.setCellValueFactory(new PropertyValueFactory<>("dateFin_event"));
        col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite_event"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_event"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image_event"));

        tableEvents.setItems(events);
        
       
        FilteredList<evenement> filteredData = new FilteredList<>(events, b -> true);
       
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((evenement event) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getNom_event().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (event.getDescription_event().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (event.getType_event().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (event.getLieu_event().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }

                                else if (String.valueOf(event.getPrix_event()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(event.getCapacite_event()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                
                                if (event.getImage_event().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                
                               
				     else  
				    	 return false; // Does not match.
			});
		});
        SortedList<evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableEvents.comparatorProperty());
        tableEvents.setItems(sortedData);
   }   
    
    
    
}