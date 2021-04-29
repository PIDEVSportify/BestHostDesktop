package Entities;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

import java.util.Objects;

public class camping {
    private int id;
    private int offre_id_id;
    private String localisation_camping;
    private String description_camping;
    private String type_camping;
    private String image_camping;
    private int rating_camping;
    private double average_rating;
    private String longitude_camping;
    private String latitude_camping;

    private Image image_site;

    private double ratecamping;

    private String fulloffre;

    private ImageView listview_image;

    private JFXButton qrcode;

    private org.controlsfx.control.Rating rating;

    private JFXButton Add_rate;

    public camping(){}

    public camping(String description_camping,double ratecamping) {
        this.description_camping = description_camping;
        this.ratecamping = ratecamping;
    }

    public camping(int offre_id_id, String localisation_camping, String description_camping, String type_camping, String image_camping) {
        this.offre_id_id = offre_id_id;
        this.localisation_camping = localisation_camping;
        this.description_camping = description_camping;
        this.type_camping = type_camping;
        this.image_camping = image_camping;
    }

    public camping(int id, int offre_id_id, String localisation_camping, String description_camping, String type_camping, Image image_site) {
        this.id = id;
        this.offre_id_id = offre_id_id;
        this.localisation_camping = localisation_camping;
        this.description_camping = description_camping;
        this.type_camping = type_camping;
        this.image_site = image_site;
    }

    public camping(int id, int offre_id_id, String localisation_camping, String description_camping, String type_camping, String image_camping) {
        this.id = id;
        this.offre_id_id = offre_id_id;
        this.localisation_camping = localisation_camping;
        this.description_camping = description_camping;
        this.type_camping = type_camping;
        this.image_camping = image_camping;
    }

    public camping(int id, int offre_id_id, String localisation_camping, String description_camping, String type_camping, String image_camping,int rating_camping,double average_rating,String longitude_camping,String latitude_camping) {
        this.id = id;
        this.offre_id_id = offre_id_id;
        this.localisation_camping = localisation_camping;
        this.description_camping = description_camping;
        this.type_camping = type_camping;
        this.image_camping = image_camping;
        this.rating_camping=rating_camping;
        this.average_rating=average_rating;
        this.longitude_camping=longitude_camping;
        this.latitude_camping=latitude_camping;
    }

    public camping(int id, String fulloffre, String localisation_camping, String description_camping, String type_camping, Image image_site, int offre_id_id,double ratecamping) {
        this.id = id;
        this.fulloffre = fulloffre;
        this.localisation_camping = localisation_camping;
        this.description_camping = description_camping;
        this.type_camping = type_camping;
        this.image_site = image_site;
        this.offre_id_id = offre_id_id;
        this.ratecamping=ratecamping;
    }

    public camping(int id, JFXButton qrcode, String localisation_camping, String description_camping, String type_camping, ImageView listview_image, int offre_id_id,int rating_camping,double average_rating,org.controlsfx.control.Rating rating,JFXButton add_rate) {
        this.id = id;
        this.qrcode = qrcode;
        this.localisation_camping = localisation_camping;
        this.description_camping = description_camping;
        this.type_camping = type_camping;
        this.listview_image = listview_image;
        this.offre_id_id = offre_id_id;
        this.rating_camping=rating_camping;
        this.average_rating=average_rating;
        this.rating=rating;
        this.Add_rate=add_rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffre_id_id() {
        return offre_id_id;
    }

    public void setOffre_id_id(int offre_id_id) {
        this.offre_id_id = offre_id_id;
    }

    public String getLocalisation_camping() {
        return localisation_camping;
    }

    public void setLocalisation_camping(String localisation_camping) { this.localisation_camping = localisation_camping; }

    public String getType_camping() {
        return type_camping;
    }

    public void setType_camping(String type_camping) {
        this.type_camping = type_camping;
    }

    public String getImage_camping() {
        return image_camping;
    }

    public void setImage_camping(String image_camping) {
        this.image_camping = image_camping;
    }

    public int getRating_camping() { return rating_camping; }

    public void setRating_camping(int rating_camping) {
        this.rating_camping = rating_camping;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(double average_rating) { this.average_rating = average_rating; }

    public String getLongitude_camping() {
        return longitude_camping;
    }

    public void setLongitude_camping(String longitude_camping) {
        this.longitude_camping = longitude_camping;
    }

    public String getLatitude_camping() {
        return latitude_camping;
    }

    public void setLatitude_camping(String latitude_camping) {
        this.latitude_camping = latitude_camping;
    }

    public String getDescription_camping() { return description_camping; }

    public void setDescription_camping(String description_camping) { this.description_camping = description_camping; }

    public String getFulloffre() { return fulloffre; }

    public void setFulloffre(String fulloffre) { this.fulloffre = fulloffre; }

    public Image getImage_site() {
        return image_site;
    }

    public void setImage_site(Image image_site) {
        this.image_site = image_site;
    }

    public ImageView getListview_image() { return listview_image; }

    public Rating getRating() { return rating; }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public JFXButton getAdd_rate() { return Add_rate; }

    public void setAdd_rate(JFXButton add_rate) { Add_rate = add_rate; }

    public JFXButton getQrcode() { return qrcode; }

    public void setQrcode(JFXButton qrcode) { this.qrcode = qrcode; }

    public double getRatecamping() { return ratecamping; }

    public void setRatecamping(double ratecamping) { this.ratecamping = ratecamping; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        camping camping = (camping) o;
        return id == camping.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "camping: " +
                "localisation_camping='" + localisation_camping + '\'' +
                ", description_camping='" + description_camping + '\'' +
                ", type_camping='" + type_camping + '\'' +
                ", image_camping='" + image_camping + '\'' +"."
                ;
    }
}
