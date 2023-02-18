/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Commande;
import codingbeasts.doulicha.entities.LigneCommande;
import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public List<LigneCommande> afficherLigne(){
        List<LigneCommande> myList= new ArrayList<>();
        try {
            
            String requete3 ="SELECT * FROM ligne_commande";
            Statement st= cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                LigneCommande p =new LigneCommande();
                p.setID_ligne(rs.getInt(1));
               
                p.setID_commande(rs.getInt("ID_commande"));
                p.setID_produit(rs.getInt("ID_produit"));
                p.setQuantite_achete_ligne(rs.getInt("quantite_achete_ligne"));
                
                myList.add(p);
                
            }
            
            
         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           return myList;
    }
    
    public LigneCommande retreiveOneligne (int ID_ligne) {
    String request = "SELECT * FROM ligne_commande WHERE ID_ligne=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(request);
        pst.setInt(1, ID_ligne);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            LigneCommande ligne = new LigneCommande();
            ligne.setID_ligne(rs.getInt("ID_ligne"));
            return ligne;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
    }
    
    
    public LigneCommande deleteligne(int idligne) {
    String requete = "DELETE FROM ligne_commande WHERE ID_ligne = ?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, idligne);
        int rowsDeleted = pst.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("ligne de commande supprimé avec succès.");
            LigneCommande ligneSupprime = new LigneCommande();
            ligneSupprime.setID_ligne(idligne);
            return ligneSupprime;
        } else {
            System.out.println("Aucun ligne de commande n'a été supprimé.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
    
    public void modifierLigne(int idligne, LigneCommande nouvelleligne) {
    String requete = "UPDATE ligne_commande SET quantite_achete_ligne = ? WHERE ID_ligne = ?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
   
        pst.setInt(1, nouvelleligne.getQuantite_achete_ligne());
        pst.setInt(2, idligne);
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Ligne Commande modifiée avec succès.");
        } else {
            System.out.println("Aucune Ligne de commande n'a été modifiée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    
}
