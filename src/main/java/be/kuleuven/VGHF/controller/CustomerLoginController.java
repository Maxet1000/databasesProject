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
    private DataCommunicationModel data;



    public CustomerLoginController(DataCommunicationModel data) {
        this.data = data;
    }

    public void initialize(){
        btnLogin.setOnAction(e -> {
            try {
                switchNextScreen();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Username or password incorrect");
                alert.showAndWait();
                throw new RuntimeException(ex);
            }
        });
    }

    private void switchNextScreen() throws IOException {
        var user = ProjectMain.getDatabase().getUserByEmail(txtEmail.getText());
        System.out.println(txtEmail.getText());
        if(txtEmail.getText().equals("UwU")){
            //test om te kijken of  DataCommunicationModel werkt

            //zelfde als laatste else
            var pane = new FXMLLoader(getClass().getClassLoader().getResource("customerpage.fxml"));
            var rootLoader = (VBox) pane.load();
            rootLoader.autosize();
            parentPane.getChildren().setAll(rootLoader);
            rootLoader.setAlignment(Pos.CENTER);

        }else if (txtEmail == null || txtPassword == null){
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

        }else if (user.getPassword() != txtPassword.getText()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Password is incorrect");
            alert.showAndWait();

        }else{
            var pane = new FXMLLoader(getClass().getClassLoader().getResource("customerpage.fxml"));
            var rootLoader = (VBox) pane.load();
            rootLoader.autosize();
            parentPane.getChildren().setAll(rootLoader);
            rootLoader.setAlignment(Pos.CENTER);
        }
    }
}
 