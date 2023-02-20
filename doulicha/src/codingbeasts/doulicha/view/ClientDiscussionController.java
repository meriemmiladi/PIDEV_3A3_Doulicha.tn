package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.services.DiscussionCRUD;
import codingbeasts.doulicha.services.ReponseCRUD;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class ClientDiscussionController implements Initializable {

    @FXML
    private VBox discussionBox;
    @FXML
    private VBox reponseBox;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ScrollPane scrollPane1;

    @FXML
    private Button newDiscussionButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = Date.valueOf(LocalDate.now());
        DiscussionCRUD dis = new DiscussionCRUD();
        List<Discussion> discussions = dis.afficherDiscussions();

        discussions.stream().map((Discussion discussion) -> {
            VBox contentBox = new VBox();

            Label titleLabel = new Label(discussion.getTitre_discussion());
            Label dateLabel = new Label(discussion.getDate_discussion().toString());
            Label contentTextLabel = new Label(discussion.getContenu_discussion());
            Button replyButton = new Button("Répondre");

            Button modifierDiscussion = new Button("Modifier la discussion");
            Button supprimerDiscussion = new Button("supprimer la discussion");

            modifierDiscussion.setOnAction((ActionEvent event) -> {
                TextArea editContent = new TextArea(discussion.getContenu_discussion());
                Button enregistrerButton = new Button("Enregistrer");
                Button annulerButton = new Button("Annuler");

                enregistrerButton.setOnAction(e -> {
                    discussion.setContenu_discussion(editContent.getText());
                    contentBox.getChildren().clear();
                    dis.modifierContenuDiscussion(discussion.getID_discussion(), editContent.getText());
                    contentTextLabel.setText(discussion.getContenu_discussion());
                    contentBox.getChildren().addAll(titleLabel, dateLabel, contentTextLabel, replyButton, modifierDiscussion, supprimerDiscussion);
                });

                annulerButton.setOnAction(e -> {
                    contentBox.getChildren().clear();
                    contentBox.getChildren().addAll(titleLabel, dateLabel, contentTextLabel, replyButton, modifierDiscussion, supprimerDiscussion);
                });

                contentBox.getChildren().clear();
                contentBox.getChildren().addAll(titleLabel, dateLabel, editContent, enregistrerButton, annulerButton);
            });
            supprimerDiscussion.setOnAction((ActionEvent) -> {
                Label alertTextLabel = new Label("êtes vous sur de supprimer votre réponse ? ");
                Button confirmerSupressionButton = new Button("oui");
                Button annulerSuppressionButton = new Button("non");
                confirmerSupressionButton.setOnAction((ActionEvent event) -> {
                    contentBox.getChildren().clear();
                });
                annulerSuppressionButton.setOnAction(e -> {
                    contentBox.getChildren().clear();
                    contentBox.getChildren().addAll(titleLabel, dateLabel, contentTextLabel, replyButton, modifierDiscussion, supprimerDiscussion);
                });
                contentBox.getChildren().clear();
                contentBox.getChildren().addAll(titleLabel, dateLabel, contentTextLabel, alertTextLabel, confirmerSupressionButton, annulerSuppressionButton);
            });

            contentBox.getChildren().addAll(titleLabel, dateLabel, contentTextLabel, replyButton);

            replyButton.setOnAction((ActionEvent event) -> {

                reponseBox.getChildren().clear();
                scrollPane1.setFitToWidth(false);

                ReponseCRUD rep = new ReponseCRUD();
                List<Reponse> reponses = rep.rechercherReponsesDiscussion(discussion.getID_discussion());
                reponses.stream().map((Reponse reponse) -> {
                    VBox reponsesBox = new VBox();
                    Label labelContenu = new Label(reponse.getContenu_reponse());
                    Label labelDate = new Label(reponse.getDate_reponse().toString());
                    reponsesBox.getChildren().addAll(labelContenu, labelDate);
                    reponsesBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
                    Button modifierReponse = new Button("Modifier la réponse");
                    Button supprimerReponse = new Button("supprimer la réponse");
                    reponsesBox.getChildren().addAll(modifierReponse, supprimerReponse);

                    modifierReponse.setOnAction((ActionEvent e) -> {
                        TextArea editContent = new TextArea(reponse.getContenu_reponse());
                        Button enregistrerButton = new Button("Enregistrer");
                        Button annulerButton = new Button("Annuler");

                        enregistrerButton.setOnAction(e1 -> {
                            reponse.setContenu_reponse(editContent.getText());
                            reponsesBox.getChildren().clear();
                            rep.modifierContenuReponse(reponse.getID_reponse(), editContent.getText());
                            labelContenu.setText(reponse.getContenu_reponse());
                            reponsesBox.getChildren().addAll(labelDate, labelContenu, modifierReponse, supprimerReponse);

                        });

                        annulerButton.setOnAction(e1 -> {
                            reponsesBox.getChildren().clear();
                            reponsesBox.getChildren().addAll(labelDate, labelContenu, modifierReponse, supprimerReponse);
                        });

                        reponsesBox.getChildren().clear();
                        reponsesBox.getChildren().clear();
                        reponsesBox.getChildren().addAll(labelDate, editContent, enregistrerButton, annulerButton);
                    });
                    supprimerReponse.setOnAction((ActionEvent e) -> {
                        Label alertTextLabel1 = new Label("êtes vous sur de supprimer votre réponse ? ");
                        Button confirmerSupressionButton1 = new Button("oui");
                        Button annulerSuppressionButton1 = new Button("non");
                        confirmerSupressionButton1.setOnAction((ActionEvent e3) -> {
                            reponsesBox.getChildren().clear();
                        });
                        annulerSuppressionButton1.setOnAction(e3 -> {
                            reponsesBox.getChildren().clear();
                            reponsesBox.getChildren().addAll(labelDate, labelContenu, modifierReponse, supprimerReponse);
                        });
                        reponsesBox.getChildren().clear();
                        reponsesBox.getChildren().addAll(labelDate, labelContenu, alertTextLabel1, confirmerSupressionButton1, annulerSuppressionButton1);
                    });

                    return reponsesBox;
                }).map(reponsesBox -> {

                    reponseBox.getChildren().add(reponsesBox);

                    return reponsesBox;
                }).forEachOrdered((VBox _item) -> {
                    reponseBox.setSpacing(10);
                    reponseBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");

                });

                TextArea nouvelleReponse = new TextArea();
                nouvelleReponse.setPrefHeight(200);
                nouvelleReponse.setPromptText("rédiger votre réponse...");
                nouvelleReponse.setWrapText(true);
                nouvelleReponse.setDisable(false);
                reponseBox.getChildren().add(nouvelleReponse);
                nouvelleReponse.requestFocus();
                Button ajouterReponse = new Button("Partager votre réponse");
                reponseBox.getChildren().add(ajouterReponse);
                ajouterReponse.setOnAction((ActionEvent event1) -> {
                    rep.ajouterReponse(new Reponse(1, discussion.getID_discussion(), nouvelleReponse.getText(), new Date(System.currentTimeMillis())));
                    nouvelleReponse.setOpacity(0);
                    ajouterReponse.setOpacity(0);
                    VBox reponsesBox1 = new VBox();
                    Label labelContenu = new Label(nouvelleReponse.getText());
                    Label labelDate = new Label((new Date(System.currentTimeMillis())).toString());
                    reponsesBox1.getChildren().addAll(labelContenu, labelDate);
                    reponseBox.getChildren().addAll(reponsesBox1);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.setHeaderText(null);
                    alert.setContentText("Réponse ajoutée avec succès !");
                    alert.showAndWait();
                }
                );
                reponseBox.setOpacity(1);
                scrollPane1.setOpacity(1);

            });
            if (discussion.getID_user() == 1) {
                contentBox.getChildren().addAll(modifierDiscussion, supprimerDiscussion);
            }

            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            discussionBox.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            discussionBox.setSpacing(10);
        });

        Button ajouterDiscussion = new Button("Ajouter discussion");
        ajouterDiscussion.getStyleClass().add("btn-add-discussion");

        ajouterDiscussion.setOnAction((ActionEvent e) -> {
            TextArea titreDiscussionArea = new TextArea();
            titreDiscussionArea.setPromptText("Ajouter votre titre...");
            TextArea nouvelleDiscussionArea = new TextArea();
            nouvelleDiscussionArea.setPromptText("Rédiger votre contenu...");
            Button publierBtn = new Button("Publier");
            publierBtn.getStyleClass().add("btn-add-discussion");
            discussionBox.getChildren().addAll(titreDiscussionArea, nouvelleDiscussionArea);

            publierBtn.setOnAction((ActionEvent event) -> {
                dis.ajouterDiscussion(new Discussion(1, titreDiscussionArea.getText(), nouvelleDiscussionArea.getText(), new Date(System.currentTimeMillis())));
                VBox discussionBox1 = new VBox();
                Label labelTitre = new Label(titreDiscussionArea.getText());
                Label labelContenu = new Label(nouvelleDiscussionArea.getText());
                Label labelDate = new Label((new Date(System.currentTimeMillis())).toString());
                discussionBox1.getChildren().addAll(labelTitre, labelContenu, labelDate);
                discussionBox.getChildren().addAll(discussionBox1);
                discussionBox.getChildren().clear();
                initialize(url, rb);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.TRANSPARENT);
                alert.setHeaderText("ajout de discussion");
                alert.setContentText("Discussion publiée avec succès !");
                alert.showAndWait();
            });

            discussionBox.getChildren().add(publierBtn);
        });

// Ajout du bouton au VBox "discussionBox"
        discussionBox.getChildren().add(ajouterDiscussion);
    }
}
