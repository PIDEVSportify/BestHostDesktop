package Templates.MailGui;

import Entities.camping;
import Entities.offre;
import Services.Camping_c;
import Services.Offre_c;
import Templates.MapGui.AfficherMap;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvoyerMail implements Initializable {
    @FXML
    public JFXTextField Email;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public static void sendMail(String recepient) throws Exception {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "Speakyjavafx@gmail.com";
        String password = "Javafx123";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient);
        assert message != null;
        Transport.send(message);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success");
        alert.setContentText("The mail has been sent successfully.");
        alert.showAndWait();
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        Camping_c c=new Camping_c();
        Offre_c c1=new Offre_c();
        String[] image = {""};
        Path path;
        String special=null;
        try {
            path = Paths.get(Objects.requireNonNull(EnvoyerMail.class.getResource("../../")).toURI());
            special = path.getParent().getParent().getParent().toString().replaceAll("\\\\", "/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Camping Checklist");

            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart me = new MimeBodyPart();

        try {
        for(camping l:c.result()){
            if(l.getOffre_id_id()!=0)
                for(offre l1:c1.result()){
                    if(l.getOffre_id_id()==l1.getId())
                        l.setFulloffre("Nombre des places: "+l1.getNombre_places()+" Date début: "+l1.getDate_debut()+" Date fin: "+l1.getDate_fin()+" Prix: "+l1.getPrix()+"Dt");
                }
            else
                l.setFulloffre("Pas d'offre pour le moment");
            UUID cid = UUID.randomUUID();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String fullmail="Localisation: "+l.getLocalisation_camping()+" Type: "+l.getType_camping()+" Description: "+l.getDescription_camping()+" Offre: "+l.getFulloffre();
            messageBodyPart.setText(""
                            + "<html>"
                            + " <body>"
                            + "  <p>"+fullmail+"</p>"
                            + "  <img src=\"cid:" + cid + "\" width=\"250\" height=\"200\"/>"
                            + " </body>"
                            + "</html>"
                    ,"US-ASCII", "html");
            MimeBodyPart messageBodyPartimage = new MimeBodyPart();
            messageBodyPartimage.attachFile(special+"/public/uploads/"+l.getImage_camping());
            messageBodyPartimage.setContentID("<" +cid+ ">");

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messageBodyPartimage);
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        me.setText(""
                + "<html>"
                + " <body>"
                + "<p>Pour plus d'information n'hésitez pas à me contacter sur Facebook </p>"
                +"<a href='https://www.facebook.com/selim.benaich/'>Selim Ben Aich</a>"
                +"<p> Tel:+21693165012</p>"
                +" </body>"
                + "</html>"
                    ,"US-ASCII", "html");
            multipart.addBodyPart(me);
            message.setContent(multipart);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(EnvoyerMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public EnvoyerMail(){};

    public void Submitmail(ActionEvent actionEvent) throws Exception {
        if(isValidEmailAddress(Email.getText()))
            sendMail(Email.getText());

        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Invalid email format: the at sign symbol (@), used to separate the local part from the domain part of the address, has not been found.");
            alert.setResizable(true);
            alert.showAndWait();
        }
    }
}
