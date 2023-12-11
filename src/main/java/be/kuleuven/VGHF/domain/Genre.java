package be.kuleuven.VGHF.domain;

import javax.persistence.*;

import java.util.List;

@Entity
public class Genre {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int genreID;

    @Column(nullable = false)
    private String genreName;

    @ManyToMany(mappedBy = "genres")
    private List<Game> games;   

}