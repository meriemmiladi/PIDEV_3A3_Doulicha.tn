/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.interfaces.MyListener;
import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EvenementClientController implements Initializable {
    
     private MyListener myListener;
     
    List<evenement> event;
    private List<evenement> events = new ArrayList<>();

    @FXML
    private GridPane event_grid;
    @FXML
    private VBox evenement_choisi;
    @FXML
    private Button btn_participer;
    
   //private int ID_event;
    @FXML
    private Label event_nom;
    @FXML
    private ImageView event_image;
    @FXML
    private Label event_description;
    @FXML
    private Label event_lieu;
    @FXML
    private Label event_dateDebut;
    @FXML
    private Label event_dateFin;
    @FXML
    private Label event_prix;
    @FXML
    private Label event_id;
    @FXML
    private Button btn_mesparticipations;
    @FXML
    private Button btn_retourhome;
    private ComboBox<?> categorie;
    @FXML
    private ComboBox<String> sort;
    @FXML
    private TextField SearchBar;
    

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceEvenement SE = new ServiceEvenement();
        List<evenement> ev = SE.afficherEvents();
        loadEvents(ev);
        SearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            ServiceEvenement SEv = new ServiceEvenement();
            List<evenement> l ;
            l =  SE.searchEvenement(newValue);
             loadEvents(l);
        });
      
        sort.getItems().add("Prix descendant");
        sort.getItems().add("Prix ascendant");
        
        sort.getSelectionModel().select("Trier");
           ServiceEvenement se = new ServiceEvenement();
            this.events =  se.afficherEvents();
            loadEvents(this.events);
      
        if (events.size() > 0) {
            choisirEvent(events.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(evenement test) {
                    choisirEvent(test);
                }
            };
            
        }
        
       /* event_grid.getChildren().clear();
       // ServiceEvenement SE = new ServiceEvenement();
       // List<evenement> ev = SE.afficherEvenements();
        System.out.println("evenement " + ev.toString());
        int row = 1, cl =0;
            try{
                for(evenement event : ev ){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/detailEvenement.fxml"));
                    
                    Node postbox = loader.load();
                    System.out.println("TEST TEST");
                    DetailEvenementController evc = loader.getController();
                    evc.setData(event, myListener, this.evenement_choisi);
                   
                    if(cl== 1){
                         cl= 0;
                         row++;
                    }
                    this.event_grid.add(postbox, cl++, row);
                    GridPane.setMargin(postbox,new Insets(10));
                }
                
            }catch(IOException e){
                System.out.println("no load for event");
                   e.printStackTrace();
            } */
            
            
             
         btn_mesparticipations.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/participationClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
          btn_retourhome.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/homeClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
              Logger.getLogger(AfficherEvenementsController.class.getName()).log(Level.SEVERE, null, ex); 
            }
        }); 
            
    }  
    
    
private void loadEvents(List<evenement> ev){
   /*   ServiceEvenement SE = new ServiceEvenement();
        List<evenement> ev = SE.afficherEvenements();*/
   
    event_grid.getChildren().clear();
        int row = 1, cl =0;
            try{
                for(evenement eve : ev ){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/detailEvenement.fxml"));
                    
                    Node postbox = loader.load();
                    //System.out.println("TEST TEST");
                    DetailEvenementController evc = loader.getController();
                    evc.setData(eve, myListener, this.evenement_choisi);
                   
                    if(cl== 1){
                         cl= 0;
                         row++;
                    }
                    this.event_grid.add(postbox, cl++, row);
                   // GridPane.setMargin(postbox,new Insets(10));
                event_grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                event_grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                event_grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                event_grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                event_grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                event_grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(postbox, new Insets(10));
                }
            }catch(IOException e){
                System.out.println("no load for event in client");
                   e.printStackTrace();
            }
}

    @FXML
    private void onSortSelected(ActionEvent event) {
         ServiceEvenement SE = new ServiceEvenement(); 
        int i = sort.getSelectionModel().getSelectedIndex();
        System.out.println(i);
        switch(i){
            case 0:
                this.events =  SE.triDesc(0);
                loadEvents(this.events);
                break;
                case 1:
                this.events =  SE.triAsc(1);
                loadEvents(this.events);
                break;
                
            
        }
    }
    
     private void choisirEvent(evenement event) {
        
        
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/detailEvenement.fxml"));
    //Node postbox = loader.load();
   /* DetailEvenementController cp = loader.getController();
    cp.setData(event, myListener, this.evenement_choisi);
    //event_nom.setText(event.getNom_event());
    //event_id.setText(String.valueOf(event.getID_event()));
    
    int ID_event=event.getID_event();
        System.out.println(ID_event + "ay haja");*/
    try {
            Parent detailEvenement = loader.load();
            DetailEvenementController cp = loader.getController();
            cp.setData(event, myListener, this.evenement_choisi);
        } catch (IOException ex) {
            Logger.getLogger(EvenementClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    @FXML
    private void jeparticipe(ActionEvent event) {
        
        evenement ev= new evenement();
            
           FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/codingbeasts/doulicha/views/ajoutParticipation.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EvenementClientController.class.getName()).log(Level.SEVERE, null, ex);
                            }
          AjoutParticipationController HomeScene = loader.getController();
          System.out.println("aaaaa");
          HomeScene.selected_item2(Integer.parseInt(event_id.getText()));
           AjoutParticipationController AjoutParticipationController = loader.getController();
           AjoutParticipationController.setUpdate(true);
       
          System.out.println(ev.getID_event());
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) btn_participer.getScene().getWindow();
            Parent parent = loader.getRoot();
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
    }


   
}
