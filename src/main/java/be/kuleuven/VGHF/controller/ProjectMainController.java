package be.kuleuven.VGHF.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
public class ProjectMainController {

    @FXML
    public StackPane pane1;
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

    public void initialize() throws IOException {
        switchToHome();
        btnHome.setOnAction(e -> {
            try {
                switchToHome();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnAllGames.setOnAction(e -> {
            try {
                switchToAllGames();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnNewGames.setOnAction(e -> {
            try {
                switchtoNewGames();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnRetroGames.setOnAction(e -> {
            try {
                switchtoRetroGames();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnInfo.setOnAction(e -> {
            try {
                switchToInfo();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void switchToHome() throws IOException {
        btnHome.setUnderline(true);
        btnAllGames.setUnderline(false);
        btnNewGames.setUnderline(false);
        btnRetroGames.setUnderline(false);
        btnInfo.setUnderline(false);

        var pane = new FXMLLoader(getClass().getClassLoader().getResource("home.fxml"));
        var rootLoader = (VBox) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }

    public void switchToAllGames() throws IOException {
        btnHome.setUnderline(false);
        btnAllGames.setUnderline(true);
        btnNewGames.setUnderline(false);
        btnRetroGames.setUnderline(false);
        btnInfo.setUnderline(false);

        var pane = new FXMLLoader(getClass().getClassLoader().getResource("allgames.fxml"));
        var rootLoader = (StackPane) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }

    public void switchtoNewGames() throws IOException {
        btnHome.setUnderline(false);
        btnAllGames.setUnderline(false);
        btnNewGames.setUnderline(true);
        btnRetroGames.setUnderline(false);
        btnInfo.setUnderline(false);

        var pane = new FXMLLoader(getClass().getClassLoader().getResource("newgames.fxml"));
        var rootLoader = (StackPane) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }

    public void switchtoRetroGames() throws IOException {
        btnHome.setUnderline(false);
        btnAllGames.setUnderline(false);
        btnNewGames.setUnderline(false);
        btnRetroGames.setUnderline(true);
        btnInfo.setUnderline(false);

        var pane = new FXMLLoader(getClass().getClassLoader().getResource("retrogames.fxml"));
        var rootLoader = (StackPane) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }

    public void switchToInfo() throws IOException {
        btnHome.setUnderline(false);
        btnAllGames.setUnderline(false);
        btnNewGames.setUnderline(false);
        btnRetroGames.setUnderline(false);
        btnInfo.setUnderline(true);

        var pane = new FXMLLoader(getClass().getClassLoader().getResource("info.fxml"));
        var rootLoader = (StackPane) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }

}

