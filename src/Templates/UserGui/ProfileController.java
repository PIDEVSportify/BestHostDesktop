package Templates.UserGui;

import Entities.User;
import Services.SceneLoader;
import Services.UserServices;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ProfileController  implements Initializable {
    public Label lbl_error_password;
    public Button btn_update_password;
    public PasswordField txt_password;
    public PasswordField txt_confirm_password;
    public Button btn_update_parameters;
    public TextField txt_first_name;
    public TextField txt_last_name;
    public TextField txt_cin;
    public TextField txt_email;
    public Label lbl_error_general_settings;
    private static User user;
    public HBox lnk_show_stats;
    public HBox lnk_show_users;
    public ImageView img_avatar;
    public Hyperlink lnk_modify_avatar;
    public Button btn_go_back;
    public Hyperlink lnk_cancel_avatar_update;
    public Hyperlink lnk_confirm_avatar_update;
    public Label lbl_error_avatar;
    public HBox lnk_dashboard_home;
    public Tab tab_parameters;
    public Tab tab_security;
    public Tab tab_avatar;

    private File file=null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle ) {
            this.txt_email.setText(this.user.getEmail());
            this.txt_cin.setText(this.user.getCin());
            this.txt_last_name.setText(this.user.getLast_name());
            this.txt_first_name.setText(this.user.getFirst_name());

        try {
            this.img_avatar.setPreserveRatio(true);
            Image img = new Image("file:public/"+this.user.getAvatar(),200,155,false,false);
            this.img_avatar.setImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.lnk_cancel_avatar_update.setVisible(false);

        this.lnk_confirm_avatar_update.setVisible(false);
        this.lnk_modify_avatar.setVisible(true);





    }


    public void updateGeneralSettings()
    {
        if(this.checkGeneralSettingsFields())
        {
            User user = new User(this.txt_first_name.getText(),this.txt_last_name.getText(),this.txt_email.getText(),this.txt_cin.getText());
            UserServices us = new UserServices();
            us.modifyUser(user);
            this.lbl_error_general_settings.setText("Paramètres mis à jour");
            this.lbl_error_general_settings.setTextFill(Color.GREEN);
        }

    }

    public void updateSecuritySettings()
    {
        if(this.checkSecuritylSettings())
        {
            User user = new User();
            user.setPassword(this.txt_password.getText());
            user.setEmail(this.user.getEmail());
            UserServices us = new UserServices();
            us.updatePassword(user);
            this.lbl_error_password.setText("Mot de passe mis à jour");
            this.lbl_error_password.setTextFill(Color.GREEN);


        }
    }

    public boolean checkSecuritylSettings()
    {
        boolean valid = true ;
        if (this.txt_password.getText().length() <8 )
        {
            this.lbl_error_password.setText("Mot de passe doit au minimum contenir 8 caractères");
            this.lbl_error_password.setTextFill(Color.RED);
            valid=false;

        }
        else if (this.txt_password.getText().compareTo(this.txt_confirm_password.getText())!=0)
        {
            this.lbl_error_password.setText("Veuillez taper le même mot de passe");
            this.lbl_error_password.setTextFill(Color.RED);
            this.txt_confirm_password.setStyle("-fx-border-color:red");
            this.lbl_error_password.setTextFill(Color.RED);
            valid=false;
        }
        return valid;
    }


    public boolean checkGeneralSettingsFields()
    {
        boolean valid = true;
        if (this.txt_first_name.getText().isEmpty() || this.txt_first_name.getText().isBlank())
        {
            this.lbl_error_general_settings.setText("Champs invalide");
            this.lbl_error_general_settings.setTextFill(Color.RED);
            this.txt_first_name.setStyle("-fx-border-color:red");
            valid=false;

        }
        else {
            this.txt_first_name.setStyle("-fx-border-color:#44F219");
        }

        if (this.txt_last_name.getText().isEmpty() || this.txt_last_name.getText().isBlank())
        {
            this.lbl_error_general_settings.setText("Champs invalide");
            this.lbl_error_general_settings.setTextFill(Color.RED);

            this.txt_last_name.setStyle("-fx-border-color:red");
            valid=false;

        }
        else {
            this.txt_last_name.setStyle("-fx-border-color:#44F219");
        }


        return valid;
    }


    public void setUser(User user) {
        ProfileController.user = user;
    }

    public void showUsers()
    {
        SceneLoader.loadScene("UserGui/ShowUsers.fxml",this.lnk_show_users);

    }
    public void showStats()
    {
        SceneLoader.loadScene("ChartsGui/Charts.fxml",this.lnk_show_stats);
    }


    public void selectAvatar(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.image)", "*.jpg","*.jpeg","*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);


        //Show save file dialog

        this.file  = fileChooser.showOpenDialog(this.lnk_modify_avatar.getScene().getWindow());
        if (this.file!=null)
        {
            Image img = new Image(this.file.getAbsoluteFile().toURI().toString());
            this.img_avatar.setImage(img);

        }
        this.lnk_cancel_avatar_update.setVisible(true);
        this.lnk_confirm_avatar_update.setVisible(true);
        this.lnk_modify_avatar.setVisible(false);

    }

    public String uploadAvatar()
    {
        if (this.file==null)
            return null;

        String filename=this.file.getName();
        String originalFilename = this.file.getName().substring(0,filename.lastIndexOf("."));
        String extension = this.file.getName().substring(filename.lastIndexOf("."));
        String time=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
        String avatar=originalFilename+time+extension;
        System.out.println(avatar);
        Path dest = Paths.get("public/uploads/"+avatar);
        try {
            Files.copy(Path.of(this.file.getPath()),dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "uploads/"+avatar;
    }

    public void cancelAvatarUpdate()
    {
        this.file=null;
        this.img_avatar.setImage(new Image("file:public/"+this.user.getAvatar()));
        this.lnk_cancel_avatar_update.setVisible(false);
        this.lnk_confirm_avatar_update.setVisible(false);
        this.lnk_modify_avatar.setVisible(true);
    }


    public void updateAvatar()
    {
        this.user.setAvatar(this.uploadAvatar());
        UserServices us = new UserServices();
        us.updateAvatar(this.user);
        this.lbl_error_avatar.setText("Avatar mis à jour");
        this.lbl_error_avatar.setTextFill(Color.GREEN);
        this.lnk_cancel_avatar_update.setVisible(false);
        this.lnk_confirm_avatar_update.setVisible(false);
        this.lnk_modify_avatar.setVisible(true);
    }


    public void goBack()
    {
        SceneLoader.loadScene("UserGui/ShowUsers.fxml",this.btn_go_back);
    }

    public void showDashboard()
    {
        SceneLoader.loadScene("AdminGui/Dashboard.fxml",this.lnk_dashboard_home);
    }




}














