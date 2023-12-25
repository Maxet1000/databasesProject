package be.kuleuven.VGHF.domain;

import javax.persistence.*;

import be.kuleuven.VGHF.enums.UserType;

import java.util.*;


@Entity
public class User {

    @Column(nullable = false)
    @Id
    @GeneratedValue
    private int userID;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(nullable = false)
    private int balance;

    @OneToMany(mappedBy = "user")
    private List<Copy> copies;

    @OneToMany(mappedBy = "user")
    private List<MonetaryTransaction> transactions;

    public User(){}

    public User(String userName, String email, String password, int balance) {
        this.userType = UserType.CUSTOMER;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public User(String userName, String email, String password) {
        this.userType = UserType.DEVELOPER;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.balance = 999999;
    }

    public int getuserID() {
        return this.userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
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

    public void removeCopy(Copy copy) {
        this.copies.remove(copy);
    }

    public UserType getUserType() {
        return this.userType;
    }

}