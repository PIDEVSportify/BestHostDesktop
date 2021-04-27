package Entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Maison {

        private int id ,prix,nombre_chambres;
        private String nom,adresse,description,type,avatar;
        private ImageView pic;

    public Maison(int prix, int nombre_chambres, String nom, String adresse, String description, String type, String avatar) {

        this.prix = prix;
        this.nombre_chambres = nombre_chambres;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.type = type;
        this.avatar =avatar;
    }



    public Maison() {

    }

    public Maison(String adresse, String nom, String description, String type, int nombre_chambres, int prix,String avatar) {
        this.prix = prix;
        this.nombre_chambres = nombre_chambres;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.type = type;
        this.avatar = avatar;
    }
    public Maison(String adresse, String nom, String description) {

        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        
       
    }
    public ImageView getPic() {
        return pic;
    }



    public void setPic(String pic) {

        try {
            Image img= new Image("file:public/"+pic,120,120,false,false);

            this.pic=new ImageView(img);
            this.pic.setPreserveRatio(true);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNombre_chambres() {
        return nombre_chambres;
    }

    public void setNombre_chambres(int nombre_chambres) {
        this.nombre_chambres = nombre_chambres;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
