/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulichafx;

import codingbeasts.doulicha.entities.Logement;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author marie
 */
public class ConsulterLogementClient extends Application{
    
    private Stage primaryStage;
    private Parent parentPage;
    
 
    
     private List<Logement> discussions = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
         this.primaryStage = primaryStage;
         this.primaryStage.setTitle("Consulter des logements et réserver");
         parentPage = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeFront.fxml"));
          Scene scene = new Scene(parentPage);
         this.primaryStage.setScene(scene);
         this.primaryStage.show();
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
