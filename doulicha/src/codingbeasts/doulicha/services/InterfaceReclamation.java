/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.avis;
import codingbeasts.doulicha.entities.reclamation;
import java.util.List;

/**
 *
 * @author HP
 */
public interface InterfaceReclamation {
    public void ajouterreclamation (reclamation p);
    public void ajouterreclamation2 (reclamation p);
    public void modifierreclamation(reclamation p);
    public void deletereclamation(int ID_reclamation);
    public List<reclamation> afficherreclamation ();
    
}
