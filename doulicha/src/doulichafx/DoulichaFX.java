/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulichafx;

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
public class DoulichaFX extends Application {
    

    private Stage primaryStage;
    private Parent parentPage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       this.primaryStage = primaryStage;
       this.primaryStage.setTitle("Gestion des logements et des réservations");
       
       parentPage = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/views/HomeBack.fxml"));
       Scene scene = new Scene(parentPage);
       this.primaryStage.setScene(scene);
       this.primaryStage.show(); }
        
       /* btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        
        /* StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene1 = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene1);
        primaryStage.show(); */
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
