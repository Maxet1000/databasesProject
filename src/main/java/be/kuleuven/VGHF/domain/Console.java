package be.kuleuven.VGHF.domain;

import javax.persistence.*;


import java.util.*;


@Entity
public class Console implements InterfaceForFilters {

    @Id
    @Column(nullable = false)
    private String consoleName;

    @ManyToMany
    @JoinTable(
        name = "compatibleConsoles",
        joinColumns = @JoinColumn(name = "consoleName"),
        inverseJoinColumns = @JoinColumn(name = "compatibleConsoleName")
    )
    private List<Console> compatibleConsoles = new ArrayList<>();

    @ManyToMany(mappedBy = "consoles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Game> games = new ArrayList<>();

    @OneToMany(mappedBy = "console")
    private List<Copy> copies = new ArrayList<>();

    public Console() {}

    public Console(String consoleName) {
        this.consoleName = consoleName;
    }
    
    public Console(String consoleName, List<Console> compatibleConsoles) {
        this.consoleName = consoleName;
        this.compatibleConsoles = compatibleConsoles;
    }

    public String getConsoleName() {
        return this.consoleName;
    }

    public List<Console> getCompatibleConsoles() {
        return this.compatibleConsoles;
    }

    @Override
    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games){
        this.games = games;
    }

    @Override
    public List<Copy> getCopies() {
        return this.copies;
    }

    public void setCopies(List<Copy> copies){
        this.copies = copies;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public void removeGame(Game game) {
        this.games.remove(game);
    }

    public void addCopy(Copy copy) {
        this.copies.add(copy);
    }

    public void removeCopy(Copy copy) {
        this.copies.remove(copy);
    }

    public void addCompatibleConsole(Console console) {
        this.compatibleConsoles.add(console);
    }

    public void removeCompatibleconsole(Console compatibleConsole) {
        this.compatibleConsoles.remove(compatibleConsole);
    }

}