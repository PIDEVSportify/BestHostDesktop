/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Config.MyConnection;

/**
 *
 * @author ahmed
 */
public class EntityService {
    Connection cnx;
    PreparedStatement ste;
    public EntityService() {
        cnx = MyConnection.current_connection.getConn();
    }
    public void AjouterEntity(Activity p){
        try {
            String dql = "select max(id)+1 from activity";
            ste = cnx.prepareStatement(dql);
            ResultSet rs = ste.executeQuery();
            int id=1;
            while(rs.next())
            {
                 id = rs.getInt(1);
            }
            String sql = "insert into activity(id,id_act,type,description,categorie,id_gerant,date_val)"+"values(?,?,?,?,?,?,?)";
            ste = cnx.prepareStatement(sql);
            ste.setInt(1,id);
            ste.setString(6,p.getId_gerant());
            ste.setString(2,p.getId_act());
            ste.setString(3,p.getType());
            ste.setString(5, p.getCategorie());
            ste.setDate(7,p.getDate_val());
            ste.setString(4, p.getDescription());
            ste.executeUpdate();
            System.out.println("Activity ajouté");
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    
    }
    
}
