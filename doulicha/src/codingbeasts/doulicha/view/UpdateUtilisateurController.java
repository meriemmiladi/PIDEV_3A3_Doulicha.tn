/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.services.UtilisateurCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UpdateUtilisateurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtpassword;
    
     int id;
        String U_nom;
        String U_Prenom;
        String U_email;
        String mot_de_pass;

    @FXML
    void miseajour(ActionEvent event) {
        String nom = txtnom.getText();
        String Prenom = txtprenom.getText();
        String email = txtemail.getText();
        String mot_passe = txtpassword.getText();

        Utilisateur u = new Utilisateur(id, nom, Prenom, email, mot_passe, "Utilisateur");
        UtilisateurCRUD uc = new UtilisateurCRUD();
        uc.modifierutilisateur(u);
        System.out.println("Utilisateur a ete Mise a jour avec succes");
    }
    
     public void setData(int id ,String nom,String prenom,String email,String mot_de_pass){
        this.id=id;       
        this.U_nom=nom;
        this.U_Prenom=prenom;
        this.U_email=email;
        this.mot_de_pass=mot_de_pass;
        
        txtnom.setText(nom);
        txtprenom.setText(prenom);
        txtemail.setText(email);
        txtpassword.setText(mot_de_pass);
        
        System.out.println("nom:"+nom+"prenom:"+prenom+"email:"+email+"mot de paasse:"+mot_de_pass);
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
