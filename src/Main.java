import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        Parent root = null;
        try {
         root = FXMLLoader.load(getClass().getResource("Templates/LoginGui/Login.fxml"));
//            root = FXMLLoader.load(getClass().getResource("Templates/AdminGui/Dashboard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);


        scene.getStylesheets().add("Stylesheets/style.css");
        primaryStage.setTitle("BestHost");
        primaryStage.setScene(scene);

//        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
