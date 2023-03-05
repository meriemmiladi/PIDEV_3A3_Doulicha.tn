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
public class avis {
    private int ID_avis;
    private int ID_categorie;
    private int ID_user;
    private int ID_event;
    private int ID_logement;
    private int note_avis;
    private String contenu_avis;
    private String type_avis;

    public avis(int ID_avis, int ID_catagorie, int ID_user, int ID_event, int ID_logement, int note_avis, String contenu_avis, String type_avis) {
        this.ID_avis = ID_avis;
        this.ID_categorie = ID_catagorie;
        this.ID_user = ID_user;
        this.ID_event = ID_event;
        this.ID_logement = ID_logement;
        this.note_avis = note_avis;
        this.contenu_avis = contenu_avis;
        this.type_avis = type_avis;
    }
    public avis(int ID_catagorie, int ID_user, int ID_event, int ID_logement, int note_avis, String contenu_avis, String type_avis) {
        this.ID_categorie = ID_catagorie;
        this.ID_user = ID_user;
        this.ID_event = ID_event;
        this.ID_logement = ID_logement;
        this.note_avis = note_avis;
        this.contenu_avis = contenu_avis;
        this.type_avis = type_avis;
    }

    public avis() {
    }
        

    public int getID_avis() {
        return ID_avis;
    }

    public void setID_avis(int ID_avis) {
        this.ID_avis = ID_avis;
    }

    public int getID_categorie() {
        return ID_categorie;
    }

    public void setID_categorie(int ID_categorie) {
        this.ID_categorie = ID_categorie;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public int getID_event() {
        return ID_event;
    }

    public void setID_event(int ID_event) {
        this.ID_event = ID_event;
    }

    public int getID_logement() {
        return ID_logement;
    }

    public void setID_logement(int ID_logement) {
        this.ID_logement = ID_logement;
    }

    public int getNote_avis() {
        return note_avis;
    }

    public void setNote_avis(int note_avis) {
        this.note_avis = note_avis;
    }

    public String getContenu_avis() {
        return contenu_avis;
    }

    public void setContenu_avis(String contenu_avis) {
        this.contenu_avis = contenu_avis;
    }

    public String getType_avis() {
        return type_avis;
    }

    public void setType_avis(String type_avis) {
        this.type_avis = type_avis;
    }

    @Override
    public String toString() {
        return "avis{" + "ID_avis=" + ID_avis + ", ID_catagorie=" + ID_categorie + ", ID_user=" + ID_user + ", ID_event=" + ID_event + ", ID_logement=" + ID_logement + ", note_avis=" + note_avis + ", contenu_avis=" + contenu_avis + ", type_avis=" + type_avis + '}';
    }

    
    
    
    
}
