/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Logement;
import java.util.List;

/**
 *
 * @author marie
 */
public interface IServiceLogement {
    
    
    public void ajouterLogement();
    public void ajouterLogement2(Logement l);
    public List<Logement> afficherLogement();
    public void modifierLogement(Logement l);
    public void supprimerLogement(int id);
    public int getIdParDesc(String id);
    public Double recupPrixNuitee(int id);
    public String recupNomLogement (int id);
    public void changerEtat0(int i);
    public void changerEtat1(int i);
    
}
