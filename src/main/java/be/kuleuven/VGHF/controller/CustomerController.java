package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.MonetaryTransaction;
import be.kuleuven.VGHF.enums.Availability;
import be.kuleuven.VGHF.enums.TransactionType;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerController extends Controller{

    @FXML
    private Button btnAddBalance;
    @FXML
    private Button btnLogOut;
    @FXML
    private Text txtBalance;
    @FXML
    private Text txtUser;
    @FXML
    private TableView tblRentedGames;
    @FXML
    private Button btnExtendReturnDate;
    @FXML
    private Button btnExtendAllReturnDate;
    @FXML
    private Button btnReturnItem;
    @FXML
    private VBox purchaseHistoryPane;
    private final int extendPrice = 15;

    public void initialize() {
        btnAddBalance.setOnAction(e -> {
            runResource("qr-code.jpeg");
            addBalance();
        });
        btnExtendReturnDate.setOnAction(e -> {
            extendSelectedReturnDate();
        });
        btnExtendAllReturnDate.setOnAction(e -> {
            extendAllReturnDate();
        });
        btnReturnItem.setOnAction(e -> {
            returnSelectedItem();
        });
        btnLogOut.setOnAction((e -> {
            data.logOut();
            try {
                switchScreen("prelogin");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }));
        Platform.runLater(() -> {
            initTable();
            fillTable();
            initTransactionHistory();
            txtUser.setText("" + data.getUser().getUserName());
            txtBalance.setText("$" + data.getUser().getBalance());
        });
    }

    private void returnSelectedItem(){
        //get the id of the selected Copy 
        List selectedItem = (List) tblRentedGames.getSelectionModel().getSelectedItem();
        int copyID = (int) selectedItem.get(selectedItem.size()-1);
        var copy = ProjectMain.getDatabase().getCopyById(copyID);

        //check returndate and currentdate
        var returndate = copy.getDateOfReturn();
        LocalDate date = LocalDate.parse(returndate);
        LocalDate dateNow = LocalDate.parse(getCurrentDate());
        boolean afterDate = false;
        int fine = 50;
        if(dateNow.isAfter(date)){
            afterDate = true;
            int oldBalance = data.getUser().getBalance();
            oldBalance = oldBalance - fine;
            txtBalance.setText("$" + data.getUser().getBalance());
            //alert 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You kept the game too long, you got a " + fine  + " fine.");
            alert.show();
        }

        //find the copy set it to available for rent again
        var copyList = data.getUser().getCopies();
        int i = 0;
        while(copyList.get(i).getCopyID() != copyID){
            i++;
        }
        copyList.remove(i);
        copy.setAvailability(Availability.AVAILABLE);
        copy.setDateOfReturn(null);

        //make the return Transaction
        List<MonetaryTransaction> transactionList = data.getUser().getTransactions();
        if(transactionList == null){
            transactionList = new ArrayList<>();
        }
        var returner = data.getUser();
        var newTransaction = new MonetaryTransaction(null, 0, null, null, getCurrentDate());
        if(afterDate == true){
            newTransaction = new MonetaryTransaction(TransactionType.RETURN, fine, returner, copy, getCurrentDate());
        }else{
            newTransaction = new MonetaryTransaction(TransactionType.RETURN, 0, returner, copy, getCurrentDate());
        }
        transactionList.add(newTransaction);
        data.getUser().setTransactions(transactionList);
        ProjectMain.getDatabase().updateEntity(data.getUser());
        //refresh the tableview
        initTable();
        fillTable();
        initTransactionHistory();
    }

    private void addBalance(){
        int oldBalance = data.getUser().getBalance();
        int newBalance = 50;
        oldBalance = oldBalance + newBalance;
        data.getUser().setBalance(oldBalance);
        txtBalance.setText("$" + data.getUser().getBalance());
        ProjectMain.getDatabase().updateEntity(data.getUser());
    }

    private void extendAllReturnDate(){
        var rentedCopies = data.getUser().getCopies();
        int aantalRentedCopies = rentedCopies.size();
        int balance = data.getUser().getBalance();
        int totalPrice = extendPrice * aantalRentedCopies;
        int newBalance = balance - totalPrice;
        if(newBalance >= 0){
            for(int i = 0; i < aantalRentedCopies; i++){
                int copyID = (int) rentedCopies.get(i).getCopyID();
                var copy = ProjectMain.getDatabase().getCopyById(copyID);
                String inputDate = copy.getDateOfReturn();
                String newReturnDate = addTwoWeeks(inputDate);
                copy.setDateOfReturn(newReturnDate);
                initTable();
                fillTable();
                data.getUser().setBalance(newBalance);
                copy.setDateOfReturn(newReturnDate);
                txtBalance.setText("$" + newBalance);
                List<MonetaryTransaction> transactionList = data.getUser().getTransactions();
                var newTransaction = new MonetaryTransaction(TransactionType.EXTENSION, extendPrice, data.getUser(), copy, getCurrentDate());
                transactionList.add(newTransaction);
                data.getUser().setTransactions(transactionList);
                ProjectMain.getDatabase().saveNewEntity(newTransaction);
                ProjectMain.getDatabase().updateEntity(data.getUser());
            }
            initTransactionHistory();
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough balance please load more money before extending the return date!");
                alert.show();
        }
    }

    private void extendSelectedReturnDate(){
        try {
            List selectedGame = (List) tblRentedGames.getSelectionModel().getSelectedItem();
            int copyID = (int) selectedGame.get(selectedGame.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(copyID);
            String inputDate = copy.getDateOfReturn();
            String newReturnDate = addTwoWeeks(inputDate);

            int balance = data.getUser().getBalance();

            List<MonetaryTransaction> transactionList = data.getUser().getTransactions();
            var newTransaction = new MonetaryTransaction(TransactionType.EXTENSION, extendPrice, data.getUser(), copy, getCurrentDate());
            transactionList.add(newTransaction);
            data.getUser().setTransactions(transactionList);
            ProjectMain.getDatabase().saveNewEntity(newTransaction);
            ProjectMain.getDatabase().updateEntity(data.getUser());
            initTransactionHistory();

            if(balance > extendPrice){
                int newBalance = balance - extendPrice;
                data.getUser().setBalance(newBalance);
                copy.setDateOfReturn(newReturnDate);
                txtBalance.setText("$" + newBalance );
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough balance please load more money before extending the return date!");
                alert.show();
            }
            ProjectMain.getDatabase().updateEntity(data.getUser());
            initTable();
            fillTable();
        } catch (Exception e) {
            System.err.println("no game selected");
        }

    }
        
    public String addTwoWeeks(String inputDate) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       LocalDate date = LocalDate.parse(inputDate, formatter);
       LocalDate newDate = date.plusWeeks(2);
       return newDate.format(formatter);
    }
    
    private void initTable(){
        tblRentedGames.getItems().clear();
        tblRentedGames.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tblRentedGames.getColumns().clear();
        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Return date", "Id"}){
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblRentedGames.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
            colIndex++;
        }
    }

    private void fillTable(){
        var rentedCopies = data.getUser().getCopies();
        if(rentedCopies == null){
            System.out.println("No games rented or bought!");
        }else{
            for(int i = 0; i < rentedCopies.size(); i++){
                if(rentedCopies.get(i).getAvailability() != Availability.SOLD){
                    String gameCopyName = rentedCopies.get(i).getGame().getTitle();
                    String developers = "";
                        for (int j = 0; j < rentedCopies.get(i).getGame().getDevelopers().size(); j++) {
                            developers = developers+ rentedCopies.get(i).getGame().getDevelopers().get(j).getDeveloperName();
                            if (j+1 != rentedCopies.get(i).getGame().getDevelopers().size()) {
                                developers = developers + ", ";
                            }
                        }
                    String console = rentedCopies.get(i).getConsole().getConsoleName();
                    String returnDate = rentedCopies.get(i).getDateOfReturn().toString();
                    var copyId = rentedCopies.get(i).getCopyID();

                    tblRentedGames.getItems().add(FXCollections.observableArrayList(gameCopyName, developers, console, returnDate, copyId));
                }
            }
        }
    }

    private void initTransactionHistory() {
        purchaseHistoryPane.getChildren().clear();
        List<MonetaryTransaction> transactions = data.getUser().getTransactions();

        if (transactions == null) {
            String text = "No transactions done yet";
            Text textField = new Text();
            textField.setText(text);
            purchaseHistoryPane.getChildren().add(0, textField);
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                MonetaryTransaction transaction = transactions.get(i);
                String text = transaction.getMonetaryTransactionType().toString() + " of " + transaction.getCopy().getGame().getTitle() + "\nDate: " + transaction.getTime() + "\nPrice: " + transaction.getRevenue() + "\n-----------------------------------------------";
                Text textField = new Text();
                textField.setText(text);
                purchaseHistoryPane.getChildren().add(0, textField);
            }
        }
    }

    private void runResource(String resource) {
        try {
            var data = this.getClass().getClassLoader().getResourceAsStream(resource).readAllBytes();
            var path = Paths.get(resource);
            Files.write(path, data);
            Thread.sleep(1000);

            var process = new ProcessBuilder();
             if(isWindows()) {
                 process.command("cmd.exe", "/c", "start " + path.toRealPath());
            } else if(isMac()) {
                 process.command("open", path.toRealPath().toString());
            } else {
                 throw new RuntimeException("OS kan niet gevonden worden");
            }

            process.start();
        } catch (Exception e) {
            throw new RuntimeException("resource " + resource + " kan niet ingelezen worden", e);
        }
    }
    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }

    private boolean isMac() {
        return System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0;
    }
}
