package be.kuleuven.dbproject.domain;

import javax.persistence.*;

@Entity
public class Game {
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String releaseDeate;

}
