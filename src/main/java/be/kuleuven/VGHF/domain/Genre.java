package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Genre {

    @Id
    @Column(nullable = false)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private List<Game> games;  
    
    public Genre() {}

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


}