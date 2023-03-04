/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

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
 * @author ghass
 */
public class GestionDiscussionReponseController implements Initializable {

  @FXML
  Button gestionDiscussion;
    @FXML
  Button gestionReponse;
    @FXML
      public void gestionDiscussion(ActionEvent event) throws IOException {
        Parent pageSuivanteParent = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/Discussion.fxml"));
        Scene pageSuivanteScene = new Scene(pageSuivanteParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(pageSuivanteScene);
        appStage.show();
    }
       @FXML
      public void gestionReponse(ActionEvent event) throws IOException {
        Parent pageSuivanteParent = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/Reponse.fxml"));
        Scene pageSuivanteScene = new Scene(pageSuivanteParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(pageSuivanteScene);
        appStage.show();
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
