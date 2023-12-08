package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int customerID;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int balance;

    @OneToMany(mappedBy = "customer")
    private List<Copy> copies;

    @OneToMany(mappedBy = "customer")
    private List<Transaction> transactions;

}