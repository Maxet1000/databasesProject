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
    private Button btnLogOut;
    @FXML
    private Button btnVisitWebsite;
    @FXML
    private Text txtPleaseLogin;
    @FXML
    private Text txtUser;
    @FXML
    private Text txtBalance;

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
                btnLogOut.setDisable(false);
                btnSignup.setDisable(true);
                btnLogin.setDisable(true);
                txtUser.setText(data.getUser().getUserName());
                txtBalance.setText("$" + data.getUser().getBalance());
            }
        });
        btnSignup.setOnAction((e -> {
            showSignUpScherm();
            if (data.loggedIn) {
                txtPleaseLogin.setVisible(false);
                btnLogOut.setDisable(false);
                btnSignup.setDisable(true);
                btnLogin.setDisable(true);
                txtUser.setText(data.getUser().getUserName());
                txtBalance.setText("$" + data.getUser().getBalance());
            }
        }));
        btnVisitWebsite.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://gamehistory.org/"));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnLogOut.setOnAction(e -> {
            data.logOut();
            btnLogOut.setDisable(true);
            btnSignup.setDisable(false);
            btnLogin.setDisable(false);
            txtUser.setText("Not logged in yet");
            txtBalance.setText("");
        });
        Platform.runLater(() -> {
            if (data.loggedIn) {
                txtUser.setText(data.getUser().getUserName());
                txtBalance.setText("$" + data.getUser().getBalance());
                btnLogOut.setDisable(false);
                btnSignup.setDisable(true);
                btnLogin.setDisable(true);
            } else {
                txtUser.setText("Not logged in yet");
                btnLogOut.setDisable(true);
                btnSignup.setDisable(false);
                btnLogin.setDisable(false);
            }
        });
    }
}
