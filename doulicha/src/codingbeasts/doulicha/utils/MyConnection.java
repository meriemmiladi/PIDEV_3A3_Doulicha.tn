package codingbeasts.doulicha.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    Connection cnx;
    public String url = "jdbc:mysql://localhost:3306/doulicha";
    public String login = "root";
    public String pwd = "";

    public MyConnection() {
        try {
            System.out.println("Connexion établie!");
            cnx = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
}
