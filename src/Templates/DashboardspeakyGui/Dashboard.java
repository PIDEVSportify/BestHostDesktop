package Templates.DashboardspeakyGui;

import Entities.camping;
import Entities.offre;
import Services.Camping_c;
import Services.Offre_c;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    public Button gestionsitesbutton;
    @FXML
    public Button statisticsbutton;
    @FXML
    public Button gestionoffersbutton;
    @FXML
    public Image imageselim;
    @FXML
    public ImageView imageviewselim;
    @FXML
    public Button pdfbutton;
    @FXML
    public JFXButton frontbutton;

    public void gestionoffers(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../OfferGui/Afficher_offers.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Offers");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void statistics(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../BarchartGui/bar_chart_sites.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void Gestionsites(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("../SiteGui/Afficher_sites.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Sites");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void BestHost(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../RatingGui/rating.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("BestHost");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void returndashboard(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       this.imageselim = new Image("file:public/uploads/20201230_174907.jpg");
        this.imageviewselim.setImage(this.imageselim);
    }

    public void pdf(ActionEvent actionEvent) {
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.NORMAL, new CMYKColor(255, 45, 0,0));
        Offre_c c=new Offre_c();
        Camping_c ca=new Camping_c();
        Document document = new Document();
        try
        {
            File f = new File("public/uploads/speaky.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
            document.open();
            Paragraph p= new Paragraph("Data_sites_offers:",blueFont);
            Chapter cp=new Chapter(p,1);
            cp.setNumberDepth(0);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            float[] columnWidths = {1f, 1f, 1f,1f, 1f};
            table.setWidths(columnWidths);
            for(offre l:c.result()) {
                table.addCell(String.valueOf(l.getId()));
                table.addCell(String.valueOf(l.getNombre_places()));
                table.addCell(l.getDate_debut());
                table.addCell(l.getDate_fin());
                table.addCell(String.valueOf(l.getPrix()));
            }
            document.add(cp);
            document.add( Chunk.NEWLINE );
            document.add(table);

            table = new PdfPTable(6);
            table.setWidthPercentage(100);
            float[] columnWidths1 = {1f, 1f, 1f,1f, 1f,1f};
            table.setWidths(columnWidths1);
            for(camping lp:ca.result()) {
                String imageUrl = "file:public/uploads/"+lp.getImage_camping();
                com.itextpdf.text.Image image2 = com.itextpdf.text.Image.getInstance(new URL(imageUrl));
                table.addCell(String.valueOf(lp.getId()));
                table.addCell(String.valueOf(lp.getOffre_id_id()));
                table.addCell(lp.getLocalisation_camping());
                table.addCell(lp.getDescription_camping());
                table.addCell(lp.getType_camping());
                table.addCell(image2);
            }
            document.add( Chunk.NEWLINE );
            document.add(table);

            document.close();
            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("You have successfully added a new Pdf(sites,offers).\npath: public/uploads/speaky.pdf");
            alert.showAndWait();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
