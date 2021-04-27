package Templates.MaisonGui;

import Entities.Maison;

import Entities.User;
import Services.MaisonService;

import Services.SceneLoader;
import Services.UserServices;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ShowMaisonController   implements Initializable {

    @FXML
    public TableColumn image_col;
    @FXML
    private TableView<Maison> tableView;

    @FXML
    private TableColumn<Maison, String> nom_col;

    @FXML
    private TableColumn<Maison, String> adresse_col;

    @FXML
    private TableColumn<Maison, String> description_col;

    @FXML
    private TableColumn<Maison, Integer> prix_col;

    @FXML
    private TableColumn<Maison, String> type_col;

    @FXML
    private TableColumn<Maison, Integer> chambre_col;



    @FXML
    private TextField txt_search_box;
    @FXML
    private Button btn_add_redirect;
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_reserver;

    @FXML
    private Button btn_modify;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nom_col.setCellValueFactory(new PropertyValueFactory<Maison,String>("nom"));
        nom_col.setMinWidth(100);

        adresse_col.setCellValueFactory(new PropertyValueFactory<Maison,String>("adresse"));
        adresse_col.setMinWidth(100);

        description_col.setCellValueFactory(new PropertyValueFactory<Maison,String>("description"));
        description_col.setMinWidth(100);

        prix_col.setCellValueFactory(new PropertyValueFactory<Maison,Integer>("prix"));
        prix_col.setMinWidth(100);

        type_col.setCellValueFactory(new PropertyValueFactory<Maison,String>("type"));
        type_col.setMinWidth(100);

        chambre_col.setCellValueFactory(new PropertyValueFactory<Maison,Integer>("nombre_chambres"));
        chambre_col.setMinWidth(100);

        image_col.setCellValueFactory(new PropertyValueFactory<Maison, ImageView>("pic"));
        image_col.setMaxWidth(200);




        this.loadMaison();


        FilteredList<Maison> filteredData = new FilteredList<>(this.tableView.getItems());
        SortedList<Maison> sortableData = new SortedList<Maison>(filteredData);
        sortableData.comparatorProperty().bind(this.tableView.comparatorProperty());
        this.txt_search_box.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(createPredicate(newValue))
        );

        this.tableView.setItems(sortableData);


    }


    public void ModifyMaison()
    {
        Maison m = this.tableView.getSelectionModel().getSelectedItem();
        if (m!=null)
        {
            ProfileController pc = new ProfileController();
            pc.setMaison(m);
            SceneLoader.loadScene("MaisonGui/Profile.fxml",this.btn_modify);
        }
    }

    private Predicate<? super Maison> createPredicate(String searchText) {
        return maison -> {
            if (searchText == null || searchText.isEmpty()) return true;
            return searchTable(maison, searchText);
        };
    }

    private void loadMaison() {

        MaisonService ms = new MaisonService();
        tableView.setItems(ms.showMaison());
    }


    private boolean searchTable(Maison maison, String searchText){

        if (maison.getNom()!=null )
        {
            return  maison.getNom().toLowerCase().contains(searchText.trim().toLowerCase());
        };
        return (maison.getAdresse().trim().toLowerCase().contains(searchText.toLowerCase())) ||
                (maison.getNom().concat(maison.getAdresse()).replaceAll("\\s+","").toLowerCase().contains(searchText.replaceAll("\\s+","").toLowerCase()));

    }


    public void deleteMaison()
    {
        MaisonService ms = new MaisonService();
        Maison m = this.tableView.getSelectionModel().getSelectedItem();
        if (m!=null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous êtes sur le point de supprimer une maison");
            alert.setContentText("Êtes vous sûr ?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                ms.deleteMaison(m.getNom());

            }
            this.loadMaison();

        }
    }

    public void ReserverMaison()
    {
        Maison m = this.tableView.getSelectionModel().getSelectedItem();
        if (m!=null)
        {
            Templates.MaisonGui.ReserverController rc= new Templates.MaisonGui.ReserverController();
            rc.setMaison(m);
            SceneLoader.loadScene("MaisonGui/Reserver.fxml",this.btn_reserver);
        }
    }

/*    public void ReserverMaison()
    {



            SceneLoader.loadScene("MaisonGui/Reserver.fxml",this.btn_reserver);

    }
*/
    public void redirect_to_add(){
        SceneLoader.loadScene("MaisonGui/AddMaison.fxml",this.btn_add_redirect);
    }

}
