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
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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
            int id = discussion.getID_discussion();
            Label titleLabel = new Label(discussion.getTitre_discussion());
            titleLabel.setFont(new Font(40));
            Label dateLabel = new Label("dernière modification le " + discussion.getDate_discussion().toString());

            VBox vbox = new VBox();

            vbox.getChildren().addAll(titleLabel, dateLabel);

            TextArea contentTextLabel = new TextArea(discussion.getContenu_discussion());
            contentTextLabel.setEditable(false);

            contentTextLabel.setWrapText(true);
            Button replyButton = new Button("Répondre");
            Button modifierDiscussion = new Button("Modifier la discussion");
            Button supprimerDiscussion = new Button("supprimer la discussion");

            HBox hbox2 = new HBox();
            hbox2.getChildren().addAll(modifierDiscussion, supprimerDiscussion);
            hbox2.setSpacing(20);
            if (discussion.getID_user() == 1) {
                contentBox.getChildren().addAll(hbox2);
            }
            VBox contentBox1 = new VBox();
            contentBox1 = contentBox;
            modifierDiscussion.setOnAction((ActionEvent event) -> {
                TextArea editContent = new TextArea(discussion.getContenu_discussion());
                Button enregistrerButton = new Button("Enregistrer");
                Button annulerButton = new Button("Annuler");
                enregistrerButton.setOnAction(e -> {
                    if (editContent.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText("Le texte ne peut pas être vide.");
                        alert.showAndWait();
                    } else {
                        discussion.setContenu_discussion(editContent.getText());
                        contentBox.getChildren().clear();
                        dis.modifierContenuDiscussion(discussion.getID_discussion(), editContent.getText());
                        contentTextLabel.setText(discussion.getContenu_discussion());
                        dis.modifierDateDiscussion(discussion.getID_discussion(), new Date(System.currentTimeMillis()));
                        dateLabel.setText("modifiée le " + new Date(System.currentTimeMillis()).toString());
                        contentBox.getChildren().addAll(hbox2,vbox, contentTextLabel, replyButton);
                                                        contentBox.requestFocus();

                    }
                });

                annulerButton.setOnAction(e -> {
                    contentBox.getChildren().clear();
                    contentBox.getChildren().addAll(hbox2,vbox, contentTextLabel, replyButton);
                                                    contentBox.requestFocus();

                });

                contentBox.getChildren().clear();
                contentBox.getChildren().addAll(vbox, editContent, enregistrerButton, annulerButton);
                editContent.requestFocus();
            });
            supprimerDiscussion.setOnAction((ActionEvent) -> {

                scrollPane.setFitToWidth(false);

                Label alertTextLabel = new Label("êtes vous sur de supprimer votre réponse ? ");
                Button confirmerSupressionButton = new Button("oui");
                Button annulerSuppressionButton = new Button("non");

                confirmerSupressionButton.setOnAction((ActionEvent event) -> {
                    discussionBox.getChildren().removeAll(contentBox);
                    reponseBox.getChildren().clear();
                    scrollPane1.setOpacity(0);
                    contentBox.requestFocus();

                });
                annulerSuppressionButton.setOnAction(e -> {
                    contentBox.getChildren().clear();
                    contentBox.getChildren().addAll(hbox2, vbox, contentTextLabel, replyButton);
                    contentBox.requestFocus();

                });
                contentBox.getChildren().remove(hbox2);
                contentBox.getChildren().addAll(alertTextLabel, confirmerSupressionButton, annulerSuppressionButton);
                contentBox.requestFocus();

            });
            contentBox.getChildren().addAll(vbox, contentTextLabel, replyButton);

            replyButton.setOnAction((ActionEvent event) -> {

                reponseBox.getChildren().clear();
                scrollPane1.setFitToWidth(false);

                ReponseCRUD rep = new ReponseCRUD();

                List<Reponse> reponses = rep.rechercherReponsesDiscussion(discussion.getID_discussion());
                reponses.stream().map((Reponse reponse) -> {
                    VBox reponsesBox = new VBox();
                    Label labelContenu = new Label(reponse.getContenu_reponse());
                    Label labelDate = new Label("Dernière modification le "+reponse.getDate_reponse().toString());
                    reponsesBox.getChildren().addAll(labelContenu, labelDate);
                    reponsesBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
                    Button modifierReponse = new Button("Modifier la réponse");
                    Button supprimerReponse = new Button("supprimer la réponse");
                    HBox hbox = new HBox();
                    hbox.setSpacing(20);
                    if (reponse.getID_user() == 1) {
                        hbox.getChildren().addAll(modifierReponse, supprimerReponse);
                        reponsesBox.getChildren().addAll(hbox);
                    }

                    modifierReponse.setOnAction((ActionEvent e) -> {
                        TextArea editContent = new TextArea(reponse.getContenu_reponse());
                        Button enregistrerButton = new Button("Enregistrer");
                        Button annulerButton = new Button("Annuler");

                        enregistrerButton.setOnAction(e1 -> {
                            if (editContent.getText().trim().isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erreur de saisie");
                                alert.setContentText("Le texte ne peut pas être vide.");
                                alert.showAndWait();
                            } else {
                                reponse.setContenu_reponse(editContent.getText());
                                reponsesBox.getChildren().clear();
                                rep.modifierContenuReponse(reponse.getID_reponse(), editContent.getText());
                                rep.modifierDateReponse(reponse.getID_reponse(), new Date(System.currentTimeMillis()));
                                labelDate.setText("Modifiée le "+reponse.getDate_reponse());
                                labelContenu.setText(reponse.getContenu_reponse());
                                reponsesBox.getChildren().addAll(labelDate, labelContenu, hbox);
                                reponsesBox.requestFocus();
                            }
                        });

                        annulerButton.setOnAction(e1 -> {
                            reponsesBox.getChildren().clear();
                            reponsesBox.getChildren().addAll(hbox, labelDate, labelContenu);
                                                            reponsesBox.requestFocus();

                        });

                        reponsesBox.getChildren().clear();
                        HBox hbox1 = new HBox();
                        hbox1.setSpacing(20);
                        hbox1.getChildren().addAll(enregistrerButton, annulerButton);
                        reponsesBox.getChildren().addAll(labelDate, editContent, hbox1);
                    });
                    supprimerReponse.setOnAction((ActionEvent e) -> {
                        Label alertTextLabel1 = new Label("êtes vous sur de supprimer votre réponse ? ");
                        Button confirmerSupressionButton1 = new Button("oui");
                        Button annulerSuppressionButton1 = new Button("non");
                        confirmerSupressionButton1.setOnAction((ActionEvent e3) -> {
                            reponseBox.getChildren().removeAll(reponsesBox);
                            reponsesBox.getChildren().clear();
                                                            reponsesBox.requestFocus();

                        });
                        annulerSuppressionButton1.setOnAction(e3 -> {
                            reponsesBox.getChildren().clear();
                            reponsesBox.getChildren().addAll(labelDate, labelContenu, hbox);
                                                            reponsesBox.requestFocus();

                        });
                        reponsesBox.getChildren().clear();
                        reponsesBox.getChildren().addAll(labelDate, labelContenu, alertTextLabel1, confirmerSupressionButton1, annulerSuppressionButton1);
                                                        reponsesBox.requestFocus();

                    });

                    return reponsesBox;
                }).map(reponsesBox -> {

                    reponseBox.getChildren().add(reponsesBox);

                    return reponsesBox;
                }).forEachOrdered((VBox _item) -> {
                    reponseBox.setSpacing(10);
                    //  reponseBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");

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
                    if (nouvelleReponse.getText().trim().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText("Le texte ne peut pas être vide.");
                        alert.showAndWait();
                    } else {
                        rep.ajouterReponse(new Reponse(1, id, nouvelleReponse.getText(), new Date(System.currentTimeMillis())));
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

                }
                );
                reponseBox.setOpacity(1);
                scrollPane1.setOpacity(1);

            });

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
                if ((nouvelleDiscussionArea.getText().trim().isEmpty()) || (titreDiscussionArea.getText().trim().isEmpty())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Le texte ne peut pas être vide.");
                    alert.showAndWait();
                } else {
                    dis.ajouterDiscussion(new Discussion(1, titreDiscussionArea.getText(), nouvelleDiscussionArea.getText(), new Date(System.currentTimeMillis())));
                    VBox discussionBox1 = new VBox();
                    Label labelTitre = new Label(titreDiscussionArea.getText());
                    Label labelContenu = new Label(nouvelleDiscussionArea.getText());
                    Label labelDate = new Label((new Date(System.currentTimeMillis())).toString());
                    discussionBox1.getChildren().addAll(labelTitre, labelContenu, labelDate);
                    discussionBox.getChildren().addAll(discussionBox1);
                    discussionBox.getChildren().clear();
                    reponseBox.setOpacity(0);
                    scrollPane1.setOpacity(0);
                    initialize(url, rb);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.initStyle(StageStyle.TRANSPARENT);
                    alert.setHeaderText("ajout de discussion");
                    alert.setContentText("Discussion publiée avec succès !");
                    alert.showAndWait();
                }
            });

            discussionBox.getChildren().add(publierBtn);
        });
        discussionBox.getChildren().add(ajouterDiscussion);
    }
}
