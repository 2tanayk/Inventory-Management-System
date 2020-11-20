package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    private Stage window;
    private Scene registerScene, loginScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        //register page
        GridPane registerRoot = FXMLLoader.load(getClass().getResource("res/register.fxml"));
        //login page
        //GridPane loginRoot = FXMLLoader.load(getClass().getResource("res/login.fxml"));
        //one time
        Scene c = new Scene(registerRoot);
        //login button
//        Button loginSwitch = new Button("Login Instead");
//
//        loginSwitch.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                c.setRoot(loginRoot);
//                window.setScene(c);
//                primaryStage.setTitle("User Login");
//            }
//        });
//        registerRoot.getChildren().add(loginSwitch);

//        Button registerSwitch = new Button("Register");
//        registerSwitch.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                c.setRoot(registerRoot);
//                window.setScene(c);
//                primaryStage.setTitle("User Registration");
//            }
//        });
//        //registerSwitch.setOnAction(event -> scene.setRoot(registerRoot));
        // loginRoot.getChildren().add(registerSwitch);


        primaryStage.setTitle("User Registration");
        primaryStage.setScene(c);
        primaryStage.show();
    }

//    public void changeScene(String fxmlFile) throws Exception {
//        FXMLLoader fxmlLoader = FXMLLoader.load(
//                getClass().getResource(fxmlFile));
//        Parent loginRoot = fxmlLoader.load();
//        if (fxmlFile.equals("calculator.fxml")) {
//            ScreenController controller = new ScreenController(new Scene());
//            controller.setModel(new BasicCalculatorModelTest(controller));
//            controller.setLogic(this);
//        } else if (fxmlFile.equals("TestSwitch.fxml")) {
//            TestSwitch controller = (TestSwitch) loader.getController();
//            controller.setLogic(this);
//        }
//        this.stage.setScene(new Scene(root));
//    }
//
//    ScreenController screenController = new ScreenController(scene);
//        screenController.add("calculator",FXMLLoader.load(


    public static void main(String[] args) {
        launch(args);
    }
}
