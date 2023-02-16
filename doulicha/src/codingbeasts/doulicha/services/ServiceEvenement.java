/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.services;
import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceEvenement {
   
    Connection cnx;
    public ServiceEvenement(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void ajouterEvenement(){
        
       String requete = " INSERT INTO `evenement`(`nom_event`, `description_event`, `lieu_event`, `type_event`, `dateDebut_event`, `dateFin_event`, `capacite_event`, `nombreActuel_event`, `image_event`, `prix_event`) "
        + "VALUES ('El hadhra','dddddd','Théatre Municipal','Artistique','2023-03-22','2023-03-22','500','0','cccccc','35')";
        
        try {
            //Statement st = new MyConnection().getCnx().createStatement();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Evènement ajouté avec succès ");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajouterEvenement2(evenement event){
        
        String requete2 = " INSERT INTO `evenement`(`nom_event`, `description_event`, `lieu_event`, `type_event`, `dateDebut_event`, `dateFin_event`, `capacite_event`, `nombreActuel_event`, `image_event`, `prix_event`) "
        + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
            //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, event.getNom_event());
            pst.setString(2, event.getDescrption_event());
            pst.setString(3, event.getLieu_event());
            pst.setString(4, event.getType_event());
            pst.setDate(5, (java.sql.Date) event.getDateDebut_event());
            pst.setDate(6, (java.sql.Date) event.getDateFin_event());
            pst.setInt(7, event.getCapacite_event());
            pst.setInt(8, event.getNombreActuel_event());
            pst.setString(9, event.getImage_event());
            pst.setDouble(10, event.getPrix_event());
            
            pst.executeUpdate();
            System.out.println("Votre évènement est ajouté ");
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
        
    }
    
    public List<evenement> afficherEvenements(){
        List<evenement> ListEvenement = new ArrayList<>();
        
        try {
         String requete3 = "SELECT * FROM evenement";
         //Statement st = new MyConnection().getCnx().createStatement();
         Statement st = cnx.createStatement();
         ResultSet rs= st.executeQuery(requete3);
         while(rs.next()){
             evenement event = new evenement();
             event.setID_event(rs.getInt(1));
             event.setNom_event(rs.getString(2));
             event.setDescrption_event(rs.getString(3));
             event.setLieu_event(rs.getString(4));
             event.setType_event(rs.getString(5));
             event.setDateDebut_event(rs.getDate(6));
             event.setDateFin_event(rs.getDate(7));
             event.setCapacite_event(rs.getInt(8));
             event.setNombreActuel_event(rs.getInt(9));
             event.setImage_event(rs.getString(10));
             event.setPrix_event(rs.getDouble(11));
             
             
             /* event.setID_event(rs.getInt("ID_event"));
             event.setNom_event(rs.getString("Nom_event"));
             event.setDescrption_event(rs.getString("Descrption_event"));
             event.setLieu_event(rs.getString("Lieu_event"));
             event.setType_event(rs.getString("Type_event"));
             event.setDateDebut_event(rs.getDate("DateDebut_event"));
             event.setDateFin_event(rs.getDate("DateFin_event"));
             event.setCapacite_event(rs.getInt("Capacite_event"));
             event.setNombreActuel_event(rs.getInt("NombreActuel_event"));
             event.setImage_event(rs.getString("Image_event"));
             event.setPrix_event(rs.getDouble("Prix_event")); */
             
             
          
             ListEvenement.add(event);
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListEvenement;
    }
    
    /* public void modifierEvenement(evenement event) {
    
      try {
      // String requete4= "UPDATE logement SET nom_logement=?, description_logement=?, adresse_logement=?, prixNuitee_logement=?, capacite_logement=?, type_logement=?, etat_logement=?, image_logement=? WHERE ID_logement=?";
     
     String requete4= "UPDATE `evenement` SET `nom_event`=?,`description_event`=?,`lieu_event`=?,`type_event`=?,`dateDebut_event`=?,`dateFin_event`=?,`capacite_event`=?,`nombreActuel_event`=?,`image_event`=?,`prix_event`=? WHERE ID_event=?";
     PreparedStatement pst = cnx.prepareStatement(requete4);

            pst.setString(1, event.getNom_event());
            pst.setString(2, event.getDescrption_event());
            pst.setString(3, event.getLieu_event());
            pst.setString(4, event.getType_event());
            pst.setDate(5, (java.sql.Date) event.getDateDebut_event());
            pst.setDate(6, (java.sql.Date) event.getDateFin_event());
            pst.setInt(7, event.getCapacite_event());
            pst.setInt(8, event.getNombreActuel_event());
            pst.setString(9, event.getImage_event());
            pst.setDouble(10, event.getPrix_event());
            pst.setInt(11,event.getID_event());
            
            
             pst.executeUpdate();
             System.out.println("Votre évènement est modifié");
             
             
        } catch (SQLException ex){
             System.err.println(ex.getMessage());
        }
      } */
    
    
    
    public boolean supprimerEvenement(int ID_event) {
        boolean eventSuppression = true;
        
        try {
           String requete4 = "DELETE FROM evenement WHERE ID_event = ?";
             PreparedStatement pst = cnx.prepareStatement(requete4);
             
            pst.setInt(1, ID_event);
            int nbSuppression = pst.executeUpdate();
            
            if (nbSuppression == 0) {
                System.out.println("Pas de suppression d'évènement effectuée");
            } else {
               System.out.println("\"L'évènement avec l'ID " + ID_event + " a été supprimé.\"");

            }

        } catch (SQLException ex) {
            eventSuppression = false;
             System.err.println(ex.getMessage());
            
        }
        return eventSuppression;
    }
    
    
    
     public boolean modifierEvenement (int ID_event, String nom_event, String descrption_event, String lieu_event, String type_event, Date dateDebut_event, Date dateFin_event, int capacite_event, int nombreActuel_event, String image_event, double prix_event) {
        
         boolean eventModification = true;
        String requete = null;
        
        try {
     String requete5= "UPDATE `evenement` SET `nom_event`=?,`description_event`=?,`lieu_event`=?,`type_event`=?,`dateDebut_event`=?,`dateFin_event`=?,`capacite_event`=?,`nombreActuel_event`=?,`image_event`=?,`prix_event`=? WHERE ID_event=?";
     PreparedStatement pst = cnx.prepareStatement(requete5);
     
           pst.setString(1, nom_event);
            pst.setString(2, descrption_event);
            pst.setString(3, lieu_event);
            pst.setString(4,type_event);
            pst.setDate(5, (java.sql.Date) dateDebut_event);
            pst.setDate(6, (java.sql.Date) dateFin_event);
            pst.setInt(7, capacite_event);
            pst.setInt(8, nombreActuel_event);
            pst.setString(9, image_event);
            pst.setDouble(10, prix_event);
            pst.setInt(11,ID_event);

            pst.executeUpdate();
            eventModification = true;
             System.out.println("Votre évènement est modifié");
             
        } catch (SQLException ex) {
            eventModification = false;
             System.err.println(ex.getMessage());
        }
        return eventModification;
    }
     
     
    
}
