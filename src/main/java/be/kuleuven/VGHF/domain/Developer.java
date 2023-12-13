package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;


@Entity
public class Developer {

    @Id
    @Column(nullable = false)
    private String developerName;

    @ManyToMany(mappedBy = "developers")
    private List<Game> games;   

    public Developer (){}

    public Developer(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperName() {
        return this.developerName;
    }

    public void setDeveloperName(String developerNameString) {
        this.developerName = developerNameString;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


}