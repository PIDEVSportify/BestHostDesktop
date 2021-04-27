package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    public String url="jdbc:mysql://localhost:3306/testoauth";
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

    public static MyConnection  getInstance ()
    {

        if (current_connection==null)
        {
            current_connection=new MyConnection();
        }
        return current_connection;

    }

    public Connection getConn() {
        return conn;
    }
}
