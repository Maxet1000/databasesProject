package be.kuleuven.VGHF.controller;

import java.io.IOException;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.HibernateManager;
import be.kuleuven.VGHF.enums.Availability;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class RentgamesController {
    
    @FXML
    private TableView tblRent;
    @FXML
    private Button btnAddFilter;
    @FXML
    public VBox pane1;

    public void initialize(){
        initTable();
    }


    public void initTable(){
        tblRent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblRent.getColumns().clear();

        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        
        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblRent.getColumns().add(col);
            colIndex++;
        }


        for(int i = 0; i < listOfCopies.size(); i++) {
                if(listOfCopies.get(i).getAvailability() == Availability.AVAILABLE){
                    var gameCopyName = listOfCopies.get(i).getGame().getTitle();
                    /* 
                    var gameDeveloperName = listOfCopies.get(i).getGame().getDevelopers().get(1).getDeveloperName();
                    var gameConsoleName = listOfCopies.get(i).getGame().getConsoles();
                    var gameGenreName = listOfCopies.get(i).getGame().getGenres();
                    */
                    tblRent.getItems().add(FXCollections.observableArrayList(gameCopyName, "gameDeveloperName", "gameConsoleName", "gameGenreName"));
                }
        }
        
    }
}
