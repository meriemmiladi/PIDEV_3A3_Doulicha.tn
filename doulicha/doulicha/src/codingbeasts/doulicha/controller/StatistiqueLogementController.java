/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingbeasts.doulicha.controller;

import codingbeasts.doulicha.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marie
 */
public class StatistiqueLogementController implements Initializable {

    @FXML
    private PieChart log_stat;

    private ResultSet rs;
    private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
int n;
    @FXML
    private Button retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx = MyConnection.getInstance().getCnx();
            stat();
            
         
        retour.setOnAction( event->{
        try{
            System.out.println("home appuyé");
            Parent page1 = FXMLLoader.load(getClass().getResource("/codingbeasts/doulicha/view/AfficherLogement.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
    }
        catch (IOException ex) {
                Logger.getLogger(AjouterLogementController.class.getName()).log(Level.SEVERE, null, ex);
    }
    });
    }    

    private void stat() {
          try{
          
           String query ="select COUNT(*),`type_logement` from logement GROUP BY `type_logement`;";

           PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("type_logement"),rs.getInt("COUNT(*)")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }        
         log_stat.setTitle("** Statistiques sur les types des logements ajoutés **");
        log_stat.setLegendSide(Side.LEFT);
        log_stat.setData(data);
    }
    
    

    
}
