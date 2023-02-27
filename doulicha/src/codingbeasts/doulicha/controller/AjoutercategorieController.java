/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;
import javafx.scene.control.Alert;
import codingbeasts.doulicha.entities.categorie_avis;
import codingbeasts.doulicha.services.serviceCategorie;
import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutercategorieController implements Initializable {

    @FXML
    private Button idafficher;
    @FXML
    private TextField idnomcategorie;
    @FXML
    private Button idajoutercategorie;
    @FXML
    private Button idajoutercategorie1;
    @FXML
    private Button btn4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
/*
    @FXML
    private void ajoutercategorie(ActionEvent event) {
        String nom_categorie=idnomcategorie.getText();
        
        categorie_avis p = new categorie_avis(nom_categorie);
        serviceCategorie pc = new serviceCategorie();
        pc.ajoutercategorie2(p);
    
    }
*/

    
    @FXML
    private void ajoutercategorie(ActionEvent event) throws IOException {
        String nom_categorie = idnomcategorie.getText();
            // Vérifier si la catégorie existe déjà dans la base de données
    serviceCategorie pc = new serviceCategorie();
    if (pc.existeCategorie(nom_categorie)) {
        // afficher un message d'erreur pour l'utilisateur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("La catégorie " + nom_categorie + " existe déjà !");
        alert.showAndWait();
        return;
    }
        if(nom_categorie.isEmpty()){
            // afficher un message d'erreur pour l'utilisateur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Le champ nom de catégorie ne peut pas être vide !");
            alert.showAndWait();
            return;
        }
        categorie_avis p = new categorie_avis(nom_categorie);
        serviceCategorie pc2 = new serviceCategorie();
        pc2.ajoutercategorie2(p);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        currentStage.close();
                // Charger la nouvelle vue
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/affichercategorie.fxml"));
        Parent root = loader.load();

        // Obtenir la scène courante et la remplacer par la nouvelle scène
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        private int id_categorie;
        public void setcategorie(categorie_avis categorie) {
        if (categorie != null) {
            // affecter les propriétés de l'objet projet aux champs de texte correspondants dans la vue d'ajout/modification de projet
            idnomcategorie.setText(categorie.getNom_categorie());
            this.id_categorie = categorie.getID_categorie();


        }
    }


    @FXML
    private void savecategorie2(ActionEvent event) throws IOException {
            String nomcategorie = idnomcategorie.getText();
    
    try {
        Connection cnx = MyConnection.getInstance().getCnx();
        
        // Mettre à jour la réclamation dans la table 'reclamation'
        String queryUpdate = "UPDATE categorie_avis SET nom_categorie = ? WHERE id_categorie = ?";
        PreparedStatement pstUpdate = cnx.prepareStatement(queryUpdate);
        pstUpdate.setString(1, nomcategorie);
        pstUpdate.setInt(2, id_categorie);
        pstUpdate.executeUpdate();
        
        System.out.println("La catégorie a été mise à jour avec succès !");
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        currentStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/affichercategorie.fxml"));
        Parent root = loader.load();

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
    private void affichercategorie(ActionEvent event) {
        try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/affichercategorie.fxml"));
        Parent root = loader.load();
        Scene scene = idafficher.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) idafficher.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    
    }

    @FXML
    private void action4(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/Pageadmin.fxml"));
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







