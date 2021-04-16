package Entities;

import javafx.scene.image.Image;

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

    public camping(){}

    public camping(String description_camping,int prix_offre_camping) {
        this.description_camping = description_camping;
        this.prix_offre_camping = prix_offre_camping;
    }

    private Image image_site;

    private int prix_offre_camping;

    public Image getImage_site() {
        return image_site;
    }

    public void setImage_site(Image image_site) {
        this.image_site = image_site;
    }

    public int getPrix_offre_camping() {
        return this.prix_offre_camping;
    }

    public void setPrix_offre_camping(int prix_offre_camping) {
        this.prix_offre_camping = prix_offre_camping;
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

    public void setLocalisation_camping(String localisation_camping) {
        this.localisation_camping = localisation_camping;
    }

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

    public int getRating_camping() {
        return rating_camping;
    }

    public void setRating_camping(int rating_camping) {
        this.rating_camping = rating_camping;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(double average_rating) {
        this.average_rating = average_rating;
    }

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
        return "camping{" +
                "id=" + id +
                ", offre_id_id=" + offre_id_id +
                ", localisation_camping='" + localisation_camping + '\'' +
                ", type_camping='" + type_camping + '\'' +
                ", image_camping='" + image_camping + '\'' +
                ", rating_camping=" + rating_camping +
                ", average_rating=" + average_rating +
                ", longitude_camping='" + longitude_camping + '\'' +
                ", latitude_camping='" + latitude_camping + '\'' +
                '}';
    }
}
