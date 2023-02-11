/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulicha.entities;

import java.sql.Date;

/**
 *
 * @author aziz
 */
public class Commande {
    private final int ID_commande;
    private final int ID_user;
    private final Date date_commande;
    private final String etat_commande;
    
    public Commande(int ID_commande,int ID_user,Date date_commande, String etat_commande) {
    this.ID_commande = ID_commande;
    this.ID_user = ID_user;
    this.date_commande = date_commande;
    this.etat_commande= etat_commande;
}
    
    
    
}
