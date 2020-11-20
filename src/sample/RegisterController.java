package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.DAO.JDBCDao;

import java.io.IOException;

public class RegisterController {
    public Button registerBtn;
    public TextField nameTextField;
    public TextField registerEmailText;
    public TextField registerPasswordText;
    public Button loginSwitchBtn;


    public void register(ActionEvent event) throws Exception {
        Window owner = registerBtn.getScene().getWindow();

        System.out.println(nameTextField.getText());
        System.out.println(registerEmailText.getText());
        System.out.println(registerPasswordText.getText());

        if (nameTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (registerEmailText.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (registerPasswordText.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String fullName = nameTextField.getText();
        String emailId = registerEmailText.getText();
        String password = registerPasswordText.getText();

        JDBCDao jdbcDao = new JDBCDao();
        jdbcDao.insertRecord(fullName, emailId, password);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + nameTextField.getText());

//        loginSwitchBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//            }
//        });


    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    //method to navigate to the login page
    public void loginPage(ActionEvent event) throws IOException {
        GridPane loginGridPane = FXMLLoader.load(getClass().getResource("res/login.fxml"));
        Scene sc = new Scene(loginGridPane);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(sc);
        window.show();
    }


}
