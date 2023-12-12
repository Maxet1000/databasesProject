package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class MonetaryTransaction {
    
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int monetaryTransactionID;

    @Column(nullable = false)
    private int revenue;

    @Column(nullable = false)
    private String monetaryTransactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copyID")
    private Copy copy;

    //getters and setters

    public int getMonetaryTransactionID() {
        return this.monetaryTransactionID;
    }

    public void setMonetaryTransactionID(int monetaryTransactionID) {
        this.monetaryTransactionID = monetaryTransactionID;
    }

    public int getRevenue() {
        return this.revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getMonetaryTransactionType() {
        return this.monetaryTransactionType;
    }

    public void setMonetaryTransactionType(String monetaryTransactionType) {
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

    
}