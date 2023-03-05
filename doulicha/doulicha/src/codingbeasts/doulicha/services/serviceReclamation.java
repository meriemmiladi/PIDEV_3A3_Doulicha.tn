
package codingbeasts.doulicha.services;
import codingbeasts.doulicha.entities.avis;
import codingbeasts.doulicha.entities.reclamation;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class serviceReclamation implements InterfaceReclamation{
    Connection cnx2;
    public serviceReclamation(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterreclamation (reclamation p) {
        java.util.Date act = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(act);
        String dc = date;
        try {
            String requete ="INSERT INTO reclamation ( contenu_reclamation,  date_reclamation , etat_reclamation)"
                    + "VALUES ('"+p.getContenu_reclamation()+"','"+date+"',,'"+p.getEtat_reclamation()+"')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("reclamation ajouté avec succés");
        } catch (SQLException ex) {
              System.err.println(ex.getMessage()); 
        }
        
    }
    @Override
    public void ajouterreclamation2(reclamation p){
        java.util.Date act = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(act);
        String dc = date;
        try {
            String requete2 ="INSERT INTO reclamation(ID_user,contenu_reclamation,etat_reclamation,date_reclamation)"
                    + "VALUES (?,?,?,'"+date+"')";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1, p.getID_user());
            pst.setString(2, p.getContenu_reclamation());
            pst.setInt(3,p.getEtat_reclamation());
            
            
            
            pst.executeUpdate();
            System.out.println("votre reclamation est ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    @Override
    public List<reclamation> afficherreclamation (){
        List<reclamation> myList = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM reclamation";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
            reclamation p = new reclamation();
            p.setID_reclamation(rs.getInt(1));
            p.setContenu_reclamation(rs.getString("contenu_reclamation"));
            p.setDate_reclamation(rs.getDate("date_reclamation"));
            p.setEtat_reclamation(rs.getInt("etat_reclamation"));
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
    public void deletereclamation(int ID_reclamation){
        try{
        String requete = "DELETE FROM reclamation WHERE ID_reclamation=" + ID_reclamation;
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("reclamation supprimée");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        
        }
}
    @Override
    public void modifierreclamation(reclamation p){
        try{
        String requete = "UPDATE reclamation SET contenu_reclamation =  '" + p.getContenu_reclamation()+ "' , etat_reclamation =  '" + p.getEtat_reclamation()+ "' , WHERE id_reclamation=" + p.getID_reclamation();
        Statement st = cnx2.createStatement();
        st.executeUpdate(requete);
            System.out.println("reclamation modifiée");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
        public void modifierreclamation2(reclamation r) {
        try {

            PreparedStatement st = cnx2.prepareStatement("UPDATE reclamation SET etat_reclamation=? WHERE id_reclamation=?");
            st.setInt(1, r.getEtat_reclamation());
            st.setInt(2, r.getID_reclamation());

            st.executeUpdate();
            System.out.println("Réclamation modifiée avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        public String getEmailByReclamationId(int reclamationId) {
        String email = "";
        try {
            String query = "SELECT email_user FROM utilisateur u JOIN reclamation r ON u.ID_user = r.ID_user WHERE r.ID_reclamation = ?";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setInt(1, reclamationId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                email = rs.getString("email_user");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return email;
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

