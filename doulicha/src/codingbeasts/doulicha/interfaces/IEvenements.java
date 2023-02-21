/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.interfaces;

import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public interface IEvenements {
    
    
     public ObservableList<evenement> afficherEvents();
    
    
    
    public void ajouterEvenement();
    public void ajouterEvenement2(evenement event);
    
    public List<evenement> afficherEvenements();
             
          
    
     public void modifierEvenement(evenement event);

    
    
    
    public boolean supprimerEvenement(int ID_event);
    
    
    
     public int getId(String id);
         public int getId2(String id);
     public String getNom(int id);
     
     
    
}
