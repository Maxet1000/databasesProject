package be.kuleuven.VGHF.domain;

import javax.persistence.*;

import java.util.*;


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
    private List<MonetaryTransaction> transactions;

    public Customer(){}

    public Customer(String customerName, String email, int balance) {
        this.customerName = customerName;
        this.email = email;
        this.balance = balance;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Copy> getCopies() {
        return this.copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    public List<MonetaryTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<MonetaryTransaction> transactions) {
        this.transactions = transactions;
    }


}