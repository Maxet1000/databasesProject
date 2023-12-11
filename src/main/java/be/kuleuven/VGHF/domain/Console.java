package be.kuleuven.VGHF.domain;

import javax.persistence.*;

@Entity
public class Console {

    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int consoleID;

    @Column(nullable = false)
    private String consoleName;

    // Kan ook direct zelf-referentieel via hibernate, nu moet er een functie voor bijgemaakt worden
    @Column
    private int compatibleConsolID;

    public Console() {}
    
    public Console(String consoleName, int compatibleConsolID) {
        this.consoleName = consoleName;
        this.compatibleConsolID = compatibleConsolID;
    }

    public String getConsoleName() {
        return this.consoleName;
    }

    public int getCompatibleConsolID() {
        return this.compatibleConsolID;
    }

}