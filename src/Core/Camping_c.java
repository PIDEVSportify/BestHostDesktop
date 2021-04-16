package Core;

import Config.Config;
import Entities.camping;
import Entities.offre;
import Interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Camping_c implements CRUD<camping> {
    private Connection connexion;

    public Connection getConnexion() throws SQLException {
        return this.connexion= Config.getDBConnection();
    }
    public Camping_c(){}

    @Override
    public void Diplay() throws SQLException {}

    @Override
    public void Add(camping camping) throws SQLException {
        PreparedStatement prepare=getConnexion().prepareStatement("insert into camping(id,offre_id_id,localisation_camping,description_camping,type_camping,image_camping) values (?,?,?,?,?,?)");
        prepare.setInt(1,camping.getId());
        if(camping.getOffre_id_id()==0)
        prepare.setString(2,null);
        else
        prepare.setInt(2,camping.getOffre_id_id());
        prepare.setString(3,camping.getLocalisation_camping());
        prepare.setString(4,camping.getDescription_camping());
        prepare.setString(5,camping.getType_camping());
        prepare.setString(6,camping.getImage_camping());
        prepare.executeUpdate();
    }

    @Override
    public void Update(camping camping) throws SQLException {
        PreparedStatement prepare = getConnexion().prepareStatement("UPDATE camping SET offre_id_id=?,localisation_camping=?,description_camping=?,type_camping=?,image_camping=? where id=?");
        if(camping.getOffre_id_id()==0)
            prepare.setString(1,null);
        else
            prepare.setInt(1, camping.getOffre_id_id());
        prepare.setString(2, camping.getLocalisation_camping());
        prepare.setString(3, camping.getDescription_camping());
        prepare.setString(4, camping.getType_camping());
        prepare.setString(5, camping.getImage_camping());
        prepare.setInt(6, camping.getId());
        prepare.executeUpdate();

    }

    @Override
    public void Delete(camping camping) throws SQLException {
        PreparedStatement prepare=getConnexion().prepareStatement("DELETE FROM camping where id=?");
        prepare.setInt(1,camping.getId());
        prepare.executeUpdate();
    }

    public ArrayList<camping> result() throws SQLException {
        ArrayList<camping> list=new ArrayList<camping>();
        PreparedStatement prepare=getConnexion().prepareStatement("select * from camping");
        ResultSet rs =prepare.executeQuery();
        while (rs.next())
        {
            camping c=new camping(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
            list.add(c);
        }
        return list;
    }
}
