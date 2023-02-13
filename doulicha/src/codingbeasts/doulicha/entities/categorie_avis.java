/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

/**
 *
 * @author HP
 */
public class categorie_avis {
    private int ID_categorie;
    private String nom_categorie;

    public categorie_avis(int ID_categorie, String nom_categorie) {
        this.ID_categorie = ID_categorie;
        this.nom_categorie = nom_categorie;
    }

    public int getID_categorie() {
        return ID_categorie;
    }

    public void setID_categorie(int ID_categorie) {
        this.ID_categorie = ID_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    @Override
    public String toString() {
        return "categorie_avis{" + "ID_categorie=" + ID_categorie + ", nom_categorie=" + nom_categorie + '}';
    }
    
    
    
}
