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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author aziz
 */
public class ProduitCrud implements Crud<Produit> {
    
    Connection cnx2;
    public ProduitCrud(){
        cnx2=MyConnection.getInstance().getCnx();
    }
    
    
    public void ajouterProduit(){
        String requete ="INSERT INTO produit (libelle_produit,quantite_produit,prixUachat_produit,prixUvente_produit,categorie_produit,image_produit) VALUES ('p1',10,10,13,'cat1','urlimage')";
        try {
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("produit ajoutéé avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            
        }
    }
    
    public void ajouterProduit2(Produit p){
        String requete2="INSERT INTO produit (libelle_produit,quantite_produit,prixUachat_produit,prixUvente_produit,categorie_produit,image_produit) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst =cnx2.prepareStatement(requete2);
            pst.setString(1,p.getLibelle_produit());
            pst.setInt(2,p.getQuantite_produit());
            pst.setDouble(3,p.getPrixUachat_produit());
            pst.setDouble(4,p.getPrixUvente_produit());
            pst.setString(5,p.getCategorie_produit());
            pst.setString(6,p.getImage_produit());
            pst.executeUpdate();
            System.out.println("votre produit est ajoutée");
            
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
    public List<Produit> afficherProduit(){
        List<Produit> myList= new ArrayList<>();
        try {
            
            String requete3 ="SELECT * FROM produit";
            Statement st= cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Produit p =new Produit();
                p.setID_produit(rs.getInt(1));
                p.setLibelle_produit(rs.getString("libelle_produit"));
                p.setQuantite_produit(rs.getInt("quantite_produit"));
                p.setPrixUachat_produit(rs.getDouble("prixUachat_produit"));
                p.setPrixUvente_produit(rs.getDouble("prixUvente_produit"));
                p.setCategorie_produit(rs.getString("categorie_produit"));
                p.setImage_produit(rs.getString("image_produit"));
                myList.add(p);
                
            }
            
            
         
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           return myList;
    }
    public Produit retreiveOneProduct (int ID_produit) {
    String request = "SELECT * FROM produit WHERE ID_produit=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(request);
        pst.setInt(1, ID_produit);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Produit produit = new Produit();
            produit.setID_produit(rs.getInt("ID_produit"));
            return produit;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
    }

    
    public Produit deleteProduit(int idProduit) {
    String requete = "DELETE FROM produit WHERE ID_produit = ?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, idProduit);
        int rowsDeleted = pst.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Produit supprimé avec succès.");
            Produit produitSupprime = new Produit();
            produitSupprime.setID_produit(idProduit);
            return produitSupprime;
        } else {
            System.out.println("Aucun produit n'a été supprimé.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
    
   public Produit modifierProduit(int idProduit, Produit nouveauProduit) {
    String requete = "UPDATE produit SET libelle_produit = ?, quantite_produit = ?, prixUachat_produit = ?, prixUvente_produit = ?, categorie_produit = ?, image_produit = ? WHERE ID_produit = ?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setString(1, nouveauProduit.getLibelle_produit());
        pst.setInt(2, nouveauProduit.getQuantite_produit());
        pst.setDouble(3, nouveauProduit.getPrixUachat_produit());
        pst.setDouble(4, nouveauProduit.getPrixUvente_produit());
        pst.setString(5, nouveauProduit.getCategorie_produit());
        pst.setString(6, nouveauProduit.getImage_produit());
        pst.setInt(7, idProduit);
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Produit modifié avec succès.");
            nouveauProduit.setID_produit(idProduit);
            return nouveauProduit;
        } else {
            System.out.println("Aucun produit n'a été modifié.");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
    
    
    
    
    
    
    
    /**
     * 
     * AddOne is a method that add a new Product
     * @param entity
     * @return 
     */
    @Override
    public Produit AddOne(Produit entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit RetreiveOne(int id_Entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produit> RetreiveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit DeleteOne(int id_Entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produit UpdateOne(Produit entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}