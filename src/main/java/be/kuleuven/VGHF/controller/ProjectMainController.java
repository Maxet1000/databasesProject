package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.Model;
import be.kuleuven.VGHF.enums.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        data = new Model();

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
                if (data.loggedIn && data.getUser().getUserType() == UserType.ADMIN){
                    switchToId("developer");
                } else {
                    switchToId("nodeveloper");
                }
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
        btnHome.setDisable(false);
        btnGameDb.setDisable(false);
        btnCustomerPage.setDisable(false);
        btnDeveloperPage.setDisable(false);
        btnInfo.setDisable(false);
        
        switch(id){
            case "home":
                btnHome.setDisable(true);
            break;
            case "gamedb":
                btnGameDb.setDisable(true);
            break;
            case "prelogin":
            case "customer":
                btnCustomerPage.setDisable(true);
            break;
            case "developer":
            case "nodeveloper":
                btnDeveloperPage.setDisable(true);
            break;
            case "info":
                btnInfo.setDisable(true);
            break;

        }    
        switchScreen(id);
    }

}

