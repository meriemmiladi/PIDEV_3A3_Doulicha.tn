/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

/**
 *
 * @author Asus
 */
public class Utilisateur {
    
     private int ID_user;
    private String nom_user;
    private String prenom_user;
    private String email_user;
    private String mdp_user;
    private String role_user; 

    public Utilisateur() {
    }
public Utilisateur(String nom_user, String prenom_user, String email_user, String mdp_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.email_user = email_user;
        this.mdp_user = mdp_user;
    }
    
    
    

    public Utilisateur(String nom_user, String prenom_user, String email_user, String mdp_user, String role_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.email_user = email_user;
        this.mdp_user = mdp_user;
        this.role_user = role_user;
    }
    
    public Utilisateur(int ID_user, String nom_user, String prenom_user, String email_user, String mdp_user, String role_user) {
        this.ID_user = ID_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.email_user = email_user;
        this.mdp_user = mdp_user;
        this.role_user = role_user;
    }

    public int getID_user() {
        return ID_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public String getMdp_user() {
        return mdp_user;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public void setMdp_user(String mdp_user) {
        this.mdp_user = mdp_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "ID_user=" + ID_user + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", email_user=" + email_user + ", mdp_user=" + mdp_user + ", role_user=" + role_user + '}';
    }
    
   
               
}
