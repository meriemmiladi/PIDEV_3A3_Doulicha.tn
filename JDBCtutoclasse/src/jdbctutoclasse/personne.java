/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbctutoclasse;

/**
 *
 * @author marie
 */
public class personne {
    public int id;
    public String nom;
    public String prenom;

    public personne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
    @Override
    public String toString() {
        return "personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
    
}
