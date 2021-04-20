package Entities;

public class MaisonEntity {
    private int id , prix , nb_chambre;
    private String nom,adress,description;

    public MaisonEntity() {
    }

    public MaisonEntity(int id, int prix, int nb_chambre, String nom, String adress, String description) {
        this.id = id;
        this.prix = prix;
        this.nb_chambre = nb_chambre;
        this.nom = nom;
        this.adress = adress;
        this.description = description;
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

    public int getNb_chambre() {
        return nb_chambre;
    }

    public void setNb_chambre(int nb_chambre) {
        this.nb_chambre = nb_chambre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
