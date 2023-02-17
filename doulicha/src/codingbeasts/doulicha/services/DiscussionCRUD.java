package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Discussion;
import java.util.List;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DiscussionCRUD {

    Connection con;

    public DiscussionCRUD() {
        con = MyConnection.getInstance().getCnx();
    }

    public void ajouterDiscussion() {
        try {
            String requete = "INSERT INTO discussion (ID_user,titre_discussion,contenu_discussion,date_discussion)"
                    + "VALUES (1,'titre_discussion','contenu_discussion','2023-02-13')";
            Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("Discussion ajoutée avec succès");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterDiscussion(Discussion d) {
        try {
            String requete2 = "INSERT INTO discussion (ID_user,titre_discussion,contenu_discussion,date_discussion)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete2);
            pst.setInt(1, 1);
            pst.setString(2, d.getTitre_discussion());
            pst.setString(3, d.getContenu_discussion());
            pst.setString(4, d.getDate_discussion().toString());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerDiscussion(int id_discussion) {
        try {
            String requete = "DELETE FROM discussion WHERE ID_discussion =?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id_discussion);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierTitreDiscussion(int id_discussion, String titre_discussion) {
        try {
            String requete = "UPDATE discussion SET titre_discussion = ?  WHERE ID_discussion= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, titre_discussion);
            pst.setInt(2, id_discussion);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierContenuDiscussion(int id_discussion, String contenu_discussion) {
        try {
            String requete = "UPDATE discussion SET contenu_discussion = ?  WHERE ID_discussion= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, contenu_discussion);
            pst.setInt(2, id_discussion);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Discussion> afficherDiscussions() {
        List<Discussion> discussions = new ArrayList<>();
        try {
            String requete = "SELECT * FROM discussion";
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Discussion dis = new Discussion();
                dis.setID_discussion(rs.getInt(1));
                dis.setID_user(rs.getInt(2));
                dis.setTitre_discussion(rs.getString(3));
                dis.setContenu_discussion(rs.getString(4));
                dis.setDate_discussion(rs.getDate(5));
                discussions.add(dis);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return discussions;
    }

    public List<Discussion> rechercherDiscussions(int ID_user) {
        List<Discussion> discussionsUtilisateur = new ArrayList<>();
        try {

            String requete = "SELECT * FROM discussion WHERE ID_user = ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, ID_user);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Discussion dis = new Discussion();
                dis.setID_discussion(rs.getInt(1));
                dis.setID_user(rs.getInt(2));
                dis.setTitre_discussion(rs.getString(3));
                dis.setContenu_discussion(rs.getString(4));
                dis.setDate_discussion(rs.getDate(5));
                discussionsUtilisateur.add(dis);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return discussionsUtilisateur;

    }

    public List<Discussion> rechercherDiscussions(String nom, String prenom) {
        List<Discussion> discussionsUtilisateur = new ArrayList<>();
        try {

            String requete = "SELECT * FROM utilisateur WHERE nom_user = ? AND prenom_user=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID_user = rs.getInt(1);
               discussionsUtilisateur.addAll(0, rechercherDiscussions(ID_user));
              /*  Discussion dis = new Discussion();
                dis.setID_discussion(rs.getInt(1));
                dis.setID_user(rs.getInt(2));
                dis.setTitre_discussion(rs.getString(3));
                dis.setContenu_discussion(rs.getString(4));
                dis.setDate_discussion(rs.getDate(5));
                discussionsUtilisateur.add(dis);*/
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return discussionsUtilisateur;

    }

    public List<Discussion> afficherDiscussionsTrieParDate() {
        List<Discussion> discussions = new ArrayList<>();
        try {
            String requete = "SELECT * FROM discussion ORDER BY date_discussion DESC";
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Discussion dis = new Discussion();
                dis.setID_discussion(rs.getInt(1));
                dis.setID_user(rs.getInt(2));
                dis.setTitre_discussion(rs.getString(3));
                dis.setContenu_discussion(rs.getString(4));
                dis.setDate_discussion(rs.getDate(5));
                discussions.add(dis);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return discussions;
    }
}
