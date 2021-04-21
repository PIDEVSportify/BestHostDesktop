package Core;

import Config.Config;
import Entities.offre;
import Interfaces.CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Offre_c implements CRUD<offre> {
    private Connection connexion;

    public Connection getConnexion() throws SQLException {
        return this.connexion=Config.getDBConnection();
    }
    public Offre_c(){}

    @Override
    public void Diplay() throws SQLException {}

    @Override
    public void Add(offre offre) throws SQLException {
        PreparedStatement prepare=getConnexion().prepareStatement("insert into offre(nombre_places_offre,date_debut_offre,date_fin_offre,prix_offre) values (?,?,?,?)");
//        prepare.setInt(1,offre.getId());
        prepare.setInt(1,offre.getNombre_places());
        prepare.setString(2,offre.getDate_debut());
        prepare.setString(3,offre.getDate_fin());
        prepare.setInt(4,offre.getPrix());
        prepare.executeUpdate();
    }

    @Override
    public void Update(offre offre) throws SQLException {
            PreparedStatement prepare = getConnexion().prepareStatement("UPDATE offre SET nombre_places_offre=?,date_debut_offre=?,date_fin_offre=?,prix_offre=? where id=?");
            prepare.setInt(1, offre.getNombre_places());
            prepare.setString(2, offre.getDate_debut());
            prepare.setString(3, offre.getDate_fin());
            prepare.setInt(4, offre.getPrix());
            prepare.setInt(5, offre.getId());
            prepare.executeUpdate();
    }

    @Override
    public void Delete(offre offre) throws SQLException {
        PreparedStatement prepare=getConnexion().prepareStatement("DELETE FROM offre where id=?");
        prepare.setInt(1,offre.getId());
        prepare.executeUpdate();
    }

    public ArrayList<offre> result() throws SQLException {
        ArrayList<offre> list=new ArrayList<offre>();
        PreparedStatement prepare=getConnexion().prepareStatement("select * from offre");
        ResultSet rs =prepare.executeQuery();
        while (rs.next())
        {
        offre o=new offre(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5));
        list.add(o);
        }
        return list;
    }
}
