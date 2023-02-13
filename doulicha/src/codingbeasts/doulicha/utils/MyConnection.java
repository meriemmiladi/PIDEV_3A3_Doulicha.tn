/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ghass
 */
public class MyConnection {
    Connection cnx;
    public String url="jdbc:mysql://localhost:3306/doulicha"; 
    public String login="root";
    public String pwd="";
    public MyConnection (){
        try{
            System.out.println("Connexion établie!");
        cnx=DriverManager.getConnection(url,login,pwd);
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
    public Connection getCnx(){
        return cnx;
    }
}
