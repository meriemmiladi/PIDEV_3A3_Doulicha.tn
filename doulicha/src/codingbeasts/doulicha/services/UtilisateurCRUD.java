/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

/**
 *
 * @author Asus
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import codingbeasts.doulicha.entities.Utilisateur;
import codingbeasts.doulicha.utils.MyConnection;

/**
 *
 * @author Asus
 */
public class UtilisateurCRUD {

    Connection cnx2;

    public UtilisateurCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

    public void ajouterUtilisateur() {

        try {
            String requete = "INSERT INTO Utilisateur (nom_user,prenom_user,email_user,mdp_user,role_user) " + "VALUES ('skander','bedwi','skanderbedwi5@gmail.com','123456','role_admin')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterUtlisateur2(Utilisateur p) {
        try {
            String requete2 = "INSERT INTO Utilisateur (nom_user,prenom_user,email_user,mdp_user,role_user) " + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, p.getNom_user());
            pst.setString(2, p.getPrenom_user());
            pst.setString(3, p.getEmail_user());
            pst.setString(4, p.getMdp_user());
            pst.setString(5, p.getRole_user());

            pst.executeUpdate();
            System.out.println("Votre Personne est ajoutée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifierutilisateur(Utilisateur p) {
        try {
            String req = "UPDATE `Utilisateur` SET `Nom_user` = '" + p.getNom_user()+ "', `Prenom_user` = '" + p.getPrenom_user()+ "', `Email_user` = '" + p.getEmail_user()+ "', `Mdp_user` = '" + p.getMdp_user()+ "', `Role_user` = '" + p.getRole_user()+ "' WHERE `Utilisateur`.`ID_user` = " + p.getID_user();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void supprimerutilisateur(int id) {
        try {
            String req = "DELETE FROM `Utilisateur` WHERE ID_user = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Personne deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Utilisateur> afficherUtilisateur() {

        List<Utilisateur> myList = new ArrayList<>();

        try {

            String requete3 = "SELECT * FROM Utilisateur";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setID_user(rs.getInt(1));
                p.setNom_user(rs.getString("Nom_user"));
                p.setPrenom_user(rs.getString("Prenom_user"));
                p.setEmail_user(rs.getString("Email_user"));
                p.setMdp_user(rs.getString("Mdp_user"));
                p.setRole_user(rs.getString("Role_user"));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

}