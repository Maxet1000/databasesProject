package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.User;
import com.sun.prism.impl.paint.PaintUtil;
import com.sun.prism.paint.Color;
import javafx.fxml.FXML;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class NewUserPageController extends Controller{

    @FXML
    private Button btnCreateAccount;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword1;
    @FXML
    private PasswordField txtPassword2;
    @FXML
    private  Text txtConfirmPassword;



    public void initialize() {
        btnCreateAccount.setOnAction(e -> {
            createAccount();
        });
    }

    private void createAccount() {
        
        //Email validator
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";         
        Pattern pat = Pattern.compile(emailRegex);
        var validEmail = pat.matcher(txtEmail.getText()).matches();
        //Password Checker (Longer than 3 chars)
        boolean validPassword = true;;
        if(txtPassword2.getText().length() < 3){
            validPassword = false;
        }
        //Username Checker (Longer then 1)
        boolean validUsername = true;
        if(txtUsername.getText().length() < 2){
            validUsername = false;
        }

        if (validUsername == true
            && validEmail == true
            && validPassword == true
            && !txtPassword2.getText().isBlank()
            && Objects.equals(txtPassword1.getText(), txtPassword2.getText())) {

            User newUser = new User(txtUsername.getText(),txtEmail.getText(),txtPassword1.getText(),0);
            ProjectMain.getDatabase().saveNewEntity(newUser);
            data.setUser(newUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setHeaderText(null);
            alert.setContentText("New account created succesfully");
            alert.showAndWait();
            Stage stage = (Stage) btnCreateAccount.getScene().getWindow();
            stage.close();
        }

        if (validUsername == false) {
            txtUsername.setStyle(txtUsername.getStyle() + "-fx-border-color: #ff8080;");
        }
        if (validEmail == false) {
            txtEmail.setStyle(txtEmail.getStyle() + "-fx-border-color: #ff8080;");
        }
        if (validPassword == false) {
            txtPassword1.setStyle(txtPassword1.getStyle() + "-fx-border-color: #ff8080;");
            System.out.println("Password not long enough!");
        }
        if (txtPassword2.getText().isBlank()) {
            txtPassword2.setStyle(txtPassword2.getStyle() + "-fx-border-color: #ff8080;");
        } else if (!Objects.equals(txtPassword1.getText(), txtPassword2.getText())) {
            txtConfirmPassword.setText("Confirm Password: Password does not match");
        }
    }

}
