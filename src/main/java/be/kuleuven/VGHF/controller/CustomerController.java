package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Customer;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerController extends Controller{

    @FXML
    private Button btnAddBalance;
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
        Platform.runLater(() -> {
            initTable();
            fillTable();
            txtBalance.setText("" + data.getUser().getBalance());
            txtUser.setText("Logged in as: " + data.getUser().getCustomerName());
        });
    }
    private void extendAllReturnDate(){
        
    }
    //TODO
    //  geld van balance afdoen
    //  checken of genoeg geld
    private void extendSelectedReturnDate(){
        List selectedGame = (List) tblRentedGames.getSelectionModel().getSelectedItem();
        int copyID = (int) selectedGame.get(selectedGame.size()-1);
        var copy = ProjectMain.getDatabase().getCopyById(copyID);
        String inputDate = copy.getDateOfReturn();
        String newReturnDate = addTwoWeeks(inputDate);
        System.out.println(newReturnDate);
        System.out.println("Hello i am daddy and i like girls fatty");
        copy.setDateOfReturn(newReturnDate);
        initTable();
        fillTable();
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
        var rented_copies = data.getUser().getCopies();

        for(int i = 0; i < rented_copies.size(); i++){
            String gameCopyName = rented_copies.get(i).getGame().getTitle();
            String developers = "";
                for (int j = 0; j < rented_copies.get(i).getGame().getDevelopers().size(); j++) {
                    developers = developers+ rented_copies.get(i).getGame().getDevelopers().get(j).getDeveloperName();
                    if (j+1 != rented_copies.get(i).getGame().getDevelopers().size()) {
                        developers = developers + ", ";
                    }
                }
            String console = rented_copies.get(i).getConsole().getConsoleName();
            String returnDate = rented_copies.get(i).getDateOfReturn().toString();
            var copyId = rented_copies.get(i).getCopyID();

            tblRentedGames.getItems().add(FXCollections.observableArrayList(gameCopyName, developers, console, returnDate, copyId));
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
