package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Discussion;
import java.util.List;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
            pst.setString(1, "1");
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
}
