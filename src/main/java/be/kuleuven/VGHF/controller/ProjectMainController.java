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
    private Button btnGameDb;
    @FXML
    private Button btnCustomerPage;
    @FXML
    private Button btnDeveloperPage;
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
        btnGameDb.setOnAction(e -> {
            try {
                switchToId("gamedb");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnCustomerPage.setOnAction(e -> {
            try {
                switchToId("customerloginpage");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnDeveloperPage.setOnAction(e -> {
            try {
                switchToId("developerloginpage");
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
        btnGameDb.setUnderline(false);
        btnCustomerPage.setUnderline(false);
        btnDeveloperPage.setUnderline(false);
        btnInfo.setUnderline(false);
        
        switch(id){
            case "home":
                btnHome.setUnderline(true);
            break;
            case "gamedb":
                btnGameDb.setUnderline(true);
            break;
            case "customerloginpage":
                btnCustomerPage.setUnderline(true);
            break;
            case "developerloginpage":
                btnDeveloperPage.setUnderline(true);
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

