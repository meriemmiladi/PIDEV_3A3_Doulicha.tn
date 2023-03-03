/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.categorie_avis;
import java.util.List;

/**
 *
 * @author HP
 */
public interface InterfaceCategorie {
        public void ajoutercategorie (categorie_avis p);
    public void ajoutercategorie2 (categorie_avis p);
    public void modifiercategorie(categorie_avis p);
    public void deletecategorie(int ID_categorie);
    public List<categorie_avis> affichercategorie ();
    
}
