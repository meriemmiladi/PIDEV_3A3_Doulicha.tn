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
import java.util.List;

/**
 *
 * @author aziz
 */
public class UserCrude implements Crud<User>{
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


    /**
     * AddOne is a method that add a new User
     * @param entity
     * @return 
     */
    @Override
    public User AddOne(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User RetreiveOne(int id_Entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> RetreiveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User DeleteOne(int id_Entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User UpdateOne(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
