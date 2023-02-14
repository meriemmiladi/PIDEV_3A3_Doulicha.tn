/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ghass
 */
public class ReponseCRUD {

    public void ajouterReponse() {
        try {
            String requete3 = "INSERT INTO reponse (ID_user,ID_discussion,contenu_reponse,date_reponse)"
                    + "VALUES (1,1,'contenu_réponse','2023-02-13')";
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete3);
            System.out.println("Réponse ajoutée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public void ajouterReponse(Reponse r){
        try {
            String requete2 = "INSERT INTO reponse(ID_user,ID_discussion,contenu_reponse,date_reponse)"
                    +"VALUES(?,?,?,?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
        pst.setString(1, "1");
        pst.setString(2,"1");
        pst.setString(3,r.getContenu_reponse());
        pst.setString(4,r.getDate_reponse().toString());
        pst.executeUpdate();
      
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
