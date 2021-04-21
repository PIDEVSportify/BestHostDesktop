package Services;

import Config.MyConnection;
import Entities.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServices {
     Connection conn;
     PreparedStatement stm;


    public User checkCredentials(String email, String password)
    {


        conn=MyConnection.getInstance().getConn();

        String sql = "select * from User where email=? ";
        try {
            stm=conn.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();
            if (rs.next())
            {
                if(this.checkPassword(password,rs.getString("password")))
                {
                User u = new User();
                u.setEmail(rs.getString("email"));
                u.setFirst_name(rs.getString("first_name"));
                u.setLast_name(rs.getString("last_name"));
                u.setIs_banned(rs.getInt("is_banned"));
                return u;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null ;

    }


    public  static String passwordEncoder(String password)
    {
        String hash= BCrypt.hashpw(password,BCrypt.gensalt(13));
        hash=hash.replace("$2a","$2y");
        return hash;
    }

    public boolean checkPassword(String password,String hashedPassword)
    {
        return BCrypt.checkpw(password,hashedPassword);
    }


}
