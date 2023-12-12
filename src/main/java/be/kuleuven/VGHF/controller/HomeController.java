package be.kuleuven.VGHF.controller;

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

public class HomeController {

    @FXML
    public VBox pane1;
    @FXML
    private Button btnRent;
    @FXML
    private Button btnBuy;

    public void initialize() throws IOException {
        btnRent.setOnAction(e -> {
            try {
                switchToRentOrBuy("rent");
            } catch (IOException ex) {
                throw new RuntimeException (ex);
            }
        });
        btnBuy.setOnAction(e -> {
            try {
                switchToRentOrBuy("buy");
            } catch (IOException ex) {
                throw new RuntimeException (ex);
            }
        });
    }


    public void switchToRentOrBuy (String RentOrBuy) throws IOException {
        //showScherm("rentgames");
        //showScherm("buygames");

        var pane = new FXMLLoader(getClass().getClassLoader().getResource(RentOrBuy + "games.fxml"));
        var rootLoader = (VBox) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }

    private void showScherm(String id) {
        var resourceName = id + ".fxml";
        try {
            var stage = new Stage();
            var root = (StackPane) FXMLLoader.load(getClass().getClassLoader().getResource(resourceName));
            var scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(id);
            stage.initOwner(ProjectMain.getRootStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        } catch (Exception e) {
            throw new RuntimeException("Kan scherm " + resourceName + " niet vinden", e);
        }
    }

}
