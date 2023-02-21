/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.avis;
import java.util.List;

/**
 *
 * @author HP
 */
public interface InterfaceAvis {
    public void ajouteravis(avis p);
    public void ajouteravis2 (avis p);
    public void modifieravis(avis p);
    public void deleteavis(int ID_avis);
    public List<avis> afficheravis();
    
}
