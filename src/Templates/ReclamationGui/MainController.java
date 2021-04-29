/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Templates.ReclamationGui;

import static java.lang.ProcessBuilder.Redirect.from;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainController implements Initializable {

    @FXML
    private TextField emailToField;
    @FXML
    private TextField emailFroField;
    @FXML
    private TextField emailPasswordField;
    @FXML
    private TextField emailSubjectField;
    @FXML
    private TextArea emailMessageField;
    @FXML
    private Button sendemailbutton;
    @FXML
    private Label sentboolValue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void buttonclicked(){
    senemail();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("message envoyé");
    }
    public void senemail(){
 String to = emailToField.getText();
 String from = "oussemacharradatesting@gmail.Com";
 final String username="oussemacharradatesting@gmail.Com";
 final String password="azer1234-";
   
 String host = "smtp.google.com";
 
       Properties props = new Properties();
         props.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        props.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        props.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        props.put("mail.smtp.port", "587");
  Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
  try {
      MimeMessage m = new MimeMessage(session);
      m.setFrom(new InternetAddress(from));
      m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
      String message = "<i>Greetings!</i><br>";
      message += "<b>Wish you a nice day!</b><br>";
      message += "<font color=red>Duke</font>";
      m.setSubject("Reclamation BestHost");
      m.setText(message);
      
      
      Transport.send(m);
      sentboolValue.setVisible(true);
      System.out.println("message envoyé");
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("message envoyé");
      
  } catch (MessagingException e){
  e.printStackTrace();
  }
  }
    }

    

