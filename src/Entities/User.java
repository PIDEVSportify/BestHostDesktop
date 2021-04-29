package Entities;


import Services.LoginServices;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Timestamp;


public class User
{
    private int id ;
    private String first_name,last_name;
    private String email;
    private String password;
    private int is_banned=0;
    private  Timestamp created_at;
    private String avatar;
    private String cin;
    private ImageView pic;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }

    public void setIs_banned(int is_banned) {
        this.is_banned = is_banned;
    }

    public int getIs_banned() {
        return is_banned;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = LoginServices.passwordEncoder(password);
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public Timestamp getCreated_at() { return created_at; }

    public User (   String first_name, String last_name, String email,String password,String avatar,String cin)
    {
        this.created_at=   new Timestamp(System.currentTimeMillis());
        this.email=email;
        this.first_name=first_name;
        this.last_name=last_name;
        this.password= LoginServices.passwordEncoder(password);
        this.avatar=avatar;
        this.cin=cin;


    }

    public User(String first_name, String last_name,String email,String cin)
    {
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.cin=cin;

    }

    public User(int id, String first_name, String last_name, String email, String password, int is_banned, Timestamp created_at, String avatar, String cin, ImageView pic, String role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.is_banned = is_banned;
        this.created_at = created_at;
        this.avatar = avatar;
        this.cin = cin;
        this.pic = pic;
        this.role = role;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public User()
    {

    }


    public String GetRole() {
        return getRole();
    }
}