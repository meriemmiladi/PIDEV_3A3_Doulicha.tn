/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class evenement {
    
    private int ID_event;
    private String nom_event;
    private String description_event;
    private String lieu_event;
    private String type_event;
    private Date dateDebut_event;
    private Date dateFin_event;
    private int capacite_event;
    private int nombreActuel_event;
    private String image_event;
    private double prix_event;

    public evenement() {
    }

    public evenement(int ID_event, String nom_event, String description_event, String lieu_event, String type_event, Date dateDebut_event, Date dateFin_event, int capacite_event, int nombreActuel_event, String image_event, double prix_event) {
        this.ID_event = ID_event;
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.lieu_event = lieu_event;
        this.type_event = type_event;
        this.dateDebut_event = dateDebut_event;
        this.dateFin_event = dateFin_event;
        this.capacite_event = capacite_event;
        this.nombreActuel_event = nombreActuel_event;
        this.image_event = image_event;
        this.prix_event = prix_event;
    }

    public evenement(String nom_event, String description_event, String lieu_event, String type_event, Date dateDebut_event, Date dateFin_event, int capacite_event, int nombreActuel_event, String image_event, double prix_event) {
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.lieu_event = lieu_event;
        this.type_event = type_event;
        this.dateDebut_event = dateDebut_event;
        this.dateFin_event = dateFin_event;
        this.capacite_event = capacite_event;
        this.nombreActuel_event = nombreActuel_event;
        this.image_event = image_event;
        this.prix_event = prix_event;
    }

    public int getID_event() {
        return ID_event;
    }

    public void setID_event(int ID_event) {
        this.ID_event = ID_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public Date getDateDebut_event() {
        return dateDebut_event;
    }

    public void setDateDebut_event(Date dateDebut_event) {
        this.dateDebut_event = dateDebut_event;
    }

    public Date getDateFin_event() {
        return dateFin_event;
    }

    public void setDateFin_event(Date dateFin_event) {
        this.dateFin_event = dateFin_event;
    }

    public int getCapacite_event() {
        return capacite_event;
    }

    public void setCapacite_event(int capacite_event) {
        this.capacite_event = capacite_event;
    }

    public int getNombreActuel_event() {
        return nombreActuel_event;
    }

    public void setNombreActuel_event(int nombreActuel_event) {
        this.nombreActuel_event = nombreActuel_event;
    }

    public String getImage_event() {
        return image_event;
    }

    public void setImage_event(String image_event) {
        this.image_event = image_event;
    }

    public double getPrix_event() {
        return prix_event;
    }

    public void setPrix_event(double prix_event) {
        this.prix_event = prix_event;
    }

    @Override
    public String toString() {
        return "evenement{" + "ID_event=" + ID_event + ", nom_event=" + nom_event + ", description_event=" + description_event + ", lieu_event=" + lieu_event + ", type_event=" + type_event + ", dateDebut_event=" + dateDebut_event + ", dateFin_event=" + dateFin_event + ", capacite_event=" + capacite_event + ", nombreActuel_event=" + nombreActuel_event + ", image_event=" + image_event + ", prix_event=" + prix_event + '}';
    }
    
    
    
}

