package Entities;

import java.util.Objects;

public class offre {
    private int id;
    private int nombre_places;
    private String date_debut;
    private String date_fin;
    private int prix;

    public offre(){}

    public offre(int id, int nombre_places, String date_debut, String date_fin, int prix) {
        this.id = id;
        this.nombre_places = nombre_places;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

    public offre(int nombre_places, String date_debut, String date_fin, int prix) {
        this.nombre_places = nombre_places;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombre_places() {
        return nombre_places;
    }

    public void setNombre_places(int nombre_places) {
        this.nombre_places = nombre_places;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        offre offre = (offre) o;
        return id == offre.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "offre{" +
                "id=" + id +
                ", nombre_places=" + nombre_places +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", prix=" + prix +
                '}';
    }
}
