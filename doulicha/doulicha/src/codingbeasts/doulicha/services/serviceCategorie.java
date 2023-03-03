
package codingbeasts.doulicha.services;
import codingbeasts.doulicha.entities.categorie_avis;
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
public class serviceCategorie implements InterfaceCategorie{
    Connection cnx2;
    public serviceCategorie(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    @Override
    public void ajoutercategorie (categorie_avis p) {
        try {
            String requete ="INSERT INTO categorie_avis ( nom_categorie)"
                    + "VALUES ('parfait')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("categorie ajouté avec succés");
        } catch (SQLException ex) {
              System.err.println(ex.getMessage()); 
        }
        
    }
    @Override
    public void ajoutercategorie2(categorie_avis p){
        try {
            String requete2 ="INSERT INTO categorie_avis(nom_categorie)"
                    + "VALUES (?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, p.getNom_categorie());
            
            
            pst.executeUpdate();
            System.out.println("votre categorie est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    @Override
    public List<categorie_avis> affichercategorie (){
        List<categorie_avis> myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM categorie_avis";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            categorie_avis p = new categorie_avis();
            p.setID_categorie(rs.getInt(1));
            p.setNom_categorie(rs.getString("nom_categorie"));
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
    @Override
    public void deletecategorie(int ID_categorie){
        try{
        String requete = "DELETE FROM categorie_avis WHERE ID_categorie=" +ID_categorie;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted categorie"+requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        
        }
}
    @Override
    public void modifiercategorie(categorie_avis p){
        try{
        String requete = "UPDATE categorie_avis SET nom_categorie = 'pafait' WHERE id_categorie=1";
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("inserted avis"+requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public String getNomCategorieById(int id_categorie) {
    String nomCategorie = null;
    String req = "SELECT nom_categorie FROM categorie_avis WHERE id_categorie = ?";
    try {
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setInt(1, id_categorie);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nomCategorie = rs.getString("nom_categorie");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return nomCategorie;
}

    public String getNomuserById(int id_user) {
    String nomuser = null;
    String req = "SELECT nom_user FROM utilisateur WHERE id_user = ?";
    try {
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setInt(1, id_user);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nomuser = rs.getString("nom_user");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return nomuser;}

    public String getNomeventById(int id_event) {
    String nomevent = null;
    String req = "SELECT nom_event FROM evenement WHERE id_event = ?";
    try {
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setInt(1, id_event);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nomevent = rs.getString("nom_event");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return nomevent;}
    
    public int getIdAvisByCategorie(int idCategorie) {
    int m = -1;
    String query = "SELECT ID_avis FROM avis WHERE ID_categorie=?";
    try (PreparedStatement statement = cnx2.prepareStatement(query)) {
        statement.setInt(1, idCategorie);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            m = rs.getInt("ID_avis");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return m;// Retourne -1 si aucun avis n'est associé à la catégorie donnée
}

    public String getNomlogementById(int id_logement) {
    String nomlogement = null;
    String req = "SELECT nom_logement FROM logement WHERE id_logement = ?";
    try {
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setInt(1, id_logement);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nomlogement = rs.getString("nom_logement");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return nomlogement;}

public boolean existeCategorie(String nom_categorie) {
    boolean existe = false;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        String query = "SELECT * FROM categorie_avis WHERE nom_categorie=?";
        ps = cnx2.prepareStatement(query);
        ps.setString(1, nom_categorie);
        rs = ps.executeQuery();

        if (rs.next()) {
            // si une ligne est trouvée dans la table avec le même nom de catégorie, alors la catégorie existe déjà
            existe = true;
        }

    } catch (SQLException ex) {
        System.out.println("Erreur lors de la vérification de l'existence de la catégorie: " + ex.getMessage());
    } finally {
        // Fermer les ressources JDBC
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la fermeture des ressources JDBC: " + ex.getMessage());
        }
    }

    return existe;
}
}
