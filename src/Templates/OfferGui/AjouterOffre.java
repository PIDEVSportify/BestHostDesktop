package Templates.OfferGui;

import Entities.offre;
import Services.Offre_c;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Array;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.function.Predicate;

public class AjouterOffre implements Initializable {
    @FXML
    public Text Title_offre;
    @FXML
    private TextField places_offre;
    @FXML
    private DatePicker datedebut_offre;
    @FXML
    private DatePicker datefin_offre;
    @FXML
    private TextField prix_offre;

    private int save_id;

    public boolean isUpdate_dynamically() {
        return update_dynamically;
    }

    public void setUpdate_dynamically(boolean update_dynamically) {
        this.update_dynamically = update_dynamically;
    }

    public void setTitle_offre(String Text) {
        this.Title_offre.setText(Text);
    }

    private boolean update_dynamically;

    private Vector<Integer> vect = new Vector<Integer>();

    public Vector<Integer> getVect() {
        return vect;
    }

    public void setVect(Integer i) {
        this.vect.add(i);
    }

    public void saveoffre(ActionEvent actionEvent) throws SQLException {
        String date_debut = String.valueOf(datedebut_offre.getValue());
        String date_fin = String.valueOf(datefin_offre.getValue());

            if (convert_string_int(places_offre, prix_offre) && compareDates(date_debut,date_fin)) {
                Offre_c c = new Offre_c();
                offre offre=new offre(getVect().get(0),date_debut,date_fin,getVect().get(1));
                    if(isUpdate_dynamically()) {
                        offre offreupdate=new offre(save_id,getVect().get(0),date_debut,date_fin,getVect().get(1));
                        c.Update(offreupdate);
                    }
                    else
                    c.Add(offre);
                    getVect().clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Success");
                    if(isUpdate_dynamically())
                        alert.setContentText("successfully modified.");
                    else
                        alert.setContentText("You have successfully added a new offer.");
                        alert.showAndWait();
            }
    }

    public static boolean compareDates(String d1,String d2)
    {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(!d1.equals("null") && !d2.equals("null")) {
                Date date1 = sdf.parse(d1);
                Date date2 = sdf.parse(d2);
                if (date1.before(date2))
                    return true;
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Date début is greater than Date fin");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Both dates are required");
                alert.showAndWait();
            }
        }
        catch(ParseException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean convert_string_int(TextField t2, TextField t3){
        try {
            if ((Integer.parseInt(t2.getText())<2 || Integer.parseInt(t2.getText())>10) || (Integer.parseInt(t3.getText())<=0 || Integer.parseInt(t3.getText())>100)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("\nplaces_offre: between 2 and 10 \nprix_offre: between 1 and 100  ");
                alert.showAndWait();
                getVect().clear();
                return false;
            }
        setVect(Integer.parseInt(t2.getText()));
        setVect(Integer.parseInt(t3.getText()));
        return true;
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("check if the input values(id,places,price) is an integer.");
            alert.showAndWait();
            getVect().clear();
            return false;
        }
    }

    public void cleanoffre(ActionEvent actionEvent) {
      if(!isUpdate_dynamically())
          this.save_id=0;
      this.places_offre.clear();
      this.datedebut_offre.setValue(null);
      this.datefin_offre.setValue(null);
      this.prix_offre.clear();
    }

    public void addvalues(int a,int b,String c,String d,int e)
    {
        this.save_id=a;
        this.places_offre.setText(String.valueOf(b));
        this.datedebut_offre.setValue(LocalDate.parse(c));
        this.datefin_offre.setValue(LocalDate.parse(d));
        this.prix_offre.setText(String.valueOf(e));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       this.datedebut_offre.setEditable(false);
        this.datefin_offre.setEditable(false);
    }
}
