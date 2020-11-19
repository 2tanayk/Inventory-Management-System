package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = FXMLLoader.load(getClass().getResource("res/login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("User Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

