package Services;
import Config.MyConnection;
import Entities.Reclamation;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Templates.AdminGui.DashboardController;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import static jdk.nashorn.internal.runtime.Debug.id;
/**
 *
 * @author USER
 */
public class ServiceReclamation implements IService<Reclamation>{
    Connection cnx = MyConnection.getInstance().getConn();

    private Statement ste;
    private PreparedStatement pst;
    @Override
    public void ajouter(Reclamation r) {
          try {
                       
            String req = "INSERT INTO `reclamation` (`type`, `ido`, `sujet`,"
                    + "`description`, `date`, `etat`, `idU`) values (?,?,?,?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");


              LocalDateTime now = LocalDateTime.now();
            pre.setString(1, r.getType());
            pre.setString(2, r.getIdo());
            pre.setString(3, r.getSujet());
            pre.setString(4, r.getDescription());
            pre.setString(5, dtf.format(now));
            pre.setString(6, r.getEtat());
            pre.setString(7, DashboardController.email);
            pre.executeUpdate();
            System.out.println("Personne Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }

     public void supprimerr(int id) {
 try {
            String req = "DELETE FROM `reclamation` WHERE id="+id+"";
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.executeUpdate();
            System.out.println("reclamation Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
      /*  public BarChart loadChart() {
        int nbr = getNbrReclamation();
         NumberAxis xAxis = new NumberAxis();
     CategoryAxis yAxis = new CategoryAxis();
      String itemA = "Attendance";
      String itemB = "CT_Marks";
      String itemC = "Assignment";
      String itemD = "Others";
     Statement statement = null;
     int i=1;
     int j=2;
  XYChart.Data<String, Number> data =  new XYChart.Data<String, Number>();
  Pane pane=new Pane();
  pane.setPrefSize(600, 500);
  BarChart<String,Number> bchart=new BarChart<String,Number>(yAxis, xAxis);
  bchart.setPrefSize(550, 450);
  bchart.setTitle("Summary");
     xAxis.setLabel("Values");
     xAxis.setTickLabelRotation(45);
     yAxis.setTickLabelRotation(45);
     yAxis.setLabel("Menus");
     XYChart.Series series1 = new XYChart.Series();
     XYChart.Series series2 = new XYChart.Series();
        try{
         String sql="select id_rec, from reclamation";
         ResultSet rset=statement.executeQuery(sql);
         while(rset.next()){
           XYChart.Data<String, Number> datax =  new XYChart.Data<String, Number>(rset.getString(i),nbr);
             System.out.println("nbr="+nbr);
            double totale = getNbrFeedback4()+getNbrReclamation();
         series1.getData().add(new XYChart.Data("Reclamations",getNbrReclamation()));
         series1.getData().add(new XYChart.Data("% des techniciens", (getNbrReclamation()/totale)*100));
         series1.getData().add(new XYChart.Data("% des techniciens", (getNbrFeedback4()/totale)*100));

         DecimalFormat df = new DecimalFormat("0.00");
        
          
          
         } i++;j++;
      
        }catch(Exception e){
        
        }
     bchart.getData().addAll(series1);
     pane.getChildren().add(bchart);
    
   return bchart;
    }
*/
 
    public int getNbrReclamation() {
        String sql="SELECT COUNT(*) FROM reclamation";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
    
     
   
    @Override
    public void supprimer(Reclamation r) {
 try {
            String req = "DELETE FROM reclamation WHERE id="+r.getId();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("reclamation Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
  public int deletereclamation(int id) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `reclamation` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
    @Override 
    public void modifier(Reclamation r) {
      try {
            String req = "UPDATE `reclamation` SET `type`=?, `sujet`=?, `description`=? , `ido`=?  WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setString(1, r.getType());
            pre.setString(2, r.getSujet());
            pre.setString(3, r.getDescription());
            pre.setString(4, r.getIdo());
            pre.setInt(5, r.getId());
             
            pre.executeUpdate();
            System.out.println("reclamation Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
    public int updateEtat(Reclamation r) throws SQLException {

        String requestUpdate = "UPDATE `reclamation` SET `etat`=?  WHERE `id`=?";
        PreparedStatement pre = cnx.prepareStatement(requestUpdate);
        pre.setString(1, r.getEtat());
        pre.setInt(2, r.getId());
        System.out.println(r);
        return pre.executeUpdate();
    }
        public int updateDescription(Reclamation r) throws SQLException {

        String requestUpdate = "UPDATE `reclamation` SET `description`=?  WHERE `id`=?";
        PreparedStatement pre = cnx.prepareStatement(requestUpdate);
        pre.setString(1, r.getDescription());
        pre.setInt(2, r.getId());
        System.out.println(r);
        return pre.executeUpdate();
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getString("type"), rs.getString("ido"), rs.getString("sujet"),rs.getString("description"), rs.getString("date"), rs.getString("etat"), rs.getString("idU")));
                
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
      public List<Reclamation> afficherdes() {
        List<Reclamation> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation order by id  desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getString("type"), rs.getString("ido"), rs.getString("sujet"),rs.getString("description"), rs.getString("date"), rs.getString("etat"), rs.getString("idU")));
                
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
      } 
   
    public List<Reclamation> recherche(int id ) {
        List<Reclamation> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation where id="+id+"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getString("type"), rs.getString("ido"), rs.getString("sujet"),rs.getString("description"), rs.getString("date"), rs.getString("etat"), rs.getString("idU")));
                
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
   
    
}
