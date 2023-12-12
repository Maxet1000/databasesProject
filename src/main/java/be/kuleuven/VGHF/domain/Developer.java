package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;


@Entity
public class Developer {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int developerID;

    @Column(nullable = false)
    private String developerNameString;

    @ManyToMany(mappedBy = "developers")
    private List<Game> games;   

    public Developer (){}

    public Developer(int developerID, String developerNameString, List<Game> games) {
        this.developerID = developerID;
        this.developerNameString = developerNameString;
        this.games = games;
    }
    

    public int getDeveloperID() {
        return this.developerID;
    }

    public void setDeveloperID(int developerID) {
        this.developerID = developerID;
    }

    public String getDeveloperNameString() {
        return this.developerNameString;
    }

    public void setDeveloperNameString(String developerNameString) {
        this.developerNameString = developerNameString;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


}