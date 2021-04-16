package Core;

import Entities.offre;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupprimerOffre implements Initializable {
    private int id_delete;

    public SupprimerOffre(int id_delete) {
        this.id_delete = id_delete;
    }

    public void deleteoffre() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you ok with this ?");
        alert.setContentText("Are you sure you wish to remove this offer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            offre offre = new offre();
            offre.setId(this.id_delete);
            Offre_c c=new Offre_c();
            try {
                c.Delete(offre);
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
