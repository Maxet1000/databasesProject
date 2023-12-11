package be.kuleuven.VGHF.domain;

import javax.persistence.*;

import java.util.ArrayList;
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

    public Customer(){}

    public Customer(int customerID, String customerName, String email, int balance) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.email = email;
        this.balance = balance;
        copies = new ArrayList<>();
        transactions = new ArrayList<>();
    }
    

}