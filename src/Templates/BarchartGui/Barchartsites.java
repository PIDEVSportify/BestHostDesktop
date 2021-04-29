package Templates.BarchartGui;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import Entities.camping;
import Services.Camping_c;
import Templates.DashboardspeakyGui.Dashboard;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;


public class Barchartsites implements Initializable {
    @FXML
    public Button Dashboardbutton;
    @FXML
    private BarChart barChart;

    private ObservableList<XYChart.Series<String, Double>> barChartData;

    private ObservableList<camping> items= FXCollections.observableArrayList();

    private  Random rnd = new Random();

    private static  int SIZE = 10;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Camping_c c=new Camping_c();
        try {
            for(camping l:c.result())
                if(l.getRatecamping()!=0 || l.getAverage_rating()!=0)
                items.add(new camping(l.getDescription_camping(), Math.floor(l.getAverage_rating()/l.getRating_camping()*100)/100));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    private void handleButtonAction( ActionEvent ev) {

        ObservableList<Data<String, Double>> seriesData = new DataConvertor(items).getData();
        CategoryAxis osaX = new CategoryAxis();
        NumberAxis osaY = new NumberAxis();
        barChartData = FXCollections.observableArrayList();
        BarChart.Series<String, Double> series1 = new BarChart.Series<>();
        series1.setData(seriesData);
        barChartData.add(series1);
        barChart.setData(barChartData);
    }

    public void Dashboardbar(ActionEvent actionEvent) throws IOException {
        Dashboard Dashboard = new Dashboard();
        Dashboard.returndashboard(actionEvent);
    }

    static class DataConvertor {

        ObservableList<camping> items;
        ObservableList<Data<String, Double>> data;

        DataConvertor(ObservableList<camping> items) {
            super();
            this.items = items;
            data = FXCollections.observableArrayList();
            items.forEach(item -> data.add(new Data<>(item.getDescription_camping(),item.getRatecamping())));
            items.addListener((Change<? extends camping> arg0) -> update());
        }

        void update() {
            for (int i = 0; i < data.size(); i++) {
                Data<String, Double> d = data.get(i);
                d.setYValue(items.get(i).getRatecamping());
            }
            data.clear();
            items.forEach(item -> data.add(new Data<>(item.getDescription_camping(),item.getRatecamping())));
        }

        ObservableList<Data<String, Double>> getData() {
            return data;
        }
    }
}
