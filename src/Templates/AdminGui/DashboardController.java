package Templates.AdminGui;

import Services.SceneLoader;
import Services.UserServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    public HBox lnk_show_speaky;
    public HBox lnk_show_reclamation;
    public HBox lnk_show_Feedback;
    public HBox lnk_show_activity;
    public HBox lnk_show_maison_hote;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Se Déconnecter ?");


        if (alert.showAndWait().get() == ButtonType.OK) {
            SceneLoader.loadScene("loginGui/Login.fxml",this.img_logout);
        }
    }


    public void showStats()
    {
        SceneLoader.loadScene("ChartsGui/Charts.fxml",this.lnk_show_stats);
    }




    public  void setEmail(String email)
    {
        this.email=email;
    }


    public void showSites() throws IOException {
//        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../DashboardspeakyGui/Dashboard.fxml")));
//        Scene scene=new Scene(root);
//        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("Camping Checklist");
//        stage.setScene(scene);
//        stage.show();
//        stage.setOnCloseRequest(e -> Platform.exit());

        SceneLoader.loadScene("DashboardspeakyGui/Dashboard.fxml",this.lnk_show_speaky);
    }
    public void showReclamation() throws IOException {
//        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../DashboardspeakyGui/Dashboard.fxml")));
//        Scene scene=new Scene(root);
//        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("Camping Checklist");
//        stage.setScene(scene);
//        stage.show();
//        stage.setOnCloseRequest(e -> Platform.exit());

        SceneLoader.loadScene("ReclamationGui/AffReclamation.fxml",this.lnk_show_reclamation);
    }
    public void showFeedback() throws IOException {
//        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../DashboardspeakyGui/Dashboard.fxml")));
//        Scene scene=new Scene(root);
//        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("Camping Checklist");
//        stage.setScene(scene);
//        stage.show();
//        stage.setOnCloseRequest(e -> Platform.exit());

        SceneLoader.loadScene("FeedbackGui/ClientFeedback.fxml",this.lnk_show_Feedback);
    }
    public void showMaison(){
        SceneLoader.loadScene("MaisonGui/ShowMaison.fxml",this.lnk_show_maison_hote);
    }
     public void showActivity() {
                SceneLoader.loadScene("ActivityGui/AfficherActivity.fxml",this.lnk_show_activity);

    }
}
