/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.participation_evenement;
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
public interface IParticipation_evenement {
    
    
        public ObservableList<participation_evenement> afficherParts();
    public void ajouterParticipationEvenement(participation_evenement participation);
    
    
    public List<participation_evenement> afficherParticipations();
    
    
    public boolean supprimerParticipation(int ID_participation);
      public void modifierParticipationEvenement(participation_evenement participation);
    
    public int getId2(String id);
    
    
    
}
