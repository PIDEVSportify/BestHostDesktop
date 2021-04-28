package Templates.LoginGui;

import Entities.User;
import Services.LoginServices;
import Services.SceneLoader;
import Services.SmsServices;
import Templates.AdminGui.DashboardController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable {
    public Hyperlink btn_sign_up;
    public Label lbl_password;
    @FXML
    private Hyperlink lnk_reset_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private Label lbl_error;

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_login;


    @FXML
    public void login()
    {
        if (this.txt_email.getText().isBlank() || this.txt_password.getText().isBlank() )
        {
            this.lbl_error.setText("Vérifier vos infos");
        }
    else {
            LoginServices ls = new LoginServices();
            User u = ls.checkCredentials(this.txt_email.getText(), this.txt_password.getText());
            if (u != null) {

                System.out.println("Login successfull");
                if (u.getIs_banned() == 1) {
                    this.lbl_error.setText("Compte Bannis ! Connexion impossible ");

                } else {
                    DashboardController dc= new DashboardController();
                    dc.setEmail(this.txt_email.getText());
                    SceneLoader.loadScene("AdminGui/Dashboard.fxml", this.txt_password);
                }
            } else {
                this.lbl_error.setText("Email ou mot de passe incorrect");
            }
        }

    }

    @FXML
    public void signUp()
    {
        SceneLoader.loadScene("UserGui/AddUser.fxml",this.btn_login);
    }

    @FXML
    public void resetPassword()
    {
        SceneLoader.loadScene("LoginGui/ResetPassword.fxml",this.btn_login);
    }


    public void facebookLogin() {

        String domain = "http://localhost.com";
        String appId = "250238063430959";
        // String accessToken= "EAADjlxbhTS8BABqyXjYdAuczifdyeRITarTSLayALhX2cRADFFaVukwJ5NlZBo3qx1vRFNc7nDkFMVm7s5sbpabDrYajenUnnBOSYbXY6mXZBqUtdrfNpOG501jKCa7sEyUybZAWLhRl07W6DJN75dLYxSGT4SeDfEeF5ZCLiFnZA3boVQ8c0B0BKvTtXgXa0NP6RwFCHMU8xURK32ZCkAqNeLzowdmXSMZAvpHXRdq4QZDZD";
        String authUrl = "https://graph.facebook.com/v2.6/device/login" +
                "access_token=" + appId +
                "scope=user_photos,user_about_me,email";


        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        while (true) {

            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();
                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                driver.quit();
            }
        }
    }

    public void sendSMS()
    {
        SmsServices.sendSMS("26701278");
    }




}
