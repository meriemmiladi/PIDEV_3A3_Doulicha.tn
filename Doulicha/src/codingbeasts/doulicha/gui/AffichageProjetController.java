/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.projetCRUD;
import codingbeasts.doulicha.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AffichageProjetController implements Initializable {
    @FXML
    private TableView<projet> table_projet;

    @FXML
    private TableColumn<projet, Integer> col_ID;

    @FXML
    private TableColumn<projet, String> col_Nom;

    @FXML
    private TableColumn<projet, String> col_Description;

    @FXML
    private TableColumn<projet,Float> col_Objectif;

    @FXML
    private TableColumn<projet, Integer> col_Etat;

    @FXML
    private TableColumn<projet, String> col_Image;  
   
    
    
  
    @FXML
    private ImageView sortie;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Affichage;
    /**
     * Initializes the controller class.
     */
  /*  
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    public void Connect(){
        try {
            Class.forName("con.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/doulicha","root","");
        }catch (ClassNotFoundException ex ){
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
   */
   private void loadUser() {
        
       // col_id.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        col_ID.setCellValueFactory(new PropertyValueFactory<>("id_projet"));
        col_Nom.setCellValueFactory(new PropertyValueFactory<>("nom_projet"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("description_projet"));
        col_Objectif.setCellValueFactory(new PropertyValueFactory<>("objectif_projet"));
        col_Etat.setCellValueFactory(new PropertyValueFactory<>("etat_projet"));
        col_Image.setCellValueFactory(new PropertyValueFactory<>("image_projet"));
        projetCRUD uc = new projetCRUD();
        List<projet> myList = new ArrayList<>();
        myList = uc.afficherprojet();
        System.out.println("nnn : " + myList);
        ObservableList<projet> observableArrayList
                = FXCollections.observableArrayList(uc.afficherprojet());
        table_projet.setItems(observableArrayList);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        loadUser();
        
        }    
    
  
    
}
