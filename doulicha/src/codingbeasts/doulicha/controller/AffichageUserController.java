package codingbeasts.doulicha.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.services.UtilisateurCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private TableColumn<Utilisateur, String> txtrole;

    @FXML
    private TableColumn<Utilisateur, String> txtstatus;

    @FXML
    private TextField searchField;

    private FilteredList<Utilisateur> filteredList;
    @FXML
    private Label countLabel;
    
    @FXML
    private ChoiceBox<String> filterStatus;


    int IndexU = -1;
    @FXML
    private Pane gestion;
    @FXML
    private Pane design;
    @FXML
    private AnchorPane appuser;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;

    @FXML
    void deleteuser(ActionEvent event) {
        IndexU = txttable.getSelectionModel().getSelectedCells().get(0).getRow();
        Utilisateur U;
        int idU;
        //        IndexU = txttable.getSelectionModel().getSelectedCells().get(0).getRow();

        System.out.println("selected " + IndexU);

        if (IndexU <= -1) {

            txttable.getItems().remove(IndexU);
////                txttable.refresh();
        } else {

            U = txttable.getItems().get(IndexU);
            idU = U.getID_user();
            System.out.println("data : " + U.getID_user());
            UtilisateurCRUD uc = new UtilisateurCRUD();
//            uc.supprimerutilisateur(idU);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment supprimer l'utilisateur N°" + idU + " ?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                // Supprimer l'utilisateur de la base de données
//                UtilisateurCRUD uc = new UtilisateurCRUD();
                uc.supprimerutilisateur(idU);
                // Supprimer l'utilisateur de la TableView
                txttable.getItems().remove(IndexU);

//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("delete");
//                alert.setHeaderText(null);
//                alert.setContentText("delete user");
//                alert.showAndWait();
//
            }
        }
//        IndexU = txttable.getSelectionModel().getSelectedCells().get(0).getRow();
//        Utilisateur U;
//        int idU;
//        //        IndexU = txttable.getSelectionModel().getSelectedCells().get(0).getRow();
//
//        System.out.println("selected " + IndexU);
//
//        if (IndexU <= -1) {
//
//            txttable.getItems().remove(IndexU);
//////                txttable.refresh();
//        } else {
//
//            U = txttable.getItems().get(IndexU);
//            idU = U.getID_user();
//            System.out.println("data : " + U.getID_user());
//            UtilisateurCRUD uc = new UtilisateurCRUD();
//            uc.supprimerutilisateur(idU);
//            Alert alert = new Alert(AlertType.CONFIRMATION);
//            alert.setTitle("Confirmation");
//            alert.setHeaderText(null);
//            alert.setContentText("Voulez-vous vraiment supprimer l'utilisateur N°" + idU + " ?");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.get() == ButtonType.OK) {
//                // Supprimer l'utilisateur de la base de données
//            //      UtilisateurCRUD uc = new UtilisateurCRUD();
//                uc.supprimerutilisateur(idU);
//                // Supprimer l'utilisateur de la TableView
//                txttable.getItems().remove(IndexU);
//
//            }
//        }
    }
    ObservableList<Utilisateur> UserList = FXCollections.observableArrayList();

    @FXML
    void refreshuser(ActionEvent event
    ) {
        UserList.clear();
//        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom_user"));
        txtemail.setCellValueFactory(new PropertyValueFactory<>("email_user"));
        //  txtpassword.setCellValueFactory(new PropertyValueFactory<>("mdp_user"));
        txtrole.setCellValueFactory(new PropertyValueFactory<>("role_user"));
        txtstatus.setCellValueFactory(new PropertyValueFactory<>("status_user"));
        UtilisateurCRUD uc = new UtilisateurCRUD();
        List<Utilisateur> myList = new ArrayList<>();
        myList = uc.afficherUtilisateur();
        System.out.println("liste des utilisateurs : " + myList);
        UserList = FXCollections.observableArrayList(myList);
        txttable.setItems(UserList);

    }

    private void AffichageUser() {
    txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
    txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom_user"));
    txtemail.setCellValueFactory(new PropertyValueFactory<>("email_user"));
    txtrole.setCellValueFactory(new PropertyValueFactory<>("role_user"));
    txtstatus.setCellValueFactory(new PropertyValueFactory<>("status_user"));

    UtilisateurCRUD uc = new UtilisateurCRUD();
    List<Utilisateur> myList = uc.afficherUtilisateur();
    FilteredList<Utilisateur> filteredList = new FilteredList<>(FXCollections.observableList(myList));

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredList.setPredicate(utilisateur
                -> (newValue == null || newValue.isEmpty())
                || utilisateur.getNom_user().toLowerCase().contains(newValue.toLowerCase())
                || utilisateur.getStatus_user().toLowerCase().contains(newValue.toLowerCase())
                || utilisateur.getEmail_user().toLowerCase().contains(newValue.toLowerCase())
        );

        // Mise à jour du label countLabel lors de la recherche
        if (filterStatus.getValue().equals("inactif")) {
            int count = getFilteredUtilisateurs("inactif").size();
            countLabel.setText(count + " utilisateurs inactifs");
        } else {
            countLabel.setText("");
        }

        txttable.setItems(filteredList);
    });

    filterStatus.valueProperty().addListener((observable, oldValue, newValue) -> {
        filteredList.setPredicate(user -> (newValue.equals("Tous")) ? true : user.getStatus_user().equals(newValue));

        // Mise à jour du label countLabel lors du changement de filtre
        if (newValue.equals("inactif")) {
            int count = getFilteredUtilisateurs("inactif").size();
            countLabel.setText(count + " utilisateurs inactifs");
        } else {
            countLabel.setText("");
        }
    });

    txttable.setItems(filteredList);
}

