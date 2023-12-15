package be.kuleuven.VGHF;
import java.util.Objects;

import be.kuleuven.VGHF.domain.Customer;


public class DataCommunicationModel {

    public Customer user;
    public boolean loggedIn = false;

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
        loggedIn = true;
    }

    public void logOut() {
        loggedIn = false;
        this.user = null;
    }
}