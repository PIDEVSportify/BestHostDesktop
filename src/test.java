import database.dbConnection;
import Entities.MaisonEntity;
import services.MaisonServices;

public class test {

    public static void main(String[] args) {
        dbConnection cm = dbConnection.getinstance();
        MaisonEntity m = new MaisonEntity(0,22,5,"dar","ariana","test");
        MaisonServices ms = new MaisonServices();
        // ms.ajouterMaison(m);
        //ms.afficherMaison();
        // ms.modifierMaison(new Maison(15,11,3,"maison","ariana","hello"));
        // ms.afficherMaison();
       // ms.supprimerMaison(15);
        ms.afficherMaison();

    }



}
