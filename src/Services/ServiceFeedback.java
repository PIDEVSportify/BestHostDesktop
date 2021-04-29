/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Feedback;
import Entities.Reclamation;
import Config.MyConnection;
import Templates.AdminGui.DashboardController;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Templates.AdminGui.DashboardController;

/**
 *
 * @author USER
 */
public class ServiceFeedback {
    Connection cnx = MyConnection.getInstance().getConn();

    private Statement ste;
    private PreparedStatement pst;
    
     public void ajouter(Feedback o) {
          try {
                       
            String req = "INSERT INTO `feedback` (`description`, `rate`, `date`,"
                    + "`idU`) values (?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
              LocalDateTime now = LocalDateTime.now();
            pre.setString(1, o.getDescription());
            pre.setInt(2, o.getRate());
            pre.setString(3, dtf.format(now));
           
            pre.setString(4, DashboardController.email);
            
            pre.executeUpdate();
            System.out.println("Merci pour noter !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   }

    public void supprimerr(int id) {
 try {
            String req = "DELETE FROM `feedback` WHERE id="+id+"";
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.executeUpdate();
            System.out.println("feedback Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
    public int deletefeedback(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `feedback` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFeedback.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
     public int getNbrFeedback() {
        String sql="SELECT COUNT(*) FROM `feedback` ";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
     public int moyenne(){
         int  m;
         m=(getNbrFeedback1()*1+getNbrFeedback2()*2+getNbrFeedback3()*3+getNbrFeedback4()*4+getNbrFeedback5()*5)/getNbrFeedback();
         return m;
     }
     public int getNbrFeedback5() {
        String sql="SELECT COUNT(*) FROM `feedback` where rate=5";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
     public int getNbrFeedback1() {
        
        String sql="SELECT COUNT(*) FROM `feedback` where rate=1";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
      public int getNbrFeedback4() {
        String sql="SELECT COUNT(*) FROM `feedback` where rate=4";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
       public int getNbrFeedback3() {
        String sql="SELECT COUNT(*) FROM `feedback` where rate=3";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
        public int getNbrFeedback2() {
        String sql="SELECT COUNT(*) FROM `feedback` where rate=2";
        ResultSet rs;
        int countIdFed=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdFed= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdFed;
    }
      public void modifier(Feedback o) {
      try {
            String req = "UPDATE `feedback` SET  `rate`=?, `idU`=?  WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
           
            pre.setInt(1, o.getRate());
            pre.setString(2, o.getUser());
            pre.setInt(3, o.getId());
            pre.executeUpdate();
            System.out.println("feedback Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
     public void supprimerf(int id) {
 try {
            String req = "DELETE FROM `feedback` WHERE id="+id+"";
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.executeUpdate();
            System.out.println("feedback Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
    public List<Feedback> afficher() {
        List<Feedback> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM feedback";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               list.add(new Feedback(rs.getInt("id"),rs.getString("description"), rs.getInt("rate"), rs.getString("date"),rs.getString("idU")));
                 
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
     public List<Feedback> afficherdes() {
        List<Feedback> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM feedback order by id desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               list.add(new Feedback(rs.getInt("id"),rs.getString("description"), rs.getInt("rate"), rs.getString("date"),rs.getString("idU")));
                 
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
     }
    public int recherche(String idU ) {
        List<Feedback> listf = new ArrayList<>();
        try {
            String requete = "SELECT * FROM feedback where idU="+idU+"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listf.add(new Feedback(rs.getInt("id"), rs.getString("description"), rs.getInt("rate"),rs.getString("rate"), rs.getString("idu")));
                
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return 1;
    }

}

