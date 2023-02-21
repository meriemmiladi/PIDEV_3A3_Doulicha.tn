/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;
import java.util.Date;

/**
 *
 * @author HP
 */
public class reclamation {
    private int ID_reclamation;
    private int ID_user;
    private String contenu_reclamation;
    private Date date_reclamation;
    private int etat_reclamation;

    public reclamation(int ID_reclamation, int ID_user, String contenu_reclamation, Date date_reclamation, int etat_reclamation) {
        this.ID_reclamation = ID_reclamation;
        this.ID_user = ID_user;
        this.contenu_reclamation = contenu_reclamation;
        this.date_reclamation = date_reclamation;
        this.etat_reclamation = etat_reclamation;
    }
<<<<<<< Updated upstream
=======
    public reclamation(int ID_user, String contenu_reclamation, int etat_reclamation) {
        
        this.ID_user = ID_user;
        this.contenu_reclamation = contenu_reclamation;
      
        this.etat_reclamation = etat_reclamation;
    }

    public reclamation() {
    }

    public reclamation(int ID_user, String contenu_reclamation, Date date_reclamation, int etat_reclamation) {
        this.ID_user = ID_user;
        this.contenu_reclamation = contenu_reclamation;
        this.date_reclamation = date_reclamation;
        this.etat_reclamation = etat_reclamation;
    }
    
>>>>>>> Stashed changes

    public int getID_reclamation() {
        return ID_reclamation;
    }

    public void setID_reclamation(int ID_reclamation) {
        this.ID_reclamation = ID_reclamation;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public String getContenu_reclamation() {
        return contenu_reclamation;
    }

    public void setContenu_reclamation(String contenu_reclamation) {
        this.contenu_reclamation = contenu_reclamation;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public int getEtat_reclamation() {
        return etat_reclamation;
    }

    public void setEtat_reclamation(int etat_reclamation) {
        this.etat_reclamation = etat_reclamation;
    }

    @Override
    public String toString() {
        return "reclamation{" + "ID_reclamation=" + ID_reclamation + ", ID_user=" + ID_user + ", contenu_reclamation=" + contenu_reclamation + ", date_reclamation=" + date_reclamation + ", etat_reclamation=" + etat_reclamation + '}';
    }
    
    
    
}
