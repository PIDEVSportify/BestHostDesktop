package services;

import database.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Entities.MaisonEntity;
public class MaisonServices {

    Connection cnx;

    PreparedStatement ste;


    public MaisonServices(){
        cnx = dbConnection.getinstance().getCnx();
    }

    public void ajouterMaison(MaisonEntity m){

        try {
            String sql = "insert into maisons(nom,adress,description,prix,nb_chambre)"+"values(?,?,?,?,?)";
            ste =  cnx.prepareStatement(sql);
            ste.setString(1, m.getNom());
            ste.setString(2, m.getAdress());
            ste.setString(3, m.getDescription());
            ste.setInt(4, m.getPrix());
            ste.setInt(5, m.getNb_chambre());
            ste.executeUpdate();
            System.out.println("maison ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public void  afficherMaison(){

        try {
            String sql = "select * from `maisons`";
            ste =  cnx.prepareStatement(sql);
            ResultSet result = ste.executeQuery(sql);
            int count = 0;
            while (result.next()) {
                String nom = result.getString(2);
                String adress = result.getString(3);
                String prix = result.getString(4);
                String description = result.getString(5);
                String output = "Maison #%d: nom: %s  adress: %s prix: %s description: %s";
                System.out.println(String.format(output, ++count,nom,adress,prix,description));
            }



        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void modifierMaison(MaisonEntity m){
        try {


            if (m.getId() !=0) {
                String sql = "UPDATE maisons SET nom=? ,adress=?,description=?,prix=?,nb_chambre=? WHERE id=?";
                ste =  cnx.prepareStatement(sql);

                ste.setString(1, m.getNom());
                ste.setString(2, m.getAdress());
                ste.setString(3, m.getDescription());
                ste.setInt(4, m.getPrix());
                ste.setInt(5, m.getNb_chambre());
                ste.setInt(6, m.getId());
                ste.executeUpdate();
                System.out.println("updated !");
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerMaison(int id){
        try {


            if (id !=0) {
                String sql = "delete from maisons WHERE id=?";
                ste =  cnx.prepareStatement(sql);
                ste.setInt(1, id);
                ste.executeUpdate();
                System.out.println("deleted !");
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
