package be.kuleuven.VGHF.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PreLoginController extends Controller {

    @FXML
    public Button btnSignup;
    @FXML
    private Button btnLogin;
    @FXML
    private VBox parentPane;

    public void initialize() {
        btnLogin.setOnAction(e -> {
            try {
                login();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnSignup.setOnAction(e -> {
            try {
                signUp();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void signUp() throws IOException {
        showSignUpScherm();
        System.out.println("ayhah "+ data.getUser().getuserName());
        if (data.loggedIn) {
            switchScreen("customer");
        }
    }

    private void login() throws IOException {
        showLoginScherm();
        if (data.loggedIn) {
            switchScreen("customer");
        }
    }
}
