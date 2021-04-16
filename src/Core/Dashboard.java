package Core;

import Entities.camping;
import Entities.offre;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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

    public void gestionoffers(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Afficher_offers.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Offers");
        stage.setScene(scene);
        stage.show();
    }

    public void statistics(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("bar_chart_sites.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.show();
    }

    public void Gestionsites(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Afficher_sites.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Sites");
        stage.setScene(scene);
        stage.show();
    }

    public void returndashboard(ActionEvent actionEvent) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene scene=new Scene(root);
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       this.imageselim = new Image("file:/C:/Users/speak/Downloads/20201230_174907.jpg");
        this.imageviewselim.setImage(this.imageselim);
    }

    public void pdf(ActionEvent actionEvent) {
        Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.NORMAL, new CMYKColor(255, 45, 0,0));
        Offre_c c=new Offre_c();
        Camping_c ca=new Camping_c();
        Document document = new Document();
        try
        {
            File f = new File("C:/Users/speak/Downloads/speaky.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(f));
            document.open();
            Paragraph p= new Paragraph("Data_sites_offers:",blueFont);
            Chapter cp=new Chapter(p,1);
            cp.setNumberDepth(0);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            float[] columnWidths = {1f, 1f, 1f,1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Id"));
            cell1.setBorderColor(BaseColor.BLUE);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Paragraph( "Nombre des places"));
            cell2.setBorderColor(BaseColor.BLUE);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Date début"));
            cell3.setBorderColor(BaseColor.BLUE);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Date fin"));
            cell3.setBorderColor(BaseColor.BLUE);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell4);

            PdfPCell cell5 = new PdfPCell(new Paragraph("Prix"));
            cell3.setBorderColor(BaseColor.BLUE);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell5);
            for(offre l:c.result()) {
                table.addCell(String.valueOf(l.getId()));
                table.addCell(String.valueOf(l.getNombre_places()));
                table.addCell(l.getDate_debut());
                table.addCell(l.getDate_fin());
                table.addCell(String.valueOf(l.getPrix()));
            }

            PdfPTable table1 = new PdfPTable(6);
            table1.setWidthPercentage(100);
            float[] columnWidths1 = {1f, 1f, 1f,1f, 1f,1f};
            table1.setWidths(columnWidths1);

            PdfPCell cell11 = new PdfPCell(new Paragraph("Id"));
            cell11.setBorderColor(BaseColor.BLUE);
            cell11.setPaddingLeft(10);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell11);
            PdfPCell cell22 = new PdfPCell(new Paragraph( "Id_offere"));
            cell22.setBorderColor(BaseColor.BLUE);
            cell22.setPaddingLeft(10);
            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell22);

            PdfPCell cell33 = new PdfPCell(new Paragraph("Localisation_camping"));
            cell33.setBorderColor(BaseColor.BLUE);
            cell33.setPaddingLeft(10);
            cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell33);

            PdfPCell cell44 = new PdfPCell(new Paragraph("Description_camping"));
            cell44.setBorderColor(BaseColor.BLUE);
            cell44.setPaddingLeft(10);
            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell44);

            PdfPCell cell55 = new PdfPCell(new Paragraph("Type_camping"));
            cell55.setBorderColor(BaseColor.BLUE);
            cell55.setPaddingLeft(10);
            cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell55.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell55);

            PdfPCell cell66 = new PdfPCell(new Paragraph("Image_camping"));
            cell66.setBorderColor(BaseColor.BLUE);
            cell66.setPaddingLeft(10);
            cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell66.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1.addCell(cell66);
            for(camping lp:ca.result()) {
                table1.addCell(String.valueOf(lp.getId()));
                table1.addCell(String.valueOf(lp.getOffre_id_id()));
                table1.addCell(lp.getLocalisation_camping());
                table1.addCell(lp.getDescription_camping());
                table1.addCell(lp.getType_camping());
                table1.addCell(lp.getImage_camping());
            }
            document.add(cp);
            document.add( Chunk.NEWLINE );
            document.add(table);
            document.add( Chunk.NEWLINE );
            document.add(table1);

            document.close();
            writer.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("You have successfully added a new Pdf(sites,offers).\npath:C:/Users/speak/Downloads/speaky.pdf");
            alert.showAndWait();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
