/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import com.sun.javafx.webkit.WebConsoleListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import jdk.nashorn.api.scripting.JSObject;

/**
 * FXML Controller class
 *
 * @author kagha
 */


public class MapController implements Initializable {

    @FXML
    private WebView web;
    static String map_value;
    /**    private final JavaBridge bridge = new JavaBridge();
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WebEngine engine = web.getEngine();
        url = this.getClass().getResource("map.html");
        engine.load(url.toString());
        engine.setOnAlert(event -> showAlert(event.getData()));
        
        //engine.load(url);
        
    }    
    public void showAlert(String message) {
        Dialog<Void> alert = new Dialog<>();
        alert.getDialogPane().setContentText(message);
        System.out.println(message);
        //AddSalleController cr = new AddSalleController();
        map_value=message;
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        alert.showAndWait();
    }

    public static String getMap_value() {
        return map_value;
    }


    
}