package Templates.AdminGui;

import Services.SceneLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public ImageView img_logout;
     

    public HBox lnk_show_users;
    public HBox lnk_show_stats;
    public HBox lnk_show_home;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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





}
