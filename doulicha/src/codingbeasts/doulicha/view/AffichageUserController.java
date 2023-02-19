/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.services.UtilisateurCRUD;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AffichageUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Utilisateur> txttable;

    @FXML
    private TableColumn<Utilisateur, String> txtnom;

    @FXML
    private TableColumn<Utilisateur, String> txtprenom;

    @FXML
    private TableColumn<Utilisateur, String> txtemail;

    @FXML
    private TableColumn<Utilisateur, String> txtpassword;

    @FXML
    private TableColumn<Utilisateur, String> txtrole;
    private void loadUser() {
        
       // col_id.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom_user"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email_user"));
        txtpassword.setCellValueFactory(new PropertyValueFactory<>("mdp_user"));
        txtrole.setCellValueFactory(new PropertyValueFactory<>("role_user"));
        UtilisateurCRUD uc = new UtilisateurCRUD();
        List<Utilisateur> myList = new ArrayList<>();
        myList = uc.afficherUtilisateur();
        System.out.println("nnn : " + myList);
        ObservableList<Utilisateur> observableArrayList
                = FXCollections.observableArrayList(uc.afficherUtilisateur());
        txttable.setItems(observableArrayList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUser();
    } 
    
}
