package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProjectMainController {

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Text txt1;
    @FXML
    private StackPane pane;

    public void initialize() {
        btn1.setOnAction(e -> showHomeScherm("hwcomponenten"));
        btn2.setOnAction(e -> showBeheerScherm("computerconfigs"));
        btn3.setOnAction(e -> showBeheerScherm("attaches"));
        pane.setAlignment(txt1, Pos.CENTER);
    }


    private void showBeheerScherm(String id) {
        var resourceName = "beheer" + id + ".fxml";
        try {
            var stage = new Stage();
            var root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource(resourceName));
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

    private void showHomeScherm(String hwcomponenten) {
        txt1.setRotate(txt1.getRotate()+30);
    }
}
