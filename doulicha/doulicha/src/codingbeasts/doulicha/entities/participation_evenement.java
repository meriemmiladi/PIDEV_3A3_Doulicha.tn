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
    private int nombre_participation;
    private String num_tel;
    
    
    public participation_evenement() {
    }

    public participation_evenement(int ID_participation, int ID_user, int ID_event, int nombre_participation, String num_tel) {
        this.ID_participation = ID_participation;
        this.ID_user = ID_user;
        this.ID_event = ID_event;
        this.nombre_participation = nombre_participation;
        this.num_tel = num_tel;
    }

    public participation_evenement(int ID_user, int ID_event, int nombre_participation, String num_tel) {
        this.ID_user = ID_user;
        this.ID_event = ID_event;
        this.nombre_participation = nombre_participation;
        this.num_tel = num_tel;
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


    public int getNombre_participation() {
        return nombre_participation;
    }

    public void setNombre_participation(int nombre_participation) {
        this.nombre_participation = nombre_participation;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "participation_evenement{" + "ID_participation=" + ID_participation + ", ID_user=" + ID_user + ", ID_event=" + ID_event + ", nombre_participation=" + nombre_participation + ", num_tel=" + num_tel + '}';
    }
    
    

    
    
    
    
    
}
