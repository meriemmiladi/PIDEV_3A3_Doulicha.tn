/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aziz
 */
public class AccprodController implements Initializable {

    @FXML
    private ImageView a;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AJOUTPROD(ActionEvent event) throws IOException {
        
        
        Stage istage = new Stage();													
		Parent root = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/ajout.fxml")); 
		Scene scene = new Scene(root,800,480);											 
		
		istage.setScene(scene);
		istage.show();
                
                
    }

    
}
