package Templates.SiteGui;

import Entities.camping;
import Entities.offre;
import Services.Camping_c;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupprimerSite implements Initializable {
    private int id_delete;

    public SupprimerSite(int id_delete) {
        this.id_delete = id_delete;
    }

    public void deletesite() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you ok with this ?");
        alert.setContentText("Are you sure you wish to remove this site camping ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            camping camping = new camping();
            camping.setId(this.id_delete);
            Camping_c c=new Camping_c();
            try {
                c.Delete(camping);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else
            alert.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
