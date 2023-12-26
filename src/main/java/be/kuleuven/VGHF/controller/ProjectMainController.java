package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
public class ProjectMainController extends Controller{

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

    

    public ProjectMainController(){
    }

    public void initialize() throws IOException {
        data = new DataCommunicationModel();

        switchToId("home");
        btnHome.setOnAction(e -> {
            try {
                switchToId("home");
                switchToId("home");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnGameDb.setOnAction(e -> {
            try {
                switchToId("gamedb");
                switchToId("gamedb");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnCustomerPage.setOnAction(e -> {
            try {
                if (data.loggedIn) {
                    switchToId("customer");
                } else {
                    switchToId("prelogin");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnDeveloperPage.setOnAction(e -> {
            try {
                switchToId("developerlogin");
                switchToId("developerlogin");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnInfo.setOnAction(e -> {
            try {
                switchToId("info");
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
            case "prelogin":
            case "customer":
                btnCustomerPage.setUnderline(true);
            break;
            case "developerlogin":
                btnDeveloperPage.setUnderline(true);
            break;
            case "info":
                btnInfo.setUnderline(true);
            break;

        }    
        switchScreen(id);
    }

}

