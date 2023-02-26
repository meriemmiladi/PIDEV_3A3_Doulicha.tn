package codingbeasts.doulicha.view;

import codingbeasts.doulicha.entities.Discussion;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsulterDiscussionsUser extends Application {
    private List<Discussion> discussions = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws IOException, GeneralSecurityException {
        Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/DiscussionUser.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
