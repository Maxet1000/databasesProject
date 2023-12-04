package be.kuleuven.dbproject.domain;

import javax.persistence.*;

@Entity
public class Transaction {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int transactionID;

    @Column(nullable = false)
    private int revenue;

    @Column(nullable = false)
    private String transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copyID")
    private Copy copy;
}