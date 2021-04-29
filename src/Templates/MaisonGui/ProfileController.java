package Templates.MaisonGui;

import Entities.Maison;

import Services.MaisonService;
import Services.SceneLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    private static Maison maison;


    public ImageView img_avatar;
    @FXML
    private Button btn_go_back;

    @FXML
    private Button btn_update_parameters;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.txt_nom.setText(this.maison.getNom());
        this.txt_adress.setText(this.maison.getAdresse());
        this.txt_description.setText(this.maison.getDescription());
       this.txt_prix.setText(String.valueOf(this.maison.getPrix()));
       this.txt_chambre.setText(String.valueOf(this.maison.getNombre_chambres()));
      try {
            this.img_avatar.setPreserveRatio(true);
            Image img = new Image("file:public/"+this.maison.getAvatar(),200,155,false,false);
            this.img_avatar.setImage(img);
        } catch (Exception e) {
            e.printStackTrace();
        }


       // this.lnk_confirm_avatar_update.setVisible(false);
       // this.lnk_modify_avatar.setVisible(true);

    }


    public void updateGeneralSettings()
    {

            Maison maison = new Maison(this.txt_adress.getText(),this.txt_nom.getText(),this.txt_description.getText());
            MaisonService ms = new MaisonService();
            ms.modifyMaison(maison);



    }
    public void goBack()
    {
        SceneLoader.loadScene("MaisonGui/ShowMaison.fxml",this.btn_go_back);
    }

       public void setMaison(Maison maison) {
        ProfileController.maison = maison;
    }



}
