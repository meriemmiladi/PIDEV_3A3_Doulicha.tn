/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulicha.tests;

import doulicha.entities.Commande;
import doulicha.entities.LigneCommande;
import doulicha.entities.Produit;
import doulicha.entities.User;
import doulicha.services.ProduitCrud;
import doulicha.services.CommandeCrud;
import doulicha.services.LigneCommandeCrud;
import doulicha.services.UserCrude;
import doulicha.utils.MyConnection;
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
        ProduitCrud pcd =new ProduitCrud();
        //Produit p2 = new Produit("p1",10,10,13,"cat1","urlimage");
        //pcd.ajouterProduit2(p2);
        //System.out.println(pcd.afficherProduit());
        
        CommandeCrud ccd=new CommandeCrud();
        //Commande c1=new Commande (2,new Date(2000-1-3) ,1);
//        User u=new User();
        //ccd.ajouterCommande();
        //System.out.println(ccd.afficherProduit());
        
        LigneCommandeCrud lcc=new LigneCommandeCrud();
//        LigneCommande lc=new LigneCommande(4,1,1);
int id_commande=2;
int id_produit=4;
        LigneCommande lc1=new LigneCommande(id_commande,14,4);
//        Commande c=new Commande();
//        lcc.ajouterLigneCommande();
    if (ccd.retreiveOneOrder(id_commande)!=null) {
                    lcc.ajouterLigneCommande2(lc1);
                    System.out.println("success add");
                    if (pcd.RetreiveOne(id_produit)!=null) {
                        pcd.ajouterProduit2(lc1);
        
    }else{
                        
                    }
                    
        }else{
    System.out.println("id commande not founded");
}
        UserCrude userCrud=new UserCrude();
        userCrud.AddOne(new User());
//        User user=userCrud.getUser(20);
//        System.out.println(user);
//        
        
    
}
}