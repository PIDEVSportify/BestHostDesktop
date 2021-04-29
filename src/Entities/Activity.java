/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ahmed
 */
public class Activity {
    private int id;
    private String id_act,type,description,categorie,id_gerant;
    private Date date_val;
        public Activity(){};
    public Activity(String id_act,String type,String description, String categorie,String id_gerant,Date date_val){
        this.id_act = id_act;
        this.type = type;
        this.description = description;
        this.categorie = categorie;
        this.id_gerant = id_gerant;
        this.date_val = date_val;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_act() {
        return id_act;
    }

    public void setId_act(String id_act) {
        this.id_act = id_act;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getId_gerant() {
        return id_gerant;
    }

    public void setId_gerant(String id_gerant) {
        this.id_gerant = id_gerant;
    }

    public Date getDate_val() {
        return date_val;
    }

    public void setDate_val(Date date_val) {
        this.date_val = date_val;
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id + ", id_act=" + id_act + ", type=" + type + ", description=" + description + ", categorie=" + categorie + ", id_gerant=" + id_gerant + ", date_val=" + date_val + '}';
    }
    

    
}
