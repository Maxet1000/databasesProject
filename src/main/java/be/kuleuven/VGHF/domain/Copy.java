package be.kuleuven.dbproject.domain;

import javax.persistence.*;
import java.util.List;


@Entity
public class Copy {

    @Column(nullable = false) 
    @Id 
    @GeneratedValue
    private int copyID;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "gameID", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consoleID", nullable = false)
    private Console console;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @Column
    private int rentPrice;

    @Column
    private int availability;

    @Column
    private String dateOfReturn;
    
    @Column(nullable = false)
    private String warehouse;

    @OneToMany(mappedBy = "copy")
    private List<Transaction> transactions;

}