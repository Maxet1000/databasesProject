package be.kuleuven.VGHF.domain;

import javax.persistence.*;

import be.kuleuven.VGHF.enums.Availability;

import java.util.*;

@Entity
public class Copy {

    @Column(nullable = false) 
    @Id 
    @GeneratedValue
    private int copyID;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "title", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consoleName", nullable = false)
    private Console console;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;
    
    @OneToMany(mappedBy = "copy")
    private List<MonetaryTransaction> transactions;

    @Column
    private int purchasePrice;

    @Column
    private int rentPrice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Availability availability;

    @Column
    private String dateOfReturn;
    
    @Column(nullable = false)
    private String warehouse;

    public Copy(){}

    private Copy(CopyBuilder builder) {
        this.game = builder.game;
        this.console = builder.console;
        this.availability = builder.availability;
        this.warehouse = builder.warehouse;
        this.customer = builder.customer;
        this.purchasePrice = builder.purchasePrice;
        this.rentPrice = builder.rentPrice;
        this.dateOfReturn = builder.dateOfReturn;
    }

    public static class CopyBuilder {
        private Game game;
        private Console console;
        private Availability availability;
        private String warehouse;
        private Customer customer;
        private int purchasePrice;
        private int rentPrice;
        private String dateOfReturn;

        public CopyBuilder game(Game game) {
            this.game = game;
            return this;
        }
    
        public CopyBuilder console(Console console) {
            this.console = console;
            return this;
        }
    
        public CopyBuilder availability(Availability availability) {
            this.availability = availability;
            return this;
        }
    
        public CopyBuilder warehouse(String warehouse) {
            this.warehouse = warehouse;
            return this;
        }

        public CopyBuilder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public CopyBuilder purchasePrice(int purchasePrice) {
            this.purchasePrice = purchasePrice;
            return this;
        }
    
        public CopyBuilder rentPrice(int rentPrice) {
            this.rentPrice = rentPrice;
            return this;
        }
    
        public CopyBuilder dateOfReturn(String dateOfReturn) {
            this.dateOfReturn = dateOfReturn;
            return this;
        }
        
        public Copy build() {
            return new Copy(this);
        }
    }

    public int getCopyID() {
        return this.copyID;
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

    public Availability getAvailability() {
        return this.availability;
    }

    public void setAvailability(Availability availability) {
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