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
}