/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.ActivityGui;

import Entities.Activity;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ActivityItemController implements Initializable {

    @FXML
    private Label cat;
    @FXML
    private Label desc;
    @FXML
    private Label datee;
    
    private Activity activity;
    private MyListener myListener;
    @FXML
    private Label idg;
    @FXML
    private Label idglab;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(activity);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setData(Activity activity,  MyListener myListener){
        this.activity = activity;
        this.myListener = myListener;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateToString = df.format(activity.getDate_val());
        cat.setText(activity.getCategorie());
        desc.setText(activity.getDescription());
        datee.setText(dateToString);
        idg.setText(activity.getId_gerant());
        idglab.setText(activity.getId_act());
        
    }
}
