package Templates.RatingGui;

import Entities.camping;
import Entities.offre;
import Services.Camping_c;
import Services.Offre_c;
import Templates.DashboardspeakyGui.Dashboard;
import Templates.QrcodeGui.AfficherQrcode;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rating implements Initializable {
    @FXML
    public JFXListView<camping> Listview;
    @FXML
    public AnchorPane ecran;
    @FXML
    public JFXButton save;

    ObservableList<camping> campingObservableList= FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.save.setOnAction(e1 ->{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../MapGui/Afficher_map.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
            }
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Map");
            stage.show();
        });
        Actualiser();
    }
    public void Actualiser() {
        this.Listview.setStyle("-fx-control-inner-background: #2a2929 ;"+
                "-fx-control-inner-background-alt: derive(-fx-control-inner-background, 50%);");
        Camping_c c = new Camping_c();
        Offre_c c1=new Offre_c();
        Timeline timeline = new Timeline(
        new KeyFrame(Duration.ZERO, event -> {
            this.Listview.getItems().clear();
            this.Listview.getSelectionModel().clearSelection();
        try {
            for(camping l:c.result()) {
                if(l.getOffre_id_id()!=0)
                    for(offre l1:c1.result()){
                        if(l.getOffre_id_id()==l1.getId())
                            l.setFulloffre("Nombre des places: "+l1.getNombre_places()+"\n"+"Date debut: "+l1.getDate_debut()+"\n"+"Date fin: "+l1.getDate_fin()+"\n"+"Prix: "+l1.getPrix());
                    }
                else
                    l.setFulloffre("Pas d'offre pour le moment");
                Image im = new Image("file:public/uploads/"+l.getImage_camping(), 230, 346, true, false);
                JFXButton add_rate=new JFXButton("Rate");
                add_rate.setId("ratebutton_r");

                Image imageqr = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/qrcode.png")));
                JFXButton display_qr=new JFXButton("Offre");
                display_qr.setId("qrcode");
                display_qr.setGraphic(new ImageView(imageqr));

                ByteArrayOutputStream out = QRCode.from(l.getFulloffre()).to(ImageType.PNG).withSize(200, 200).stream();
                ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
                Image image = new Image(in);

                if(l.getRating()==null) {
                    l.setRating(new org.controlsfx.control.Rating());
                    l.getRating().setPartialRating(true);
                    l.getRating().setMouseTransparent(true);
                    l.getRating().setRating(l.getAverage_rating()/l.getRating_camping());
                }
                l.setAdd_rate(add_rate);
                l.setQrcode(display_qr);
                campingObservableList.add(new camping(l.getId(),l.getQrcode(),l.getLocalisation_camping(),l.getDescription_camping(),l.getType_camping(),new ImageView(im),l.getOffre_id_id(),l.getRating_camping(),l.getAverage_rating(),l.getRating(),l.getAdd_rate()));
                this.Listview.setCellFactory(listView -> new ListCell<camping>() {
                    @Override
                    protected void updateItem(camping piece, boolean empty) {
                        super.updateItem(piece, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            VBox VBox = new VBox(10);
                            VBox.setAlignment(Pos.CENTER);
                            if(piece.getRating_camping()!=0)
                            VBox.getChildren().addAll(piece.getQrcode(),new Label("Localisation: "+piece.getLocalisation_camping()),new Label("Description: "+piece.getDescription_camping()),new Label("Type: "+piece.getType_camping()),piece.getListview_image(),piece.getRating(),new Label(Math.floor(piece.getAverage_rating()/piece.getRating_camping()*100)/100+" ("+piece.getRating_camping()+")"),piece.getAdd_rate());
                            else
                                VBox.getChildren().addAll(piece.getQrcode(),new Label("Localisation: "+piece.getLocalisation_camping()),new Label("Description: "+piece.getDescription_camping()),new Label("Type: "+piece.getType_camping()),piece.getListview_image(),piece.getRating(),new Label(0+" ("+piece.getRating_camping()+")"),piece.getAdd_rate());
                            setGraphic(VBox);
                        }
                    }
                });
                add_rate.setOnAction(e ->{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Ajouter_rate.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    AjouterRate ajouterrate = loader.getController();
                    ajouterrate.idrate(l.getId(),l.getRating_camping());
                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setTitle("Rate");
                    stage.show();
                });
                display_qr.setOnAction(e ->{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../QrcodeGui/Afficher_qrcode.fxml"));
                    try {
                        loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    AfficherQrcode afficherQrcode = loader.getController();
                    afficherQrcode.Addqrcode(image);
                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setTitle("Qrcode");
                    stage.show();
                });
                this.Listview.setItems(campingObservableList);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        }),new KeyFrame(Duration.seconds(2))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void backdashboard(ActionEvent actionEvent) throws IOException {
        Dashboard Dashboard = new Dashboard();
        Dashboard.returndashboard(actionEvent);
    }
}
