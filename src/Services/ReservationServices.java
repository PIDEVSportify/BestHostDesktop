package Services;

import Config.MyConnection;
import Entities.Maison;
import Entities.User;
import Entities.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationServices   {
    Connection conn;
    PreparedStatement stm;
    public  void Addreservation(Reservation reservation){

        this.conn= MyConnection.getInstance().getConn();
        String sql = "INSERT INTO reservation (nom_client,email_client,numero_client,date_debut,date_fin ) VALUES (?,?,?,?,?)";
        try {
            stm= conn.prepareStatement(sql);

            stm.setString(1,reservation.getNom_client());
            stm.setString(2,reservation.getEmail_client());
            stm.setString(3,reservation.getNumero_client());
            stm.setString(4,reservation.getDate_debut());
            stm.setString(5,reservation.getDate_fin());


            stm.executeUpdate();
            System.out.println("reservation added Successfully");
        } catch (SQLException throwables) {
            System.out.println("Error Adding reservation");
            System.out.println(sql);
            throwables.printStackTrace();
        }

    }
    public ObservableList<Reservation> showReservation()
    {
        conn= MyConnection.getInstance().getConn();
        String sql = "SELECT id , nom_client , email_client,numero_client  from reservation ";
        ObservableList<Reservation> reservationsList= FXCollections.observableArrayList();
        try {
            ResultSet rs =conn.prepareStatement(sql).executeQuery();

            while (rs.next())
            {
                Reservation m = new Reservation();
                m.setId(rs.getInt("id"));
                m.setNom_client(rs.getString("nom_client"));
                m.setEmail_client(rs.getString("email_client"));
                m.setNumero_client(rs.getString("numero_client"));

                System.out.println("maison fetched");
                reservationsList.add(m);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("done fetching");
        return reservationsList;

    }
    public   void modifyMaison(Maison m)
    {
        conn=MyConnection.getInstance().getConn();
        String sql="UPDATE maison_hote SET   adresse=?  , description=? where nom=?";
        try {
            stm=conn.prepareStatement(sql);


            stm.setString(1,m.getAdresse());
            stm.setString(2,m.getDescription());
            //stm.setInt(3,m.getPrix());
            stm.setString(3,m.getNom());


            stm.executeUpdate();

            System.out.println("User modified successfully");
            System.out.println(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }




}
