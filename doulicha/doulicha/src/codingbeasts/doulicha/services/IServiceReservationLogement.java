/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Reservation_logement;
import java.util.List;

/**
 *
 * @author marie
 */
public interface IServiceReservationLogement {
    
    public void ajouterReservationLogement();
    public void ajouterReservationLogement2(Reservation_logement rl);
    public List<Reservation_logement> afficherReservationLogement();
    public void modifierReservationLogement(Reservation_logement rl);
    public void supprimerReservationLogement(int id);
    public List<Reservation_logement> afficherMesReservations(int id);
}
