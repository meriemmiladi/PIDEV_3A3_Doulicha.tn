/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulicha.entities;

/**
 *
 * @author aziz
 */
public class Produit {
    private int ID_produit;
    private String libelle_produit;
    private int quantite_produit;
    private double prixUachat_produit;
    private double prixUvente_produit;
    private String categorie_produit;
    private String image_produit;
    
    public Produit(){}

    public Produit(int ID_produit, String libelle_produit, int quantite_produit, double prixUachat_produit, double prixUvente_produit, String categorie_produit, String image_produit) {
        this.ID_produit = ID_produit;
        this.libelle_produit = libelle_produit;
        this.quantite_produit = quantite_produit;
        this.prixUachat_produit = prixUachat_produit;
        this.prixUvente_produit = prixUvente_produit;
        this.categorie_produit = categorie_produit;
        this.image_produit = image_produit;
    }
    
    public Produit(String libelle_produit, int quantite_produit, double prixUachat_produit, double prixUvente_produit, String categorie_produit, String image_produit) {
        this.libelle_produit = libelle_produit;
        this.quantite_produit = quantite_produit;
        this.prixUachat_produit = prixUachat_produit;
        this.prixUvente_produit = prixUvente_produit;
        this.categorie_produit = categorie_produit;
        this.image_produit = image_produit;
    }

    public int getID_produit() {
        return ID_produit;
    }

    public void setID_produit(int ID_produit) {
        this.ID_produit = ID_produit;
    }

    public String getLibelle_produit() {
        return libelle_produit;
    }

    public void setLibelle_produit(String libelle_produit) {
        this.libelle_produit = libelle_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public double getPrixUachat_produit() {
        return prixUachat_produit;
    }

    public void setPrixUachat_produit(double prixUachat_produit) {
        this.prixUachat_produit = prixUachat_produit;
    }

    public double getPrixUvente_produit() {
        return prixUvente_produit;
    }

    public void setPrixUvente_produit(double prixUvente_produit) {
        this.prixUvente_produit = prixUvente_produit;
    }

    public String getCategorie_produit() {
        return categorie_produit;
    }

    public void setCategorie_produit(String categorie_produit) {
        this.categorie_produit = categorie_produit;
    }

    public String getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(String image_produit) {
        this.image_produit = image_produit;
    }
}
