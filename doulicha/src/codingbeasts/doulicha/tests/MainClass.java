
package codingbeasts.doulicha.tests;

import codingbeasts.doulicha.entities.Discussion;
import codingbeasts.doulicha.entities.Reponse;
import codingbeasts.doulicha.services.DiscussionCRUD;
import codingbeasts.doulicha.controller.DiscussionController;
import codingbeasts.doulicha.services.ReponseCRUD;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class MainClass {

    public static void main(String[] args) {
        
        //  MyConnection mc = new MyConnection();
       // DiscussionCRUD dis = new DiscussionCRUD();
        //dis.ajouterDiscussion();
       Date date = Date.valueOf(LocalDate.MAX);
        //dis.ajouterDiscussion(new Discussion(1, "titre de discussion", "contenu de discussion", date));
       // ReponseCRUD rep = new ReponseCRUD();
       // dis.supprimerDiscussion(1);
       // rep.ajouterReponse();
        //rep.afficherReponses();
        //rep.ajouterReponse(new Reponse(1, 1, "contenu de la réponse", date));
        //dis.modifierTitreDiscussion(9, "nouveau titre");
        //dis.modifierContenuDiscussion(9, "nouveau contenu");
       // dis.afficherDiscussion().stream().forEach(x->System.out.println(x));
        DiscussionCRUD dc = new DiscussionCRUD();

        // Insertion d'une nouvelle discussion dans la base de données
        Discussion discussion1 = new Discussion(1, "Ma première discussion", "Contenu de la première discussion", date);
        dc.ajouterDiscussion(discussion1);

        // Récupération de toutes les discussions de la base de données
        List<Discussion> discussions = dc.afficherDiscussions();
        discussions.forEach((d) -> {
            System.out.println(d);
        });
    }

}
