package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import sample.DAO.JDBCDao;


public class LoginController {
    public Button loginBtn;
    public TextField loginEmailText;
    public TextField loginPasswordText;

    public void login(ActionEvent event) throws Exception {
        Window owner = loginBtn.getScene().getWindow();

        System.out.println(loginEmailText.getText());
        System.out.println(loginPasswordText.getText());


        if (loginEmailText.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (loginPasswordText.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String emailId = loginEmailText.getText();
        String password = loginPasswordText.getText();

        JDBCDao jdbcDao = new JDBCDao();
        boolean flag = jdbcDao.validate(emailId, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Success!");
        }
    }


    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


}
