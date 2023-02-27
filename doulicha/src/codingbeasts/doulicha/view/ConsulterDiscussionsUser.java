package codingbeasts.doulicha.view;

import java.io.IOException;
import java.security.GeneralSecurityException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ConsulterDiscussionsUser extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, GeneralSecurityException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/DiscussionUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
