/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.tests;

import codingbeasts.doulicha.services.DiscussionCRUD;
import codingbeasts.doulicha.utils.MyConnection;

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
    }
    
}
