package be.kuleuven.VGHF;
import java.util.Objects;

import be.kuleuven.VGHF.domain.Customer;


public class DataCommunicationModel {

    private Customer user;

    public DataCommunicationModel() {
    }

    public DataCommunicationModel(Customer user) {
        this.user = user;
    }
    

    //getters and setters

    public Customer getUser() {
        return this.user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
}