package Templates.RatingGui;

import Entities.offre;
import Services.Camping_c;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterRate implements Initializable {

    @FXML
    public Text Title_rate;
    @FXML
    private JFXButton rate;
    @FXML
    private Rating ratebutton;

    public int compteur=0;

    private int Id_camping;

    private int Rate_camping;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void detect(MouseEvent mouseEvent) {
        if(ratebutton.getRating()==1)
            compteur++;
        if(compteur>1)
            ratebutton.setRating(0);
        if(ratebutton.getRating()!=1)
            compteur=0;
    }

    public void saverate(ActionEvent actionEvent) throws IOException {
        Camping_c c=new Camping_c();
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText("Success");
        alert1.setContentText("You have successfully added a new Rate.");
        if(ratebutton.getRating()==0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Warning");
            alert.setContentText("Zero rating");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    c.Updaterate(this.Id_camping,this.ratebutton.getRating(),this.Rate_camping);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                alert1.showAndWait();
            } else
                alert.close();
        }
        else
        {
            try {
                c.Updaterate(this.Id_camping,this.ratebutton.getRating(),this.Rate_camping);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            alert1.showAndWait();
        }
        if(this.Rate_camping==0)
        this.Rate_camping+=1;
    }

    public void idrate(int id,int rate){
        this.Id_camping=id;
        this.Rate_camping=rate;
    }
}
