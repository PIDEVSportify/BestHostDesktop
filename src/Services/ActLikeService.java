/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.MyConnection;
import Entities.ActLike;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class ActLikeService {
    Connection cnx;
    PreparedStatement ste;
    public ActLikeService() {
        cnx = MyConnection.current_connection.getConn();
    }
    public void addLike(String a,String b){
        String sql="INSERT into act_like(post_id,user_id) values (?,?)";
        try {
            ste= cnx.prepareStatement(sql);
            ste.setString(1, a);
            ste.setString(2, b);
                        ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ActLikeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void suppLike(String a,String b){
    String sql="Delete from act_like where post_id = ? AND user_id = ?";
        try {
            ste= cnx.prepareStatement(sql);
            ste.setString(1, a);
            ste.setString(2, b);
                        ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ActLikeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
