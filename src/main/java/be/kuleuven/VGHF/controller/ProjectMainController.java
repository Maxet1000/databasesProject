package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
public class ProjectMainController extends Controller{

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
    @FXML
    private StackPane parentPane;
    

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
                //switch to pane
                /*var pane = new FXMLLoader(getClass().getClassLoader().getResource(id + ".fxml"));
                var rootLoader = (VBox) pane.load();

                var controller = pane.<Controller>getController();
                controller.setModel(data);

                //System.out.println("Yah " + pane.<Controller>getController().getModel().getUser().getCustomerName());
                rootLoader.autosize();
                pane1.getChildren().setAll(rootLoader);
                rootLoader.setAlignment(Pos.CENTER);*/
    }

}

