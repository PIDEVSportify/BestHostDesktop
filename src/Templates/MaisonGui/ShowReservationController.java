package Templates.MaisonGui;

import Entities.Maison;
import Entities.Reservation;
import Services.ReservationServices;
import Services.SceneLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowReservationController implements Initializable {

    @FXML
    private TableView<Reservation> tableView;

    @FXML
    private TableColumn<Reservation, Integer> txt_id;

    @FXML
    private TableColumn<Reservation, String> txt_nom;

    @FXML
    private TableColumn<Reservation, String> txt_numero;

    @FXML
    private TableColumn<Reservation, String> txt_email;

    @FXML
    private Button back;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_id.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id"));
        txt_id.setMinWidth(100);

        txt_email.setCellValueFactory(new PropertyValueFactory<Reservation,String>("email_client"));
        txt_email.setMinWidth(100);

        txt_numero.setCellValueFactory(new PropertyValueFactory<Reservation,String>("numero_client"));
        txt_numero.setMinWidth(100);

        txt_nom.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nom_client"));
        txt_nom.setMinWidth(100);

        this.loadReservation();
    }


    private void loadReservation() {

        ReservationServices rc = new ReservationServices();

        tableView.setItems(rc.showReservation());
    }
    public void goBack()
    {



        SceneLoader.loadScene("MaisonGui/ShowMaison.fxml",this.back);

    }



}
