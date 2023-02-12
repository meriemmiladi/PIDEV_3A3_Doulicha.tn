/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doulicha.services;

import doulicha.entities.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import doulicha.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author aziz
 */
public class UserCrude {
    Connection cnx2;
    
        public UserCrude(){
        cnx2=MyConnection.getInstance().getCnx();
    }
    public User getUser(int ID_user) {
    String requete = "SELECT * FROM utilisateur WHERE ID_user=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, ID_user);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setID_user(rs.getInt("ID_user"));
            
            
            // autres attributs
            return user;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
    public User getID_user(int ID_user) {
    String requete = "SELECT * FROM utilisateur WHERE ID_user=?";
    try {
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, ID_user);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setID_user(rs.getInt("ID_user"));
            
            // autres attributs
            return user;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}
    
}
