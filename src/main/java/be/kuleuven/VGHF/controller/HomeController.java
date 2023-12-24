package be.kuleuven.VGHF.controller;

import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import be.kuleuven.VGHF.ProjectMain;

public class HomeController extends Controller{

    @FXML
    public VBox parentPane;
    @FXML
    private Button btnShop;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignup;
    @FXML
    private Button btnVisitWebsite;
    @FXML
    private Text txtPleaseLogin;

    public void initialize() throws IOException {
        txtPleaseLogin.setVisible(false);
        btnShop.setOnAction(e -> {
            try {
                if (!data.loggedIn) {
                    txtPleaseLogin.setVisible(true);
                } else {
                    switchScreen("shop");
                }
            } catch (IOException ex) {
                throw new RuntimeException (ex);
            }
        });
        btnLogin.setOnAction(e -> {
            showLoginScherm();
            if (data.loggedIn) {
                txtPleaseLogin.setVisible(false);
            }
        });
        btnSignup.setOnAction((e -> {
            showSignUpScherm();
            if (data.loggedIn) {
                txtPleaseLogin.setVisible(false);
            }
        }));
        btnVisitWebsite.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://gamehistory.org/"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
