package be.kuleuven.VGHF.domain;

import javax.persistence.*;
import java.util.*;




@Entity
public class Copy {

    @Column(nullable = false) 
    @Id 
    @GeneratedValue
    private int copyID;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "gameID", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consoleID", nullable = false)
    private Console console;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @Column
    private int purchasePrice;

    @Column
    private int rentPrice;

    @Column
    private int availability;

    @Column
    private String dateOfReturn;
    
    @Column(nullable = false)
    private String warehouse;

    @OneToMany(mappedBy = "copy")
    private List<MonetaryTransaction> transactions;

    public Copy(){}

    public Copy(int purchasePrice, int rentPrice, int availability, String dateOfReturn, String warehouse) {
        this.purchasePrice = purchasePrice;
        this.rentPrice = rentPrice;
        this.availability = availability;
        this.dateOfReturn = dateOfReturn;
        this.warehouse = warehouse;
        //transactions = new ArrayList<>();
    }


    public int getCopyID() {
        return this.copyID;
    }

    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Console getConsole() {
        return this.console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getRentPrice() {
        return this.rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getAvailability() {
        return this.availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public String getDateOfReturn() {
        return this.dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getWarehouse() {
        return this.warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public List<MonetaryTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<MonetaryTransaction> transactions) {
        this.transactions = transactions;
    }


    
    

}