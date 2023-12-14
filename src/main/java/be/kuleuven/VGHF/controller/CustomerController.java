package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.DataCommunicationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerController {

    @FXML
    private Button btnAddBalance;
    @FXML
    private Button btnExtendReturnDate;

    public void initialize() {
        initTable();
        btnAddBalance.setOnAction(e -> {

        });
    }
    
    public void initTable(){
        DataCommunicationModel loggedInUser = new DataCommunicationModel();
        var x = loggedInUser.getUser();
        System.out.println(x);
    }
}
