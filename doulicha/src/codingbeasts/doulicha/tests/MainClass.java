/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.tests;

import codingbeasts.doulicha.entities.avis;
import codingbeasts.doulicha.entities.categorie_avis;
import codingbeasts.doulicha.entities.reclamation;
import codingbeasts.doulicha.services.serviceAvis;
import codingbeasts.doulicha.services.serviceCategorie;
import codingbeasts.doulicha.services.serviceReclamation;
import codingbeasts.doulicha.utils.MyConnection;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class MainClass {
    public static void main (String[] args)throws SQLException{
    //MyConnection mc =  MyConnection.getInstance();
    //MyConnection mc2 =  MyConnection.getInstance();
    //System.out.println(mc.hashCode()+ " - " + mc2.hashCode());
    //serviceAvis pcd = new serviceAvis();
    //avis p2 = new avis(1,1,1,1,9,"mal","avis evenement");
    //pcd.ajouteravis2(p2);
    //pcd.deleteavis();
    //System.out.println(pcd.afficheravis());
    //pcd.modifieravis();
    serviceCategorie pcd = new serviceCategorie();
    categorie_avis p2 = new categorie_avis("pas mal");
    pcd.ajoutercategorie2(p2);
    System.out.println(pcd.affichercategorie());
        //serviceReclamation pcd = new serviceReclamation();
        
        
        //reclamation p2 = new reclamation(1,"j'ai pas aimé l'endroit",0);
        //pcd.ajouterreclamation2(p2);
        //System.out.println(pcd.afficherreclamation());
    
    }
}

