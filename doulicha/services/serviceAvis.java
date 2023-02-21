
package codingbeasts.doulicha.services;
import codingbeasts.doulicha.entities.avis;
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
public class serviceAvis implements InterfaceAvis{
    Connection cnx2;
    public serviceAvis(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    @Override
    public void ajouteravis (avis p) {
        try {
            String requete ="INSERT INTO avis ( ID_categorie,ID_user,ID_event,ID_logement,note_avis,  contenu_avis,  type_avis)"
                    + "VALUES (1,1,1,1,10,'tres beau endroit','maison d hote')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("avis ajouté avec succés");
        } catch (SQLException ex) {
              System.err.println(ex.getMessage()); 
        }
        
    }
    @Override
    public void ajouteravis2(avis p){
        try {
            String requete2 ="INSERT INTO avis(ID_categorie,ID_user,ID_event,ID_logement,note_avis,contenu_avis,type_avis)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, p.getID_categorie());
            pst.setInt(2, p.getID_user());
            pst.setInt(3, p.getID_event());
            pst.setInt(4, p.getID_logement());
            pst.setInt(5, p.getNote_avis());
            pst.setString(6, p.getContenu_avis());
            pst.setString(7, p.getType_avis());
            
            
            pst.executeUpdate();
            System.out.println("votre avis est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    @Override
    public List<avis> afficheravis (){
        List<avis> myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM avis";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            avis p = new avis();
            p.setID_avis(rs.getInt(1));
            p.setID_categorie(rs.getInt("ID_categorie"));
            p.setID_user(rs.getInt("ID_user"));
            p.setID_event(rs.getInt("ID_event"));
            p.setID_logement(rs.getInt("ID_logement"));
            p.setNote_avis(rs.getInt("note_avis"));
            p.setContenu_avis(rs.getString("contenu_avis"));
            p.setType_avis(rs.getString("type_avis"));
            myList.add(p);
        }
            System.out.println(myList);
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
    }
    
    private void While(boolean next) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void deleteavis(int ID_avis){
        try{
        String requete = "DELETE FROM avis WHERE ID_avis= "  + ID_avis;
        PreparedStatement st = cnx2.prepareStatement(requete);
        st.executeUpdate();
        System.out.println("avis supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        
        }
    }
    @Override
    public void modifieravis(avis p){
        try{
        String requete = "UPDATE avis SET contenu_avis = '" + p.getContenu_avis()+ "' WHERE ID_avis=" + p.getID_avis();
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("avis modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

