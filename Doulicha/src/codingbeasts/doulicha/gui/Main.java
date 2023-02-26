package codingbeasts.doulicha.gui;

import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class Main extends Application {

@Override
public void start(Stage primaryStage) {
    try {
 

    Parent root = FXMLLoader.load(getClass().getResource("AffichageProjetUser.fxml"));
    Scene scene = new Scene(root);
    
    primaryStage.setScene(scene);
    primaryStage.show();
    }catch (Exception e){
        e.printStackTrace();
        System.err.println("tttttttttttt");
    }
}
public static void main(String[] args) {
    launch(args);
}


}
  
