package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.services.DiscussionCRUD;
import codingbeasts.doulicha.services.ReponseCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import static codingbeasts.doulicha.services.PerspectiveService.getToxicity;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;

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
    ToggleButton darkModeToggle = new ToggleButton("Dark Mode");
    @FXML
    private Button newDiscussionButton;

    @FXML
    public void darkMode(ActionEvent event) {
        Scene scene = darkModeToggle.getScene();
        if (darkModeToggle.isSelected()) {
            scrollPane.getStylesheets().remove("codingbeasts/doulicha/view/style.css");
            scrollPane1.getStylesheets().remove("codingbeasts/doulicha/view/style.css");
            scrollPane.getStylesheets().add("codingbeasts/doulicha/view/dark.css");
            scrollPane1.getStylesheets().add("codingbeasts/doulicha/view/dark.css");
            darkModeToggle.setText("Light Mode");
        } else {
            scrollPane.getStylesheets().remove("codingbeasts/doulicha/view/dark.css");
            scrollPane1.getStylesheets().remove("codingbeasts/doulicha/view/dark.css");
            scrollPane.getStylesheets().add("codingbeasts/doulicha/view/style.css");
            scrollPane1.getStylesheets().add("codingbeasts/doulicha/view/style.css");
            darkModeToggle.setText("Dark mode");
        }
    }

    /*  private void analyzeDiscussion(String content, Button b) {
        try {
            AnalyzeCommentResponse response = PerspectiveService.analyzeComment(content);
            Map<String, AttributeScores> attributeScores = response.getAttributeScores();
            AttributeScores summaryScore = attributeScores.get("SUMMARY");
            Score score = summaryScore.getSummaryScore();
            Float toxicityScore = score.getValue();

            if (response != null && toxicityScore != null) {
                if (toxicityScore > 0.9) {
                    Alert alert = new Alert(AlertType.WARNING, "Response contains toxic content!");
                    alert.showAndWait();
                    b.setDisable(true); // disable submit button if toxic
                } else {
                    b.setDisable(false); // enable submit button if not toxic
                }
            }
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR, "Error analyzing response!");
            alert.showAndWait();
        }
    } */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (darkModeToggle.isSelected()) {
            scrollPane.getStylesheets().remove("codingbeasts/doulicha/view/style.css");
            scrollPane1.getStylesheets().remove("codingbeasts/doulicha/view/style.css");
            scrollPane.getStylesheets().add("codingbeasts/doulicha/view/dark.css");
            scrollPane1.getStylesheets().add("codingbeasts/doulicha/view/dark.css");
        } else {
            scrollPane.getStylesheets().remove("codingbeasts/doulicha/view/dark.css");
            scrollPane1.getStylesheets().remove("codingbeasts/doulicha/view/dark.css");
            scrollPane.getStylesheets().add("codingbeasts/doulicha/view/style.css");
            scrollPane1.getStylesheets().add("codingbeasts/doulicha/view/style.css");
        }
        DiscussionCRUD dis = new DiscussionCRUD();
        List<Discussion> discussions = dis.afficherDiscussions();
        VBox mainContainer = new VBox();
        HBox dateSelections = new HBox();
        DatePicker fromDate = new DatePicker();
        DatePicker toDate = new DatePicker();
        Button showButton = new Button("Afficher");

        // Ajouter les éléments dans la fenêtre
        dateSelections.getChildren().addAll(fromDate, toDate, showButton);
        discussionBox.getChildren().addAll(dateSelections);

        discussions.stream().map((Discussion discussion) -> {
            VBox contentBox = new VBox();
            contentBox.setId("#VBoxx");
            int id = discussion.getID_discussion();
            Label titleLabel = new Label(discussion.getTitre_discussion());
            titleLabel.setFont(new Font(40));

            Label dateLabel = new Label(discussion.getDate_discussion().toString());

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
                enregistrerButton.setOnAction((ActionEvent e) -> {
                    if (editContent.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText("Le texte ne peut pas être vide.");
                        alert.showAndWait();
                    } else {
                        double toxicity = 0;
                        try {
                            toxicity = getToxicity(editContent.getText());
                        } catch (IOException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setContentText("Une erreur s'est produite lors du calcul de la toxicité du texte. Veuillez réessayer plus tard.");
                            alert.showAndWait();
                            return;
                        } catch (GeneralSecurityException ex) {
                            System.err.println(ex.getMessage());
                        }
                        if (toxicity < 0.7) {
                            discussion.setContenu_discussion(editContent.getText());
                            contentBox.getChildren().clear();
                            dis.modifierContenuDiscussion(discussion.getID_discussion(), editContent.getText());
                            contentTextLabel.setText(discussion.getContenu_discussion());
                            dis.modifierDateDiscussion(discussion.getID_discussion(), new Date(System.currentTimeMillis()));
                            dateLabel.setText(new Date(System.currentTimeMillis()).toString());
                            contentBox.getChildren().addAll(hbox2, vbox, contentTextLabel, replyButton);
                            contentBox.requestFocus();
                        } else {
                            if (toxicity > 0.9) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Texte inapproprié");
                                alert.setContentText("Le contenu est très toxique.");
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Texte inapproprié");
                                alert.setContentText("Le contenu est inapproprié.");
                                alert.showAndWait();
                            }
                        }

                    }
                });

                annulerButton.setOnAction(e -> {
                    contentBox.getChildren().clear();
                    contentBox.getChildren().addAll(hbox2, vbox, contentTextLabel, replyButton);
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
                reponseBox.setOpacity(1);
                scrollPane1.setOpacity(1);
                reponseBox.getChildren().clear();
                scrollPane1.setFitToWidth(false);

                ReponseCRUD rep = new ReponseCRUD();

                List<Reponse> reponses = rep.rechercherReponsesDiscussion(discussion.getID_discussion());
                reponses.stream().map((Reponse reponse) -> {
                    VBox reponsesBox = new VBox();
                    Label labelContenu = new Label(reponse.getContenu_reponse());
                    Label labelDate = new Label("Dernière modification le " + reponse.getDate_reponse().toString());
                    VBox vboxx = new VBox();
                    vboxx.getChildren().addAll(labelContenu, labelDate);
                    reponsesBox.getChildren().addAll(vboxx);
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
                            if (editContent.getText().isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erreur de saisie");
                                alert.setContentText("Le texte ne peut pas être vide.");
                                alert.showAndWait();
                            } else {
                                double toxicity = 0;
                                try {
                                    toxicity = getToxicity(editContent.getText());
                                } catch (IOException ex) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Erreur");
                                    alert.setContentText("Une erreur s'est produite lors du calcul de la toxicité du texte. Veuillez réessayer plus tard.");
                                    alert.showAndWait();
                                    return;
                                } catch (GeneralSecurityException ex) {
                                    System.err.println(ex.getMessage());
                                }
                                if (toxicity < 0.7) {
                                    reponse.setContenu_reponse(editContent.getText());
                                    reponsesBox.getChildren().clear();
                                    rep.modifierContenuReponse(reponse.getID_reponse(), editContent.getText());
                                    reponse.setDate_reponse(new Date(System.currentTimeMillis()));
                                    vboxx.getChildren().clear();

                                    labelDate.setText("Modifiée le " + reponse.getDate_reponse().toString());
                                    labelContenu.setText(reponse.getContenu_reponse());
                                    vboxx.getChildren().addAll(labelContenu, labelDate);
                                    reponsesBox.getChildren().addAll(vboxx, hbox);
                                    reponsesBox.requestFocus();
                                } else {
                                    if (toxicity > 0.9) {
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Texte inapproprié");
                                        alert.setContentText("Le contenu est très toxique.");
                                        alert.showAndWait();
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Texte inapproprié");
                                        alert.setContentText("Le contenu est inapproprié.");
                                        alert.showAndWait();
                                    }
                                }
                            }
                        });

                        annulerButton.setOnAction(e1 -> {
                            reponsesBox.getChildren().clear();
                            vboxx.getChildren().clear();
                            vboxx.getChildren().addAll(labelContenu, labelDate);
                            reponsesBox.getChildren().addAll(vboxx, hbox);
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

                    String contenu = nouvelleReponse.getText().trim();
                   
                    if (!contenu.matches("^[a-zA-Z0-9,.!? ]*$")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur de saisie");
                        alert.setContentText("Le contenu ne peut contenir que des lettres, des chiffres et les caractères suivants : , . ! ?");
                        alert.showAndWait();
                    } else {
                       
                        double toxicity = 0;
                        try {
                         
                            toxicity = getToxicity(contenu);
                        } catch (IOException ex) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur");
                            alert.setContentText("Une erreur s'est produite lors du calcul de la toxicité du texte. Veuillez réessayer plus tard.");
                            alert.showAndWait();
                            return;
                        } catch (GeneralSecurityException ex) {
                            System.err.println(ex.getMessage());
                        }

                        if (toxicity < 0.7) {
                            rep.ajouterReponse(new Reponse(1, id, nouvelleReponse.getText(), new Date(System.currentTimeMillis())));
                            VBox discussionBox1 = new VBox();
                            Label labelContenu = new Label(contenu);
                            Label labelDate = new Label((new Date(System.currentTimeMillis())).toString());
                            nouvelleReponse.setOpacity(0);
                            ajouterReponse.setOpacity(0);
                            VBox reponsesBox1 = new VBox();
                            reponsesBox1.getChildren().addAll(labelContenu, labelDate);
                            reponseBox.getChildren().addAll(reponsesBox1);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("ajout de réponse");
                            alert.setContentText("Réponse publiée avec succès !");
                            alert.showAndWait();
                        } else {
                            if (toxicity > 0.9) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Texte inapproprié");
                                alert.setContentText("Le contenu est très toxique.");
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Texte inapproprié");
                                alert.setContentText("Le contenu est inapproprié.");
                                alert.showAndWait();
                            }
                        }

                    }
                });

            });
            reponseBox.setOpacity(1);
            scrollPane1.setOpacity(1);
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
                String titre = titreDiscussionArea.getText().trim();
                String contenu = nouvelleDiscussionArea.getText().trim();

                if (titre.isEmpty() || contenu.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Le titre et le contenu ne peuvent pas être vides.");
                    alert.showAndWait();
                } else if (titre.length() > 50 || contenu.length() > 500) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Le titre ne peut pas dépasser 50 caractères, et le contenu ne peut pas dépasser 500 caractères.");
                    alert.showAndWait();
                } else if (!contenu.matches("^[a-zA-Z0-9,.!? ]*$")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setContentText("Le contenu ne peut contenir que des lettres, des chiffres et les caractères suivants : , . ! ?");
                    alert.showAndWait();
                } else {
                    double toxicity = 0;
                    double toxicity1=0;
                    try {
                        toxicity1=getToxicity(titre);
                        toxicity = getToxicity(contenu);
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setContentText("Une erreur s'est produite lors du calcul de la toxicité du texte. Veuillez réessayer plus tard.");
                        alert.showAndWait();
                        return;
                    } catch (GeneralSecurityException ex) {
                        System.err.println(ex.getMessage());
                    }

                    if (toxicity < 0.7&&toxicity1<0.7){
                        dis.ajouterDiscussion(new Discussion(1, titre, contenu, new Date(System.currentTimeMillis())));
                        VBox discussionBox1 = new VBox();
                        Label labelTitre = new Label(titre);
                        Label labelContenu = new Label(contenu);
                        Label labelDate = new Label((new Date(System.currentTimeMillis())).toString());
                        discussionBox1.getChildren().addAll(labelTitre, labelContenu, labelDate);
                        discussionBox.getChildren().add(discussionBox1);
                        discussionBox.getChildren().clear();
                        reponseBox.setOpacity(0);
                        scrollPane1.setOpacity(0);
                        initialize(url, rb);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("ajout de discussion");
                        alert.setContentText("Discussion publiée avec succès !");
                        alert.showAndWait();
                    } else {
                        if (toxicity > 0.9||toxicity1>0.9) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Texte inapproprié");
                            alert.setContentText("Le contenu est très toxique.");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Texte inapproprié");
                            alert.setContentText("Le contenu est inapproprié.");
                            alert.showAndWait();
                        }
                    }

                }
            });

            discussionBox.getChildren().add(publierBtn);
            publierBtn.requestFocus();
        });
        discussionBox.getChildren().add(ajouterDiscussion);
