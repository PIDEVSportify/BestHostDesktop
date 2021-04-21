package Templates.ChartsGui;

import Services.SceneLoader;
import Services.UserServices;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartsController  implements Initializable {
    public PieChart chrt_roles;
    public Button btn_back;
    public HBox lnk_show_users;
    public HBox lnk_show_stats;

    public Pane pane;
    public HBox lnk_dashboard_home;
    public BorderPane pane_chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserServices us = new UserServices();

        ScrollPane sp =new ScrollPane();
        sp.setContent(this.pane);
        CategoryAxis xAxis= new CategoryAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre de comptes ");
        LineChart<String,Number>  lineChart = new LineChart<String,Number>(xAxis,yAxis);
        lineChart.getData().add(us.numberOfUsers());
        lineChart.setPrefWidth(800);

        this.pane.getChildren().add(lineChart);



    }

    public void goBack()
    {
        SceneLoader.loadScene("AdminGui/Dashboard.fxml",this.btn_back);
    }


    public void showUsers()
    {
        SceneLoader.loadScene("UserGui/ShowUsers.fxml",this.lnk_show_users);

    }


    public void showStats()
    {
        SceneLoader.loadScene("ChartsGui/Charts.fxml",this.lnk_show_stats);
    }

    public void showDashboard()
    {
        SceneLoader.loadScene("AdminGui/Dashboard.fxml",this.lnk_dashboard_home);
    }


}
