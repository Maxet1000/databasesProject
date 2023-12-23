package be.kuleuven.VGHF.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PreCustomerController extends Controller {

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
    }
    private void login() throws IOException {
        showLoginScherm();
        switchScreen("customer");
    }
    private void switchToId(String id) throws IOException {

        //switch to pane
        var pane = new FXMLLoader(getClass().getClassLoader().getResource(id + ".fxml"));
        var rootLoader = (VBox) pane.load();

        var controller = pane.<Controller>getController();
        controller.setModel(data);

        //System.out.println("Yah " + pane.<Controller>getController().getModel().getUser().getCustomerName());
        rootLoader.autosize();
        parentPane.getChildren().setAll(rootLoader);
        rootLoader.setAlignment(Pos.CENTER);
    }
}
