package database;

import java.sql.*;

public class dbConnection {


    public String url = "jdbc:mysql://localhost:3306/sportify";
    public String user = "root";
    public String password = "";
    private Connection cnx;
    public static dbConnection  ct;

    private dbConnection (){
        try {
            cnx = DriverManager.getConnection(url,user,password);
            System.out.println("we are good connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static dbConnection getinstance(){
        if (ct == null)
            ct= new dbConnection ();
        return ct;

    }

    public Connection getCnx() {
        return cnx;
    }



}
