package Core;

import Entities.offre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Afficheroffers implements Initializable {
    @FXML
    public TableView<offre> Tableoffers;
    @FXML
    public TableColumn<offre, Integer> IDColumn;
    @FXML
    public TableColumn<offre,Integer> NombredesplacesColumn;
    @FXML
    public TableColumn<offre,String> DatedébutColumn;
    @FXML
    public TableColumn<offre,String> DatefinColumn;
    @FXML
    public TableColumn<offre,Integer> PrixColumn;
    @FXML
    public Button updateButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button insertButton;
    @FXML
    public Button refreshButton;
    @FXML
    public Button Dashboardbutton;

    ObservableList<offre> offerslist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicBoolean verif= new AtomicBoolean(false);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NombredesplacesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_places"));
        DatedébutColumn.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        DatefinColumn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        PrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        IDColumn.setVisible(false);
        Actualiser();
        Tableoffers.setOnMousePressed(e1 -> {
            updateButton.setOnAction(e -> {
                offre offermodify = Tableoffers.getSelectionModel().getSelectedItem();
                if(offermodify == null)
                    verif.set(true);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Ajouter_offre.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(Afficheroffers.class.getName()).log(Level.SEVERE, null, ex);
                }
                AjouterOffre ajouteroffre = loader.getController();
                ajouteroffre.setUpdate_dynamically(true);
                ajouteroffre.setTitle_offre("Modifier Offre");
                if(!verif.get()) {
                    assert offermodify != null;
                    ajouteroffre.addvalues(offermodify.getId(), offermodify.getNombre_places(), offermodify.getDate_debut(), offermodify.getDate_fin(), offermodify.getPrix());
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
                }
                else
                    Select_row();
                verif.set(false);
            });
            deleteButton.setOnAction(e2 ->{
                offre offerdelete = Tableoffers.getSelectionModel().getSelectedItem();
                if(offerdelete == null)
                    verif.set(true);
                if(!verif.get()) {
                    assert offerdelete != null;
                    SupprimerOffre supprimerOffre = new SupprimerOffre(offerdelete.getId());
                    try {
                        supprimerOffre.deleteoffre();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Actualiser();
                }
                else
                    Select_row();
                verif.set(false);
            });
        });
        updateButton.setOnAction(e ->{
           Select_row();
        });
        deleteButton.setOnAction(e ->{
            Select_row();
        });
    }

    public void AjouterOffer(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Ajouter_offre.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Afficheroffers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ActualiserOffer(ActionEvent actionEvent) {
        Actualiser();
    }

    public void Actualiser() {
        Offre_c c = new Offre_c();
        try {
            offerslist.clear();
            Tableoffers.getSelectionModel().clearSelection();
            for(offre l:c.result()) {
                offerslist.add(new offre(l.getId(),l.getNombre_places(),l.getDate_debut(),l.getDate_fin(),l.getPrix()));
                Tableoffers.setItems(offerslist);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Select_row(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("Please select a row");
        alert.showAndWait();
    }

    public void Dushboardoffer(ActionEvent actionEvent) throws IOException {
        Dashboard Dashboard = new Dashboard();
        Dashboard.returndashboard(actionEvent);
    }
}
