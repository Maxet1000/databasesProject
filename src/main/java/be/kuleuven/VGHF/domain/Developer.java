package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;


@Entity
public class Developer implements InterfaceForFilters {

    @Id
    @Column(nullable = false)
    private String developerName;

    @ManyToMany(mappedBy = "developers", fetch = FetchType.EAGER)
    private List<Game> games = new ArrayList<>();

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

    @Override
    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void removeGame(Game game) {
        this.games.remove(game);
    }

}