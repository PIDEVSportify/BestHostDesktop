/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.ActivityGui;

import Config.MyConnection;
import Entities.Activity;
import Services.ActLikeService;
import Templates.AdminGui.DashboardController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class UserActivityController implements Initializable {
Connection cnx;
    PreparedStatement ste,stm;
        private final List<Activity> activities = new ArrayList<>();
        String sql = "select * from activity";
    @FXML
    private GridPane grid;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label catlab;
    @FXML
    private Label desclab;
    @FXML
    private Label datlab;
    MyListener myListener;
    @FXML
    private TextField txt_search;
    @FXML
    private Label conlab;
    @FXML
    private Label ref;
    private Button btn_like;
    @FXML
    private HBox action_buttons;
    @FXML
    private Button sort;
    @FXML
    private ChoiceBox<String> srtbox;
    private void Btn_Like(ActionEvent event)
    {
        if (event.getSource()==btn_like)
        {
            if (btn_like.getText().equals("Like"))
            {
                this.like();
                String title = "";
        String message = "You've successfully liked this post";
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(4));
            }
            else if (btn_like.getText().equals("Unlike"))
            {
                this.dislike();
                
            }
        }
    }

    public UserActivityController() {
        cnx = MyConnection.current_connection.getConn();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //activities.addAll(getData(sql));
        srtbox.getItems().add("categorie");
        srtbox.getItems().add("description");     
        srtbox.getItems().add("date de validité");
        srtbox.getItems().add("contact gerant");        
        btn_like = new Button();
             action_buttons.getChildren().add(btn_like);
             btn_like.setVisible(false);
             btn_like.getStyleClass().add("add-btn");
             btn_like.setOnAction(this::Btn_Like);
             btn_like.setAlignment(Pos.CENTER);
             btn_like.setStyle("-fx-font-size:25");

        activities.addAll(getData("select * from activity"));
                if (activities.size() > 0) {
            setChosenActivity(activities.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Activity activity) {
                    setChosenActivity(activity);
                }
            };}
        int column = 0;
        int row = 1;
        for(int i=0; i<activities.size(); i++){
            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ActivityItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                ActivityItemController itemcontroller = fxmlloader.getController(); 
                itemcontroller.setData(activities.get(i),myListener);
                if(column == 3){
                column = 0;
                row++;
                }
                
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        searchActivity();
        
    }  
    private List<Activity> getData(String sql){
        List<Activity> activities = new ArrayList<>();
        Activity activity;
    try {
        ste = cnx.prepareStatement(sql);
        ResultSet rs = ste.executeQuery();
        while(rs.next()){
            activity = new Activity();
            activity.setCategorie(rs.getString("categorie"));
            activity.setDate_val(rs.getDate("date_val"));
            activity.setDescription(rs.getString("description"));
            activity.setId_gerant(rs.getString("id_gerant"));
            activity.setId_act(rs.getString("id_act"));
            activities.add(activity);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
    }
       return activities; 
    }
    private void setChosenActivity(Activity activity) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateToString = df.format(activity.getDate_val());
        String rf = activity.getId_act();
        catlab.setText(activity.getCategorie());
        desclab.setText(activity.getDescription());
        datlab.setText(dateToString);
        conlab.setText(activity.getId_gerant());
        ref.setText(activity.getId_act());
        chosenFruitCard.setStyle("    -fx-background-radius: 30;");
        isLiked(rf);
    }

    @FXML
    private List<Activity> searchActivity() {

        txt_search.setOnKeyReleased(e->{
            if(txt_search.getText().equals("")){
                                activities.clear();
                                                grid.getChildren().clear();
                activities.addAll(getData("select * from activity"));
                if (activities.size() > 0) {
            setChosenActivity(activities.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Activity activity) {
                    setChosenActivity(activity);
                }
            };}
        int column = 0;
        int row = 1;
        for(int i=0; i<activities.size(); i++){
            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ActivityItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                ActivityItemController itemcontroller = fxmlloader.getController(); 
                itemcontroller.setData(activities.get(i),myListener);
                if(column == 3){
                column = 0;
                row++;
                }
                
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            }
            else{
                activities.clear();
                grid.getChildren().clear();
                activities.addAll(
                getData("Select * from activity where description LIKE '%"+txt_search.getText()+"%'"
                        +"UNION Select * from activity where categorie LIKE '%"+txt_search.getText()+"%'"
                        +"UNION Select * from activity where id_gerant LIKE '%"+txt_search.getText()+"%'"
                        +"UNION Select * from activity where date_val LIKE '%"+txt_search.getText()+"%'"));
                if (activities.size() > 0) {
            setChosenActivity(activities.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Activity activity) {
                    setChosenActivity(activity);
                }
            };}
        int column = 0;
        int row = 1;
        for(int i=0; i<activities.size(); i++){
            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ActivityItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                ActivityItemController itemcontroller = fxmlloader.getController(); 
                itemcontroller.setData(activities.get(i),myListener);
                if(column == 3){
                column = 0;
                row++;
                }
                
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
                } 
                
           
        );
    return activities;
    }
    public void isLiked(String rf){
    try {
        String mail = DashboardController.email;
        ste = cnx.prepareStatement("select count(*) from act_like where post_id = '"+rf+"' AND user_id = '"+mail+"'");
        ResultSet rs = ste.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        
        
        
        
        if (count==0)
        {
            btn_like.setText("Like");
        }
        else
        {
            btn_like.setText("Unlike");
        }
        btn_like.setVisible(true);
    } catch (SQLException ex) {
        Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void like(){
        ActLikeService as = new ActLikeService();
    String rf =this.ref.getText();
    String mail = DashboardController.email;
    as.addLike(rf, mail);
                btn_like.setText("Unlike");
    }
    public void dislike(){
    ActLikeService as = new ActLikeService();
    String rf = this.ref.getText();
    String mail = DashboardController.email;
    as.suppLike(rf, mail);
                btn_like.setText("Like");

    }

    private void sortAct(ActionEvent event) {
       
    }

    @FXML
    private void sortAct(MouseEvent event) {
         activities.clear();
                grid.getChildren().clear();
        String srt = (String) srtbox.getSelectionModel().getSelectedItem();
        if ("date de validité".equals(srt)){
        activities.addAll(getData("select * from activity ORDER BY date_val"));
        }else if("contact gerant".equals(srt))
        {
                activities.addAll(getData("select * from activity ORDER BY id_gerant"));
        }
        else{
        activities.addAll(getData("select * from activity ORDER BY '"+srt+"' ASC"));}
                if (activities.size() > 0) {
            setChosenActivity(activities.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Activity activity) {
                    setChosenActivity(activity);
                }
            };}
        int column = 0;
        int row = 1;
        for(int i=0; i<activities.size(); i++){
            try {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ActivityItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                
                ActivityItemController itemcontroller = fxmlloader.getController(); 
                itemcontroller.setData(activities.get(i),myListener);
                if(column == 3){
                column = 0;
                row++;
                }
                
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane,new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
        public void sendEmail() {
            
         String to = conlab.getText();
         String from = DashboardController.email;
         String type = catlab.getText();
         String date = datlab.getText();
         String host ="smtp.gmail.com";
         final String username = "emna.lazzez@esprit.tn";
         final String password = "181JFT1429";
         
         Properties props = System.getProperties();
         props.put("mail.smtp.auth","true");
         props.put("mail.smtp.starttls.enable","true");
         props.put("mail.smtp.host",host);
         props.put("mail.smtp.port", "587");
         
         Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentification(){
                 final String username = "emna.lazzez@esprit.tn";
         final String password = "181JFT1429";
                return new PasswordAuthentication(username,password);
             }
         });
         
    try {
        MimeMessage m = new MimeMessage(session);
        m.setFrom(new InternetAddress(from));
        m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        m.setSubject("Reservation activité");
        m.setText("Notre client '"+from+"' veut reserver une place pour l'activité de "+type+" pour la date '"+date+"'");
        
        Transport.send(m, username, password);
        System.out.println("Message sent!");
        String title = "Reservation";
        String message = "Reservation request has been sent , we will contact you soon!";
        
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
    } catch (AddressException ex) {
        Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MessagingException ex) {
        Logger.getLogger(UserActivityController.class.getName()).log(Level.SEVERE, null, ex);
    }

    
   
    }

    @FXML
    private void mailSending(MouseEvent event) {
        sendEmail();
    }
}

        
