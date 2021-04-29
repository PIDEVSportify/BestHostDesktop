package Templates.MaisonGui;

import Entities.Maison;
import Entities.Reservation;
import Entities.Token;
import Entities.User;
import Services.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReserverController implements Initializable {

    private static Maison maison;

    public ImageView imageView;

    public TextField txt_nom;

    @FXML
    private TextField txt_adresse;

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_prix;

    @FXML
    private TextField txt_chambre;

    @FXML
    private TextField txt_email;



    @FXML
    private Button btn_cancel;

    @FXML
    private TextField txt_nom_client;


    @FXML
    private TextField txt_numero;

    @FXML
    private DatePicker txt_deb;

    @FXML
    private DatePicker txt_fin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.txt_nom.setText(this.maison.getNom());
        this.txt_adresse.setText(this.maison.getAdresse());
        this.txt_description.setText(this.maison.getDescription());
        this.txt_prix.setText(String.valueOf(this.maison.getPrix()));
        this.txt_chambre.setText(String.valueOf(this.maison.getNombre_chambres()));
        try {
            this.imageView.setPreserveRatio(true);
            Image img = new Image("file:public/"+this.maison.getAvatar(),200,155,false,false);
            this.imageView.setImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void setMaison(Maison maison) {
        ReserverController.maison = maison;
    }

@FXML
    public void cancel()
    {
        SceneLoader.loadScene("MaisonGui/ShowMaison.fxml",this.btn_cancel);
    }



    @FXML
    void addReservation(ActionEvent event) {

        String nom_c=txt_nom_client.getText();
        String email=txt_email.getText();
        String num = txt_numero.getText();
        String date_deb= String.valueOf(txt_deb.getValue());
        String date_fin= String.valueOf(txt_fin.getValue());

        Reservation m = new Reservation(num,nom_c,email,date_deb,date_fin);
        ReservationServices ms = new ReservationServices();

        ms.Addreservation(m);
        //MailServices.sendMail(m.getEmail());
        // SmsServices.sendSMS(this.inscription_phone_number.getText());
        // SceneLoader.loadScene("LoginGui/Login.fxml",this.btn_back);


    sendConfirmationMail();

    }




    public void sendConfirmationMail()
    {

        if (this.txt_email.getText().isBlank() || this.txt_email.getText().isEmpty())
        {
           System.out.println("no email");
        }
        else {
            String email= this.txt_email.getText();

            Maison maison = new Maison();



                String title = "Reservation confirmer";
                String body = "Votre reservation pour la maison d'hote :"+this.txt_nom.getText()+" pour la date du "+this.txt_deb.getValue()+" au "+this.txt_fin.getValue()+"  est bien recu";
                MailServices.sendMail(email,title,body);




        }

    }



}
