package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReponseCRUD {
 Connection con ;

    public ReponseCRUD() {
                con=MyConnection.getInstance().getCnx();

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
            PreparedStatement pst =con.prepareStatement(requete2);
            pst.setInt(1,1);
            pst.setInt(2,1);
            pst.setString(3, r.getContenu_reponse());
            pst.setString(4, r.getDate_reponse().toString());
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
            pst.setInt(2,id_reponse);
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
            pst.setInt(2,id_reponse);
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
}
