package Core;

import Entities.camping;
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

    public void deleteoffre() throws SQLException {
        Camping_c c=new Camping_c();
        String full="";
        if(!c.checkvalidity(this.id_delete).isEmpty())
        full = c.checkvalidity(this.id_delete).stream().map(camping::toString).reduce((e1 , e2) -> e1+"\n\n"+e2).get();

        if(!full.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Warning");
            alert.setContentText("The offer is already used by some Sites:\n\n"+full);
            alert.setResizable(true);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                duplicatealert();
            } else
                alert.close();
        }else
        {
            duplicatealert();
        }
    }

    private void duplicatealert() {
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("Are you ok with this ?");
        alert2.setContentText("Are you sure you wish to remove this offer ?");
        Optional<ButtonType> result1 = alert2.showAndWait();
        if (result1.get() == ButtonType.OK) {
            offre offre = new offre();
            offre.setId(this.id_delete);
            Offre_c c1 = new Offre_c();
            try {
                c1.Delete(offre);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else
            alert2.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
