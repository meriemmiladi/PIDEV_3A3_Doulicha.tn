/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.don;
import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.donCRUD;
import codingbeasts.doulicha.services.projetCRUD;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AjouterDonController implements Initializable {

    @FXML
    private TextField tfvaleur_don;
    @FXML
    private Button btn_valider;
    @FXML
    private Button btn_valider1;
    @FXML
    private TextField tfProjetId;
    @FXML
    private ComboBox<String> idnomutilisateur;
    private TextField tfprojet;
    private int ID_projet;
    
    @FXML
    private Button tfretourn;
    @FXML
    private ImageView tfretourne;
    @FXML
    private Button tfAffichage1;
    /*
    @FXML
    private void savedon(ActionEvent event) {
        
        
       
        float valeur_don = Float.parseFloat(tfvaleur_don.getText());
        
        
        don p = new don(valeur_don);
        donCRUD pc = new donCRUD();
        pc.ajouterdon2(p);
    }
    */
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void retourn(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/AffichageProjetUser.fxml"));
        Parent root = loader.load();
        Scene scene = tfretourn.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
}
    @FXML
    private void affichage1(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/ListAffichageDon.fxml"));
        Parent root = loader.load();
        Scene scene = tfAffichage1.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    
    }
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
    }    
    @FXML
    private void savedon(ActionEvent event) {
    String nomUtilisateur = idnomutilisateur.getValue();
        System.out.println(nomUtilisateur);
 
    String valeur_don = tfvaleur_don.getText();
    String ID_projet = tfProjetId.getText();
    if (valeur_don == null || valeur_don.isEmpty()) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText("Valeur de don manquante");
    alert.setContentText("Veuillez saisir une valeur de don.");
    alert.showAndWait();
    return;
}

// Vérification du format pour le champ valeur_don
try {
    float valeurDonFloat = Float.parseFloat(valeur_don);
} catch (NumberFormatException e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText("Valeur de don invalide");
    alert.setContentText("Veuillez saisir une valeur de don valide (nombre décimal).");
    alert.showAndWait();
    return;
}
    
    try {
        Connection cnx = MyConnection.getInstance().getCnx();
        
        // Récupérer l'ID de l'utilisateur en fonction du nom sélectionné
        String queryIdUser = "SELECT ID_user FROM utilisateur WHERE nom_user = ?";
        PreparedStatement pstIdUser = cnx.prepareStatement(queryIdUser);
        pstIdUser.setString(1, nomUtilisateur);
        ResultSet rsIdUser = pstIdUser.executeQuery();
        int idUser = 0;
        if (rsIdUser.next()) {
            idUser = rsIdUser.getInt("id_user");
        }
        
        // Insérer la nouvelle réclamation dans la table 'reclamation'
        String queryInsert = "INSERT INTO don (ID_user, ID_projet, valeur_don, date_don) VALUES (?, ?, ?, ?)";
        PreparedStatement pstInsert = cnx.prepareStatement(queryInsert);
        pstInsert.setInt(1, idUser);
        pstInsert.setString(2, ID_projet);
        pstInsert.setString(3, valeur_don);
        pstInsert.setDate(4, new java.sql.Date(new java.util.Date().getTime())); // Utiliser la date actuelle
        pstInsert.executeUpdate();
        
        System.out.println("La DON a été ajoutée avec succès !");
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    }
   private int id;
   void recupererID(int id){
       this.id = id;
       tfProjetId.setText(String.valueOf(id));
   }


    private int id_don;
    public void setdon(don don) {
        if (don != null) {
            // affecter les propriétés de l'objet projet aux champs de texte correspondants dans la vue d'ajout/modification de projet
            
            
            tfvaleur_don.setText(Float.toString(don.getValeur_don()));
            
        this.id_don = don.getID_don();
        }
    }
    @FXML
    private void savedon2(ActionEvent event) throws SQLException {
        
       
        
        float valeur_don = Float.parseFloat(tfvaleur_don.getText());
       
        
         
        don d = new don(valeur_don);
        donCRUD pc = new donCRUD();
        pc.modifierdon(d,id_don);
    }

    @FXML
    private void affichage(ActionEvent event) {
    }
    
    }    
/*
    int ID_projet;
    public void setProjetId(int id_projet) {
    // On stocke l'ID du projet dans une variable de classe pour l'utiliser lors de l'ajout du don
    this.ID_projet = id_projet;
    }

   public List<don> getDonsByUser(int ID_projet) {
    Connection cnx = MyConnection.getInstance().getCnx();
    List<don> dons = new ArrayList<>();
    String sql = "SELECT * FROM don INNER JOIN projet ON don.ID_projet = projet.ID_projet WHERE don.ID_projet = ?";
    try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
        pstmt.setInt(1, ID_projet);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            don don = new don();
            don.setID_don(rs.getInt("ID_don"));
            don.setValeur_don(rs.getFloat("valeur_don"));
            don.setID_user(rs.getInt("ID_user"));
            don.setID_projet(rs.getInt("ID_projet"));
            don.setDate_don(rs.getDate("date_don"));
            projetCRUD pc = new projetCRUD();
            dons.ajouterdon2(don d);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return dons;
}
/**
    @FXML
    private void saveProjet2(ActionEvent event) {
    */
  /*  
    
    @FXML
    private void savedon(ActionEvent event) {
    int id_user = Integer.parseInt(tfuser.getText());
        System.out.println(id_user);
    float valeur_don = Float.parseFloat(tfvaleur_don.getText());
    System.out.println( valeur_don);
    int id_projet = Integer.parseInt(tfProjetId.getText());
    System.out.println(id_projet);
    projetCRUD projetCRUD = new projetCRUD();
    try { 
    projet projet = projetCRUD.getProjetById(id_projet);
    System.out.println(projet);
    if (projet != null) {
        don p = new don(id_user,id_projet,valeur_don);
        donCRUD donCRUD = new donCRUD();
        donCRUD.ajouterdon2(p);
    } else {
        // afficher un message d'erreur si l'ID du projet n'existe pas
        System.out.println("Projet introuvable");
    }
    }catch (Exception e) {
    e.printStackTrace();
}
            }
*/
    

