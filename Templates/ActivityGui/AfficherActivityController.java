/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.Activitygui;
import javafx.scene.paint.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import Services.ActLikeService;
import Templates.AdminGui.DashboardController;
import Services.SceneLoader;
import Config.MyConnection;
import Entities.Activity;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AfficherActivityController implements Initializable {
    Connection cnx;
    PreparedStatement ste,stm;
    @FXML
    private Button addActivity;
    private Button listgerant;
    @FXML
    private Button showstats;
    @FXML
    private HBox action_buttons;
    @FXML
    private Button Exc;
    @FXML
    private HBox lnk_show_home;
    @FXML
    private HBox lnk_show_users;
    @FXML
    private HBox lnk_show_stats;
    @FXML
    private HBox lnk_show_activity;
    public AfficherActivityController() {
        cnx = MyConnection.current_connection.getConn();
    }

    @FXML
    private TableView<Activity> tvgerant;
    @FXML
    private TableColumn<Activity, String> idcol;
    @FXML
    private TableColumn<Activity, String> txttype;
    @FXML
    private TableColumn<Activity, String> desccol;
    @FXML
    private TableColumn<Activity, String> datcol;
    @FXML
    private TableColumn<Activity, String> catcol;
    @FXML
    private TableColumn<Activity, String> idgcol;
    @FXML
    private TextField text_search;
    @FXML
    private Button remove;
   

    ObservableList<Activity> activities = FXCollections.observableArrayList();
        ObservableList<Activity> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             
        affichtab();
        editTableCols();
        ObservableList<Activity> allgerant,singlegerant;
        allgerant = tvgerant.getItems();
        singlegerant = tvgerant.getSelectionModel().getSelectedItems();
        singlegerant.forEach(allgerant::remove);
    }    

    public void affichtab(){
    try {
ste = cnx.prepareStatement("select * from activity");
            ResultSet rs = ste.executeQuery();             
             while(rs.next()){
                 
             activities.add(new Activity(rs.getString("id_act"),rs.getString("type"),rs.getString("description"),rs.getString("categorie"),rs.getString("id_gerant"),rs.getDate("date_val")));
             }
         } catch (SQLException ex) {
         }
        idcol.setCellValueFactory(new PropertyValueFactory<>("id_act"));
        txttype.setCellValueFactory(new PropertyValueFactory<>("type"));
        desccol.setCellValueFactory(new PropertyValueFactory<>("description"));
        catcol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        idgcol.setCellValueFactory(new PropertyValueFactory<>("id_gerant"));
        datcol.setCellValueFactory(new PropertyValueFactory<>("date_val")); 
                tvgerant.setItems(activities);
    }
    private void editTableCols(){
    idgcol.setCellFactory(TextFieldTableCell.forTableColumn());
    idgcol.setOnEditCommit(event -> {
        Activity g = event.getRowValue();
    g.setId_act(event.getNewValue());
    updateData("id_gerant", event.getNewValue(),g.getId_act());
            });
    txttype.setCellFactory(TextFieldTableCell.forTableColumn());
    txttype.setOnEditCommit(event -> {
        Activity g = event.getRowValue();
    g.setType(event.getNewValue());
    updateData("type", event.getNewValue(),g.getId_act());
            });
    catcol.setCellFactory(TextFieldTableCell.forTableColumn());
    catcol.setOnEditCommit(event -> {
        Activity g = event.getRowValue();
    g.setCategorie(event.getNewValue());
    updateData("categorie", event.getNewValue(),g.getId_act());
            });
    desccol.setCellFactory(TextFieldTableCell.forTableColumn());
    desccol.setOnEditCommit(event -> {
        Activity g = event.getRowValue();
    g.setDescription(event.getNewValue());
    updateData("description", event.getNewValue(),g.getId_act());
            });
     tvgerant.setEditable(true);
    }
    
    
    private void updateData(String column,String newValue, String id){
         try {
             ste = cnx.prepareStatement("UPDATE activity SET "+column+"  =? where id_act = ?");
             ste.setString(1, newValue);
             ste.setString(2, id);
             ste.execute();
         } catch (SQLException ex) {
        ex.printStackTrace(System.err);
         }
    
    }
    private void deleteData(String id) throws SQLException{
        ste = cnx.prepareStatement("DELETE FROM activity where id_act LIKE '"+id+"' ");
        ste.execute();


    }
    @FXML
    private void Ajoutersc(ActionEvent event) {
                        SceneLoader.loadScene("ActivityGui/AjoutActivity.fxml",this.addActivity);

    }

    @FXML
    private void searchActivity(KeyEvent event) {
        text_search.setOnKeyReleased(e->{
            if(text_search.getText().equals("")){
                affichtab();
            }
            else{
                data.clear();
                String sql = "Select * from activity where id_act LIKE '%"+text_search.getText()+"%'"
                        +"UNION Select * from activity where type LIKE '%"+text_search.getText()+"%'"
                        +"UNION Select * from activity where description LIKE '%"+text_search.getText()+"%'"
                        +"UNION Select * from activity where categorie LIKE '%"+text_search.getText()+"%'"
                        +"UNION Select * from activity where id_gerant LIKE '%"+text_search.getText()+"%'"
                        +"UNION Select * from activity where date_val LIKE '%"+text_search.getText()+"%'";
                try {
                    ste = cnx.prepareStatement(sql);
                    ResultSet rs = ste.executeQuery();
                    while(rs.next()){
                        data.add(new Activity(rs.getString("id_act"),rs.getString("type"),rs.getString("description"),rs.getString("categorie"),rs.getString("id_gerant"),rs.getDate("date_val")));
                        tvgerant.setItems(data);
                    }
                } catch (SQLException ex) {
                }
            }}
        );
    }

    @FXML
    private void suppActivity(ActionEvent event) throws SQLException {
        ObservableList<Activity> allgerant,singlegerant;
        String id;
        allgerant = tvgerant.getItems();
        singlegerant = tvgerant.getSelectionModel().getSelectedItems();
        id = tvgerant.getSelectionModel().getSelectedItem().getId_act();
        deleteData(id);
        singlegerant.forEach(allgerant::remove);
        String title = "";
        String message = "You've successfully removed the activity";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(4));
    }

    private void listgerant(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherGerant.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage Window  = (Stage) listgerant.getScene().getWindow();
             Window.setScene(scene);
         } catch (IOException ex) {
                        System.out.println(ex.getMessage());
         }
    }

    @FXML
    private void showStats(ActionEvent event) {
                                SceneLoader.loadScene("ActivityGui/Stats.fxml",this.showstats);

    }

    @FXML
    private void expExcel(ActionEvent event) {
        try {
            ste = cnx.prepareStatement("select * from activity");
            ResultSet rs = ste.executeQuery();
            
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Activity List");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Reference");
            header.createCell(1).setCellValue("Description");
            header.createCell(2).setCellValue("Date limite");
            header.createCell(3).setCellValue("Categorie");
            header.createCell(4).setCellValue("Contact gerant");
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(0);
            int index = 1;
            while(rs.next()){
            XSSFRow row = sheet.createRow(index);
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateToString = df.format(rs.getDate("date_val"));
            row.createCell(0).setCellValue(rs.getString("id_act"));
            row.createCell(1).setCellValue(rs.getString("description"));
            row.createCell(2).setCellValue(dateToString);
            row.createCell(3).setCellValue(rs.getString("categorie"));
            row.createCell(4).setCellValue(rs.getString("id_gerant"));
            index++;

            
            }


            FileOutputStream fileOut = new FileOutputStream("Activity_List.xlsx");
            wb.write(fileOut);
            fileOut.close();
            rs.close();
            ste.close();
            String title = "EXCEL FILE CREATED";
        String message = "You've successfully created an excel file";
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(4));
            
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(AfficherActivityController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    public void showUsers()
    {
        SceneLoader.loadScene("UserGui/ShowUsers.fxml",this.lnk_show_users);


    }



    @FXML
    public void showStats()
    {
        SceneLoader.loadScene("ChartsGui/Charts.fxml",this.lnk_show_stats);
    }



    @FXML
    private void showActivity(MouseEvent event) {
                SceneLoader.loadScene("ActivityGui/AfficherActivity.fxml",this.lnk_show_activity);

    }
    public void showDashboard() {
        SceneLoader.loadScene("AdminGui/Dashboard.fxml", this.lnk_show_home);
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
    }

}
