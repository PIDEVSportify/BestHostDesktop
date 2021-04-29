/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.ReclamationGui;


import Entities.Feedback;
import Entities.Reclamation;

import Services.ServiceReclamation;
import Config.MyConnection;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import javafx.collections.ObservableList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ClientReclamationController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField sujet;
    @FXML
    private TextField desc;
    @FXML
    private TextField etat;
    @FXML
    private TextField user;
    @FXML
    private TextField iddd;
    @FXML
    private TextField prod1;
    @FXML
    private ComboBox<String> prod;
    
    @FXML
    private Button ajout;
    @FXML
    private Button btnu;
    @FXML
    private TableView<Reclamation> RECl;
    @FXML
    private TableColumn<?, ?> dd;
    @FXML
    private TableColumn<?, ?> tt;
    @FXML
    private TableColumn<?, ?> oo;
    @FXML
    private TableColumn<?, ?> ss;
    @FXML
    private TableColumn<?, ?> ds;
    @FXML
    private TableColumn<?, ?> dt;
    @FXML
    private TableColumn<?, ?> dtt;
    @FXML
    private TableColumn<?, ?> usr;
    @FXML
    private Button supprimer;

    
      private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
    ObservableList<Reclamation> listM;
    /**
     * Initializes the controller class.
     */
    ObservableList<Reclamation> list = FXCollections.observableArrayList();
    @FXML
    private Button btnu2;
    /**
     * Initializes the controller class.
     */
    @FXML
    private void Feedback(ActionEvent event) {
        btnu2.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setTitle("Consultation");
            Parent root = FXMLLoader.load(getClass().getResource("ClientFeedback.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      try {
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(AffReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           afficherReclamation();
    }    
     private void afficherReclamation(){
          dd.setCellValueFactory(new PropertyValueFactory<>("id"));
          tt.setCellValueFactory(new PropertyValueFactory<>("type"));
          oo.setCellValueFactory(new PropertyValueFactory<>("ido"));
          ss.setCellValueFactory(new PropertyValueFactory<>("sujet"));
          ds.setCellValueFactory(new PropertyValueFactory<>("description"));
          dt.setCellValueFactory(new PropertyValueFactory<>("date"));
           dtt.setCellValueFactory(new PropertyValueFactory<>("etat"));
            usr.setCellValueFactory(new PropertyValueFactory<>("User"));
            
            
        RECl.setItems(list);
    }
     
          public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MyConnection.getInstance().getConn();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM reclamation");
            while(rs.next()){
            list.add(new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
        }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
              Connection cnx = MyConnection.getInstance().getConn();
            rs = cnx.createStatement().executeQuery("SELECT nom FROM maison_hote");
            // Now add the comboBox addAll statement
           while (rs.next()){
            prod.getItems().addAll(rs.getString("nom"));
           
           }
        }

    @FXML
    private void AjoutReclamation(ActionEvent event) {
        
    }

    @FXML
 public void Edit () throws SQLException{   
        try {
            cnx = MyConnection.ConnectDb();
            String value0 = iddd.getText();
            String value2 = prod.getValue();
            
            String value6 = prod1.getText();
            
            String value3 = sujet.getText();
            
            String value1 = type.getText();
            String value4 = desc.getText();
            String value7 = etat.getText();
            String value8 = user.getText();
            String sql = "update reclamation set type= '"+value1+"',ido= '"+value2+"',sujet= '"+
                    value3+"',description= '"+value4+"',date= '"+value6+"',etat= '"+value7+"',idu= '"+value8+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
            UpdateTable();
                list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
            JOptionPane.showMessageDialog(null, "Update");
            iddd.setText("");
             list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
    type.setText("");
    /*prod.setText("");*/
    sujet.setText("");
    desc.setText("");
    
    prod1.setText("");
    etat.setText("");
    user.setText("");
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
    }
 @FXML
    private void getSelected(MouseEvent event) {
            int index = RECl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    iddd.setText(dd.getCellData(index).toString());
    type.setText(tt.getCellData(index).toString());
    prod.setValue(oo.getCellData(index).toString());
    sujet.setText(ss.getCellData(index).toString());
    desc.setText(ds.getCellData(index).toString());
    
    prod1.setText(dt.getCellData(index).toString());
    etat.setText(dtt.getCellData(index).toString());
    user.setText(usr.getCellData(index).toString());
    
    
    }
    public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = (Reclamation) RECl.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
   private void supprimerrc(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation x = gettempReclamation(edittedcell);

        if (x != null) {

            int i = x.getId();
            ServiceReclamation cat = new ServiceReclamation();

            int s = cat.deletereclamation(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Reclamation deleted");
                alert.showAndWait();
                UpdateTable();
                       list.clear();
                initialiserlist(); 
                afficherReclamation();
                RECl.refresh();
                  iddd.setText("");
    type.setText("");
    prod.setValue("");
    sujet.setText("");
    desc.setText("");
    
    prod1.setText("");
    etat.setText("");
    user.setText("");
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
     public void UpdateTable() throws SQLException{
         dd.setCellValueFactory(new PropertyValueFactory<>("id"));
          tt.setCellValueFactory(new PropertyValueFactory<>("type"));
          oo.setCellValueFactory(new PropertyValueFactory<>("ido"));
          ss.setCellValueFactory(new PropertyValueFactory<>("sujet"));
          ds.setCellValueFactory(new PropertyValueFactory<>("description"));
          dt.setCellValueFactory(new PropertyValueFactory<>("date"));
           dtt.setCellValueFactory(new PropertyValueFactory<>("etat"));
            usr.setCellValueFactory(new PropertyValueFactory<>("ido"));
         listM = MyConnection.getDataReclamation();
        
        RECl.setItems(listM);
    }

  
}
