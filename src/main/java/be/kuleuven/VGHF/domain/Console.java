package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Console {

    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int consoleID;

    @Column(nullable = false)
    private String consoleName;

    @ManyToMany
    @JoinTable(
        name = "compatibleConsoles",
        joinColumns = @JoinColumn(name = "consoleID"),
        inverseJoinColumns = @JoinColumn(name = "compatibleConsoleID")
    )
    private List<Console> compatibleConsoles;

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