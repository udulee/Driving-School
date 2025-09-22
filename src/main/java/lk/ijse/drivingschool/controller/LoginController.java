package lk.ijse.drivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox chkRememberMe;

    @FXML
    private Label lblErrorMessage;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPasswordVisible;

    @FXML
    private TextField txtUsername;

    @FXML
    void handleForgotPassword(MouseEvent event) {

    }

    @FXML
    void handleLogin(ActionEvent event) {

    }

}
