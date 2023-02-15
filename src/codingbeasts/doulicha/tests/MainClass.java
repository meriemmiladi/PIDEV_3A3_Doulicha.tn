/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.tests;

import codingbeasts.doulicha.entities.Commande;
import codingbeasts.doulicha.entities.LigneCommande;
import codingbeasts.doulicha.entities.Produit;
import codingbeasts.doulicha.entities.User;
import codingbeasts.doulicha.services.ProduitCrud;
import codingbeasts.doulicha.services.CommandeCrud;
import codingbeasts.doulicha.services.LigneCommandeCrud;
import codingbeasts.doulicha.services.UserCrude;
import codingbeasts.doulicha.utils.MyConnection;
import java.sql.Date;


/**
 *
 * @author aziz
 */
public class MainClass {
    public static void main(String[] args){
        MyConnection mc = MyConnection.getInstance();
        //MyConnection mc2 = MyConnection.getInstance();
        //System.out.println(mc.hashCode()+ "-" +mc2.hashCode());
        
        //*********prooooooduit***************//
        //ProduitCrud pcd =new ProduitCrud();
        //Produit p2 = new Produit("p1",10,10,13,"cat1","urlimage");
        
        //*******ajout produit
        //pcd.ajouterProduit2(p2);
       
        //********affichage liste :
        
        //System.out.println(pcd.afficherProduit());
        
       
        //********supp produit :
        
        //pcd.deleteProduit(2);
        
     
        
        //*******modification produit :
        
//       Produit p2 = pcd.retreiveOneProduct(1);
//    if (p2 != null) {
//        p2.setLibelle_produit("produit_modifie");
//        p2.setQuantite_produit(20);
//        p2.setPrixUachat_produit(15.0);
//        p2.setPrixUvente_produit(18.0);
//        p2.setCategorie_produit("cat2");
//        p2.setImage_produit("urlimage2");
//        pcd.modifierProduit(5,p2);
 // }
    //fin modification
    
    //******************************************************************//
        
        //*************comaaaaaaaande**********//
        
        CommandeCrud ccd=new CommandeCrud();
        //Commande c1=new Commande (2,new Date(2000-1-3) ,1);
        //User u=new User();
        
        //*******ajout commande
        
        //ccd.ajouterCommande();
        
        
        //****supp commande
        
        //  ccd.deleteCommande(5);
        
        //******afficher commande
        
        //  System.out.println(ccd.afficherCommande());
        
        //***********modifier commande
        
        Commande c1 = ccd.retreiveOneOrder(1);
         if (c1 != null) {
        c1.setID_user(1); // ID de l'utilisateur qui a passé la commande
        c1.setDate_commande(new Date()); // Date actuelle
        c1.setEtat_commande(2); // Nouvel état de la commande

    // Appel de la méthode modifierCommande pour mettre à jour la commande avec l'ID 3
        int idCommande = 3; // ID de la commande à modifier
        ccd.modifierCommande(idCommande, c1);
    }

        
    //**************************************************************************//    
        //ligne commandeeee//
        
        
//        LigneCommandeCrud lcc=new LigneCommandeCrud();
//        lcc.deleteligne(6);
////      LigneCommande lc=new LigneCommande(4,1,1);
//        int ID_commande=6;
//        int ID_produit=1;
//        LigneCommande lc1=new LigneCommande(ID_commande,ID_produit,4);
//     Commande c=new Commande();
//    //lcc.ajouterLigneCommande();
//        if (ccd.retreiveOneOrder(ID_commande)!=null) {
//
//                        if (pcd.retreiveOneProduct(ID_produit)!=null) {
//                           //lcc.ajouterLigneCommande2(lc1);
//                            System.out.println("success add");
//                            }else{
//                            System.out.println("id produit not founded");
//                        }
//                        }else{
//                System.out.println("id commande not founded");
//    }
//        
//           // System.out.println(lcc.afficherLigne());
//           
//            UserCrude userCrud=new UserCrude();
            //userCrud.AddOne(u);
    //        User user=userCrud.getUser(20);
    //        System.out.println(user);
//        
        
    
}
}