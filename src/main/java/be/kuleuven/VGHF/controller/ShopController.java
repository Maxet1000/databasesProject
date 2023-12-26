package be.kuleuven.VGHF.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Console;
import be.kuleuven.VGHF.domain.Copy;
import be.kuleuven.VGHF.domain.Developer;
import be.kuleuven.VGHF.domain.Genre;
import be.kuleuven.VGHF.domain.MonetaryTransaction;
import be.kuleuven.VGHF.enums.Availability;
import be.kuleuven.VGHF.enums.TransactionType;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShopController extends Controller{
    
    @FXML
    private TableView tblRent;
    @FXML
    private Button btnAddGameToCart;
    @FXML
    private Button btnAddFilter;
    @FXML
    private Button btnRemoveFilters;
    @FXML
    public VBox pane1;
    @FXML
    private VBox filtersBox;
    @FXML
    private TreeView<String> filtersTreeView;
    @FXML
    private TableView tblRentCart;
    @FXML
    private Button btnRemoveFromCart;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnPurchase;
    @FXML
    private Button btnAddGameToBuy;
    @FXML
    private TableView tblBuyCart;
    @FXML
    private Text txtBalance;
    @FXML
    private Text txtUser;
    private ArrayList<Developer> toBeFilteredDevelopers;
    private ArrayList<Console> toBeFilteredConsoles;
    private ArrayList<Genre> toBeFilteredGenres;


    public ShopController() {
        toBeFilteredDevelopers = new ArrayList<>();
        toBeFilteredConsoles = new ArrayList<>();
        toBeFilteredGenres = new ArrayList<>();
    }

    public void initialize(){
        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        initTable(listOfCopies);
        initTableCart();
        initFilters();
        btnAddGameToCart.setOnAction(e -> {
            addGameToRentCart();
        });
        btnAddFilter.setOnAction(e -> {
            activateFilters();
        });
        btnRemoveFilters.setOnAction(e -> {
            removeFilters();
        });
        btnRemoveFromCart.setOnAction(e -> {
            removeGameFromCart(tblRentCart);
            removeGameFromCart(tblBuyCart);
        });
        btnPurchase.setOnAction(e -> {
            rentAndBuyGamesFromCart(tblRentCart);
            rentAndBuyGamesFromCart(tblBuyCart);
        });
        btnAddGameToBuy.setOnAction(e -> {
            addGameToBuyCart();
        });
        btnLogOut.setOnAction(e -> {
            data.logOut();
            try {
                switchScreen("home");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        Platform.runLater(() -> {
            txtUser.setText(data.getUser().getUserName());
            txtBalance.setText("$" + data.getUser().getBalance());
        });
    }
    
    public void rentAndBuyGamesFromCart(TableView table){
        var balance = data.getUser().getBalance(); 
        List listItems = table.getItems();

        //vraag al de gehuurde en verkochte games van de user op
        var copyFromUser = data.getUser().getCopies();
            if (copyFromUser == null) {
                    copyFromUser = new ArrayList<>();
            }

        int itemCounter = 0;
        while (itemCounter != listItems.size()) {
            List copyData = (List) listItems.get(itemCounter);
            int copyID = (int) copyData.get(copyData.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(copyID);      

            if(table == tblBuyCart && balance >= copy.getPurchasePrice() && copy.getAvailability() == Availability.AVAILABLE && copy.getPurchasePrice() != 0){
                //game kan verkocht worden
                //copy wordt aangepast en naar hibernate gestuurd
                balance = balance - copy.getPurchasePrice();
                copy.setAvailability(Availability.SOLD);
                copy.setDateOfReturn(twoWeeksLonger());
                ProjectMain.database.updateEntity(copy);
                copyFromUser.add(copy);
                data.getUser().setBalance(balance);

                //transactie toevoegen aan user
                List<MonetaryTransaction> transactionList = data.getUser().getTransactions();
                if(transactionList == null){
                    transactionList = new ArrayList<>();
                }
                var newTransaction = new MonetaryTransaction(TransactionType.PURCHASE, copy.getPurchasePrice(), data.getUser(), copy, getCurrentDate());
                transactionList.add(newTransaction);
                data.getUser().setTransactions(transactionList);

            } else if(table == tblRentCart && balance >= copy.getRentPrice() && copy.getAvailability() == Availability.AVAILABLE && copy.getRentPrice() != 0){
                //game kan verhuurd worden
                //copy wordt aangepast en naar hibernate gestuurd
                balance = balance - copy.getRentPrice(); 
                copy.setAvailability(Availability.RENTED);
                copy.setDateOfReturn(twoWeeksLonger());
                ProjectMain.database.updateEntity(copy);
                copyFromUser.add(copy);
                data.getUser().setBalance(balance);

                //transactie toevoegen aan user
                List<MonetaryTransaction> transactionList = data.getUser().getTransactions();
                if(transactionList == null){
                    transactionList = new ArrayList<>();
                }
                var newTransaction = new MonetaryTransaction(TransactionType.RENTAL, copy.getRentPrice(), data.getUser(), copy, getCurrentDate());
                transactionList.add(newTransaction);
                data.getUser().setTransactions(transactionList);

            }else{
                //show alert dat er iets misliep
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Game: " + copy.getGame().getTitle().toString() +" not available or not enough money!" );
                alert.show();
            }

            itemCounter++;
        }

        //refresh balance
        txtBalance.setText("" + data.getUser().getBalance());

        //refresh de gehuurde en verkochte games van de user
        data.getUser().setCopies(copyFromUser);
        listItems.clear();

        //refresh de tableview met copies
        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        initTable(listOfCopies);
        activateFilters();
    }


    public String twoWeeksLonger(){
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return futureDate.format(formatter);
    }

    public void removeGameFromCart(TableView table){
        //zit bug/feature in die de grijze geselecteerde ook verwijderd wanneer een andere element geselecteerd is in een andere lijst
        //mogelijke oplossing is selectionModel zetten op SINGLE
        var selectedItem = table.getSelectionModel().getSelectedItem();
        var data = table.getItems();
        data.remove(selectedItem);
    }

    public void addGameToRentCart(){
        try {
            List selectedItem =  (List) tblRent.getSelectionModel().getSelectedItem();
         
            int x = (int) selectedItem.get(selectedItem.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(x);
            var gameName = copy.getGame().getTitle();
            var copyId = copy.getCopyID();

            boolean doubleCopy = false;

                String developers = "";
                for (int j = 0; j < copy.getGame().getDevelopers().size(); j++) {
                    developers = developers+ copy.getGame().getDevelopers().get(j).getDeveloperName();
                    if (j+1 != copy.getGame().getDevelopers().size()) {
                        developers = developers + ", ";
                    }
                }
                String consoles = "";
                for (int j = 0; j < copy.getGame().getConsoles().size(); j++) {
                    consoles = consoles+ copy.getGame().getConsoles().get(j).getConsoleName();
                    if (j+1 != copy.getGame().getConsoles().size()) {
                        consoles = consoles + ", ";
                    }
                }
                String genres = "";
                for (int j = 0; j < copy.getGame().getGenres().size(); j++) {
                    genres = genres+ copy.getGame().getGenres().get(j).getGenreName();
                    if (j+1 != copy.getGame().getGenres().size()) {
                        genres = genres + ", ";
                    }
                }
                 String rentPrice = "" + copy.getRentPrice();
            
                ObservableList<List> items = tblRentCart.getItems();
                for (List item : items) {
                    if (item.get(item.size()-1).equals(copyId)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("This copy is already in your cart");
                        alert.showAndWait();
                        doubleCopy = true; 
                    }
                }
                ObservableList<List> items1 = tblBuyCart.getItems();
            for (List item : items1) {
                if (item.get(item.size()-1).equals(copyId)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("This copy is already in your cart");
                    alert.showAndWait();
                    doubleCopy = true; 
                }
            }
        if (!doubleCopy){
            tblRentCart.getItems().add(FXCollections.observableArrayList(gameName, developers, consoles, genres, rentPrice, copyId));
        }else {
            doubleCopy = false;
        }
        } catch (Exception e) {
            System.out.println("No game selected");
        }
    }

    public void addGameToBuyCart(){
        try {
            List selectedItem =  (List) tblRent.getSelectionModel().getSelectedItem();

            int x = (int) selectedItem.get(selectedItem.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(x);
            var gameName = copy.getGame().getTitle();
            var copyId = copy.getCopyID();
            boolean doubleCopy = false;

            String developers = "";
            for (int j = 0; j < copy.getGame().getDevelopers().size(); j++) {
                developers = developers+ copy.getGame().getDevelopers().get(j).getDeveloperName();
                if (j+1 != copy.getGame().getDevelopers().size()) {
                    developers = developers + ", ";
                }
            }
            String consoles = "";
            for (int j = 0; j < copy.getGame().getConsoles().size(); j++) {
                consoles = consoles+ copy.getGame().getConsoles().get(j).getConsoleName();
                if (j+1 != copy.getGame().getConsoles().size()) {
                    consoles = consoles + ", ";
                }
            }
            String genres = "";
            for (int j = 0; j < copy.getGame().getGenres().size(); j++) {
                genres = genres+ copy.getGame().getGenres().get(j).getGenreName();
                if (j+1 != copy.getGame().getGenres().size()) {
                    genres = genres + ", ";
                }
            }
            String purchasePrice = "" + copy.getPurchasePrice();

            ObservableList<List> items = tblBuyCart.getItems();
            for (List item : items) {
                if (item.get(item.size()-1).equals(copyId)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("This copy is already in your cart");
                    alert.showAndWait();
                    doubleCopy = true;
                }
            }
            ObservableList<List> items1 = tblRentCart.getItems();
            for (List item : items1) {
                if (item.get(item.size()-1).equals(copyId)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("This copy is already in your cart");
                    alert.showAndWait();
                    doubleCopy = true;
                }
            }

            if (!doubleCopy){
                tblBuyCart.getItems().add(FXCollections.observableArrayList(gameName, developers, consoles, genres, purchasePrice, copyId));
            }else {
                doubleCopy = false;
            }
        } catch (Exception e) {
            System.out.println("No game selected");
        }

    }

    private void activateFilters() {
        var listOfFilteredCopies = ProjectMain.getDatabase().getAllCopies();
        if(toBeFilteredDevelopers.isEmpty()){
            initTable(listOfFilteredCopies);
        } else {
            listOfFilteredCopies = filter(listOfFilteredCopies, toBeFilteredDevelopers);
        }
        if(toBeFilteredConsoles.isEmpty()) {
            initTable(listOfFilteredCopies);
        } else {
            listOfFilteredCopies = filter(listOfFilteredCopies, toBeFilteredConsoles);
        }
        if (toBeFilteredGenres.isEmpty()) {
        } else {
            listOfFilteredCopies = filter(listOfFilteredCopies, toBeFilteredGenres);
        }
        initTable(listOfFilteredCopies);
    }

    public void removeFilters() {
        filtersTreeView.getRoot().getChildren().clear();
        toBeFilteredConsoles.clear();
        toBeFilteredDevelopers.clear();
        toBeFilteredGenres.clear();
        initFilters();
        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        initTable(listOfCopies);
    }

    public void initTableCart(){
        tblRentCart.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tblRentCart.getColumns().clear();

        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre", "Rent Price", "Id"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblRentCart.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
            colIndex++;
        }

        tblBuyCart.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tblBuyCart.getColumns().clear();

        int colIndex1 = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre", "Purchase Price", "Id"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex1;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblBuyCart.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
            colIndex1++;
        }
    }

    public void initTable(List<Copy> listOfCopies){
        tblRent.getItems().clear();
        tblRent.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tblRent.getColumns().clear();

        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre", "Rent Price", "Purchaseprice", "Id"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblRent.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
            colIndex++;
        }

        System.out.println(listOfCopies);

        for(int i = 0; i < listOfCopies.size(); i++) {
            if(listOfCopies.get(i).getAvailability() == Availability.AVAILABLE && (listOfCopies.get(i).getPurchasePrice() != 0 || listOfCopies.get(i).getRentPrice() != 0)){
                var gameCopyName = listOfCopies.get(i).getGame().getTitle();
                var copyId = listOfCopies.get(i).getCopyID();

                String developers = "";
                for (int j = 0; j < listOfCopies.get(i).getGame().getDevelopers().size(); j++) {
                    developers = developers+ listOfCopies.get(i).getGame().getDevelopers().get(j).getDeveloperName();
                    if (j+1 != listOfCopies.get(i).getGame().getDevelopers().size()) {
                        developers = developers + ", ";
                    }
                }
                String console = listOfCopies.get(i).getConsole().getConsoleName();

                String genres = "";
                for (int j = 0; j < listOfCopies.get(i).getGame().getGenres().size(); j++) {
                    genres = genres+ listOfCopies.get(i).getGame().getGenres().get(j).getGenreName();
                    if (j+1 != listOfCopies.get(i).getGame().getGenres().size()) {
                        genres = genres + ", ";
                    }
                }

                String rentPrice = "" + listOfCopies.get(i).getRentPrice();
                String purchasePrice = "" + listOfCopies.get(i).getPurchasePrice();
                if(rentPrice.equals("0")){
                    rentPrice = "Not available";
                }
                if(purchasePrice.equals("0")){
                    purchasePrice = "Not for sale";
                }
                tblRent.getItems().add(FXCollections.observableArrayList(gameCopyName, developers, console, genres, rentPrice, purchasePrice, copyId));
            }
        }
        System.out.println(""+ProjectMain.getDatabase().getCopyById(36).getPurchasePrice());

    }

    public void initFilters() {
        List<Developer> listOfDevelopers = getAllDevelopers();
        List<Console> listOfConsoles = getAllConsoles();
        List<Genre> listOfGenres = getAllGenres();
        TreeItem<String> developersTreeItem = new CheckBoxTreeItem<>("Developers");
        TreeItem<String> consolesTreeItem = new CheckBoxTreeItem<>("Consoles");
        TreeItem<String> genresTreeItem = new CheckBoxTreeItem<>("Genres");

        developersTreeItem.setExpanded(false);
        consolesTreeItem.setExpanded(false);
        genresTreeItem.setExpanded(false);
        filtersTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        for (int i=0; i<listOfDevelopers.size(); i++ ) {
            Developer developer = listOfDevelopers.get(i);
            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(developer.getDeveloperName());
            developersTreeItem.getChildren().add(checkBoxTreeItem);
            checkBoxTreeItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println(checkBoxTreeItem.getValue() + " selection state: " + newVal);
                if (newVal) {
                    toBeFilteredDevelopers.add(developer);
                } else {
                    toBeFilteredDevelopers.remove(developer);
                }
            });
        }

        for (int i=0; i<listOfConsoles.size(); i++ ) {
            Console console = listOfConsoles.get(i);
            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(console.getConsoleName());
            consolesTreeItem.getChildren().add(checkBoxTreeItem);
            checkBoxTreeItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println(checkBoxTreeItem.getValue() + " selection state: " + newVal);
                if (newVal) {
                    toBeFilteredConsoles.add(console);
                } else {
                    toBeFilteredConsoles.remove(console);
                }
            });
        }

        for (int i=0; i<listOfGenres.size(); i++ ) {
            Genre genre = listOfGenres.get(i);
            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(genre.getGenreName());
            genresTreeItem.getChildren().add(checkBoxTreeItem);
            checkBoxTreeItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println(checkBoxTreeItem.getValue() + " selection state: " + newVal);
                if (newVal) {
                    toBeFilteredGenres.add(genre);
                } else {
                    toBeFilteredGenres.remove(genre);
                }
            });
        }

        TreeItem<String> tree = new TreeItem<>();
        tree.getChildren().add(developersTreeItem);
        tree.getChildren().add(consolesTreeItem);
        tree.getChildren().add(genresTreeItem);
        filtersTreeView.setRoot(tree);
        filtersTreeView.setShowRoot(false);

    }

    public List<Developer> getAllDevelopers() {
        return ProjectMain.getDatabase().getAllDevelopers();
    }

    private List<Console> getAllConsoles() {
        return ProjectMain.getDatabase().getAllConsoles();
    }

    private List<Genre> getAllGenres() {
        return ProjectMain.getDatabase().getAllGenres();
    }

}