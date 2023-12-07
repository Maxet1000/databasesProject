package be.kuleuven.dbproject.domain;

import javax.persistence.*;

@Entity
public class Genre {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int genreID;

    @Column(nullable = false)
    private String genreName;

}