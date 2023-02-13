/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class don {
    private int ID_don;
    private int ID_user;
    private int ID_projet;
    private float valeur_don;
    private Date date_don;

    public don(int ID_don, int ID_user, int ID_projet, float valeur_don, Date date_don) {
        this.ID_don = ID_don;
        this.ID_user = ID_user;
        this.ID_projet = ID_projet;
        this.valeur_don = valeur_don;
        this.date_don = date_don;
    }
    public don(int ID_user, int ID_projet, float valeur_don, Date date_don) {
       
        this.ID_user = ID_user;
        this.ID_projet = ID_projet;
        this.valeur_don = valeur_don;
        this.date_don = date_don;
    }
    public don(int ID_user, int ID_projet, float valeur_don) {
       
        this.ID_user = ID_user;
        this.ID_projet = ID_projet;
        this.valeur_don = valeur_don;
       
    }
     public don(float valeur_don) {
       
       
        this.valeur_don = valeur_don;
       
    }

    public don(float valeur_don, Date date_don) {
       
       
        this.valeur_don = valeur_don;
        this.date_don = date_don;
    }
    public don () {

}

    public don(int i, String dat) {
        Date act = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(act);
        System.out.println(date);
    }

    public int getID_don() {
        return ID_don;
    }

    public void setID_don(int ID_don) {
        this.ID_don = ID_don;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public int getID_projet() {
        return ID_projet;
    }

    public void setID_projet(int ID_projet) {
        this.ID_projet = ID_projet;
    }

    public float getValeur_don() {
        return valeur_don;
    }

    public void setValeur_don(float valeur_don) {
        this.valeur_don = valeur_don;
    }

    public Date getDate_don() {
        return date_don;
    }

    public void setDate_don(Date date_don) {
        this.date_don = date_don;
    }

    @Override
    public String toString() {
        return "don{" + "ID_don=" + ID_don + ", ID_user=" + ID_user + ", ID_projet=" + ID_projet + ", valeur_don=" + valeur_don + ", date_don=" + date_don + '}';
    }
    
}
