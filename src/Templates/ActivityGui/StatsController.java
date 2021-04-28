/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.Activitygui;

import Entities.Activity;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import Config.MyConnection;
import Services.SceneLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class StatsController implements Initializable {
     Connection cnx;
    PreparedStatement ste,stm;
    @FXML
    private Button showAct;
    public StatsController() {
        cnx = MyConnection.current_connection.getConn();
    }
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private NumberAxis ybarChart;
    @FXML
    private CategoryAxis xbarChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadChart();
    }   
    private void loadChart(){
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        int i=0,j=0,k=0;
         try {
             ste = cnx.prepareStatement("select * from activity where categorie like 'Randonnée'");
            ResultSet rs = ste.executeQuery();             
             while(rs.next()){
             stm = cnx.prepareStatement("select count(*) from act_like where post_id = '"+rs.getString("id_act")+"'");
                 ResultSet rss = stm.executeQuery();
                 rss.next();
                i=i+rss.getInt(1);
             }
             
             
             ste = cnx.prepareStatement("select * from activity where categorie like 'PaintBall'");
             rs = ste.executeQuery();             
             while(rs.next()){
             stm = cnx.prepareStatement("select count(*) from act_like where post_id = '"+rs.getString("id_act")+"'");
                 ResultSet rss = stm.executeQuery();
                 rss.next();
                j=j+rss.getInt(1);
             }
             
             series.getData().add(new XYChart.Data<>("Randonnée",i));
             
             series.getData().add(new XYChart.Data<>("PaintBall",j));
             ste = cnx.prepareStatement("select * from activity where categorie like 'Match foot'");
             rs = ste.executeQuery();             
             while(rs.next()){
             stm = cnx.prepareStatement("select count(*) from act_like where post_id = '"+rs.getString("id_act")+"'");
                 ResultSet rss = stm.executeQuery();
                 rss.next();
                k=k+rss.getInt(1);
             }
             
             series.getData().add(new XYChart.Data<>("Match foot",k));

             //while(rs.next()){
             //}
                 
         } catch (SQLException ex) {
             Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
         }
         barChart.getData().add(series);
      
    }

    @FXML
    private void listAct(ActionEvent event) {
                                        SceneLoader.loadScene("ActivityGui/AfficherActivity.fxml",this.showAct);

    }
    
}
