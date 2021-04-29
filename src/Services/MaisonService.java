package Services;

import Config.MyConnection;
import Entities.Maison;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaisonService {

    Connection conn;
    PreparedStatement stm;


    public  void addMaison(Maison maison)
    {
        this.conn= MyConnection.getInstance().getConn();
        String sql = "INSERT INTO maison_hote (adresse,nom,description,type,nombre_chambres,prix,image_360) VALUES (?,?,?,?,?,?,?)";
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
    public ObservableList<Maison> showMaison()
    {
        conn= MyConnection.getInstance().getConn();
        String sql = "SELECT * from maison_hote ";
        ObservableList<Maison> maisonsList= FXCollections.observableArrayList();
        try {
            ResultSet rs =conn.prepareStatement(sql).executeQuery();

            while (rs.next())
            {
                Maison m = new Maison();
                m.setAdresse(rs.getString("adresse"));
                m.setNom(rs.getString("nom"));
                m.setDescription(rs.getString("description"));
                m.setType(rs.getString("type"));
                m.setNombre_chambres(rs.getInt("nombre_chambres"));
                m.setPrix(rs.getInt("prix"));
                m.setAvatar(rs.getString("image_360"));

                m.setPic(m.getAvatar());
                System.out.println("maison fetched");
                maisonsList.add(m);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("done fetching");
        return maisonsList;

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

    public void deleteMaison(String nom)
    {
        conn= MyConnection.getInstance().getConn();
        String sql= "Delete from maison_hote WHERE nom=?";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,nom);
            stm.executeUpdate();
            System.out.println("maison with nom ="+ nom +" Deleted Successfully ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }





}
