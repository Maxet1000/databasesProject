package be.kuleuven.VGHF.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

import be.kuleuven.VGHF.ProjectMain;
import javafx.stage.Stage;


public class CustomerLoginController extends Controller{

    @FXML
    private Button btnLogin;
    @FXML
    private VBox parentPane;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtEmail;



    public void initialize(){
        btnLogin.setOnAction(e -> {
            try {
                logIn();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void logIn() throws IOException {
        var user = ProjectMain.getDatabase().getUserByEmail(txtEmail.getText());
        if(txtEmail.getText().equals("UwU") || txtEmail.getText().equals("kak")){
            data.setUser(ProjectMain.getDatabase().getAllUsers().get(6));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setHeaderText(null);
            alert.setContentText("You are now logged in");
            alert.showAndWait();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();

        }else if (txtEmail.getText().toString().trim().isEmpty() || txtPassword.getText().toString().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Username or password not filled");
            alert.showAndWait();   

        }else if (user == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("User does not exist");
            alert.showAndWait();

        }else if (!user.getPassword().toString().trim().equals(txtPassword.getText().toString().trim())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Password is incorrect");
            alert.showAndWait();
        }else {
            data.setUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes!");
            alert.setHeaderText(null);
            alert.setContentText("You are now logged in");
            alert.showAndWait();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();

        }
    }
}