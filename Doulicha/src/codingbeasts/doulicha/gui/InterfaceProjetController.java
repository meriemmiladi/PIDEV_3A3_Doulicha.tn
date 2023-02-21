/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.gui;

import codingbeasts.doulicha.entities.don;
import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.services.projetCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class InterfaceProjetController implements Initializable {
    @FXML
    private TextField tfnom_projet;
    @FXML
    private TextField tfdescription_projet;
    @FXML
    private TextField tfobjectif_projet;
    @FXML
    private TextField tfetat_projet;
    @FXML
    private TextField tfimage_projet;
    @FXML
    private Button btn_valider;
    @FXML
    private Button btn_valider1;
    @FXML
    private Button tfAffichage;
    

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void saveProjet(ActionEvent event) {
      /*  
        String nom_projet = tfnom_projet.getText();
        String description_projet = tfdescription_projet.getText();
        float objectif_projet = Float.parseFloat(tfobjectif_projet.getText());
        int etat_projet = Integer.parseInt(tfetat_projet.getText());
        String image_projet = tfimage_projet.getText();
        
        */
    String nom_projet = tfnom_projet.getText();
    String description_projet = tfdescription_projet.getText();
    
    String objectif_projet_str = tfobjectif_projet.getText();
    String etat_projet_str = tfetat_projet.getText();
    String image_projet = tfimage_projet.getText();

    // Vérification de la validité des entrées de l'utilisateur
    if (nom_projet.isEmpty() || description_projet.isEmpty() || objectif_projet_str.isEmpty() || etat_projet_str.isEmpty() || image_projet.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Certains champs sont vides");
        alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
        alert.showAndWait();
        return;
    }

    if (!objectif_projet_str.matches("\\d+(\\.\\d+)?")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le champ 'objectif projet' doit être un nombre décimal");
        alert.setContentText("Veuillez saisir un nombre valide pour le champ 'objectif projet'.");
        alert.showAndWait();
        return;
    }

    if (!etat_projet_str.matches("\\d+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Le champ 'état projet' doit être un nombre entier");
        alert.setContentText("Veuillez saisir un nombre entier valide pour le champ 'état projet'.");
        alert.showAndWait();
        return;
    }

    float objectif_projet = Float.parseFloat(objectif_projet_str);
    int etat_projet = Integer.parseInt(etat_projet_str);
        
        projet p = new projet(nom_projet,description_projet,objectif_projet,etat_projet,image_projet);
        projetCRUD pc = new projetCRUD();
        pc.ajouterprojet2(p);
        
        
        
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
        
        try {
            Parent root = loader.load();
            DetailsController dwc = loader.getController();
            dwc.setTextID(""+p.getId_projet());
            dwc.setTextNom(p.getNom_projet());
            dwc.setTextDescription(p.getDescription_projet());
            dwc.setTextObjectif(""+p.getObjectif_projet());
            dwc.setTextEtat(""+p.getEtat_projet());
            dwc.setTextImage(p.getImage_projet());
            
            tfnom_projet.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error : "+ex.getMessage());
        }
*/
    }
    private int id_projet;
    public void setProjet(projet projet) {
        if (projet != null) {
            // affecter les propriétés de l'objet projet aux champs de texte correspondants dans la vue d'ajout/modification de projet
            tfnom_projet.setText(projet.getNom_projet());
            tfdescription_projet.setText(projet.getDescription_projet());
            tfobjectif_projet.setText(Float.toString(projet.getObjectif_projet()));
            tfetat_projet.setText(Integer.toString(projet.getEtat_projet()));
            tfimage_projet.setText(projet.getImage_projet());
        this.id_projet = projet.getId_projet();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveProjet2(ActionEvent event) throws SQLException {
        
        String nom_projet = tfnom_projet.getText();
        String description_projet = tfdescription_projet.getText();
        float objectif_projet = Float.parseFloat(tfobjectif_projet.getText());
        int etat_projet = Integer.parseInt(tfetat_projet.getText());
        String image_projet = tfimage_projet.getText();
         
        projet p = new projet(nom_projet,description_projet,objectif_projet,etat_projet,image_projet);
        projetCRUD pc = new projetCRUD();
        pc.modifierprojet2(p,id_projet);
    }
    @FXML
    private void affichage(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/gui/Affichage2.fxml"));
        Parent root = loader.load();
        Scene scene = tfAffichage.getScene();
        scene.setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    
    }

    void setProjet(don don) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setdon(don don) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