private List<Utilisateur> getFilteredUtilisateurs(String status) {
    UtilisateurCRUD uc = new UtilisateurCRUD();
    List<Utilisateur> userList = uc.afficherUtilisateur();
    List<Utilisateur> filteredList = new ArrayList<>();

    for (Utilisateur user : userList) {
        if (user.getStatus_user().equals(status)) {
            filteredList.add(user);
        }
    }

    return filteredList;
//        // col_id.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
//        txtnom.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
//        txtprenom.setCellValueFactory(new PropertyValueFactory<>("prenom_user"));
//        txtemail.setCellValueFactory(new PropertyValueFactory<>("email_user"));
//        //    txtpassword.setCellValueFactory(new PropertyValueFactory<>("mdp_user"));
//        txtrole.setCellValueFactory(new PropertyValueFactory<>("role_user"));
//        txtstatus.setCellValueFactory(new PropertyValueFactory<>("status_user"));
//        UtilisateurCRUD uc = new UtilisateurCRUD();
//        List<Utilisateur> myList = new ArrayList<>();
//        myList = uc.afficherUtilisateur();
//        filteredList = new FilteredList<>(FXCollections.observableArrayList(myList));
//        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredList.setPredicate(utilisateur -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//                if (utilisateur.getNom_user().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (utilisateur.getEmail_user().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else {
//                    return false;
//                }
//
//            });
//
//            txttable.setItems(filteredList);
//        });
//        System.out.println("nnn : " + myList);
//        ObservableList<Utilisateur> observableArrayList
//                = FXCollections.observableArrayList(uc.afficherUtilisateur());
//        txttable.setItems(observableArrayList);

    }

    @FXML
    void onupdate(ActionEvent event) {
        IndexU = txttable.getSelectionModel().getSelectedCells().get(0).getRow();
        Utilisateur d;
        int idU;

        System.out.println("selected " + IndexU);
        d = txttable.getItems().get(IndexU);
        idU = d.getID_user();
        String nomU = d.getNom_user();
        String prenomU = d.getPrenom_user();
        String emailU = d.getEmail_user();
        String mdpU = d.getMdp_user();

        System.out.println("id user =" + d.getID_user());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/UpdateUtilisateur.fxml"));
            Parent root = loader.load();
            UpdateUtilisateurController usc = loader.getController();
            usc.setData(idU, nomU, prenomU, emailU, mdpU);
            // Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("UpdateUtilisateur");
            stage.resizableProperty().setValue(false);
            stage.show();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            Logger.getLogger(AffichageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<String> statusOptions = FXCollections.observableArrayList("Tous", "actif", "inactif");
    filterStatus.setItems(statusOptions);
    filterStatus.setValue("Tous");

      AffichageUser();

    // Mise à jour du label countLabel lors du changement de filtre
    filterStatus.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.equals("Tous")) {
            countLabel.setText("");
        } else {
            int count = getFilteredUtilisateurs(newValue).size();
            countLabel.setText(count + " utilisateurs ");
        }
    });

}

    @FXML
    private void btnuser(ActionEvent event) {
        gestion.setVisible(true);
        design.setVisible(true);
        appuser.setVisible(true);
    }

    @FXML
    private void action1(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/ajoutercategorie.fxml"));
        Parent root = loader.load();
        Scene scene = btn1.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn1.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void action2(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficheravisadmin.fxml"));
        Parent root = loader.load();
        Scene scene = btn2.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn2.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void action3(ActionEvent event) {
                                                try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/afficherreclamationADmin.fxml"));
        
        Parent root = loader.load();
        Scene scene = btn3.getScene();
        scene.setRoot(root);
        Stage currentStage = (Stage) btn3.getScene().getWindow();
        currentStage.close();
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }
}
