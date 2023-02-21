/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

/**
 *
 * @author marie
 */
public class Logement {
    private int ID_logement,capacite_logement,etat_logement;
    private String nom_logement,description_logement,adresse_logement,image_logement,type_logement;
    private double prixNuitee_logement; 

    public Logement() {
    }
    
    public Logement(int ID_logement, String nom_logement, String description_logement, String adresse_logement, double prixNuitee_logement, int capacite_logement,String type_logement, int etat_logement, String image_logement) {
        this.ID_logement = ID_logement;
        this.nom_logement = nom_logement;
        this.description_logement = description_logement;
        this.adresse_logement = adresse_logement;
        this.prixNuitee_logement = prixNuitee_logement;
        this.capacite_logement = capacite_logement;
        this.type_logement= type_logement;
        this.etat_logement = etat_logement;
        this.image_logement = image_logement;
         }
    
     public Logement( String nom_logement, String description_logement, String adresse_logement, double prixNuitee_logement, int capacite_logement,String type_logement, int etat_logement, String image_logement) {
        this.nom_logement = nom_logement;
        this.description_logement = description_logement;
        this.adresse_logement = adresse_logement;
        this.prixNuitee_logement = prixNuitee_logement;
        this.capacite_logement = capacite_logement;
        this.type_logement= type_logement;
        this.etat_logement = etat_logement;
        this.image_logement = image_logement;
     }

    public int getID_logement() {
        return ID_logement;
    }

    public void setID_logement(int ID_logement) {
        this.ID_logement = ID_logement;
    }

    public int getCapacite_logement() {
        return capacite_logement;
    }

    public void setCapacite_logement(int capacite_logement) {
        this.capacite_logement = capacite_logement;
    }

    public int getEtat_logement() {
        return etat_logement;
    }

    public void setEtat_logement(int etat_logement) {
        this.etat_logement = etat_logement;
    }

    public String getNom_logement() {
        return nom_logement;
    }

    public void setNom_logement(String nom_logement) {
        this.nom_logement = nom_logement;
    }

    public String getDescription_logement() {
        return description_logement;
    }

    public void setDescription_logement(String description_logement) {
        this.description_logement = description_logement;
    }

    public String getAdresse_logement() {
        return adresse_logement;
    }

    public void setAdresse_logement(String adresse_logement) {
        this.adresse_logement = adresse_logement;
    }

    public String getImage_logement() {
        return image_logement;
    }

    public void setImage_logement(String image_logement) {
        this.image_logement = image_logement;
    }

    public String getType_logement() {
        return type_logement;
    }

    public void setType_logement(String type_logement) {
        this.type_logement = type_logement;
    }

    public double getPrixNuitee_logement() {
        return prixNuitee_logement;
    }

    public void setPrixNuitee_logement(double prixNuitee_logement) {
        this.prixNuitee_logement = prixNuitee_logement;
    }

    @Override
    public String toString() {
        return "Logement{" + "ID_logement=" + ID_logement + ", capacite_logement=" + capacite_logement + ", etat_logement=" + etat_logement + ", nom_logement=" + nom_logement + ", description_logement=" + description_logement + ", adresse_logement=" + adresse_logement + ", image_logement=" + image_logement + ", type_logement=" + type_logement + ", prixNuitee_logement=" + prixNuitee_logement + '}';
    }
     
     
    
    
    
}
