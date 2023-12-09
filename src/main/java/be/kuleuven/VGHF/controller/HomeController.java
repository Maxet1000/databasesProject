package be.kuleuven.VGHF.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import be.kuleuven.VGHF.ProjectMain;

public class HomeController {

    @FXML
    public StackPane pane1;
    @FXML
    private Button btnRent;

    public void initialize() throws IOException {
        btnRent.setOnAction(e -> {
            try {
                switchToRentGames();
            } catch (IOException ex) {
                throw new RuntimeException (ex);
            }
        });
    }


    public void switchToRentGames () throws IOException {
        showScherm("rentgames");
        /* 
        var pane = new FXMLLoader(getClass().getClassLoader().getResource("rentgames.fxml"));
        var rootLoader = (StackPane) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
        */
    }
    private void showScherm(String id) {
        var resourceName = id + ".fxml";
        try {
            var stage = new Stage();
            var root = (StackPane) FXMLLoader.load(getClass().getClassLoader().getResource(resourceName));
            var scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin " + id);
            stage.initOwner(ProjectMain.getRootStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException("Kan beheerscherm " + resourceName + " niet vinden", e);
        }
    }

}
