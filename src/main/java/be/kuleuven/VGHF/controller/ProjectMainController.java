package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Customer;
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
    private DataCommunicationModel data;
    

    public ProjectMainController(){
    }

    public void initialize() throws IOException {
        data = new DataCommunicationModel();
        var homeController = new HomeController();
        switchToId("home", homeController);
        btnHome.setOnAction(e -> {
            try {
                switchToId("home", homeController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnGameDb.setOnAction(e -> {
            try {
                var gameDbController = new GameDbController();
                switchToId("gamedb", gameDbController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnCustomerPage.setOnAction(e -> {
            try {
                var loginController = new CustomerLoginController(data);
                switchToId("customerloginpage", loginController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnDeveloperPage.setOnAction(e -> {
            try {
                var developerController = new DeveloperController();
                switchToId("developerloginpage", developerController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnInfo.setOnAction(e -> {
            try {
                var infoController = new InfoController();
                switchToId("info", infoController);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    
    public void switchToId (String id, Controller controller) throws IOException {
        
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
        pane.setController(controller);
        var rootLoader = (VBox) pane.load();
        rootLoader.autosize();
        pane1.getChildren().setAll(rootLoader);
        rootLoader.setAlignment(Pos.CENTER);
    }

}

