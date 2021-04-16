package Core;

import Entities.camping;
import Entities.offre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private TextField Id_camping;
    @FXML
    private ComboBox<Object> offre_camping;
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

    private int check_id;

    public int getCheck_id() {
        return check_id;
    }

    public void setCheck_id(int check_id) {
        this.check_id = check_id;
    }

    private boolean update_dynamically;

    public void setId_camping(boolean b) {
        this.Id_camping.setEditable(b);
    }

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

    public void savecamping(ActionEvent actionEvent) throws SQLException {
        boolean check=false;
        Object offercamping =  offre_camping.getSelectionModel().getSelectedItem();
        if(this.Id_camping.getText().matches("\\d+") && !this.local_camping.getText().isEmpty() && !this.desc_camping.getText().isEmpty()
                && !this.ty_camping.getText().isEmpty() && !isEmpty(this.im_camping)) {
            setCheck_id(Integer.parseInt(this.Id_camping.getText()));
            Camping_c c = new Camping_c();
            Nooffer no = new Nooffer();
            camping camping;
            Predicate<camping> p1 = o -> o.getId() == getCheck_id();
            if (c.result().stream().anyMatch(p1))
                check = true;
            if (isUpdate_dynamically())
                check = false;
            if (!check) {
                if (isUpdate_dynamically()) {
                    if(offercamping.equals(no.no))
                    camping = new camping(Integer.parseInt(this.Id_camping.getText()), 0, this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.image.getUrl());
                    else
                        camping = new camping(Integer.parseInt(this.Id_camping.getText()), ((offre) offercamping).getId(), this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.image.getUrl());
                    c.Update(camping);
                }
                else {
                    if (offercamping.equals(no.no))
                        camping = new camping(Integer.parseInt(this.Id_camping.getText()), 0, this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.file.getAbsoluteFile().toURI().toString());
                    else
                        camping = new camping(Integer.parseInt(this.Id_camping.getText()), ((offre) offercamping).getId(), this.local_camping.getText(), this.desc_camping.getText(), this.ty_camping.getText(), this.file.getAbsoluteFile().toURI().toString());
                    c.Add(camping);
                }
                setCheck_id(0);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                if(isUpdate_dynamically())
                    alert.setContentText("successfully modified.");
                else
                    alert.setContentText("You have successfully added a new offer.");
                alert.showAndWait();

            } else {
                setCheck_id(0);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("You enter an ID that already exists.");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please fill the required fields (Id, localisation, description, type, image).");
            alert.showAndWait();
        }
    }

    public void cleancamping(ActionEvent actionEvent) {
        if(!isUpdate_dynamically())
        Id_camping.clear();
        local_camping.clear();
        desc_camping.clear();
        ty_camping.clear();
        im_camping.setImage(null);
        this.offre_camping.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Nooffer o = new Nooffer();
        siteslist.add(0,o.no);
        this.offre_camping.setItems(siteslist);
        this.offre_camping.getSelectionModel().selectFirst();
        Offre_c c = new Offre_c();
        try {
            for(offre l:c.result()) {
                siteslist.add(new offre(l.getId(),l.getNombre_places(),l.getDate_debut(),l.getDate_fin(),l.getPrix()));
                this.offre_camping.setItems(siteslist);
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
            this.offre_camping.getSelectionModel().select(offre);
        }
        else
            this.offre_camping.getSelectionModel().select(b);
        this.Id_camping.setText(String.valueOf(a));
        this.local_camping.setText(String.valueOf(c));
        this.desc_camping.setText(String.valueOf(d));
        this.ty_camping.setText(String.valueOf(e));
        image = new Image(f, this.im_camping.getFitWidth(), this.im_camping.getFitHeight(), true, true);
        this.im_camping.setImage(image);
        this.im_camping.setPreserveRatio(true);
    }
}
