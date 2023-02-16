/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.tests;
import codingbeasts.doulicha.entities.evenement;
import codingbeasts.doulicha.entities.participation_evenement;
import codingbeasts.doulicha.services.ServiceEvenement;
import codingbeasts.doulicha.services.ServiceParticipationEvenement;
import codingbeasts.doulicha.utils.MyConnection;

/**
 *
 * @author Admin
 */
public class MainClass {
    
    public static void main (String[] args){
    //MyConnection mc =  MyConnection.getInstance();
    
    ServiceEvenement eventcd = new ServiceEvenement();
    java.sql.Date dateDebut = java.sql.Date.valueOf("2023-03-01");
    java.sql.Date dateFin = java.sql.Date.valueOf("2023-03-30");
    evenement event = new evenement("ElHadhra","jfkfkf","Théatre Municipal","Artistique-Musical",dateDebut,dateFin,500,0,"lien",35);
    
    java.sql.Date dateDebut2 = java.sql.Date.valueOf("2023-03-01");
    java.sql.Date dateFin2 = java.sql.Date.valueOf("2024-03-15");
    
    
    //eventcd.ajouterEvenement();
    //eventcd.ajouterEvenement2(event);
    
    // System.out.println(eventcd.afficherEvenements());     
      
      //eventcd.modifierEvenement(12,"ElHadhra","jfkfkf","Théatre MunicipalCENTREVILLE","Artistique-Musical",dateDebut,dateFin,498,2,"lien",3);
      //eventcd.modifierEvenement(10,"ZIARA-HADHRA","JVKREJGT","Théatre MunicipalCENTREVILLE","Artistique-Musical",dateDebut2,dateFin2,498,2,"lien",3);
      
     // eventcd.supprimerEvenement(8);
     //eventcd.supprimerEvenement(1);
     
     ServiceParticipationEvenement participationeventcd = new ServiceParticipationEvenement();
     java.sql.Date dateParticipation = java.sql.Date.valueOf("2023-03-10"); 
      participation_evenement participation = new participation_evenement(1,7,dateParticipation,2);
      
      java.sql.Date dateParticipation2 = java.sql.Date.valueOf("2023-02-28"); 
      participation_evenement participation2 = new participation_evenement(1,9,dateParticipation2,10);
      
       // participationeventcd.ajouterParticipationEvenement(participation);
       //participationeventcd.ajouterParticipationEvenement(participation2);
       
        //System.out.println(participationeventcd.afficherParticipations());
        
        participationeventcd.supprimerParticipation(5);
        
        //participationeventcd.modifierParticipationEvenement(1, 1, 9, dateParticipation, 5);
    }
    
}
