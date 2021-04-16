package Core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class Rating implements Initializable {
    @FXML
    public org.controlsfx.control.Rating rating;
    public int compteur=0;
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void detect(MouseEvent mouseEvent) {
        if(rating.getRating()==1)
            compteur++;
        if(compteur>1)
            rating.setRating(0);
        if(rating.getRating()!=1)
            compteur=0;
    }
}
