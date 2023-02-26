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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class ServiceEvenement {
   
    Connection cnx;
    public ServiceEvenement(){
        cnx = MyConnection.getInstance().getCnx();
    }
    
     public ObservableList<evenement> afficherEvents()
 {
       ObservableList<evenement> evenement = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `evenement`";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                evenement event = new evenement();
               event.setID_event(rs.getInt(1));
             event.setNom_event(rs.getString(2));
             event.setDescription_event(rs.getString(3));
             event.setLieu_event(rs.getString(4));
             event.setType_event(rs.getString(5));
             event.setDateDebut_event(rs.getDate(6));
             event.setDateFin_event(rs.getDate(7));
             event.setCapacite_event(rs.getInt(8));
             event.setImage_event(rs.getString(9));
             event.setPrix_event(rs.getDouble(10));
           evenement.add(event);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
        return evenement;
    }
    
    
    
    public void ajouterEvenement(){
        
       String requete = " INSERT INTO `evenement`(`nom_event`, `description_event`, `lieu_event`, `type_event`, `dateDebut_event`, `dateFin_event`, `capacite_event`, `image_event`, `prix_event`) "
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
        
        String requete2 = " INSERT INTO `evenement`(`nom_event`, `description_event`, `lieu_event`, `type_event`, `dateDebut_event`, `dateFin_event`, `capacite_event`, `image_event`, `prix_event`) "
        + "VALUES (?,?,?,?,?,?,?,?,?)";
        
        try {
            //PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, event.getNom_event());
            pst.setString(2, event.getDescription_event());
            pst.setString(3, event.getLieu_event());
            pst.setString(4, event.getType_event());
            pst.setDate(5, (java.sql.Date) event.getDateDebut_event());
            pst.setDate(6, (java.sql.Date) event.getDateFin_event());
            pst.setInt(7, event.getCapacite_event());
            pst.setString(8, event.getImage_event());
            pst.setDouble(9, event.getPrix_event());
            
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
             event.setDescription_event(rs.getString(3));
             event.setLieu_event(rs.getString(4));
             event.setType_event(rs.getString(5));
             event.setDateDebut_event(rs.getDate(6));
             event.setDateFin_event(rs.getDate(7));
             event.setCapacite_event(rs.getInt(8));
             event.setImage_event(rs.getString(9));
             event.setPrix_event(rs.getDouble(10));
             
             
             /* event.setID_event(rs.getInt("ID_event"));
             event.setNom_event(rs.getString("Nom_event"));
             event.setDescrption_event(rs.getString("Descrption_event"));
             event.setLieu_event(rs.getString("Lieu_event"));
             event.setType_event(rs.getString("Type_event"));
             event.setDateDebut_event(rs.getDate("DateDebut_event"));
             event.setDateFin_event(rs.getDate("DateFin_event"));
             event.setCapacite_event(rs.getInt("Capacite_event"));
             event.setImage_event(rs.getString("Image_event"));
             event.setPrix_event(rs.getDouble("Prix_event")); */
             
             
          
             ListEvenement.add(event);
         }
         } catch (SQLException ex){
                 System.err.println(ex.getMessage());
                 }
        return ListEvenement;
    }
    
   
    
     public void modifierEvenement(evenement event) {
try {
            Statement st = cnx.createStatement();
            //nb: on ne peut pas modifier la date
            String requete4 = "UPDATE  evenement SET nom_event  = '" + event.getNom_event() + "', description_event = '" + event.getDescription_event() + "', lieu_event = '" + event.getLieu_event() + "' , type_event = '" + event.getType_event() + "', dateDebut_event = '" + event.getDateDebut_event() + "', dateFin_event = '" + event.getDateFin_event() + "',capacite_event = '" + event.getCapacite_event() + "', image_event = '" + event.getImage_event()+"', prix_event = '" + event.getPrix_event()+"' WHERE ID_event = '" + event.getID_event()+ "'";
            st.executeUpdate(requete4);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
            System.out.println(ex);
        }
    }

    
    
    
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
    
    
    
   
    
     public int getId(String id) {
try {
            Statement st = cnx.createStatement();
            
            String req = "select ID_event from `evenement` WHERE  ID_event LIKE '" + id + "'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return 0;
    }
         public int getId2(String id) {
try {
            Statement st = cnx.createStatement();
            
            String req = "select ID_participation from `participation_evenement` WHERE  ID_participation LIKE '" + id + "'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return 0;
    }
 
     
     
    
     
     public String getNom(int id) {
try {
            Statement st = cnx.createStatement();
            
            String req = "select nom_event from `evenement` WHERE  ID_event LIKE '" + id + "'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return "";
    } 
     
        public String getNomUser(int id) {
try {
            Statement st = cnx.createStatement();
            
            String req = "select nom_user from `utilisateur` WHERE  ID_user LIKE '" + id + "'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return "";
    } 
            public int getCapacite(int id) {
try {
            Statement st = cnx.createStatement();
            
            String req = "select capacite_event from `evenement` WHERE  ID_event LIKE '" + id + "'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("erreur");
            System.out.println(ex);
        }
            return 0;
    } 
     
   ///////////////////////////////////////////////
     
    public List<evenement> searchEvenement(String snap) {
        List<evenement> list = new ArrayList<>();
        try{
            Statement stm = this.cnx.createStatement();
            String query = "SELECT * from evenement WHERE nom_event like '%"+snap+"%';";
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                evenement event = new evenement();
               event.setID_event(rs.getInt("ID_event"));
             event.setNom_event(rs.getString("nom_event"));
             event.setDescription_event(rs.getString("description_event"));
             event.setLieu_event(rs.getString("lieu_event"));
             event.setType_event(rs.getString("type_event"));
             event.setDateDebut_event(rs.getDate("dateDebut_event"));
             event.setDateFin_event(rs.getDate("dateFin_event"));
             event.setCapacite_event(rs.getInt("capacite_event"));
             event.setImage_event(rs.getString("image_event"));
             event.setPrix_event(rs.getDouble("prix_event"));
                list.add(event);
            }
        }catch(SQLException ex){
            System.out.println("Could not show events");
            list = null;
        }
        return list;
    }
    
    
    public List<evenement> triAsc(int id_event) {
         List<evenement> list = new ArrayList<>();
        //ObservableList<Article> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from evenement ORDER BY evenement.`prix_event` ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                evenement event = new evenement();
                event.setID_event(rs.getInt("ID_event"));
             event.setNom_event(rs.getString("nom_event"));
             event.setDescription_event(rs.getString("description_event"));
             event.setLieu_event(rs.getString("lieu_event"));
             event.setType_event(rs.getString("type_event"));
             event.setDateDebut_event(rs.getDate("dateDebut_event"));
             event.setDateFin_event(rs.getDate("dateFin_event"));
             event.setCapacite_event(rs.getInt("capacite_event"));
             event.setImage_event(rs.getString("image_event"));
             event.setPrix_event(rs.getDouble("prix_event"));
                list.add(event);
              
             //list.add(new event(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5), rs.getInt(6),rs.getInt(7))); 
       
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 

        return list;
    }
    
    public List<evenement> triDesc(int id_event) {
         List<evenement> list = new ArrayList<>();
        //ObservableList<Article> list = FXCollections.observableArrayList();
        try {
            
            String requete = "select * from evenement ORDER BY evenement.`prix_event` DESC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                evenement event = new evenement();
                event.setID_event(rs.getInt("ID_event"));
             event.setNom_event(rs.getString("nom_event"));
             event.setDescription_event(rs.getString("description_event"));
             event.setLieu_event(rs.getString("lieu_event"));
             event.setType_event(rs.getString("type_event"));
             event.setDateDebut_event(rs.getDate("dateDebut_event"));
             event.setDateFin_event(rs.getDate("dateFin_event"));
             event.setCapacite_event(rs.getInt("capacite_event"));
             event.setImage_event(rs.getString("image_event"));
             event.setPrix_event(rs.getDouble("prix_event"));
                list.add(event);
             
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 

        return list;
    }
    
   
 /*  public List<evenement> afficherEvenementParType(String x) {
    List<evenement> list = new ArrayList<>();
    try {
        String requete = "SELECT event.id_event, event.nom_event, event.description_event, event.lieu_event, event.type_event, event.dateDebut_event, event.dateFin_event, event.capacite_event, event.image_event, event.prix_event FROM evenement event, type cat WHERE event.categorie_id = cat.id AND cat.nom = ?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, x);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            evenement event = new evenement();
            event.setID_event(rs.getInt("id_event"));
            event.setNom_event(rs.getString("nom_event"));
            event.setDescription_event(rs.getString("description_event"));
            event.setLieu_event(rs.getString("lieu_event"));
            event.setType_event(rs.getString("type_event"));
            event.setDateDebut_event(rs.getDate("dateDebut_event"));
            event.setDateFin_event(rs.getDate("dateFin_event"));
            event.setCapacite_event(rs.getInt("capacite_event"));
            event.setImage_event(rs.getString("image_event"));
            event.setPrix_event(rs.getDouble("prix_event"));
            list.add(event);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    } 
    return list;
} */

    
    
     
    
}
