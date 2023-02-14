/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.tests;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.services.DiscussionCRUD;
import codingbeasts.doulicha.services.ReponseCRUD;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Date;

/**
 *
 * @author ghass
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //  MyConnection mc = new MyConnection();
        DiscussionCRUD dis = new DiscussionCRUD();
        dis.ajouterDiscussion();
        Date date = Date.valueOf("2023-02-13");
        dis.ajouterDiscussion(new Discussion(1, "titre de discussion", "contenu de discussion", date));
        ReponseCRUD rep = new ReponseCRUD();
        rep.ajouterReponse();
        rep.ajouterReponse(new Reponse(1, 1, "contenu de la réponse", date));
    }

}
