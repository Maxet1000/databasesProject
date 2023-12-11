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
        switchToId("home");
        btnHome.setOnAction(e -> {
            try {
                switchToId("home");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnAllGames.setOnAction(e -> {
            try {
                switchToId("allgames");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnNewGames.setOnAction(e -> {
            try {
                switchToId("newgames");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnRetroGames.setOnAction(e -> {
            try {
                switchToId("retrogames");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnInfo.setOnAction(e -> {
            try {
                switchToId("info");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    
    public void switchToId (String id) throws IOException {
        
        btnHome.setUnderline(false);
        btnAllGames.setUnderline(false);
        btnNewGames.setUnderline(false);
        btnRetroGames.setUnderline(false);
        btnInfo.setUnderline(false);
        
        switch(id){
            case "home":
                btnHome.setUnderline(true);
            break;
            case "allgames":
                btnAllGames.setUnderline(true);
            break;
            case "newgames":
                btnNewGames.setUnderline(true);
            break;
            case "retrogames":
                btnRetroGames.setUnderline(true);
            break;
            case "info":
                btnInfo.setUnderline(true);
            break;


        }    
        

        //switch to pane
        var pane = new FXMLLoader(getClass().getClassLoader().getResource(id + ".fxml"));
        var rootLoader = (VBox) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        rootLoader.setAlignment(Pos.CENTER);
    }
}

