package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Customer;
import be.kuleuven.VGHF.enums.Availability;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private int extendPrice = 15;

    public void initialize() {
        btnAddBalance.setOnAction(e -> {
            runResource("qr-code.jpeg");
        });
        btnExtendReturnDate.setOnAction(e -> {
            extendSelectedReturnDate();
        });
        btnExtendAllReturnDate.setOnAction(e -> {
            extendAllReturnDate();
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
            txtBalance.setText("" + data.getUser().getBalance());
            txtUser.setText("Logged in as: " + data.getUser().getCustomerName());
        });
    }
    private void extendAllReturnDate(){
        var rentedCopies = data.getUser().getCopies();
        int aantalRentedCopies = rentedCopies.size();
        int balance = data.getUser().getBalance();
        int newExtendPrice = extendPrice * aantalRentedCopies;
        int newBalance = balance - newExtendPrice;
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
                txtBalance.setText(newBalance + "");
            }
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough balance plz load more money before extending the return date!");
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
            //eerst checken of de customer nog genoeg geld heeft om te kunnen verlengen
            //15 euro om game te verlengen
            int balance = data.getUser().getBalance();
            if(balance > extendPrice){
                int newBalance = balance - extendPrice;
                data.getUser().setBalance(newBalance);
                copy.setDateOfReturn(newReturnDate);
                txtBalance.setText(newBalance + "");
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough balance plz load more money before extending the return date!");
                alert.show();
            }

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
        tblRentedGames.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
                 throw new RuntimeException("Ik ken uw OS niet jong");
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
