package Templates.SiteGui;

import Entities.camping;
import Entities.offre;
import Services.Camping_c;
import Services.Offre_c;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AjouterSite implements Initializable {
    public static class Nooffer{
        private final String no="No offer";
        public Nooffer(){}
    }
    @FXML
    public Text Title_site;
    @FXML
    private ComboBox<String> offre_camping;
    @FXML
    private ComboBox<Object> full_offre_camping;
    @FXML
    private TextField local_camping;
    @FXML
    private TextField desc_camping;
    @FXML
    private TextField ty_camping;
    @FXML
    private Button button_browser;
    @FXML
    private ImageView im_camping;
    @FXML
    private Button save;
    @FXML
    private Button clean;
    @FXML
    private File file;
    @FXML
    private Image image;

    private int save_id;

    private boolean update_dynamically;

    public boolean isUpdate_dynamically() {
        return update_dynamically;
    }

    public void setUpdate_dynamically(boolean update_dynamically) {
        this.update_dynamically = update_dynamically;
    }

    public void setTitle_site(String Text) {
        this.Title_site.setText(Text);
    }

    ObservableList<Object> siteslist= FXCollections.observableArrayList();
    ObservableList<String> full_siteslist= FXCollections.observableArrayList();

    public void savecamping(ActionEvent actionEvent) throws SQLException {
        Object offercamping =  full_offre_camping.getSelectionModel().getSelectedItem();
        if(!this.local_camping.getText().isEmpty() && !this.desc_camping.getText().isEmpty() && !this.ty_camping.getText().isEmpty() && !isEmpty(this.im_camping)) {
            Camping_c c = new Camping_c();
            Nooffer no = new Nooffer();
            camping camping;
                if (isUpdate_dynamically()) {
                    if(offercamping.equals(no.no))
                    camping = new camping(this.save_id, 0, this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.image.getUrl());
                    else
                        camping = new camping(this.save_id, ((offre) offercamping).getId(), this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.image.getUrl());
                    c.Update(camping);
                }
                else {
                    if (offercamping.equals(no.no))
                        camping = new camping(0, this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.file.getAbsoluteFile().toURI().toString());
                    else
                        camping = new camping(((offre) offercamping).getId(), this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.file.getAbsoluteFile().toURI().toString());
                    c.Add(camping);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                if(isUpdate_dynamically())
                    alert.setContentText("successfully modified.");
                else
                    alert.setContentText("You have successfully added a new offer.");
                alert.showAndWait();

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill the required fields (Id, localisation, description, type, image).");
            alert.showAndWait();
        }
    }

    public void cleancamping(ActionEvent actionEvent) {
        if(!isUpdate_dynamically())
            this.save_id=0;
        this.local_camping.clear();
        this.desc_camping.clear();
        this.ty_camping.clear();
        this.im_camping.setImage(null);
        this.full_offre_camping.getSelectionModel().selectFirst();
        this.offre_camping.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        camping c1=new camping();
        Nooffer o = new Nooffer();
        this.full_siteslist.add(0,"No offre");
        this.offre_camping.setItems(this.full_siteslist);
        this.offre_camping.getSelectionModel().selectFirst();
        this.siteslist.add(0,o.no);
        this.full_offre_camping.setItems(this.siteslist);
        this.full_offre_camping.getSelectionModel().selectFirst();
        Offre_c c = new Offre_c();
        try {
            for(offre l:c.result()) {
                c1.setFulloffre("Nombre des places: "+l.getNombre_places()+"\n"+"Date début: "+l.getDate_debut()+"\n"+"Date fin: "+l.getDate_fin()+"\n"+"Prix: "+l.getPrix());
                this.siteslist.add(new offre(l.getId(),l.getNombre_places(),l.getDate_debut(),l.getDate_fin(),l.getPrix()));
                this.full_siteslist.add(c1.getFulloffre());
                this.full_offre_camping.setItems(this.siteslist);
                this.offre_camping.setItems(this.full_siteslist);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void uploadimage(ActionEvent actionEvent) {
        final  FileChooser fileChooser=new FileChooser();
            this.file = fileChooser.showOpenDialog(null);
            if (file != null) {
                image = new Image(file.getAbsoluteFile().toURI().toString(), this.im_camping.getFitWidth(), this.im_camping.getFitHeight(), true, false);
                this.im_camping.setImage(image);
                this.im_camping.setPreserveRatio(true);
                this.im_camping.setSmooth(true);
            }
    }

    public static boolean isEmpty(ImageView imageView) {
        Image image = imageView.getImage();
        return image == null || image.isError();
    }

    public void addvalues(int a,int b,String c,String d,String e,String f) throws SQLException {
        Offre_c c1 = new Offre_c();
        offre offre=new offre();
        if(b!=0) {
            for (offre l : c1.result()) {
                if (l.getId() == b)
                    offre = new offre(l.getId(), l.getNombre_places(), l.getDate_debut(), l.getDate_fin(), l.getPrix());
            }
            this.full_offre_camping.getSelectionModel().select(offre);
            this.offre_camping.getSelectionModel().select(this.full_offre_camping.getSelectionModel().getSelectedIndex());
        }
        else{
            this.full_offre_camping.getSelectionModel().select(b);
            this.offre_camping.getSelectionModel().select(b);
        }
        this.save_id=a;
        this.local_camping.setText(String.valueOf(c));
        this.desc_camping.setText(String.valueOf(d));
        this.ty_camping.setText(String.valueOf(e));
        image = new Image(f, this.im_camping.getFitWidth(), this.im_camping.getFitHeight(), true, true);
        this.im_camping.setImage(image);
        this.im_camping.setPreserveRatio(true);
    }

    public void Mouseclique(ActionEvent actionEvent) {
        this.full_offre_camping.getSelectionModel().select(this.offre_camping.getSelectionModel().getSelectedIndex());
    }



}
