package Services;

import Config.MyConnection;
import Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserServices {

    Connection conn;
    PreparedStatement stm;

    public UserServices()
    {

    }


    public  void addUser(User user)
    {
        this.conn= MyConnection.getInstance().getConn();
        String sql = "INSERT INTO user (first_name,last_name,email,password,is_banned,created_at,avatar,cin) VALUES (?,?,?,?,?,?,?,?)";
        try {
            stm= conn.prepareStatement(sql);

            stm.setString(1,user.getFirst_name());
            stm.setString(2,user.getLast_name());
            stm.setString(3,user.getEmail());
            stm.setString(4,user.getPassword());
            stm.setInt(5,user.getIs_banned());
            stm.setTimestamp(6,  user.getCreated_at());
            stm.setString(7,  user.getAvatar());
            stm.setString(8,  user.getCin());
            stm.executeUpdate();
            System.out.println("User added Successfully");
        } catch (SQLException throwables) {
            System.out.println("Error Adding User");
            throwables.printStackTrace();
        }
    }

    public ObservableList<User> showUsers()
    {
        conn= MyConnection.getInstance().getConn();
        String sql = "SELECT * from user ";
        ObservableList<User> usersList= FXCollections.observableArrayList();
        try {
            ResultSet rs =conn.prepareStatement(sql).executeQuery();

            while (rs.next())
            {
             User u = new User();
             u.setFirst_name(rs.getString("first_name"));
             u.setLast_name(rs.getString("last_name"));
             u.setEmail(rs.getString("email"));
             u.setIs_banned(rs.getInt("is_banned"));
             u.setAvatar(rs.getString("avatar"));
             u.setCin(rs.getString("cin"));
             u.setPic(u.getAvatar());
                System.out.println("user fetched");
             usersList.add(u);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("done fetching");
        return usersList;

    }

    public void updatePassword(User user)
    {
        conn=MyConnection.getInstance().getConn();
        String sql="UPDATE USER SET  password=? where email=?";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,user.getPassword());
            stm.setString(2,user.getEmail());
            stm.executeUpdate();

            System.out.println("User's password modified successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public   void modifyUser(User user)
    {
        conn=MyConnection.getInstance().getConn();
        String sql="UPDATE USER SET  first_name=? , last_name=? where email=?";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,user.getFirst_name());
            stm.setString(2,user.getLast_name());
            stm.setString(3,user.getEmail());
            stm.executeUpdate();

            System.out.println("User modified successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    public void updateAvatar(User user)
    {
        conn= MyConnection.getInstance().getConn();
        String sql= "UPDATE user set avatar=? WHERE email=?";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,user.getAvatar());
            stm.setString(2,user.getEmail());
            stm.executeUpdate();
            System.out.println("User modified updated Successfully ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void deleteUser(String email)
    {
        conn= MyConnection.getInstance().getConn();
        String sql= "Delete from user WHERE email=?";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,email);
            stm.executeUpdate();
            System.out.println("User with email ="+ email +" Deleted Successfully ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void banUser(String email)
    {
        conn=MyConnection.getInstance().getConn();
        String sql = "UPDATE USER set is_banned=1 where email=?";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,email);
            stm.executeUpdate();
            System.out.println("User having email = "+email+" got banned");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public void unbanUser(String email)
    {
        conn=MyConnection.getInstance().getConn();
        String sql = "UPDATE USER set is_banned=0 where email=?";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,email);
            stm.executeUpdate();
            System.out.println("User having email = "+email+" got unbanned");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public  ObservableList<PieChart.Data> rolesChart()
    {
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList();
        conn = MyConnection.getInstance().getConn();
        String sql ="select count(*) ,roles from user group by roles";
        try {
            stm=conn.prepareStatement(sql);
            ResultSet rs= stm.executeQuery();

            while (rs.next())
            {
                PieChart.Data d = new PieChart.Data(rs.getString(2),rs.getInt(1));
                pieChart.add(d);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pieChart;
    }

    public XYChart.Series<String,Number> numberOfUsers()
    {

        conn = MyConnection.getInstance().getConn();
        String sql =" select CONVERT(created_at,date) , count(*) from user group by CONVERT(created_at,date)";

        XYChart.Series <String,Number> series = new XYChart.Series<String,Number>();
        series.setName("Nombre de comptes créés par mois");

        try {
            stm=conn.prepareStatement(sql);
            ResultSet rs= stm.executeQuery();

            while (rs.next())
            {
                series.getData().add(new XYChart.Data<String,Number>(rs.getString(1),rs.getInt(2)));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return series;

    }


    public Integer getAdminsCount()
    {
        this.conn = MyConnection.getInstance().getConn();
        Integer count=0;
        String sql="SELECT count(*) from user WHERE JSON_CONTAINS(roles, '[\"ROLE_ADMIN\"]') ";
        try {
            stm= this.conn.prepareStatement(sql);
             ResultSet rs=stm.executeQuery();
             rs.next();
             count=rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count;
    }


    public Integer getGerantsMaisonHotesCount()
    {
        this.conn = MyConnection.getInstance().getConn();
        Integer count=0;
        String sql="SELECT count(*) from user WHERE JSON_CONTAINS(roles, '[\"ROLE_GERANT_MAISON_HOTE\"]') ";
        try {
            stm= this.conn.prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            rs.next();
            count=rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count;
    }
    public Integer getGerantSiteCampingCount()
    {
        this.conn = MyConnection.getInstance().getConn();
        Integer count=0;
        String sql="SELECT count(*) from user WHERE JSON_CONTAINS(roles, '[\"ROLE_GERANT_CAMPING\"]') ";
        try {
            stm= this.conn.prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            rs.next();
            count=rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count;
    }
    public Integer getGerantSecteurActivite()
    {
        this.conn = MyConnection.getInstance().getConn();
        Integer count=0;
        String sql="SELECT count(*) from user WHERE JSON_CONTAINS(roles, '[\"ROLE_GERANT_SECTEUR_ACTIVITE\"]') ";
        try {
            stm= this.conn.prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            rs.next();
            count=rs.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count;
    }


    public String GetRole() {
        this.conn = MyConnection.getInstance().getConn();
        String r=null;
        String sql="SELECT roles from user ";
        try {
            stm= this.conn.prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            rs.next();
            r=rs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return r;
    }
    }

