package Templates.AdminGui;

import Services.SceneLoader;
import Services.UserServices;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DashboardController implements Initializable {

    public ImageView img_logout;
     

    public HBox lnk_show_users;
    public HBox lnk_show_stats;
    public HBox lnk_show_home;
    public Label lbl_nmb_admins;
    public Label lbl_nmb_gerant_camping;
    public Label lbl_gerant_maison;
    public Label lbl_gerant_activité;
    public static String email=null;
    @FXML
    private HBox lnk_show_activity;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
             System.out.print(email);

        UserServices us = new UserServices();
        this.lbl_nmb_admins.setText(us.getAdminsCount().toString());
        this.lbl_gerant_activité.setText(us.getGerantSecteurActivite().toString());
        this.lbl_nmb_gerant_camping.setText(us.getGerantSiteCampingCount().toString());
        this.lbl_gerant_maison.setText(us.getGerantsMaisonHotesCount().toString());

    }


    @FXML
    public void showUsers()
    {
        SceneLoader.loadScene("UserGui/ShowUsers.fxml",this.lnk_show_users);
                System.out.print(email);


    }

    public void logout()
    {
        SceneLoader.loadScene("loginGui/Login.fxml",this.img_logout);
    }


    @FXML
    public void showStats()
    {
        SceneLoader.loadScene("ChartsGui/Charts.fxml",this.lnk_show_stats);
        System.out.print(email);
    }


    public  void setEmail(String email)
    {
        this.email=email;
    }

    @FXML
    private void showActivity(MouseEvent event) {
                SceneLoader.loadScene("ActivityGui/AfficherActivity.fxml",this.lnk_show_activity);

    }



}
