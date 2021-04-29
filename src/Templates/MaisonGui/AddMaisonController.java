package Templates.MaisonGui;

import Entities.Maison;
import Entities.User;
import Services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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



public class AddMaisonController implements Initializable {
    private File file=null;

    ObservableList<String> HouseType = FXCollections.observableArrayList("Villa","Appartement","Autre");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    txt_type.setItems(HouseType);
    }
    @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_adress;

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_prix;

    @FXML
    private TextField txt_chambre;

    @FXML
    private ChoiceBox<String> txt_type;
    @FXML
    private ImageView img_avatar;

    @FXML
    private Button btn_upload;


    @FXML
    private Button btn_Insert;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;
    @FXML
    private Button btn_back;


    @FXML
    void AddMaison(ActionEvent event) {
        String nom = txt_nom.getText();
        String adresse= txt_adress.getText();
        String description = txt_description.getText();
        Integer prix= Integer.valueOf(txt_prix.getText());
        //String image=this.uploadAvatar();
        String avatar=this.uploadAvatar();
        Integer nb_chambre = Integer.valueOf(txt_chambre.getText());
        String type= txt_type.getValue();


            Maison m = new Maison(adresse,nom,description,type,nb_chambre,prix,avatar);

            MaisonService ms = new MaisonService();
            ms.addMaison(m);
            //MailServices.sendMail(m.getEmail());
           // SmsServices.sendSMS(this.inscription_phone_number.getText());
           // SceneLoader.loadScene("LoginGui/Login.fxml",this.btn_back);




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

    @FXML
    void selectAvatar(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo");
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg,*.png,*.jpeg,*.bmp)", "*.jpg","*.jpeg","*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);


        //Show save file dialog

        this.file  = fileChooser.showOpenDialog(this.txt_nom.getScene().getWindow());
        if (this.file!=null)
        {
            Image img = new Image(this.file.getAbsoluteFile().toURI().toString());
            this.img_avatar.setImage(img);
            this.uploadAvatar();
        }

    }
    @FXML
    void backscreen (){
        SceneLoader.loadScene("MaisonGui/ShowMaison.fxml",this.btn_back);
    }

}
