package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Console {

    @Id
    @Column(nullable = false)
    private String consoleName;

    @ManyToMany
    @JoinTable(
        name = "compatibleConsoles",
        joinColumns = @JoinColumn(name = "consoleName"),
        inverseJoinColumns = @JoinColumn(name = "compatibleConsoleName")
    )
    private List<Console> compatibleConsoles;

    @ManyToMany(mappedBy = "consoles")
    private List<Game> games;  

    @OneToMany(mappedBy = "console")
    private List<Copy> copies;

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

}