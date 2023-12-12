package be.kuleuven.VGHF.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class CustomerLoginController {

    @FXML
    private Button btnLogin;
    @FXML
    private VBox parentPane;

    public void initialize(){
        btnLogin.setOnAction(e -> {
            try {
                switchNextScreen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void switchNextScreen() throws IOException {

        var pane = new FXMLLoader(getClass().getClassLoader().getResource("customerpage.fxml"));
        var rootLoader = (VBox) pane.load();
        rootLoader.autosize();
        parentPane.getChildren().setAll(rootLoader);
        rootLoader.setAlignment(Pos.CENTER);
    }

}
 