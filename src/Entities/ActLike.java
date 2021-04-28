/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ahmed
 */
public class ActLike {
    private int id;
    private String post_id,user_id;
    public ActLike(){};

    public ActLike(String post_id, String user_id) {
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ActLike{" + "id=" + id + ", post_id=" + post_id + ", user_id=" + user_id + '}';
    }
    
    
    
}
