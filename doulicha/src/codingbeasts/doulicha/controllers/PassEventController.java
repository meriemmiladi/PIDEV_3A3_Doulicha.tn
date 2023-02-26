/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PassEventController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          String details = "Voici votre QR code";
        ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
        File F = new File("C:\\Users\\Admin\\Desktop\\MOI\\3 ème année\\PIDEV\\PIDEV_3A3_Doulicha.tn\\doulicha\\src\\images\\QRcode.jpg"); 
        try {
            FileOutputStream fos = new FileOutputStream(F);
            fos.write(out.toByteArray());
            fos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PassEventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PassEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
