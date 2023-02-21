package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReponseCRUD {

    Connection con;

    public ReponseCRUD() {
        con = MyConnection.getInstance().getCnx();

    }

    public void ajouterReponse() {
        try {
            String requete3 = "INSERT INTO reponse (ID_user,ID_discussion,contenu_reponse,date_reponse)"
                    + "VALUES (1,1,'contenu_réponse','2023-02-13')";
            Statement st = con.createStatement();
            st.executeUpdate(requete3);
            System.out.println("Réponse ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterReponse(Reponse r) {
        try {
            String requete2 = "INSERT INTO reponse(ID_user,ID_discussion,contenu_reponse,date_reponse)"
                    + "VALUES(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(requete2);
            pst.setInt(1, r.getID_user());
            pst.setInt(2, r.getID_discussion());
            pst.setString(3, r.getContenu_reponse());
            pst.setDate(4, r.getDate_reponse());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerReponse(int id_reponse) {
        try {
            String requete = "DELETE FROM reponse WHERE ID_discussion =?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, id_reponse);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierTitreReponse(int id_reponse, String titre_reponse) {
        try {
            String requete = "UPDATE discussion SET titre_reponse = ?  WHERE id_reponse= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, titre_reponse);
            pst.setInt(2, id_reponse);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierContenuReponse(int id_reponse, String contenu_reponse) {
        try {
            String requete = "UPDATE reponse SET contenu_reponse = ?  WHERE id_reponse= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, contenu_reponse);
            pst.setInt(2, id_reponse);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void modifierDateReponse(int id_reponse, Date date_reponse) {
        try {
            String requete = "UPDATE reponse SET date_reponse = ?  WHERE id_reponse= ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setDate(1, date_reponse);
            pst.setInt(2, id_reponse);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Reponse> afficherReponses() {
        List<Reponse> reponses = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reponse";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reponse r = new Reponse();
                r.setID_reponse(rs.getInt("id_reponse"));
                r.setID_user(rs.getInt("id_user"));
                r.setID_discussion(rs.getInt("id_discussion"));
                r.setContenu_reponse(rs.getString("contenu_reponse"));
                r.setDate_reponse(rs.getDate("date_reponse"));
                reponses.add(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reponses;
    }

    public List<Reponse> rechercherReponses(int ID_user) {
        List<Reponse> reponsesUtilisateur = new ArrayList<>();
        try {

            String requete = "SELECT * FROM reponse WHERE ID_user = ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, ID_user);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reponse rep = new Reponse();
                rep.setID_reponse(rs.getInt("ID_reponse"));
                rep.setID_user(rs.getInt("ID_user"));
                rep.setID_discussion(rs.getInt("ID_discussion"));
                rep.setContenu_reponse(rs.getString("contenu_reponse"));
                rep.setDate_reponse(rs.getDate("date_reponse"));
                reponsesUtilisateur.add(rep);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reponsesUtilisateur;
    }
     public List<Reponse> rechercherReponsesDiscussion(int ID_discussion) {
        List<Reponse> reponsesDiscussions = new ArrayList<>();
        try {

            String requete = "SELECT * FROM reponse WHERE ID_discussion = ?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, ID_discussion);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reponse rep = new Reponse();
                rep.setID_reponse(rs.getInt("ID_reponse"));
                rep.setID_user(rs.getInt("ID_user"));
                rep.setID_discussion(rs.getInt("ID_discussion"));
                rep.setContenu_reponse(rs.getString("contenu_reponse"));
                rep.setDate_reponse(rs.getDate("date_reponse"));
                reponsesDiscussions.add(rep);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reponsesDiscussions;
    }
      public List<Reponse> rechercherReponsesDiscussionUtilisateur(int ID_discussion,int ID_user) {
        List<Reponse> reponsesDiscussions = new ArrayList<>();
        try {

            String requete = "SELECT * FROM reponse WHERE ID_discussion = ? AND ID_user=?";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, ID_discussion);
            pst.setInt(2, ID_user);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reponse rep = new Reponse();
                rep.setID_reponse(rs.getInt("ID_reponse"));
                rep.setID_user(rs.getInt("ID_user"));
                rep.setID_discussion(rs.getInt("ID_discussion"));
                rep.setContenu_reponse(rs.getString("contenu_reponse"));
                rep.setDate_reponse(rs.getDate("date_reponse"));
                reponsesDiscussions.add(rep);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reponsesDiscussions;
    }
}
