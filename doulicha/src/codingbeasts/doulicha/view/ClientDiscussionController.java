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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
            Label contentLabel = new Label(discussion.getContenu_discussion());
            Button replyButton = new Button("Répondre");
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

                    return reponsesBox;
                }).map(reponsesBox -> {
                    reponseBox.getChildren().add(reponsesBox);
                    return reponsesBox;
                }).forEachOrdered((_item) -> {
                    reponseBox.setSpacing(10);
                    reponseBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");

                });
                TextArea nouvelleReponse = new TextArea();
                nouvelleReponse.setPrefHeight(200);
                nouvelleReponse.setPromptText("rédiger votre réponse...");
                nouvelleReponse.setWrapText(true);
                nouvelleReponse.setDisable(false);
                reponseBox.getChildren().add(nouvelleReponse);
                Button ajouterReponse = new Button("Partager votre réponse");
                reponseBox.getChildren().add(ajouterReponse);
                ajouterReponse.setOnAction((ActionEvent event1) -> {
                    rep.ajouterReponse(new Reponse(1, discussion.getID_discussion(), nouvelleReponse.getText(), new Date(System.currentTimeMillis())));
                    nouvelleReponse.setOpacity(0);
                    ajouterReponse.setOpacity(0);
                    VBox reponsesBox1 = new VBox();
                    Label labelContenu = new Label(nouvelleReponse.getText());
                    Label labelDate = new Label((new Date(System.currentTimeMillis())).toString());
                    reponsesBox1.getChildren().addAll(labelContenu,labelDate);
                    reponseBox.getChildren().addAll(reponsesBox1);

                }
                );
            });
            contentBox.getChildren().addAll(titleLabel, dateLabel, contentLabel, replyButton);
            contentBox.setSpacing(10);
            contentBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #3FC4ED; -fx-border-width: 2px; -fx-border-radius: 5px;");
            return contentBox;
        }).map((contentBox) -> {
            discussionBox.getChildren().add(contentBox);
            return contentBox;
        }).forEachOrdered((_item) -> {
            discussionBox.setSpacing(10);
        });

        scrollPane.setFitToWidth(false);
        /**
         * newDiscussionButton.setOnAction(event -> { // Ajouter le code de
         * création d'une nouvelle discussion ici });
         */
    }
}
