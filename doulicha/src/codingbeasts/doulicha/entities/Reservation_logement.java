/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author marie
 */
public class Reservation_logement {
    private int ID_reservation,ID_user,ID_logement,nbPersonnes_reservation;
    private Date dateArrivee_reservation,dateDepart_reservation;
    private double montantTotal_reservation;

    public Reservation_logement() {
    }

    public Reservation_logement(int ID_user, int ID_logement, Date dateArrivee_reservation, Date dateDepart_reservation, int nbPersonnes_reservation, double montantTotal_reservation) {
        this.ID_user = ID_user;
        this.ID_logement = ID_logement;
        this.nbPersonnes_reservation = nbPersonnes_reservation;
        this.dateArrivee_reservation = dateArrivee_reservation;
        this.dateDepart_reservation = dateDepart_reservation;
        this.montantTotal_reservation = montantTotal_reservation;
    }

    public Reservation_logement(int ID_reservation, int ID_user, int ID_logement, Date dateArrivee_reservation, Date dateDepart_reservation, int nbPersonnes_reservation, double montantTotal_reservation) {
        this.ID_reservation = ID_reservation;
        this.ID_user = ID_user;
        this.ID_logement = ID_logement;
        this.nbPersonnes_reservation = nbPersonnes_reservation;
        this.dateArrivee_reservation = dateArrivee_reservation;
        this.dateDepart_reservation = dateDepart_reservation;
        this.montantTotal_reservation = montantTotal_reservation;
    }

    
    
    public static boolean editPossible(Date dateArrivee) {
    // Obtenir la date actuelle
    Date dateActuelle = new Date();
    
    // Ajouter deux jours à la date actuelle
    Calendar cal = Calendar.getInstance();
    cal.setTime(dateActuelle);
    cal.add(Calendar.DATE, 2);
    Date dateLimite = cal.getTime();
    
    // Vérifier si la date d'arrivée est supérieure à la date limite
    return dateArrivee.after(dateLimite);
}

    
    public int getID_reservation() {
        return ID_reservation;
    }

    public void setID_reservation(int ID_reservation) {
        this.ID_reservation = ID_reservation;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public int getID_logement() {
        return ID_logement;
    }

    public void setID_logement(int ID_logement) {
        this.ID_logement = ID_logement;
    }

    public int getNbPersonnes_reservation() {
        return nbPersonnes_reservation;
    }

    public void setNbPersonnes_reservation(int nbPersonnes_reservation) {
        this.nbPersonnes_reservation = nbPersonnes_reservation;
    }

    public Date getDateArrivee_reservation() {
        return dateArrivee_reservation;
    }

    public void setDateArrivee_reservation(Date dateArrivee_reservation) {
        this.dateArrivee_reservation = dateArrivee_reservation;
    }

    public Date getDateDepart_reservation() {
        return dateDepart_reservation;
    }

    public void setDateDepart_reservation(Date dateDepart_reservation) {
        this.dateDepart_reservation = dateDepart_reservation;
    }

    public double getMontantTotal_reservation() {
        return montantTotal_reservation;
    }

    public void setMontantTotal_reservation(double montantTotal_reservation) {
        this.montantTotal_reservation = montantTotal_reservation;
    }

    @Override
    public String toString() {
        return "Reservation_logement{" + "ID_reservation=" + ID_reservation + ", ID_user=" + ID_user + ", ID_logement=" + ID_logement + ", nbPersonnes_reservation=" + nbPersonnes_reservation + ", dateArrivee_reservation=" + dateArrivee_reservation + ", dateDepart_reservation=" + dateDepart_reservation + ", montantTotal_reservation=" + montantTotal_reservation + '}';
    }
    
    
}
