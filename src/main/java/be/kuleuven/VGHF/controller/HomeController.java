package be.kuleuven.VGHF.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import be.kuleuven.VGHF.ProjectMain;

public class HomeController extends Controller{

    @FXML
    public VBox parentPane;
    @FXML
    private Button btnShop;
    @FXML
    private Button btnLogin;

    public void initialize() throws IOException {
        btnShop.setOnAction(e -> {
            try {
                switchScreen("shop");
            } catch (IOException ex) {
                throw new RuntimeException (ex);
            }
        });
        btnLogin.setOnAction(e -> {
            login();
        });
    }
    private void login() {
        showLoginScherm();
        System.out.println("SWAG " + data.getUser().getCustomerName());
    }
}
