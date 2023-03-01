/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.entities.avis;
import codingbeasts.doulicha.services.serviceAvis;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouteravisController implements Initializable {

    @FXML
    private ImageView idimageavis;
    @FXML
    private ImageView iddoulicha;
    @FXML
    private Button idafficheravis;
    @FXML
    private Button idajouteravis;
    @FXML
    private TextField idcategorie;
    @FXML
    private TextField idcontenu;
    @FXML
    private TextField idnote;
    @FXML
    private TextField idtype;
    
    private int idCategorie;
    @FXML
    private ComboBox<String> idnomutilisateur;
    @FXML
    private ComboBox<String> idnomevent;
    @FXML
    private ComboBox<String> idnomlogement;
    @FXML
    private Button idajouteravis1;
    @FXML
    private Button btn4;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        Connection cnx = MyConnection.getInstance().getCnx();
        String query = "SELECT nom_user FROM utilisateur";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nom_user");
            idnomutilisateur.getItems().add(nom);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
            try {
        Connection cnx = MyConnection.getInstance().getCnx();
        String query = "SELECT nom_logement FROM logement";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nom_logement");
            idnomlogement.getItems().add(nom);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
                try {
        Connection cnx = MyConnection.getInstance().getCnx();
        String query = "SELECT nom_event FROM evenement";
        PreparedStatement pst = cnx.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String nom = rs.getString("nom_event");
            idnomevent.getItems().add(nom);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    
    }
    public void setIdCategorie(int ID_categorie) {
    this.idCategorie = ID_categorie;
    }
    
    @FXML
    private void idajouteravis(ActionEvent event) throws SQLException, IOException {
    String idCategorieString = idcategorie.getText();
    String nomUtilisateur = idnomutilisateur.getValue();
    String nomLogement = idnomlogement.getValue();
    String nomEvent = idnomevent.getValue();
    String noteAvisString = idnote.getText();
    String contenuAvis = idcontenu.getText();
    String typeAvis = idtype.getText();
if(nomUtilisateur == null) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setContentText("Veuillez sélectionner une valeur pour le champ Nom d'utilisateur.");
    alert.showAndWait();
} 
/* else if(nomLogement == null && nomEvent == null) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setContentText("Veuillez sélectionner une valeur pour le champ Nom de logement ou Nom d'événement.");
    alert.showAndWait();
} else if(nomLogement != null && nomEvent != null) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setContentText("Veuillez sélectionner une seule valeur pour le champ Nom de logement ou Nom d'événement.");
    alert.showAndWait();
} */
    
        if (idCategorieString.isEmpty() || noteAvisString.isEmpty() || contenuAvis.isEmpty() || typeAvis.isEmpty()) {
        // Affichage d'un message d'erreur si un champ est vide
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Certains champs sont vides !");
        alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
        alert.showAndWait();
        return;
    }
    
    int idCategorie = Integer.parseInt(idCategorieString);

    // Vérification que noteAvis est un entier
    int noteAvis;
try {
    noteAvis = Integer.parseInt(noteAvisString);
    if (noteAvis < 0 || noteAvis > 5) {
        // Affichage d'un message d'erreur si noteAvis n'est pas comprise entre 0 et 5
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("La note doit être un entier compris entre 0 et 5 !");
        alert.setContentText("Veuillez entrer une note entière entre 0 et 5 pour continuer.");
        alert.showAndWait();
        return;
    }
} catch (NumberFormatException e) {
    // Affichage d'un message d'erreur si noteAvis n'est pas un entier
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText("La note doit être un entier compris entre 0 et 5 !");
    alert.setContentText("Veuillez entrer une note entière entre 0 et 5 pour continuer.");
    alert.showAndWait();
    return;
}
        
    Connection cnx = MyConnection.getInstance().getCnx();
        
    // Récupération de l'ID de l'utilisateur
    String queryIdUser = "SELECT ID_user FROM utilisateur WHERE nom_user = ?";
    PreparedStatement pstIdUser = cnx.prepareStatement(queryIdUser);
    pstIdUser.setString(1, nomUtilisateur);
    ResultSet rsIdUser = pstIdUser.executeQuery();
    int idUser = 0;
    if (rsIdUser.next()) {
        idUser = rsIdUser.getInt("ID_user");
    }
        
    // Récupération de l'ID du logement
    String queryIdLogement = "SELECT ID_logement FROM logement WHERE nom_logement = ?";
    PreparedStatement pstIdLogement = cnx.prepareStatement(queryIdLogement);
    pstIdLogement.setString(1, nomLogement);
    ResultSet rsIdLogement = pstIdLogement.executeQuery();
    int idLogement = 0;
    if (rsIdLogement.next()) {
        idLogement = rsIdLogement.getInt("ID_logement");
    }
        
    // Récupération de l'ID de l'événement
    String queryIdEvent = "SELECT ID_event FROM evenement WHERE nom_event = ?";
    PreparedStatement pstIdEvent = cnx.prepareStatement(queryIdEvent);
    pstIdEvent.setString(1, nomEvent);
    ResultSet rsIdEvent = pstIdEvent.executeQuery();
    int idEvent = 0;
    if (rsIdEvent.next()) {
        idEvent = rsIdEvent.getInt("ID_event");
    }
        
    // Création de l'objet Avis avec les clés étrangères
        System.out.println(idCategorie);
        System.out.println(idUser);
        System.out.println(idLogement);
        System.out.println(idEvent);
    avis avis = new avis(idCategorie, idUser, idLogement, idEvent, noteAvis, contenuAvis, typeAvis);
        
    // Appel de la méthode d'ajout de l'avis dans la base de données
    serviceAvis serviceAvis = new serviceAvis();
    serviceAvis.ajouteravis2(avis);
        
    // Redirection vers la page d'affichage des avis
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficheravis.fxml"));
    Parent root = loader.load();
    AfficheravisController controller = loader.getController();
    controller.afficherAvisParCategorie(idCategorie);
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    private int id_avis;
    public void setavis(avis avis) {
        if (avis != null) {
            // affecter les propriétés de l'objet projet aux champs de texte correspondants dans la vue d'ajout/modification de projet
            idcategorie.setText(Integer.toString(avis.getID_categorie()));
            idnote.setText(Integer.toString(avis.getNote_avis()));
            idcontenu.setText(avis.getContenu_avis());
            idtype.setText(avis.getType_avis());
        this.id_avis = avis.getID_categorie();

     


        }
    }
    private int id;
    void recupererID(int id){
        this.id = id;
        idcategorie.setText(String.valueOf(id));
        
    }

    @FXML
    private void idajouteravis2(ActionEvent event) throws IOException {
    String contenuavis = idcontenu.getText();
    String categorie = idcategorie.getText();
    System.out.println(categorie );
    String note = idnote.getText();
    String type = idtype.getText();
    
    int id_categorie = 0; // initialisation de la variable id_categorie

try {
    Connection cnx = MyConnection.getInstance().getCnx();
    
    // Obtenir l'id_categorie de l'avis avant la modification
    String querySelect = "SELECT ID_categorie FROM avis WHERE ID_avis = ?";
    PreparedStatement pstSelect = cnx.prepareStatement(querySelect);
    pstSelect.setInt(1, id_avis);
    ResultSet rs = pstSelect.executeQuery();
    if (rs.next()) {
        id_categorie = rs.getInt("id_categorie");
    }
    
    

        
        // Mettre à jour la réclamation dans la table 'reclamation'
        String queryUpdate = "UPDATE avis SET contenu_avis = ?, note_avis = ?, type_avis = ? WHERE ID_avis = ?";
        PreparedStatement pstUpdate = cnx.prepareStatement(queryUpdate);
        pstUpdate.setString(1, contenuavis);
        pstUpdate.setString(2, note);
        pstUpdate.setString(3, type);
        pstUpdate.setInt(4, id_avis);
        pstUpdate.executeUpdate();
        
        System.out.println("L'avis a été mise à jour avec succès !");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficheravis.fxml"));
        Parent root = loader.load();
        AfficheravisController controller = loader.getController();
        controller.afficherAvisParCategorie(id_categorie);

        // Obtenir la scène courante et la remplacer par la nouvelle scène
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    @FXML
    private void action4(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/Pageuser.fxml"));
        Parent root = loader.load();
        Scene scene = btn4.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn4.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
}
    

