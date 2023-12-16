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

public class HomeController extends Controller{

    @FXML
    public VBox pane1;
    @FXML
    private Button btnRentOrBuy;
    @FXML
    private Button btnLogin;

    public void initialize() throws IOException {
        btnRentOrBuy.setOnAction(e -> {
            try {
                switchToRentOrBuy("rent");
            } catch (IOException ex) {
                throw new RuntimeException (ex);
            }
        });
        btnLogin.setOnAction(e -> {
            showScherm("customerloginpage");
        });
    }


    public void switchToRentOrBuy (String RentOrBuy) throws IOException {
        //showScherm("rentgames");
        //showScherm("buygames");

        var pane = new FXMLLoader(getClass().getClassLoader().getResource(RentOrBuy + "games.fxml"));
        var rootLoader = (VBox) pane.load();

        var controller = pane.<Controller>getController();
        controller.setModel(data);

        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }

        private void showScherm(String id) {
            var resourceName = id + ".fxml";
            try {
                var stage = new Stage();
                var pane = new FXMLLoader(getClass().getClassLoader().getResource(resourceName));
                var root = (VBox) pane.load();

                var controller = pane.<CustomerLoginController>getController();
                controller.setModel(data);

                var scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle(id);
                root.setAlignment(Pos.CENTER);
                stage.initOwner(ProjectMain.getRootStage());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.show();

            } catch (Exception e) {
                throw new RuntimeException("Kan scherm " + resourceName + " niet vinden", e);
            }
        }
}
