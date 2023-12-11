package be.kuleuven.VGHF.domain;

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

    //getters and setters

    public int getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getRevenue() {
        return this.revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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