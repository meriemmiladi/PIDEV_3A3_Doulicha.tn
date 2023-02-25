/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
     private String status_user; // nouveau champ pour le statut de l'utilisateur

    public String getStatus_user() {
        return status_user;
    }

    public void setStatus_user(String status_user) {
        this.status_user = status_user;
    }

    public Utilisateur(int ID_user, String nom_user, String prenom_user, String email_user, String mdp_user, String role_user, String status_user) {
        this.ID_user = ID_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.email_user = email_user;
        this.mdp_user = mdp_user;
        this.role_user = role_user;
        this.status_user = status_user;
    }
   

    public Utilisateur() {
    }

    public Utilisateur(String nom_user, String prenom_user, String email_user, String mdp_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.email_user = email_user;
        this.mdp_user = hashMotDePasse(mdp_user);
    }

    public Utilisateur(String nom_user, String prenom_user, String email_user, String mdp_user, String role_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.email_user = email_user;
        this.mdp_user =hashMotDePasse(mdp_user);
        this.role_user = role_user;
    }

    public Utilisateur(int ID_user, String nom_user, String prenom_user, String email_user, String mdp_user, String role_user) {
        this.ID_user = ID_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.email_user = email_user;
        this.mdp_user = hashMotDePasse(mdp_user);
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
    
    public String hashMotDePasse(String motDePasse) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(motDePasse.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

   
    @Override
    public String toString() {
        return "Utilisateur{" + "ID_user=" + ID_user + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", email_user=" + email_user + ", mdp_user=" + mdp_user + ", role_user=" + role_user + ", status_user=" + status_user +  '}';
    }
}
