package Services;

import Config.MyConnection;
import Entities.Maison;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationServices   {
    Connection conn;
    PreparedStatement stm;
    public  void reservation(Maison maison){

        this.conn= MyConnection.getInstance().getConn();
        String sql = "INSERT INTO reservation (nom_client,email_client,numero_client,date_debut,date_fin where nom=? ) VALUES (?,?,?,?,?,?)";
        try {
            stm= conn.prepareStatement(sql);

            stm.setString(1,maison.getAdresse());
            stm.setString(2,maison.getNom());
            stm.setString(3,maison.getDescription());
            stm.setString(4,maison.getType());
            stm.setInt(5,maison.getNombre_chambres());
            stm.setInt(6,  maison.getPrix());
            stm.setString(7,  maison.getAvatar());

            stm.executeUpdate();
            System.out.println("maison added Successfully");
        } catch (SQLException throwables) {
            System.out.println("Error Adding maison");
            System.out.println(sql);
            throwables.printStackTrace();
        }

    }





}
