package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.services.ReponseCRUD;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReponseController implements Initializable {

    @FXML
    private TextField idUtilisateurTextField;
    @FXML
    private TextField idDiscussionTextField;
    @FXML
    private Button afficherReponsesUtilisateur;
    @FXML
    private Button afficherReponsesDiscussion;
    @FXML
    private Button afficherReponsesDiscussionUtilisateur;
    @FXML
    private Button home;
    @FXML
    private TableView tableReponse;
    @FXML
    private TableColumn<Reponse, Integer> colID_Reponse;
    @FXML
    private TableColumn<Reponse, Integer> colID_Discussion;
    @FXML
    private TableColumn<Reponse, Integer> colID_User;
    @FXML
    private TableColumn<Reponse, String> colContenuReponse;
    @FXML
    private TableColumn<Reponse, Date> colDateReponse;
    private ObservableList<Reponse> ReponsesUser;

    @FXML
    public void afficherReponsesUtilisateur(ActionEvent event) throws SQLException {
        if ((idUtilisateurTextField.getText().isEmpty()) || ((Integer.parseInt(idUtilisateurTextField.getText())) == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le texte ne peut pas être vide ou nul");
            alert.showAndWait();
        } else {
            ReponseCRUD dc = new ReponseCRUD();
            ReponsesUser = FXCollections.observableArrayList(dc.rechercherReponses(parseInt(idUtilisateurTextField.getText())));
            if (ReponsesUser.isEmpty()) {
                tableReponse.setOpacity(0);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("cet utilisateur n'existe pas ou n'as pas de discussions créés");
                alert.showAndWait();
            } else {
                tableReponse.setItems(ReponsesUser);
                tableReponse.setOpacity(1);
                idUtilisateurTextField.clear();
            }
        }
    }
    private ObservableList<Reponse> ReponsesDiscussion;

    @FXML
    public void afficherReponsesDiscussions(ActionEvent event) throws SQLException {
        if ((idDiscussionTextField.getText().isEmpty()) || ((Integer.parseInt(idDiscussionTextField.getText())) == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le texte ne peut pas être vide ou nul");
            alert.showAndWait();
        }
        ReponseCRUD dc = new ReponseCRUD();
        ReponsesDiscussion = FXCollections.observableArrayList(dc.rechercherReponsesDiscussion(parseInt(idDiscussionTextField.getText())));
        if (ReponsesDiscussion.isEmpty()) {
            tableReponse.setOpacity(0);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("cet discussion n'existe pas ou n'as pas de réponses");
            alert.showAndWait();
        } else {
            tableReponse.setItems(ReponsesDiscussion);
            tableReponse.setOpacity(1);
            idDiscussionTextField.clear();
        }
    }
    private ObservableList<Reponse> reponsesDiscussionUtilisateur;

    @FXML
    public void afficherReponsesDiscussionUtilisateur(ActionEvent event) {
        if (((idDiscussionTextField.getText().isEmpty()) || ((Integer.parseInt(idDiscussionTextField.getText())) == 0)) || ((idUtilisateurTextField.getText().isEmpty()) || ((Integer.parseInt(idUtilisateurTextField.getText())) == 0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le texte ne peut pas être vide ou nul");
            alert.showAndWait();
        } else {
            ReponseCRUD dc = new ReponseCRUD();
            reponsesDiscussionUtilisateur = FXCollections.observableArrayList(dc.rechercherReponsesDiscussionUtilisateur(parseInt(idDiscussionTextField.getText()), parseInt(idUtilisateurTextField.getText())));
            if (reponsesDiscussionUtilisateur.isEmpty()) {
                tableReponse.setOpacity(0);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setContentText("cet utilisateur n'existe pas ou n'as pas de réponses créés sur cette discussion");
                alert.showAndWait();
            } else {
                tableReponse.setItems(reponsesDiscussionUtilisateur);
                tableReponse.setOpacity(1);
                idDiscussionTextField.clear();
                idUtilisateurTextField.clear();
            }
        }

    }

    @FXML
    public void home(ActionEvent event) throws IOException {
        Parent pageSuivanteParent = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/GestionDiscussionReponse.fxml"));
        Scene pageSuivanteScene = new Scene(pageSuivanteParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(pageSuivanteScene);
        appStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID_Reponse.setCellValueFactory(new PropertyValueFactory<>("ID_reponse"));
        colID_User.setCellValueFactory(new PropertyValueFactory<>("ID_user"));
        colID_Discussion.setCellValueFactory(new PropertyValueFactory<>("ID_discussion"));
        colContenuReponse.setCellValueFactory(new PropertyValueFactory<>("contenu_reponse"));
        colDateReponse.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
    }

}
