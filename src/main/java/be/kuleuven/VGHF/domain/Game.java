package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Game {
    
    @Id
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String releaseDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "GameConsoleLink",
        joinColumns = @JoinColumn(name = "title"),
        inverseJoinColumns = @JoinColumn(name = "consoleName")
    )
    private List<Console> consoles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "GameDeveloperLink",
        joinColumns = @JoinColumn(name = "title"),
        inverseJoinColumns = @JoinColumn(name = "developerName")
    )
    private List<Developer> developers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "GameGenreLink",
        joinColumns = @JoinColumn(name = "title"),
        inverseJoinColumns = @JoinColumn(name = "genreName")
    )
    private List<Genre> genres;

    @OneToMany(mappedBy = "game")
    private List<Copy> copies = new ArrayList<>();

    public Game() {}

    public Game(String title, String releaseDate, List<Console> consoles, List<Developer> developers, List<Genre> genres) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.consoles = consoles;
        this.developers = developers;
        this.genres = genres;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDeate) {
        this.releaseDate = releaseDeate;
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

    public List<Copy> getCopies() {
        return this.copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    public void addDeveloper(Developer developer) {
        this.developers.add(developer);
    }

    public void addCopy(Copy copy) {
        this.copies.add(copy);
    }

    public void removeCopy(Copy copy) {
        this.copies.remove(copy);
    }

}
