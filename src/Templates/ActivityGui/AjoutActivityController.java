/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.Activitygui;

import Config.MyConnection;
import Entities.Activity;
import Services.EntityService;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Templates.AdminGui.DashboardController;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AjoutActivityController implements Initializable {
    Connection cnx;
    PreparedStatement ste;
    @FXML
    private Button addActivity;
    @FXML
    private Button listact;
            
public AjoutActivityController() {
        cnx = MyConnection.current_connection.getConn();
    }
    @FXML
    private TextField txtid;
    @FXML
    private TextField txttype;
    @FXML
    private ChoiceBox txtcat;
    @FXML
    private TextField txtdes;
    @FXML
    private DatePicker datp;
   String email = DashboardController.email;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        ObservableList<String> txtidgl = FXCollections.observableArrayList();

        
                txtcat.getItems().add("PaintBall");
                txtcat.getItems().add("Randonnée");
                txtcat.getItems().add("Match foot");

                                             

    }    

    @FXML
    private void addActivity(ActionEvent event) {
       try {
            String id = txtid.getText();
            String type = txttype.getText();
            String categorie = (String) txtcat.getSelectionModel().getSelectedItem();
            String desc = txtdes.getText();
            String idg = email;
            LocalDate data=datp.getValue(); 
            Activity p = new Activity(id,type,desc,categorie,idg,Date.valueOf(data));
            EntityService ps = new EntityService();
            ps.AjouterEntity(p);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherActivity.fxml"));
            Parent root = loader.load();
            AfficherActivityController apc = loader.getController();
            txtid.getScene().setRoot(root);


        } catch (IOException ex) {
                      System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listact(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherActivity.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage Window  = (Stage) listact.getScene().getWindow();
             Window.setScene(scene);
         } catch (IOException ex) {
                        System.out.println(ex.getMessage());
         }
    }
    
}
