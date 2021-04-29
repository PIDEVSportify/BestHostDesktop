/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.FeedbackGui;

import Entities.Reclamation;
import Services.SceneLoader;
import Services.ServiceFeedback;
import Services.ServiceReclamation;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatisticsController implements Initializable {
    @FXML
    private Button btnu2;

    @FXML
    private BarChart<?, ?> rt;
    @FXML
    private NumberAxis x;
    @FXML
    private CategoryAxis y;
    @FXML
    private TextField xd;
    @FXML
    private TextField fd;

    /**
     * Initializes the controller class.
     */
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceFeedback o = new ServiceFeedback();
        Reclamation rc = new Reclamation();
        BarChart.Series set1 = new BarChart.Series<>();
        int i=o.moyenne();
        fd.setText("vous avez "+o.getNbrFeedback()+"reclamation") ;
      String  s= String.valueOf(i);
      
rt.setTitle("la moyenne est "+s);
        set1.getData().add(new BarChart.Data("1",o.getNbrFeedback1()));
        
        set1.getData().add(new BarChart.Data("2",o.getNbrFeedback2()));
        set1.getData().add(new BarChart.Data("3",o.getNbrFeedback3()));
        set1.getData().add(new BarChart.Data("4",o.getNbrFeedback4()));
        set1.getData().add(new BarChart.Data("5",o.getNbrFeedback5()));
        rt.getData().addAll(set1);
        // TODO
    }
    @FXML
    private void back(ActionEvent event) {

        SceneLoader.loadScene("AdminGui/Dashboard.fxml",this.btnu2);

    }
    
}
