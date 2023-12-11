package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.List;


@Entity
public class Developer {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int developerID;

    @Column(nullable = false)
    private String developerNameString;

    @ManyToMany(mappedBy = "developers")
    private List<Game> games;   

    public Developer (){}
}