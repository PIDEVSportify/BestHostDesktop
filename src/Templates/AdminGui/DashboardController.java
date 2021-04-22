package Templates.AdminGui;

import Services.SceneLoader;
import Services.UserServices;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserServices us = new UserServices();
        this.lbl_nmb_admins.setText(us.getAdminsCount().toString());
        this.lbl_gerant_activité.setText(us.getGerantSecteurActivite().toString());
        this.lbl_nmb_gerant_camping.setText(us.getGerantSiteCampingCount().toString());
        this.lbl_gerant_maison.setText(us.getGerantsMaisonHotesCount().toString());

    }


    public void showUsers()
    {
        SceneLoader.loadScene("UserGui/ShowUsers.fxml",this.lnk_show_users);

    }

    public void logout()
    {
        SceneLoader.loadScene("loginGui/Login.fxml",this.img_logout);
    }


    public void showStats()
    {
        SceneLoader.loadScene("ChartsGui/Charts.fxml",this.lnk_show_stats);
    }


    public  void setEmail(String email)
    {
        this.email=email;
    }



}
