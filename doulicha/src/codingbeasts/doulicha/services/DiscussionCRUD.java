package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Discussion;
import java.util.List;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DiscussionCRUD {

    public void ajouterDiscussion() {
        try {
            String requete = "INSERT INTO discussion (ID_user,titre_discussion,contenu_discussion,date_discussion)"
                    + "VALUES (1,'titre_discussion','contenu_discussion','2023-02-13')";
            Statement st = new MyConnection().getCnx().createStatement();
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
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            pst.setInt(1, 1);
            pst.setString(2, d.getTitre_discussion());
            pst.setString(3, d.getContenu_discussion());
            pst.setString(4, d.getDate_discussion().toString());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Discussion> afficherDiscussion() {
        return null;

    }

    public void supprimerDiscussion(int id_discussion) {
        try {
            String requete = "DELETE FROM discussion WHERE id_discussion =?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, id_discussion);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierTitreDiscussion(int id_discussion, String titre_discussion) {
        try {
            String requete = "UPDATE discussion SET titre_discussion = ?  WHERE id_discussion= ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, titre_discussion);
            pst.setInt(2,id_discussion);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   public void modifierContenuDiscussion(int id_discussion, String contenu_discussion) {
        try {
            String requete = "UPDATE discussion SET contenu_discussion = ?  WHERE id_discussion= ?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, contenu_discussion);
            pst.setInt(2,id_discussion);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    } 
   public List<Discussion> afficherReponses() {
    List<Discussion> discussions = new ArrayList<>();
    try {
        String requete = "SELECT * FROM reponse";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Discussion dis = new Discussion();
            dis.setID_discussion(rs.getInt("id_discussion"));
            dis.setID_user(rs.getInt("id_user"));
            dis.setContenu_discussion(rs.getString("contenu_reponse"));
            dis.setDate_discussion(rs.getDate("date_reponse"));
            discussions.add(dis);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return discussions;
} 
}
