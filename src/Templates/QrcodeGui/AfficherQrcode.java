package Templates.QrcodeGui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AfficherQrcode {
    @FXML
    public ImageView imageqrcode;

    public void Addqrcode(Image image){
        this.imageqrcode.setImage(image);
        this.imageqrcode.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");
    }
}
