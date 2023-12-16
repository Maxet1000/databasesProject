package be.kuleuven.VGHF.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Customer;


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
                switchNextScreen();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Something went terribly wrong");
                alert.showAndWait();
                throw new RuntimeException(ex);
            }
        });
    }

// TODO model data veranderen na een juiste login
    private void switchNextScreen() throws IOException {
        var user = ProjectMain.getDatabase().getUserByEmail(txtEmail.getText());
        if(txtEmail.getText().equals("UwU")){
            //zelfde als laatste else
            var pane = new FXMLLoader(getClass().getClassLoader().getResource("customerpage.fxml"));
            var rootLoader = (VBox) pane.load();

            var controller = pane.<CustomerController>getController();
            controller.setModel(data);

            rootLoader.autosize();
            parentPane.getChildren().setAll(rootLoader);
            rootLoader.setAlignment(Pos.CENTER);

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
        }else{
            
            var pane = new FXMLLoader(getClass().getClassLoader().getResource("customerpage.fxml"));
            var rootLoader = (VBox) pane.load();

            var controller = pane.<Controller>getController();
            controller.setModel(data);

            rootLoader.autosize();
            parentPane.getChildren().setAll(rootLoader);
            rootLoader.setAlignment(Pos.CENTER);
        }
    }
}