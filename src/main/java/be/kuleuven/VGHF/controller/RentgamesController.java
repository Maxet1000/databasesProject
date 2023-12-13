package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.HibernateManager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RentgamesController {
    
    @FXML
    private TableView tblRent;

    public void initialize(){
        initTable();
    }

    public void initTable(){
        tblRent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblRent.getColumns().clear();

        
        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        
        /*
        int colIndex = 0;
        //var colName = new String[]{"Game", "Developer", "Console"};
        for (var colName : new String[]{"Game", "Developer", "Console"}){
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblRent.getColumns().add(col);
            colIndex++;
        }

        for(int i = 0; i < 10; i++) {

            tblRent.getItems().add(FXCollections.observableArrayList("Budget PCke " + i, "Budget type 1", i*10 + "", i * 33 + ""));
        }
        
        for (int i = 0; i < ListOfCopies.size(); i++){
            var game = ListOfCopies.get(i).getGame();

        }*/
        


        
        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblRent.getColumns().add(col);
            colIndex++;
        }


        for(int i = 0; i < listOfCopies.size(); i++) {
            var gameCopyName = listOfCopies.get(i).getGame().getTitle();

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
            tblRent.getItems().add(FXCollections.observableArrayList(gameCopyName, developers, consoles, genres));
        }
        
    }
}
