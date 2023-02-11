/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulicha.tests;

import doulicha.entities.Produit;
import doulicha.services.ProduitCrud;
import doulicha.utils.MyConnection;


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
        Produit p2 = new Produit("p1",10,10,13,"cat1","urlimage");
        pcd.ajouterProduit2(p2);
        System.out.println(pcd.afficherProduit());
        
    
}
}