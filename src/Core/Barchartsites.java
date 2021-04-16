package Core;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import Entities.camping;
import Entities.offre;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Barchartsites implements Initializable {
    @FXML
    public Button Dashboardbutton;
    @FXML
    private BarChart barChart;
    private ObservableList<XYChart.Series<String, Number>> barChartData;
    private ObservableList<camping> items= FXCollections.observableArrayList();;
    private  Random rnd = new Random();
    private static  int SIZE = 10;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Camping_c c=new Camping_c();
        Offre_c c1=new Offre_c();
        try {
            for(camping l:c.result()) {
                for (offre l1 : c1.result()) {
                    if (l.getOffre_id_id() == l1.getId()) {
                        l.setPrix_offre_camping(l1.getPrix());
                        items.add(new camping(l.getDescription_camping(), l1.getPrix()));
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    private void handleButtonAction( ActionEvent ev) {

        ObservableList<Data<String, Number>> seriesData = new DataConvertor(items).getData();
        CategoryAxis osaX = new CategoryAxis();
        NumberAxis osaY = new NumberAxis();
        barChartData = FXCollections.observableArrayList();
        BarChart.Series<String, Number> series1 = new BarChart.Series<>();
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
        ObservableList<Data<String, Number>> data;

        DataConvertor(ObservableList<camping> items) {
            super();
            this.items = items;
            data = FXCollections.observableArrayList();
            items.forEach(item -> data.add(new Data<>(item.getDescription_camping(),item.getPrix_offre_camping())));
            items.addListener((Change<? extends camping> arg0) -> update());
        }

        void update() {
            for (int i = 0; i < data.size(); i++) {
                Data<String, Number> d = data.get(i);
                d.setYValue(items.get(i).getId());
            }
            data.clear();
            items.forEach(item -> data.add(new Data<>(item.getDescription_camping(),item.getPrix_offre_camping())));
        }

        ObservableList<Data<String, Number>> getData() {
            return data;
        }
    }
}
