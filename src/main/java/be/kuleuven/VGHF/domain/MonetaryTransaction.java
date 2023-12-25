package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;
import be.kuleuven.VGHF.enums.*;

@Entity
public class MonetaryTransaction {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int monetaryTransactionID;

    @Column(nullable = false)
    private int revenue;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType monetaryTransactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copyID")
    private Copy copy;

    public MonetaryTransaction(TransactionType monetaryTransactionType, int revenue, Customer customer, Copy copy, String time) {
        this.monetaryTransactionType = monetaryTransactionType;
        this.revenue = revenue;
        this.customer = customer;
        this.copy = copy;
        this.time = time;
    }

    public int getMonetaryTransactionID() {
        return this.monetaryTransactionID;
    }

    public int getRevenue() {
        return this.revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public TransactionType getMonetaryTransactionType() {
        return this.monetaryTransactionType;
    }

    public void setMonetaryTransactionType(TransactionType monetaryTransactionType) {
        this.monetaryTransactionType = monetaryTransactionType;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Copy getCopy() {
        return this.copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public String getTime() {
        return time;
    }
    
}