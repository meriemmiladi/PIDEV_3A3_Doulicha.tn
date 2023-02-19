
package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.services.ReponseCRUD;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
        ReponseCRUD dc = new ReponseCRUD();
        ReponsesUser = FXCollections.observableArrayList(dc.rechercherReponses(Integer.parseInt(idUtilisateurTextField.getText())));
        tableReponse.setItems(ReponsesUser);
        tableReponse.setOpacity(1);
        idUtilisateurTextField.clear();
    }
         private ObservableList<Reponse> ReponsesDiscussion;

    @FXML
    public void afficherReponsesDiscussions(ActionEvent event) throws SQLException {
        ReponseCRUD dc = new ReponseCRUD();
        ReponsesDiscussion = FXCollections.observableArrayList(dc.rechercherReponsesDiscussion(Integer.parseInt(idDiscussionTextField.getText())));
        tableReponse.setItems(ReponsesDiscussion);
        tableReponse.setOpacity(1);
        idDiscussionTextField.clear();
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
