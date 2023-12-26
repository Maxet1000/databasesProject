package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Genre implements InterfaceForFilters {

    @Id
    @Column(nullable = false)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private List<Game> games = new ArrayList<>();
    
    public Genre() {}

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return this.genreName;
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