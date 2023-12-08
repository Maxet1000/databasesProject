package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Game {
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String releaseDeate;

    @ManyToMany
    @JoinTable(
        name = "GameConsoleLink",
        joinColumns = @JoinColumn(name = "gameID"),
        inverseJoinColumns = @JoinColumn(name = "consoleID")
    )
    private Set<Console> consoles;

    @ManyToMany
    @JoinTable(
        name = "GameDeveloperLink",
        joinColumns = @JoinColumn(name = "gameID"),
        inverseJoinColumns = @JoinColumn(name = "developerID")
    )
    private Set<Developer> developers;

}
