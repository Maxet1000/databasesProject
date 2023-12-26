package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Copy;
import be.kuleuven.VGHF.enums.Availability;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

public class GameInfoController extends Controller{


    public String gameTitle;

    @FXML
    private TableView tblCopiesTable;
    @FXML
    private Button btnExit;


    public void initialize() {
        btnExit.setOnAction(e -> {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
        });
        Platform.runLater(() -> {
            var listOfCopies = ProjectMain.getDatabase().getGameByTitle(gameTitle).getCopies();
            initTable(listOfCopies);
        });
    }

    public void initTable(List<Copy> listOfCopies){
        tblCopiesTable.getItems().clear();
        tblCopiesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblCopiesTable.getColumns().clear();

        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre", "Warehouse", "Rent Price", "Purchaseprice", "Id"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblCopiesTable.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
            colIndex++;
        }

        System.out.println(listOfCopies);

        Copy currentCopy;
        for(int i = 0; i < listOfCopies.size(); i++) {
            currentCopy = listOfCopies.get(i);
            var gameCopyName = currentCopy.getGame().getTitle();
            var copyId = currentCopy.getCopyID();

            String developers = "";
            for (int j = 0; j < currentCopy.getGame().getDevelopers().size(); j++) {
                developers = developers + currentCopy.getGame().getDevelopers().get(j).getDeveloperName();
                if (j+1 != currentCopy.getGame().getDevelopers().size()) {
                    developers = developers + ", ";
                }
            }
            String console = currentCopy.getConsole().getConsoleName();

            String genres = "";
            for (int j = 0; j < currentCopy.getGame().getGenres().size(); j++) {
                genres = genres + currentCopy.getGame().getGenres().get(j).getGenreName();
                if (j+1 != currentCopy.getGame().getGenres().size()) {
                    genres = genres + ", ";
                }
            }

            String warehouse = currentCopy.getWarehouse();
            String rentPrice = "" + currentCopy.getRentPrice();
            String purchasePrice = "" + currentCopy.getPurchasePrice();
            if(rentPrice.equals("0")){
                rentPrice = "Not available";
            }
            if(purchasePrice.equals("0")){
                purchasePrice = "Not for sale";
            }
            tblCopiesTable.getItems().add(FXCollections.observableArrayList(gameCopyName, developers, console, genres, warehouse, rentPrice, purchasePrice, copyId));
        }
        System.out.println(""+ProjectMain.getDatabase().getCopyById(36).getPurchasePrice());

    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
}
