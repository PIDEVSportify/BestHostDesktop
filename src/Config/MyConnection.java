package Config;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entities.Reclamation;

import javax.swing.*;
import java.sql.*;

public class MyConnection {

    public String url="jdbc:mysql://localhost:3306/testoauth?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public String user="root";
    public String pass="";

    private Connection conn;
    public  static MyConnection current_connection;

    public  MyConnection() {

        try {
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected Successfully to Db ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/testoauth?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    public static MyConnection  getInstance ()
    {

        if (current_connection==null)
        {
            current_connection=new MyConnection();
        }
        return current_connection;

    }
    public static ObservableList<Reclamation> getDataReclamation(){
        Connection conn = ConnectDb();
        ObservableList<Reclamation> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reclamation");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Reclamation( rs.getInt("id"),rs.getString("type"), rs.getString("ido"), rs.getString("sujet"), rs.getString("description"), rs.getString("date"), rs.getString("etat") ,rs.getString("idU")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public Connection getConn() {
        return conn;
    }
}
