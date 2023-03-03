/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface InterfaceUtilisateur {

    public void ajouterUtilisateur();

    public void ajouterUtlisateur2(Utilisateur p);

    public void modifierutilisateur(Utilisateur p);

    public void supprimerutilisateur(int id);

    public List<Utilisateur> afficherUtilisateur();

}
