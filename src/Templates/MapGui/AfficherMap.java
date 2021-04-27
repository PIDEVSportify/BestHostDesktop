package Templates.MapGui;

import Entities.camping;
import Entities.offre;
import Services.Camping_c;
import Services.Offre_c;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.controlsfx.control.WorldMapView;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.nio.file.*;

public class AfficherMap implements Initializable {
    @FXML
    private WebView webview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Camping_c c=new Camping_c();
        Offre_c c1=new Offre_c();
        WebEngine webEngine = this.webview.getEngine();
        final URL urlGoogleMaps = getClass().getResource("mapjavafx.html");
        ArrayList<String> tablat=new ArrayList<String>();
        ArrayList<String> tablot=new ArrayList<String>();
        String[] s = {""};
        String[] image = {""};
        Path path;
        String special=null;
        try {
            path = Paths.get(AfficherMap.class.getResource("../../").toURI());
            special = path.getParent().getParent().getParent().toString().replaceAll("\\\\", "/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            for(camping l:c.result()){
                if(l.getOffre_id_id()!=0)
                    for(offre l1:c1.result()){
                        if(l.getOffre_id_id()==l1.getId())
                            l.setFulloffre("Nombre des places: "+l1.getNombre_places()+" Date début: "+l1.getDate_debut()+" Date fin: "+l1.getDate_fin()+" Prix: "+l1.getPrix()+"Dt");
                    }
                else
                    l.setFulloffre("Pas d'offre pour le moment");
                tablat.add(l.getLatitude_camping());
                tablot.add(l.getLongitude_camping());
                s[0] +=("Localisation: "+l.getLocalisation_camping()+" Type: "+l.getType_camping()+" Description: "+l.getDescription_camping()+" Offre: "+l.getFulloffre()+"£");
                image[0] +=("file:"+special+"/public/uploads/"+l.getImage_camping()+"£");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String Full=s[0].substring(0, s[0].length() - 1);
        String map_images=image[0].substring(0, image[0].length() - 1);
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                if (t1 == Worker.State.SUCCEEDED) {
                   webEngine.executeScript("document.goToLocation(" + tablat + ", " + tablot + ",\""+ Full + "\",\""+ map_images + "\")");
                }
            }
        });
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(urlGoogleMaps.toExternalForm());
    }
}
