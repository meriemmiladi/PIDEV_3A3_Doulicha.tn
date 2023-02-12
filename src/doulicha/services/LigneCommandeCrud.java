/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulicha.services;

import doulicha.entities.Commande;
import doulicha.entities.LigneCommande;
import doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aziz
 */
public class LigneCommandeCrud {
    
    Connection cnx2;
    public LigneCommandeCrud(){
        cnx2=MyConnection.getInstance().getCnx();
    }
    public void ajouterLigneCommande(){
        String requete ="INSERT INTO ligne_commande (ID_commande,ID_produit,quantite_achete_ligne) VALUES (4,1,1)";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("ligne de commande ajoutéé avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    }
    public void ajouterLigneCommande2(LigneCommande lc){
        String requete2="INSERT INTO ligne_commande (ID_commande,ID_produit,quantite_achete_ligne) VALUES (?,?,?)";
        try {
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setInt(1,lc.getID_commande());
            pst.setInt(2,lc.getID_produit());
            pst.setInt(3,lc.getQuantite_achete_ligne());
            pst.executeUpdate();
            System.out.println("votre ligne de commande est ajoutée");
            
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
    
    
}
