/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

/**
 *
 * @author Admin
 */
public class projet {
    private int ID_projet ;
    private String nom_projet;
    private String description_projet;
    private float objectif_projet;
    private int etat_projet;
    private String image_projet;

    public projet(int id_projet, String nom_projet, String description_projet, float objectif_projet, int etat_projet, String image_projet) {
        this.ID_projet = id_projet;
        this.nom_projet = nom_projet;
        this.description_projet = description_projet;
        this.objectif_projet = objectif_projet;
        this.etat_projet = etat_projet;
        this.image_projet = image_projet;
    }
    public projet(String nom_projet, String description_projet, float objectif_projet, int etat_projet, String image_projet) {
        
        this.nom_projet = nom_projet;
        this.description_projet = description_projet;
        this.objectif_projet = objectif_projet;
        this.etat_projet = etat_projet;
        this.image_projet = image_projet;
    }

    public projet() {
       
    }
    public int getId_projet() {
        return ID_projet;
    }

    public void setId_projet(int id_projet) {
        this.ID_projet = id_projet;
    }

    public String getNom_projet() {
        return nom_projet;
    }

    public void setNom_projet(String nom_projet) {
        this.nom_projet = nom_projet;
    }

    public String getDescription_projet() {
        return description_projet;
    }

    public void setDescription_projet(String description_projet) {
        this.description_projet = description_projet;
    }

    public float getObjectif_projet() {
        return objectif_projet;
    }

    public void setObjectif_projet(float objectif_projet) {
        this.objectif_projet = objectif_projet;
    }

    public int getEtat_projet() {
        return etat_projet;
    }

    public void setEtat_projet(int etat_projet) {
        this.etat_projet = etat_projet;
    }

    public String getImage_projet() {
        return image_projet;
    }

    public void setImage_projet(String image_projet) {
        this.image_projet = image_projet;
    }

    @Override
    public String toString() {
        return "projet{" + "id_projet=" +ID_projet + ", nom_projet=" + nom_projet + ", description_projet=" + description_projet + ", objectif_projet=" + objectif_projet + ", etat_projet=" + etat_projet + ", image_projet=" + image_projet + '}';
    }
    
    
    
}
