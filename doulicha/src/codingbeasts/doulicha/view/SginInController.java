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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
     private Parent fxml;
    
     @FXML
    private TextField txtnom;

     @FXML
    private PasswordField txtpassword;
     @FXML
    private Button btnopenhome;
    
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
       
    
          @FXML
    void openhome(ActionEvent event) {
        String nom = txtnom.getText();
        String pass = txtpassword.getText();
        if(nom.equals("Admin")&& pass.equals("Admin")){
            System.out.println("bien!");
//          VBox.getScene().getWindow().hide();
            Stage home = new Stage();
            try{
               fxml = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
              Scene scene = new Scene(fxml);
              home.setScene(scene);
              home.show();
               
            } catch (IOException e){
                e.printStackTrace();
                
            }
            
        }else{
            System.out.println("error!");
        }

    }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
}
