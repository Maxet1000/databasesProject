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
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copyID")
    private Copy copy;

    public MonetaryTransaction(TransactionType monetaryTransactionType, int revenue, User user, Copy copy, String time) {
        this.monetaryTransactionType = monetaryTransactionType;
        this.revenue = revenue;
        this.user = user;
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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