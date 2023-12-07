package be.kuleuven.dbproject.domain;

import javax.persistence.*;

@Entity
public class Developer {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int developerID;

    @Column(nullable = false)
    private String developerNameString;
}