import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


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

    public static void main(String[] args) {
        launch(args);
    }
}