// Set the action for the "show" button
        showButton.setOnAction((ActionEvent event2) -> {
            LocalDate startDate = fromDate.getValue();
            LocalDate endDate = toDate.getValue();

            // Perform the date search and remove contentBoxes that don't fit within the selected date range
            filterDiscussionsByDate(startDate, endDate);
        });

    }
    // Define a method to perform the date search and remove contentBoxes that don't fit within the selected date range

    private void filterDiscussionsByDate(LocalDate startDate, LocalDate endDate) {
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        List<Node> nodesToRemove = new ArrayList<>();
        for (Node node : discussionBox.getChildren()) {
            if (node instanceof VBox) {
                VBox contentBox = (VBox) node;
                boolean hasHBox = false;
                VBox labelBox;
                for (Node child : contentBox.getChildren()) {
                    if (child instanceof HBox) {
                        hasHBox = true;
                        break;
                    }
                }
                if (hasHBox) {
                    // Get the HBox containing the title and date labels
                    labelBox = (VBox) contentBox.getChildren().get(1);
                } else {
                    labelBox = (VBox) contentBox.getChildren().get(0);
                }

                // Get the label containing the date string
                Label dateLabel = (Label) labelBox.getChildren().get(1);

                // Convert the date string to sql.Date and compare with the selected range
                String dateString = dateLabel.getText();
                java.sql.Date contentDate = java.sql.Date.valueOf(dateString);
                if (contentDate.compareTo(sqlStartDate) < 0 || contentDate.compareTo(sqlEndDate) > 0) {
                    nodesToRemove.add(contentBox);
                }
            }
        }

        discussionBox.getChildren().removeAll(nodesToRemove);
    }

}
