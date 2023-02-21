/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import codingbeasts.doulicha.interfaces.MyListener;
import codingbeasts.doulicha.entities.evenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DetailEvenementController implements Initializable {
    
    private evenement event;
    String xamppFolderPath = "C:/xampp/htdocs/images/";

    @FXML
    private ImageView image_detail;
    @FXML
    private Label nom_detail;
    @FXML
    private Label lieu_detail;
    @FXML
    private Label datedebut_detail;
    @FXML
    private Label prix_detail;
    @FXML
    private Label datefin_detail;
    @FXML
    private Label description_detail;
    
    private MyListener myListener;
    private VBox parent;
     private HBox parent2;
    @FXML
    private Label id_detail;

    /**
     * Initializes the controller class.
     */
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     public void setData(evenement event, MyListener myListener, VBox parent) {
        this.event =event;
        this.myListener = myListener;
        this.parent = parent;
        
      
       LocalDate dateDebut_local = event.getDateDebut_event().toLocalDate();
    LocalDate dateFin_local = event.getDateFin_event().toLocalDate();
        
       this.id_detail.setText(String.valueOf(event.getID_event()));
        this.nom_detail.setText(event.getNom_event());
        this.description_detail.setText(event.getDescription_event());
        this.lieu_detail.setText(event.getLieu_event());
        this.prix_detail.setText(String.valueOf(event.getPrix_event())+" DT");
        this.datedebut_detail.setText(String.valueOf(dateDebut_local));
        this.datefin_detail.setText(String.valueOf(dateFin_local));
        
       
        
       String test = xamppFolderPath + event.getImage_event();
    try {
            BufferedImage bufferedImage = ImageIO.read(new File(test));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.image_detail.setImage(image);
        } catch (IOException ex) {
            System.out.println("could not get the image");
        } 
          
    }
     
      public void setData2(evenement event, MyListener myListener) {
        this.event =event;
        this.myListener = myListener;
        
       this.id_detail.setText(String.valueOf(event.getID_event()));
        
    } 

    @FXML
    private void click(MouseEvent event) {
        
        //        myListener.onClickListener(promo);
        
      //  VBox v = (VBox)this.parent.getChildren().get(0);
        Label nom = (Label)this.parent.getChildren().get(0);
        nom.setText(this.nom_detail.getText());
        
         ImageView img = (ImageView)this.parent.getChildren().get(1);
        img.setImage(this.image_detail.getImage());
        
        
        Label desc = (Label)this.parent.getChildren().get(2);
        desc.setText(this.description_detail.getText());
         description_detail.setWrapText(true);
        
        
        Label lieu = (Label)this.parent.getChildren().get(3);
        lieu.setText(this.lieu_detail.getText());
        
        Label datedebut = (Label) this.parent.getChildren().get(4);
        datedebut.setText(String.valueOf(this.datedebut_detail.getText()));
        
        Label datefin = (Label)this.parent.getChildren().get(5);
        datefin.setText(String.valueOf(this.datefin_detail.getText()));
        
        
        
        
       Label prix = (Label) this.parent.getChildren().get(6);
        prix.setText(String.valueOf(this.prix_detail.getText()));
        
         Label id = (Label) this.parent.getChildren().get(7);
        id.setText(String.valueOf(this.id_detail.getText()));
      
    }
    
}
