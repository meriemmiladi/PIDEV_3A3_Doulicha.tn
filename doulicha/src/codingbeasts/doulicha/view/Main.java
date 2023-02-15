package codingbeasts.doulicha.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ghass
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Charge le fichier FXML

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/codingbeasts/doulicha/view/Discussion.fxml"));
        Parent root = loader.load();

        // Récupère la référence du contrôleur associé au fichier FXML
        // Affiche la scène
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
