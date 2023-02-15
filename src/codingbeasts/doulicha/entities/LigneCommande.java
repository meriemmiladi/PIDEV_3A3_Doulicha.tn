/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

/**
 *
 * @author aziz
 */
public class LigneCommande {
    private int ID_ligne ;
    private int ID_commande;
    private int ID_produit;
    private int quantite_achete_ligne;
    
    public LigneCommande(){};

    public LigneCommande(int ID_ligne, int ID_commande, int ID_produit, int quantite_achete_ligne) {
        this.ID_ligne = ID_ligne;
        this.ID_commande = ID_commande;
        this.ID_produit = ID_produit;
        this.quantite_achete_ligne = quantite_achete_ligne;
    }

    public LigneCommande(int ID_commande, int ID_produit, int quantite_achete_ligne) {
        this.ID_commande = ID_commande;
        this.ID_produit = ID_produit;
        this.quantite_achete_ligne = quantite_achete_ligne;
    }

    public int getID_ligne() {
        return ID_ligne;
    }

    public void setID_ligne(int ID_ligne) {
        this.ID_ligne = ID_ligne;
    }

    public int getID_commande() {
        return ID_commande;
    }

    public void setID_commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public int getID_produit() {
        return ID_produit;
    }

    public void setID_produit(int ID_produit) {
        this.ID_produit = ID_produit;
    }

    public int getQuantite_achete_ligne() {
        return quantite_achete_ligne;
    }

    public void setQuantite_achete_ligne(int quantite_achete_ligne) {
        this.quantite_achete_ligne = quantite_achete_ligne;
    }
    
    
    
}
