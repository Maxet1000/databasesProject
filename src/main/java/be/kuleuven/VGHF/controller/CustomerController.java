package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.domain.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerController extends Controller{

    @FXML
    private Button btnAddBalance;
    @FXML
    private Button btnExtendReturnDate;
    private DataCommunicationModel data;
    private Customer user;

    public CustomerController(DataCommunicationModel data){
        this.data = data;
    }

    public void initialize() {
        initTable();
        btnAddBalance.setOnAction(e -> {

        });
    }
    
    public void initTable(){
        user = data.getUser();
        System.out.println(user);
    }
}
