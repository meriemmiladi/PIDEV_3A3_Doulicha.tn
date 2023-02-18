/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class SginInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnSignIn;

    @FXML
    void onsave(ActionEvent event) {
         try {   
         Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
         Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root, 300, 250);
         Scene  scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

        
    } catch (IOException ex){
         System.out.println(ex.getMessage());
    }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
