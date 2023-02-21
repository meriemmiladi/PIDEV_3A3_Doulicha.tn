/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

import java.sql.Date;

/**
 *
 * @author aziz
 */
public class Commande {
    private int ID_commande;
    private int ID_user;
    private Date date_commande;
    private int etat_commande;

    @Override
    public String toString() {
        return "Commande{" + "ID_commande=" + ID_commande + ", ID_user=" + ID_user + ", date_commande=" + date_commande + ", etat_commande=" + etat_commande + '}';
    }
    
    public Commande(){}
    
    public Commande(int ID_user,Date date_commande, int etat_commande) {
    this.ID_user = ID_user;
    this.date_commande = date_commande;
    this.etat_commande= etat_commande;
}
   
    
    public Commande(Date date_commande, int etat_commande) {
    this.date_commande = date_commande;
    this.etat_commande= etat_commande;
}
    
    public Commande(int ID_commande,int ID_user,Date date_commande, int etat_commande) {
    this.ID_commande = ID_commande;
    this.ID_user = ID_user;
    this.date_commande = date_commande;
    this.etat_commande= etat_commande;
}

    public int getID_commande() {
        return ID_commande;
    }

    public void setID_commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public int getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(int etat_commande) {
        this.etat_commande = etat_commande;
    }
    
    
    
    
    
}
