/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.entities;

/**
 *
 * @author Asus
 */
public class MySession {
    
      private static Utilisateur loggedInUser;

    public static void setLoggedInUser(Utilisateur user) {
        loggedInUser = user;
    }

    public static Utilisateur getLoggedInUser() {
        return loggedInUser;
    }

    public MySession() {
    }
    
    
}
