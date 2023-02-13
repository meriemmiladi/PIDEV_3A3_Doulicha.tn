/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;
import codingbeasts.doulicha.entities.projet;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class projetCRUD {
    Connection cnx2;
    public projetCRUD(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    public void ajouterprojet () {
        try {
            String requete ="INSERT INTO projet ( nom_projet,  description_projet,  objectif_projet,  etat_projet,  image_projet)"
                    + "VALUES ('taher','dzzzzzz','bbbbbb','12','15','zfqzcfq')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("projet ajoutée avec succés");
        } catch (SQLException ex) {
              System.err.println(ex.getMessage()); 
        }
        
    }
    
    public void ajouterprojet2(projet p){
        try {
            String requete2 ="INSERT INTO projet(nom_projet,description_projet,objectif_projet,etat_projet,image_projet)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, p.getNom_projet());
            pst.setString(2, p.getDescription_projet());
            pst.setDouble(3, p.getObjectif_projet());
            pst.setInt(4, p.getEtat_projet());
            pst.setString(5, p.getImage_projet());
            
            
            pst.executeUpdate();
            System.out.println("votre projet est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    public List<projet> afficherprojet (){
        List<projet> myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM projet";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            projet p = new projet();
            p.setId_projet(rs.getInt(1));
            p.setNom_projet(rs.getString("nom_projet"));
            p.setDescription_projet(rs.getString("description_projet"));
            p.setObjectif_projet((float) rs.getDouble("objectif_projet"));
            p.setEtat_projet(rs.getInt("etat_projet"));
            p.setImage_projet(rs.getString("image_projet"));
            myList.add(p);
        }
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
    }

    private void While(boolean next) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void deleteprojet() throws SQLException{
        try{
        String requete = "DELETE FROM projet WHERE ID_projet=1";
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted projet"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        
        }
}
    
    public void modifierprojet() throws SQLException{
        try{
        String requete = "UPDATE projet SET nom_projet = 'zezo' , description_projet = 'tito' WHERE id_projet=3";
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted projet"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }

    
 /*   
    
    public void modifierprojet(projet p) {
        

        try {
            String req = "UPDATE projet SET titre = '" + p.getTitre()+ "', description = '" + p.getDescription()+ "' WHERE projet.`id_projet` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("projet updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerprojet(int id) {
        try {
            String req = "DELETE FROM projet WHERE id_projet = " + id;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("projet deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
*/
    
    
    
    
}

