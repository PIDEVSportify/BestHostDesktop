package Templates.LoginGui;


import Entities.Token;
import Entities.User;
import Services.LoginServices;
import Services.MailServices;
import Services.SceneLoader;
import Services.UserServices;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {
    public Hyperlink btn_sign_up;
    public TextField txt_email;
    public Button btn_send_recovery_token;
    public Label lbl_info;
    public TextField txt_token;
    public Button btn_reset_password;
    public PasswordField txt_password;
    public PasswordField txt_confirm_password;
    public Button btn_cancel;
    public Button btn_confirm_token;
    public VBox vbox_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            this.txt_password.setVisible(false);
            this.txt_confirm_password.setVisible(false);
            this.btn_reset_password.setVisible(false);
            this.txt_token.setVisible(false);
            this.btn_confirm_token.setVisible(false);
            this.txt_email.setEditable(true);
            this.txt_email.toFront();

    }


    public void signUp()
    {
        SceneLoader.loadScene("UserGui/AddUser.fxml",this.btn_sign_up);
    }


    public void sendRecoveryToken()
    {

        if (this.txt_email.getText().isBlank() || this.txt_email.getText().isEmpty())
        {
            this.lbl_info.setText("Veuiller spécifier une adresse mail");
            this.lbl_info.setTextFill(Color.RED);
        }
        else {
            String email= this.txt_email.getText();
           Token token= new Token(email);
            LoginServices ls = new LoginServices();
            if (ls.validEmail(email))
            {
                //ls.updateToken(email,token);
                String title = "Réinitialization du mot de passe";
                String body = "Votre code est  \n"+token.getToken()+"\n Ce code expire dans 10 mns.";
                MailServices.sendMail(email,title,body);
            }
            this.txt_token.setVisible(true);
            this.txt_token.toFront();
            this.txt_email.setEditable(false);
            this.btn_send_recovery_token.setVisible(false);
            this.btn_confirm_token.setVisible(true);
            this.lbl_info.setText("Un mail contenant le token vous a été envoyé.\nVeuillez vérifier votre boite mail");
            this.lbl_info.setTextFill(Color.PURPLE);
        }

    }

    public void verifyToken()
    {

            LoginServices ls = new LoginServices();
            /*Token token =  ls.fetchToken(this.txt_email.getText());
            if (token ==null)
            {
                this.lbl_info.setText("Token incorrect ou a expiré");
                this.lbl_info.setTextFill(Color.RED);
            }
            else if (token.getExpires_at().compareTo(Timestamp.from(Instant.now()))<0)
            {
                this.lbl_info.setText("Token a expiré");
                this.lbl_info.setTextFill(Color.RED);
            }
            else if (token.getToken().equals(this.txt_token.getText()))
            {

                this.btn_confirm_token.setVisible(false);
                this.lbl_info.setText("Entrer votre nouveau mot de passe");
                this.lbl_info.setTextFill(Color.PURPLE);
                this.txt_email.setVisible(false);
                this.txt_token.setVisible(false);
                this.btn_reset_password.setVisible(true);

                this.txt_password.setVisible(true);
                this.txt_confirm_password.setVisible(true);
                this.vbox_password.toFront();

            }
            else {
                this.lbl_info.setText("Token incorrect ou a expiré");
                this.lbl_info.setTextFill(Color.RED);
            }
*/

    }

    public void cancel()
    {
        SceneLoader.loadScene("LoginGui/Login.fxml",this.btn_cancel);
    }




    public void resetPassword()
    {
        String email= this.txt_email.getText();
        if(this.validPassword())
        {
            User user = new User();
            user.setPassword(this.txt_password.getText());
            user.setEmail(email);
            UserServices us = new UserServices();
            us.updatePassword(user);
            this.lbl_info.setText("Mot de passe mis à jour");
            this.lbl_info.setTextFill(Color.GREEN);
            SceneLoader.loadScene("LoginGui/Login.fxml",this.btn_reset_password);


        }
    }

    public boolean validPassword()
    {
        boolean valid = true ;
        if (this.txt_password.getText().length() <8 )
        {
            this.lbl_info.setText("Mot de passe doit au minimum contenir 8 caractères");
            this.lbl_info.setTextFill(Color.RED);
            valid=false;

        }
        else if (this.txt_password.getText().compareTo(this.txt_confirm_password.getText())!=0)
        {
            this.lbl_info.setText("Veuillez taper le même mot de passe");
            this.lbl_info.setTextFill(Color.RED);
            this.txt_confirm_password.setStyle("-fx-border-color:red");
            this.lbl_info.setTextFill(Color.RED);
            valid=false;
        }
        return valid;
    }
}
