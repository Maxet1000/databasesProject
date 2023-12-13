package be.kuleuven.VGHF.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Copy;
import be.kuleuven.VGHF.domain.Developer;
import be.kuleuven.VGHF.domain.Game;
import be.kuleuven.VGHF.domain.HibernateManager;
import be.kuleuven.VGHF.enums.Availability;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import java.util.List;


public class RentgamesController {
    
    @FXML
    private TableView tblRent;
    @FXML
    private Button btnAddGameToCart;
    @FXML
    public VBox pane1;
    @FXML
    private VBox filtersBox;
    @FXML
    private TableView tblCart;

    public void initialize(){
        initTable();
        initTableCart();
        List listOfDevelopers = getAllDevelopers();
        for (int i=0; i<listOfDevelopers.size(); i++ ) {
            CheckBox checkBox = new CheckBox();
            Developer developer = (Developer) listOfDevelopers.get(i);
            checkBox.setText(developer.getDeveloperName());
            filtersBox.getChildren().add(checkBox);
        }

        btnAddGameToCart.setOnAction(e -> {
                AddGameToCart();
        });
    }
    
    public void AddGameToCart(){
        
        var selectedItem =  tblRent.getSelectionModel().getSelectedItem();

        System.out.println(selectedItem);
        /* 
        for (int i = 0; i < 1; i++){
            
        }*/

    }

    public void initTableCart(){
        tblCart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblCart.getColumns().clear();

        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblCart.getColumns().add(col);
            colIndex++;
        }
    }

    public void initTable(){
        tblRent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblRent.getColumns().clear();

        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        
        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre", "Id"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblRent.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
            colIndex++;
        }


        for(int i = 0; i < listOfCopies.size(); i++) {
            if(listOfCopies.get(i).getAvailability() == Availability.AVAILABLE){
                var gameCopyName = listOfCopies.get(i).getGame().getTitle();
                var copyId = listOfCopies.get(i).getCopyID() + "";

                String developers = "";
                for (int j = 0; j < listOfCopies.get(i).getGame().getDevelopers().size(); j++) {
                    developers = developers+ listOfCopies.get(i).getGame().getDevelopers().get(j).getDeveloperName();
                    if (j+1 != listOfCopies.get(i).getGame().getDevelopers().size()) {
                        developers = developers + ", ";
                    }
                }
                String consoles = "";
                for (int j = 0; j < listOfCopies.get(i).getGame().getConsoles().size(); j++) {
                    consoles = consoles+ listOfCopies.get(i).getGame().getConsoles().get(j).getConsoleName();
                    if (j+1 != listOfCopies.get(i).getGame().getConsoles().size()) {
                        consoles = consoles + ", ";
                    }
                }
                String genres = "";
                for (int j = 0; j < listOfCopies.get(i).getGame().getGenres().size(); j++) {
                    genres = genres+ listOfCopies.get(i).getGame().getGenres().get(j).getGenreName();
                    if (j+1 != listOfCopies.get(i).getGame().getGenres().size()) {
                        genres = genres + ", ";
                    }
                }
                tblRent.getItems().add(FXCollections.observableArrayList(gameCopyName, developers, consoles, genres, copyId));
            }
        }
        
    }

    public List getAllDevelopers() {
        return ProjectMain.getDatabase().getAllDevelopers();
    }
}
