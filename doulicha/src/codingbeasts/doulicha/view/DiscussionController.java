/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.services.DiscussionCRUD;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ghass
 */
public class DiscussionController implements Initializable {

    @FXML
    private TextField idUtilisateurTextField;
    @FXML
    private TextField titreDiscussionTextField;
    @FXML
    private TextField contenuDiscussionTextField;
    @FXML
    private Button afficherToutesLesDiscussions;
    @FXML
    private Button afficherDiscussionsUtilisateur;
    @FXML
    private Button trierDiscussionsParDate;
     @FXML
    private Button rechercherparNomPrenom;
 @FXML
    private TextField nomTextField;
  @FXML
    private TextField prenomTextField;
    @FXML
    public void afficherDiscussions(ActionEvent event) throws SQLException {
        DiscussionCRUD dc = new DiscussionCRUD();
        List<Discussion> discussions = dc.afficherDiscussions();
        discussions.forEach((d) -> {
            System.out.println(d);
        });
    }

    @FXML
    public void trierDiscussionsParDate(ActionEvent event) {
        System.out.println("Les discussions triées par date : ");
        DiscussionCRUD dc = new DiscussionCRUD();
        List<Discussion> discussions = dc.afficherDiscussionsTrieParDate();
        discussions.forEach((d) -> {
            System.out.println(d);
        });
    }

    @FXML
    public void afficherDiscussionsUtilisateur(ActionEvent event) {
        DiscussionCRUD dc = new DiscussionCRUD();
        List<Discussion> discussions = dc.rechercherDiscussions(Integer.parseInt(idUtilisateurTextField.getText()));
        discussions.forEach((d) -> {
            System.out.println(d);
        });
    }
     @FXML
    public void afficherDiscussionsUtilisateurNomPrenom(ActionEvent event) {
        DiscussionCRUD dc = new DiscussionCRUD();
        List<Discussion> discussions = dc.rechercherDiscussions((nomTextField.getText()),prenomTextField.getText());
        discussions.forEach((d) -> {
            System.out.println(d);
        });
    }
    

    /**
     * @FXML public void handleButtonAction(ActionEvent event) {
     *
     * Connection con = null; PreparedStatement ps = null; try { con =
     * DriverManager.getConnection("jdbc:mysql://localhost:3306/doulicha",
     * "root", ""); String query = "INSERT INTO discussion (ID_user,
     * titre_discussion, contenu_discussion, date_discussion) VALUES (?, ?, ?,
     * ?)"; ps = con.prepareStatement(query); ps.setInt(1,
     * Integer.parseInt(idUtilisateurTextField.getText())); ps.setString(2,
     * titreDiscussionTextField.getText()); ps.setString(3,
     * contenuDiscussionTextField.getText()); ps.setDate(4,
     * java.sql.Date.valueOf(java.time.LocalDate.now())); //date actuelle
     * ps.executeUpdate(); System.out.println("Nouvelle discussion ajoutée à la
     * base de données!"); } catch (SQLException ex) {
     * System.err.println(ex.getMessage()); } finally { try { if (ps != null) {
     * ps.close(); } if (con != null) { con.close(); } } catch (SQLException ex)
     * { System.err.println(ex.getMessage()); } } }
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
