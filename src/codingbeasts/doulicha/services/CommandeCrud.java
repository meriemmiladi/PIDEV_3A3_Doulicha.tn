/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;

import codingbeasts.doulicha.entities.Commande;
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
public class CommandeCrud {
    Connection cnx2;
    public CommandeCrud(){
        cnx2=MyConnection.getInstance().getCnx();
    }
    
    
    
    public void ajouterCommande(){
        String requete ="INSERT INTO commande_produit (ID_user,date_commande,etat_commande) VALUES (1,'2023-02-21',1)";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("commande de produit ajoutéé avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    }
    
    
    
    
    public Commande ajouterCommande2(Commande p, int ID_user){
        String requete2="INSERT INTO commande_produit (ID_user,date_commande,etat_commande) VALUES (?,?,?)";
        try {
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setInt(1,ID_user);
            pst.setDate(2,p.getDate_commande());
            pst.setInt(3, p.getEtat_commande());
            pst.execute();
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
        return afficherCommande().get(afficherCommande().size()-1);
    }
    
    
    public List<Commande> afficherCommande(){
        List<Commande> myList= new ArrayList<>();
        try {
            
            String requete3 ="SELECT * FROM commande_produit";
            Statement st= cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Commande p =new Commande();
                p.setID_commande(rs.getInt(1));
                p.setID_user(rs.getInt(1));
                p.setDate_commande(rs.getDate("date_commande"));
                p.setEtat_commande(rs.getInt("etat_commande"));
                myList.add(p);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           return myList;
    }
    
    
    public Commande retreiveOneOrder(int ID_commande) {
    String request = "SELECT * FROM commande_produit WHERE ID_commande=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(request);
        pst.setInt(1, ID_commande);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Commande commande = new Commande();
            commande.setID_commande(rs.getInt("ID_commande"));
            return commande;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}    
    public Commande deleteCommande(int idcommande) {
    String requete = "DELETE FROM commande_produit WHERE ID_commande = ?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, idcommande);
        int rowsDeleted = pst.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Commande supprimé avec succès.");
            Commande commandeSupprime = new Commande();
            commandeSupprime.setID_commande(idcommande);
            return commandeSupprime;
        } else {
            System.out.println("Aucune Commande n'a été supprimé.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
    
    public void modifierCommande(int idCommande, Commande nouvelleCommande) {
    String requete = "UPDATE commande_produit SET date_commande = ?, etat_commande = ? WHERE ID_commande = ?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
        
        LocalDate localDate = LocalDate.now();
        Date sqlDate = Date.valueOf(localDate);

        pst.setDate(1, sqlDate);
        pst.setInt(2, nouvelleCommande.getEtat_commande());
        pst.setInt(3, idCommande);
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Commande modifiée avec succès.");
        } else {
            System.out.println("Aucune commande n'a été modifiée.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

     public Commande lastCommand() {
    String request = "SELECT * FROM commande_produit ORDER BY ID_commande DESC LIMIT 1";
    try {
        Statement  pst = cnx2.prepareStatement(request);
        
        ResultSet rs = pst.executeQuery(request);
        if (rs.next()) {
            Commande commande = new Commande();
            commande.setID_commande(rs.getInt("ID_commande"));
            
            return commande;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}   
    
    
}

