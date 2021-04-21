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
        PreparedStatement prepare=getConnexion().prepareStatement("insert into camping(offre_id_id,localisation_camping,description_camping,type_camping,image_camping,latitude_camping,longitude_camping) values (?,?,?,?,?,?,?)");
        CheckOffreId(camping, prepare);
        prepare.executeUpdate();
    }

    private void CheckOffreId(camping camping, PreparedStatement prepare) throws SQLException {
        if(camping.getOffre_id_id()==0)
        prepare.setString(1,null);
        else
        prepare.setInt(1,camping.getOffre_id_id());
        prepare.setString(2,camping.getLocalisation_camping());
        prepare.setString(3,camping.getDescription_camping());
        prepare.setString(4,camping.getType_camping());
        prepare.setString(5,camping.getImage_camping());
        prepare.setString(6,camping.getLocalisation_camping().substring(0,camping.getLocalisation_camping().lastIndexOf(",")));
        prepare.setString(7,camping.getLocalisation_camping().substring(camping.getLocalisation_camping().lastIndexOf(",")+2));
    }

    @Override
    public void Update(camping camping) throws SQLException {
        PreparedStatement prepare = getConnexion().prepareStatement("UPDATE camping SET offre_id_id=?,localisation_camping=?,description_camping=?,type_camping=?,image_camping=?,latitude_camping=?,longitude_camping=? where id=?");
        CheckOffreId(camping, prepare);
        prepare.setInt(8, camping.getId());
        prepare.executeUpdate();

    }

    public void Updaterate(int Id,double avg,int rate) throws SQLException {
        PreparedStatement prepare;
        if(rate==0)
            prepare = getConnexion().prepareStatement("UPDATE camping SET rating_camping=?,average_rating=? where id=?");
        else
            prepare = getConnexion().prepareStatement("UPDATE camping SET rating_camping=rating_camping+?,average_rating=average_rating+? where id=?");

        prepare.setInt(1, 1);
        prepare.setDouble(2, avg);
        prepare.setInt(3, Id);
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
        return getCampings(list, prepare);
    }

    public ArrayList<camping> checkvalidity(int check_id) throws SQLException {
        ArrayList<camping> list=new ArrayList<camping>();
        PreparedStatement prepare=getConnexion().prepareStatement("select * from camping where ?=offre_id_id");
        prepare.setInt(1,check_id);
        return getCampings(list, prepare);
    }

    private ArrayList<camping> getCampings(ArrayList<camping> list, PreparedStatement prepare) throws SQLException {
        ResultSet rs =prepare.executeQuery();
        while (rs.next())
        {
            camping c=new camping(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getDouble(8),rs.getString(9),rs.getString(10));
            list.add(c);
        }
        return list;
    }
}
