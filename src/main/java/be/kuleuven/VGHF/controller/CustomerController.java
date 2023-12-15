package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.domain.Customer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerController extends Controller{

    @FXML
    private Button btnAddBalance;


    public void initialize() {
        initTable();
        btnAddBalance.setOnAction(e -> {

        });
        Platform.runLater(() -> {
            System.out.println("CCCCCCCCCCCCCCCCCCCCCCc "+ data.getUser().getCustomerName());
        });
    }
    
    public void initTable(){
    }
}
