package be.kuleuven.VGHF;
import java.util.Objects;

import be.kuleuven.VGHF.domain.User;


public class Model {

    public User user;
    public boolean loggedIn = false;

    public Model() {
    }

    public Model(User user) {
        this.user = user;
    }
    

    //getters and setters

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        loggedIn = true;
    }
    
    public void logOut() {
        loggedIn = false;
        this.user = null;
    }

}