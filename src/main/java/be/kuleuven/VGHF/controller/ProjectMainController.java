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
    private Button btnHome;
    @FXML
    private Button btnAllGames;
    @FXML
    private Button btnNewGames;
    @FXML
    private Button btnRetroGames;
    @FXML
    private Button btnInfo;
    @FXML
    private Text txtVGHF;
    @FXML
    private Text txtInfo;
    @FXML
    private StackPane paneHome;
    @FXML
    private StackPane paneInfo;

    public void initialize() {
        btnHome.setOnAction(e -> showHomeScherm("hwcomponenten"));
        btnInfo.setOnAction(e -> showInfoScherm("computerconfigs"));
        btnAllGames.setOnAction(e -> showBeheerScherm("attaches"));
        paneHome.setAlignment(txtVGHF, Pos.CENTER);
        paneInfo.setAlignment(txtInfo, Pos.CENTER);
        paneInfo.setVisible(false);
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
        paneInfo.setVisible(false);
        paneHome.setVisible(true);
    }
    private void showInfoScherm(String computerconfigs) {
        paneHome.setVisible(false);
        paneInfo.setVisible(true);
    }
}
