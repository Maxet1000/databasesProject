package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Customer;
import com.sun.prism.impl.paint.PaintUtil;
import com.sun.prism.paint.Color;
import javafx.fxml.FXML;

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
        if (!txtUsername.getText().isBlank()
            && !txtEmail.getText().isBlank()
            && !txtPassword1.getText().isBlank()
            && !txtPassword2.getText().isBlank()
            && Objects.equals(txtPassword1.getText(), txtPassword2.getText())) {

            Customer newCustomer = new Customer(txtUsername.getText(),txtEmail.getText(),txtPassword1.getText(),0);
            ProjectMain.getDatabase().saveNewObject(newCustomer);
            data.setUser(newCustomer);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct");
            alert.setHeaderText(null);
            alert.setContentText("New account created succesfully");
            alert.showAndWait();
            Stage stage = (Stage) btnCreateAccount.getScene().getWindow();
            stage.close();
        }

        if (txtUsername.getText().isBlank()) {
            txtUsername.setStyle(txtUsername.getStyle() + "-fx-border-color: #ff8080;");
        }
        if (txtEmail.getText().isBlank()) {
            txtEmail.setStyle(txtEmail.getStyle() + "-fx-border-color: #ff8080;");
        }
        if (txtPassword1.getText().isBlank()) {
            txtPassword1.setStyle(txtPassword1.getStyle() + "-fx-border-color: #ff8080;");
        }
        if (txtPassword2.getText().isBlank()) {
            txtPassword2.setStyle(txtPassword2.getStyle() + "-fx-border-color: #ff8080;");
        } else if (!Objects.equals(txtPassword1.getText(), txtPassword2.getText())) {
            txtConfirmPassword.setText("Confirm Password: Password does not match");
        }
    }
}
