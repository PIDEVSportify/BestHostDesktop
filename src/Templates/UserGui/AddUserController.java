package Templates.UserGui;

import Entities.User;
import Services.MailServices;
import Services.SceneLoader;
import Services.SmsServices;
import Services.UserServices;
import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUserController implements Initializable {

    public ImageView img_avatar;

    public Button btn_back;
    public TextField inscription_phone_number;
    public TextField inscription_cin;
    public Label lbl_error;

    public Pane pane_avatar;
    public Webcam webcam = Webcam.getDefault();
    public Button btn_open_webcam;
    public Button btn_take_picture;


    private File file=null;
    private VideoFeed videoFeed;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private TextField inscription_first_name;

    @FXML
    private TextField inscription_last_name;

    @FXML
    private PasswordField inscription_password;

    @FXML
    private TextField inscription_email;

    @FXML
    private Button inscription_submit;

    @FXML
    void AddUser(ActionEvent event) {
        String first_name = inscription_first_name.getText();
        String last_name= inscription_last_name.getText();
        String email = inscription_email.getText();
        String password=inscription_password.getText();

        String cin = inscription_cin.getText();
        String phone_number= inscription_phone_number.getText();

        if (this.validFields(first_name,last_name,email,password,cin,phone_number))
        {
            String avatar=this.uploadAvatar();
            User u = new User(first_name,last_name,email,password,avatar,cin);

            UserServices us = new UserServices();
            us.addUser(u);
            MailServices.sendMail(u.getEmail());
            SmsServices.sendSMS(this.inscription_phone_number.getText());
            SceneLoader.loadScene("LoginGui/Login.fxml",this.btn_back);

        }


    }
    @FXML
    void selectAvatar()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg,*.png,*.jpeg,*.bmp)", "*.jpg","*.jpeg","*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);


        //Show save file dialog

        this.file  = fileChooser.showOpenDialog(this.inscription_email.getScene().getWindow());
        if (this.file!=null)
        {
             Image img = new Image(this.file.getAbsoluteFile().toURI().toString());
             this.img_avatar.setImage(img);
             this.uploadAvatar();
        }

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

    private boolean validFields(String first_name, String last_name , String email, String password,String cin,String phone_number)
    {
        boolean valid= true;
            if (first_name.isEmpty() || first_name.isBlank())
            {
                valid=false;
                this.inscription_first_name.setStyle("-fx-border-color:red");

            }
            else
            {
                this.inscription_first_name.setStyle("-fx-border-color:#44F219");
            }

            if (last_name.isEmpty() || last_name.isBlank())
            {
                valid=false;
                this.inscription_last_name.setStyle("-fx-border-color:red");

            }
            else
            {
                this.inscription_last_name.setStyle("-fx-border-color:#44F219");
            }
        if (email.isEmpty()||email.isBlank())
        {
            valid=false;
            this.inscription_email.setStyle("-fx-border-color:red");

        }
        else
        {
            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(email);
            if (mat.matches()) {
                this.inscription_email.setStyle("-fx-border-color:#44F219");
            }
            else {
                valid = false;
                this.inscription_email.setStyle("-fx-border-color:red");
            }
        }

        if (password.isEmpty()||password.isBlank())
        {
            valid=false;
            this.inscription_password.setStyle("-fx-border-color:red");


        }
        else if (password.length()<8)
        {
            valid=false;
            this.inscription_password.setStyle("-fx-border-color:red");
            this.lbl_error.setVisible(true);
            this.lbl_error.setText("Le mot de passe doit être supérieur à 8 caractères");

        }
        else
        {
            this.inscription_password.setStyle("-fx-border-color:#44F219");
            this.lbl_error.setVisible(false);
        }

        if (cin.isEmpty()|| cin.isBlank() || cin.length()!=8)
        {
            this.inscription_cin.setStyle("-fx-border-color:red");
        }
        else {
            this.inscription_cin.setStyle("-fx-border-color:#44F219");
        }

        if(phone_number.isBlank() ||  phone_number.length() !=8  )
        {
            this.inscription_phone_number.setStyle("-fx-border-color:red");
        }
        else {
            this.inscription_phone_number.setStyle("-fx-border-color:#44F219");
        }


        return valid;
    }


    public void getBack()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../LoginGui/Login.fxml"));
        try {
            Parent root = loader.load();
            this.btn_back.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


   class   VideoFeed extends  Thread
    {
        private  boolean isRunning=true;

        @Override
        public void run()
        {

            while(isRunning)
            {
                    Image img = SwingFXUtils.toFXImage(webcam.getImage(),null);
                    img_avatar.setImage(img);

            }
        }
        @Override
        public void interrupt()
        {
            isRunning=false;
        }
    }

    public void openWebcam()
    {
        this.btn_open_webcam.setVisible(false);
        this.btn_take_picture.setVisible(true);
        this.videoFeed=new VideoFeed();
        webcam.open();
        this.videoFeed.start();

    }
    public void takePicture()
    {
        this.btn_take_picture.setVisible(false);
        this.btn_open_webcam.setVisible(true);
        this.videoFeed.interrupt();

        Image img = SwingFXUtils.toFXImage(webcam.getImage(),null);
        webcam.close();
        this.img_avatar.setImage(img);
        try {
            BufferedImage bufferedImage=  SwingFXUtils.fromFXImage(img,null);

            SecureRandom sr = new SecureRandom();
            byte bytes[] = sr.generateSeed(10);
            sr.nextBytes(bytes);
            String filename=  bytes.toString()+".jpg"  ;
            File destination = new File("public/tmp/"+filename);
            ImageIO.write(bufferedImage, "PNG", destination);
            this.file = destination;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }


}
