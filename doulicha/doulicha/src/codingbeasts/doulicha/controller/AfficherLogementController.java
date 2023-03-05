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
import com.google.api.services.sheets.v4.Sheets;
import com.twilio.rest.api.v2010.account.sip.credentiallist.Credential;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.Optional;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import static org.apache.poi.sl.usermodel.ObjectMetaData.Application.lookup;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



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
    @FXML
    private Button excel;
    @FXML
    private TextField recherche;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherLogementList();
        recherche_avance();
        
        
       
    stat.setOnAction( event->{
        try{
            System.out.println("Bouton stat appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/StatistiqueLogement.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
               ex.getStackTrace();
    }
    });
         

    
    excel.setOnAction(event -> {
        System.out.println("excel appuyé");
        genererExcel(event);
    });
  
        
    home.setOnAction( event->{
        try{
            System.out.println("Bouton appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AccueilBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                ex.getStackTrace();
    }
    });
         
        ajouter_logement.setOnAction( event->{
        try{
            System.out.println("ajouter_logement appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AjouterLogement.fxml"));
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

    
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<Logement> filtereddata = new FilteredList<>(listL, b -> true);
        System.out.println(recherche.getText());
        recherche.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((Logement logement) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (logement.getNom_logement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (logement.getAdresse_logement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (logement.getType_logement().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        SortedList<Logement> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(table_view.comparatorProperty());
        table_view.setItems(filtereddata);
    }

      
    
      ServiceLogement ServL = new ServiceLogement();
        //ObservableList<Logement> listL = (ObservableList < Logement >) ServL.afficherLogement();
        ObservableList<Logement> listL = FXCollections.observableList(ServL.afficherLogement());
        
              public void genererExcel(ActionEvent event) {
    // Créer un nouveau Workbook Excel
    Workbook workbook = new XSSFWorkbook();

    // Créer une feuille dans le Workbook
      org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Logements");

    // Récupérer les données de la TableView
    ObservableList<Logement> logements = table_view.getItems();

    // Ajouter les en-têtes de colonne à la première ligne de la feuille
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Nom du logement");
    headerRow.createCell(1).setCellValue("Description du logement");
    headerRow.createCell(2).setCellValue("Adresse du logement");
    headerRow.createCell(3).setCellValue("Prix par nuitée");
    headerRow.createCell(4).setCellValue("Capacité du logement");
    headerRow.createCell(5).setCellValue("Type du logement");
    headerRow.createCell(6).setCellValue("État du logement");
    headerRow.createCell(7).setCellValue("Image du logement");

    // Ajouter les données de chaque logement à une ligne de la feuille
    int rowNum = 1;
    for (Logement logement : logements) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(logement.getNom_logement());
        row.createCell(1).setCellValue(logement.getDescription_logement());
        row.createCell(2).setCellValue(logement.getAdresse_logement());
        row.createCell(3).setCellValue(logement.getPrixNuitee_logement());
        row.createCell(4).setCellValue(logement.getCapacite_logement());
        row.createCell(5).setCellValue(logement.getType_logement());
        if (logement.getEtat_logement()==0){
        row.createCell(6).setCellValue("Disponible");}
        else{
           row.createCell(6).setCellValue("Réservé");}  
        
        row.createCell(7).setCellValue(logement.getImage_logement());
    }

    // Écrire les données dans un fichier Excel
    try {
        FileOutputStream outputStream = new FileOutputStream("logements.xlsx");
        workbook.write(outputStream);
        //workbook.close();
        //outputStream.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("TELECHARGEMENT DE VOTRE RESERVATION");
        alert.setHeaderText("Etes vous sur de vouloir télécharger votre réservation ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            File file = new File("logements.xlsx");
         // Ouvrir le fichier PDF avec l'application par défaut
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        
        }
       }
        
        
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
                            loader.setLocation(getClass().getResource("/codingbeasts/doulicha/view/ModifierLogement.fxml"));
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
