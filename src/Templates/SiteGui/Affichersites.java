package Templates.SiteGui;

import Entities.camping;
import Entities.offre;
import Services.Camping_c;
import Services.Offre_c;
import Templates.DashboardspeakyGui.Dashboard;
import Templates.OfferGui.Afficheroffers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class Affichersites implements Initializable {
    @FXML
    public Button insertButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button deleteButton;
    @FXML
    public TableView<camping> Tablesites;
    @FXML
    public TableColumn<camping,Integer> IDColumn;
    @FXML
    public TableColumn<camping,String> OffreColumn;
    @FXML
    public TableColumn<camping,String> LocalisationColumn;
    @FXML
    public TableColumn<camping,String> DescriptionColumn;
    @FXML
    public TableColumn<camping,String> TypeColumn;
    @FXML
    public TableColumn<camping, Image> ImageColumn;
    @FXML
    public TableColumn<camping,Double> RatingColumn;
    @FXML
    public ImageView Imagecamping;
    @FXML
    public Button refreshbutton;
    @FXML
    public Button Dashboardbutton;

    ObservableList<camping> siteslist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AtomicBoolean verif= new AtomicBoolean(false);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        OffreColumn.setCellValueFactory(new PropertyValueFactory<>("fulloffre"));
        LocalisationColumn.setCellValueFactory(new PropertyValueFactory<>("localisation_camping"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description_camping"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type_camping"));
        ImageColumn.setCellFactory(param -> {
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(200);
            imageview.setFitWidth(250);
            TableCell<camping, Image> cell = new TableCell<camping, Image>() {
                public void updateItem(Image item, boolean empty) {
                    if (item != null) {
                       Image image = new Image(item.getUrl(), imageview.getFitWidth(), imageview.getFitHeight(), true, false);
                        imageview.setImage(image);
                       imageview.setPreserveRatio(true);
                       imageview.setSmooth(true);
                    }
                    else {
                        imageview.setImage(null);
                    }
                }
            };
            cell.setGraphic(imageview);
            return cell;
        });
       ImageColumn.setCellValueFactory(new PropertyValueFactory<camping, Image>("image_site"));
       RatingColumn.setCellValueFactory(new PropertyValueFactory<camping, Double>("ratecamping"));
       IDColumn.setVisible(false);
       Actualiser();
       Tablesites.setOnMousePressed(e1 ->{
           updateButton.setOnAction(e ->{
               camping campingmodify = Tablesites.getSelectionModel().getSelectedItem();
               if(campingmodify == null)
                   verif.set(true);
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("Ajouter_site.fxml"));
               try {
                   loader.load();
               } catch (IOException ex) {
                   Logger.getLogger(Afficheroffers.class.getName()).log(Level.SEVERE, null, ex);
               }
               AjouterSite ajoutersite = loader.getController();
               ajoutersite.setUpdate_dynamically(true);
               ajoutersite.setTitle_site("Modifier Site");
               if(!verif.get()) {
                   assert campingmodify != null;
                   try {
                       ajoutersite.addvalues(campingmodify.getId(), campingmodify.getOffre_id_id(), campingmodify.getLocalisation_camping(), campingmodify.getDescription_camping(), campingmodify.getType_camping(),campingmodify.getImage_site().getUrl());
                   } catch (SQLException throwables) {
                       throwables.printStackTrace();
                   }
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
               camping sitedelete = Tablesites.getSelectionModel().getSelectedItem();
               if(sitedelete == null)
                   verif.set(true);
               if(!verif.get()) {
                   assert sitedelete != null;
                   SupprimerSite supprimersite = new SupprimerSite(sitedelete.getId());
                   supprimersite.deletesite();
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

    public void ActualiserSite(ActionEvent actionEvent) { Actualiser(); }

    public void Actualiser() {
        Camping_c c = new Camping_c();
        Offre_c c1=new Offre_c();
        try {
            siteslist.clear();
            Tablesites.getSelectionModel().clearSelection();
            for(camping l:c.result()) {
                if(l.getOffre_id_id()!=0)
                for(offre l1:c1.result()){
                    if(l.getOffre_id_id()==l1.getId())
                        l.setFulloffre("Nombre des places: "+l1.getNombre_places()+"\n"+"Date début: "+l1.getDate_debut()+"\n"+"Date fin: "+l1.getDate_fin()+"\n"+"Prix: "+l1.getPrix());
                }
                else
                    l.setFulloffre("Pas d'offre\npour le moment");
                Image im = new Image(l.getImage_camping(), this.Imagecamping.getFitWidth(), this.Imagecamping.getFitHeight(), true, true);
                if(l.getRating_camping()!=0)
                siteslist.add(new camping(l.getId(),l.getFulloffre(),l.getLocalisation_camping(),l.getDescription_camping(),l.getType_camping(),im,l.getOffre_id_id(),Math.floor(l.getAverage_rating()/l.getRating_camping()*100)/100));
                else
                    siteslist.add(new camping(l.getId(),l.getFulloffre(),l.getLocalisation_camping(),l.getDescription_camping(),l.getType_camping(),im,l.getOffre_id_id(),0));
                Tablesites.setItems(siteslist);
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

    public void AjouterSite(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Ajouter_site.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Afficheroffers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Dashboardsite(ActionEvent actionEvent) throws IOException {
        Dashboard Dashboard = new Dashboard();
        Dashboard.returndashboard(actionEvent);
    }
}
