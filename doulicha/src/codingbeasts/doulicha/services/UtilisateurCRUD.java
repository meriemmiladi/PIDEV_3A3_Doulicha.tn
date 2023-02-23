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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

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
            System.out.println("Utilisateur avec succés");
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
            System.out.println("Utilisateur ajoutée ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterUtlisateur3(Utilisateur p) {
        try {
            if (p.getNom_user().isEmpty() || p.getPrenom_user().isEmpty() || p.getEmail_user().isEmpty() || p.getMdp_user().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs vides");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.showAndWait();
                return;
            }
            // Valider l'email
            if (!validerEmail(p.getEmail_user())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Email non valide");
                alert.setHeaderText(null);
                alert.setContentText("L'email que vous avez entré n'est pas valide.");
                alert.showAndWait();
                return;
            }

            // Vérifier si l'utilisateur existe déjà dans la base de données
            String query = "SELECT * FROM Utilisateur WHERE email_user = ?";
            PreparedStatement statement = cnx2.prepareStatement(query);
            statement.setString(1, p.getEmail_user());
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                // L'utilisateur n'existe pas dans la base de données, on peut l'ajouter
                String requete2 = "INSERT INTO Utilisateur (nom_user,prenom_user,email_user,mdp_user,role_user) " + "VALUES (?,?,?,?,'Utilisateur')";
                PreparedStatement pst = cnx2.prepareStatement(requete2);
                pst.setString(1, p.getNom_user());
                pst.setString(2, p.getPrenom_user());
                pst.setString(3, p.getEmail_user());
                pst.setString(4, p.getMdp_user());
                pst.executeUpdate();
                System.out.println("Votre Personne est ajoutée ");
                // Ajouter une alerte de succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("L'utilisateur a été ajouté avec succès.");
                alert.showAndWait();
            } else {
                // L'utilisateur existe déjà dans la base de données
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Utilisateur déjà existant");
                alert.setHeaderText(null);
                alert.setContentText("Cet utilisateur existe déjà dans la base de données.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierutilisateur(Utilisateur p) {
        try {
            String req = "UPDATE `Utilisateur` SET `Nom_user` = '" + p.getNom_user() + "', `Prenom_user` = '" + p.getPrenom_user() + "', `Email_user` = '" + p.getEmail_user() + "', `Mdp_user` = '" + p.getMdp_user() + "', `Role_user` = '" + p.getRole_user() + "' WHERE `Utilisateur`.`ID_user` = " + p.getID_user();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerutilisateur(int id) {
        try {
            String req = "DELETE FROM `Utilisateur` WHERE ID_user = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur deleted !");
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

    public boolean validerEmail(String email) {
        // Vérifier si l'email contient un "@" et un "."
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
