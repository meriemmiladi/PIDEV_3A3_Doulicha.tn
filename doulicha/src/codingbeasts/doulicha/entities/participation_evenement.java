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
public class participation_evenement {
    
    private int ID_participation;
    private int ID_user;
    private int ID_event;
    private Date date_participation;
    private int nombre_participation;
    
    
    public participation_evenement() {
    }

    public participation_evenement(int ID_participation, int ID_user, int ID_event, Date date_participation, int nombre_participation) {
        this.ID_participation = ID_participation;
        this.ID_user = ID_user;
        this.ID_event = ID_event;
        this.date_participation = date_participation;
        this.nombre_participation = nombre_participation;
    }

    public participation_evenement(int ID_user, int ID_event, Date date_participation, int nombre_participation) {
        this.ID_user = ID_user;
        this.ID_event = ID_event;
        this.date_participation = date_participation;
        this.nombre_participation = nombre_participation;
    }

    public int getID_participation() {
        return ID_participation;
    }

    public void setID_participation(int ID_participation) {
        this.ID_participation = ID_participation;
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

    public Date getDate_participation() {
        return date_participation;
    }

    public void setDate_participation(Date date_participation) {
        this.date_participation = date_participation;
    }

    public int getNombre_participation() {
        return nombre_participation;
    }

    public void setNombre_participation(int nombre_participation) {
        this.nombre_participation = nombre_participation;
    }

    @Override
    public String toString() {
        return "participation_evenement{" + "ID_participation=" + ID_participation + ", ID_user=" + ID_user + ", ID_event=" + ID_event + ", date_participation=" + date_participation + ", nombre_participation=" + nombre_participation + '}';
    }
    
    
    
    
    
}
