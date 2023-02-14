
package codingbeasts.doulicha.tests;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.services.DiscussionCRUD;
import codingbeasts.doulicha.services.ReponseCRUD;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Date;


public class MainClass {

    public static void main(String[] args) {
        
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
