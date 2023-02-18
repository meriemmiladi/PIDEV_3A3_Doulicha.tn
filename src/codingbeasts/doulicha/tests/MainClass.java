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
import java.util.Calendar;
import java.util.Date;


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
        
//        Commande c1 = ccd.retreiveOneOrder(6);
//         if (c1 != null) {
//        // Créez une instance de Commande avec la date d'aujourd'hui et l'état de commande souhaité
//        Commande nouvelleCommande = new Commande();
//        nouvelleCommande.setDate_commande(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
//        nouvelleCommande.setEtat_commande(12);
//
//    // Appelez la méthode modifierCommande avec l'ID de commande et la nouvelle commande
//        int idCommande = 6; // ID de la commande à modifier
//        ccd.modifierCommande(idCommande, nouvelleCommande);
//
//         }
        
    //**************************************************************************//    
        //ligne commandeeee//
        
        
        LigneCommandeCrud lcc=new LigneCommandeCrud();
        LigneCommande l1=lcc.retreiveOneligne(1);
        if (l1 != null) {
        LigneCommande nouvelleLigne = new LigneCommande();
      
        nouvelleLigne.setQuantite_achete_ligne(12);

        int idligne = 1; // ID de la ligne de commande à modifier
        lcc.modifierLigne(idligne, nouvelleLigne);

         }else{
            System.out.println("ligne nexiste pas");
        }
        
        System.out.println(""+lcc.retreiveOneligne(1));
        //*************modifier ligne********//
        
        
        
        
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

