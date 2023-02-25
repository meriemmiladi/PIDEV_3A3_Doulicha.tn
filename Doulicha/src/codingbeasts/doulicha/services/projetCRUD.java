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
import javax.activation.DataSource;
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
    
    public void deleteprojet(int id_projet) throws SQLException{
        System.out.println(id_projet);
        try{
        String requete = "DELETE FROM projet WHERE ID_projet=" + id_projet;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted projet"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        
        }
}

    public void modifierprojet2(projet p ,int id_projet) throws SQLException{
        try{
        String requete = "UPDATE projet SET nom_projet  = '" + p.getNom_projet() + "', description_projet = '" + p.getDescription_projet() + "', objectif_projet = '" + p.getObjectif_projet() + "' , etat_projet = '" + p.getEtat_projet() + "', image_projet = '" + p.getImage_projet()+"'  WHERE ID_projet=" + id_projet;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted projet"+requete);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            p.setId_projet(rs.getInt("ID_projet"));
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
    public projet getProjetById(int id_projet) {
    projet p = null;
    try {

        String req = "SELECT * FROM projet WHERE id_projet = ?";
        PreparedStatement pst = cnx2.prepareStatement(req);
        pst.setInt(1, id_projet);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            p = new projet(rs.getInt("id_projet"), rs.getString("nom_projet"), rs.getString("description_projet"), rs.getFloat("objectif_projet"), rs.getInt("etat_projet"),rs.getString("image_projet"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return p;
}

/*
public List<projet> rechercherProjet(String critere) {
    List<projet> projets = new ArrayList<>();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
       
        String query = "SELECT * FROM projet WHERE nom_projet LIKE ? OR description_projet LIKE ?";
        PreparedStatement pst = cnx2.prepareStatement(query);
        ps = conn.prepareStatement(query);
        ps.setString(1, "%" + critere + "%");
        ps.setString(2, "%" + critere + "%");
        rs = ps.executeQuery();
        
        while (rs.next()) {
            projet projet = new projet();
            projet.setId_projet(rs.getInt("id_projet"));
            projet.setNom_projet(rs.getString("nom_projet"));
            projet.setDescription_projet(rs.getString("description_projet"));
            projet.setObjectif_projet((float) rs.getDouble("objectif_projet"));
            projet.setEtat_projet(rs.getInt("etat_projet"));
            projet.setImage_projet(rs.getString("image_projet"));
            projets.add(projet);
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    
    return projets;
}
*/
    public List<projet> rechercherProjet(String searchTerm) throws SQLException {
    List<projet> myList = new ArrayList<>();
    String sql = "SELECT * FROM projet WHERE nom_projet LIKE ?";
    try (PreparedStatement statement = cnx2.prepareStatement(sql)) {
        statement.setString(1, "%" + searchTerm + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            projet projet = new projet();
            projet.setId_projet(resultSet.getInt("id_projet"));
            projet.setNom_projet(resultSet.getString("nom_projet"));
            projet.setDescription_projet(resultSet.getString("description_projet"));
            projet.setObjectif_projet(resultSet.getFloat("objectif_projet"));
            projet.setEtat_projet(resultSet.getInt("etat_projet"));
            projet.setImage_projet(resultSet.getString("image_projet"));
            myList.add(projet);
        }
        System.out.println(" projet"+sql);
    }
    return myList;
}
    /*
public List<projet> rechercherProjet(String nom, String prenom) {
        List<projet> projet = new ArrayList<>();
        try {

            String requete = "SELECT * FROM projet WHERE nom_projet = ?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, nom);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int ID_user = rs.getInt(1);
               projet.addAll(0, rechercherProjet(ID_projet));
              
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return projet;

    }
    */
    /*
    public void modifierprojet2(projet p,int id_projet) throws SQLException {
        System.out.println(id_projet);
    try {
        String requete = "UPDATE projet SET nom_projet=?, description_projet=?, objectif_projet=?, etat_projet=?, image_projet=? WHERE ID_projet=?" ;
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setString(1, p.getNom_projet());
        pst.setString(2, p.getDescription_projet());
        pst.setFloat(3, p.getObjectif_projet());
        pst.setFloat(4, p.getEtat_projet());
        pst.setString(5, p.getImage_projet());
        pst.setInt(6, id_projet);
        pst.executeUpdate();
        System.out.println("Projet modifié avec succès !"+ requete);
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
}

   */

    
}
    
 
    /*
    public void modifierprojet(projet p) {
        

        try {
            String req = "UPDATE projet SET nom_projet = '" + p.getNom_projet()+ "', description_projet = '" + p.getDescription_projet()+ "' , objectif_projet = '" + p.getObjectif_projet()+ "', etat_projet = '" + p.getEtat_projet()+ "', image_projet = '" + p.getImage_projet()+ "'WHERE projet.`ID_projet` = " + p.getId_projet();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("projet updated !"+ req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
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
    
    
    
    


