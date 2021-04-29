/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Reclamation {
       
       public Reclamation(int id,String type, String sujet, String description, String date, String etat) {
        this.type = type;
        this.id = id;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }    
        
    public Reclamation(String type, String sujet, String description, String date, String etat) {
        this.type = type;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }

    public Reclamation(String description) {
        this.description = description;
    }
    public Reclamation(String description, int id) {
        this.id = id;
        this.description = description;
    }
    public Reclamation(int id, String etat) {
        this.id = id;
        this.etat = etat;
    }

    
    private int id;
    private String type;
    private String ido;
    private String sujet;
    private String description;
    private String date;
    private String etat;
    private String User;

    public Reclamation(int id, String type, String ido, String sujet, String description, String date, String etat, String User  ) {
        this.id = id;
        this.type = type;
        this.ido = ido;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.User = User;
    }

    public Reclamation(int id, String type, String ido, String sujet, String description, String date, String etat) {
        this.id = id;
        this.type = type;
        this.ido = ido;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
    } 
  

    public Reclamation(String type, String sujet, String description) {
        this.type = type;
        this.sujet = sujet;
        this.description = description;
    }
    public Reclamation() {
    }

    public Reclamation(int id, String type, String sujet, String description) {
        this.id = id;

        this.type = type;
        this.sujet = sujet;
        this.description = description;
    }

    public Reclamation(String type, String ido, String sujet, String description, String etat, String User) {
        this.type = type;
        this.ido = ido;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
        this.User = User;
    }

   

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", ido=" + ido + ", sujet=" + sujet + ",description=" + description + ", date=" + date + ", etat=" + etat + ", User=" + User + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdo() {
        return ido;
    }

    public void setIdo(String ido) {
        this.ido = ido;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

}
