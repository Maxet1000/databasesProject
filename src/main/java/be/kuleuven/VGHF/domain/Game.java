package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Game {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int gameID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String releaseDeate;

    @ManyToMany
    @JoinTable(
        name = "GameConsoleLink",
        joinColumns = @JoinColumn(name = "gameID"),
        inverseJoinColumns = @JoinColumn(name = "consoleID")
    )
    private List<Console> consoles;

    @ManyToMany
    @JoinTable(
        name = "GameDeveloperLink",
        joinColumns = @JoinColumn(name = "gameID"),
        inverseJoinColumns = @JoinColumn(name = "developerID")
    )
    private List<Developer> developers;

    @ManyToMany
    @JoinTable(
        name = "GameGenreLink",
        joinColumns = @JoinColumn(name = "gameID"),
        inverseJoinColumns = @JoinColumn(name = "genreID")
    )
    private List<Genre> genres;



    public int getGameID() {
        return this.gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReleaseDeate() {
        return this.releaseDeate;
    }

    public void setReleaseDeate(String releaseDeate) {
        this.releaseDeate = releaseDeate;
    }

    public List<Console> getConsoles() {
        return this.consoles;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    public List<Developer> getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}
