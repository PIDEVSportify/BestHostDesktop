package Services;

import Config.MyConnection;
import Entities.Token;
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


    public boolean validEmail(String email)
    {
        this.conn = MyConnection.getInstance().getConn();
        String sql =" select * from user where email=?";
        try
        {
            stm=conn.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()==true)
            {
                return true ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            return false;
    }

    public void updateToken(String email, Token token)
    {
            this.conn=MyConnection.getInstance().getConn();

            String sql = "select id from user where email= ?";
        try {
            stm=this.conn.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();

            if (rs.next())
            {
                int user_id = rs.getInt(1);
                 sql = "select count(*) from recovery_tokens where user_id = ? ";
                stm=this.conn.prepareStatement(sql);
                stm.setInt(1,user_id);
                rs=stm.executeQuery();
                rs.next();
                if (rs.getInt(1)!=0)
                {
                    sql = "update recovery_tokens set token =? , created_at=? ,expires_at =? where user_id=?";
                    stm=this.conn.prepareStatement(sql);
                    stm.setString(1,token.getToken());
                    stm.setTimestamp(2,token.getCreated_at());
                    stm.setTimestamp(3,token.getExpires_at());
                    stm.setInt(4,user_id);
                    stm.executeUpdate();
                }
                else {
                    sql = "insert into  recovery_tokens (token,created_at,expires_at,user_id) values (?,?,?,?)";
                    stm=this.conn.prepareStatement(sql);
                    stm.setString(1,token.getToken());
                    stm.setTimestamp(2,token.getCreated_at());
                    stm.setTimestamp(3,token.getExpires_at());
                    stm.setInt(4,user_id);
                    stm.executeUpdate();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }



    public Token fetchToken(String email)
    {
        this.conn=MyConnection.getInstance().getConn();
        String sql ="select token,expires_at from recovery_tokens where user_id = ( select id from user where email = ? ) ";
        try {
            stm=this.conn.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();
            if (rs.next())
            {
                Token token = new Token();
                token.setToken(rs.getString("token"));
                token.setExpires_at(rs.getTimestamp("expires_at"));
                return token;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

            return null;
    }







}
