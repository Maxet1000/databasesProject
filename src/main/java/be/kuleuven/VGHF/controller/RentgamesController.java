package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Console;
import be.kuleuven.VGHF.domain.Developer;
import be.kuleuven.VGHF.domain.Genre;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.VBox;

import java.util.List;

public class RentgamesController {
    
    @FXML
    private TableView tblRent;
    @FXML
    private VBox filtersBox;
    @FXML
    private TreeView<String> filtersTreeView;

    public void initialize(){
        initTable();
        List<Developer> listOfDevelopers = getAllDevelopers();
        List<Console> listOfConsoles = getAllConsoles();
        List<Genre> listOfGenres = getAllGenres();
        TreeItem<String> developersTreeItem = new CheckBoxTreeItem<>("Developers");
        TreeItem<String> consolesTreeItem = new CheckBoxTreeItem<>("Consoles");
        TreeItem<String> genresTreeItem = new CheckBoxTreeItem<>("Genres");

        developersTreeItem.setExpanded(true);
        consolesTreeItem.setExpanded(true);
        genresTreeItem.setExpanded(true);
        filtersTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        for (int i=0; i<listOfDevelopers.size(); i++ ) {
            Developer developer = listOfDevelopers.get(i);
            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(developer.getDeveloperName());
            developersTreeItem.getChildren().add(checkBoxTreeItem);
        }

        for (int i=0; i<listOfConsoles.size(); i++ ) {
            Console console = listOfConsoles.get(i);
            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(console.getConsoleName());
            consolesTreeItem.getChildren().add(checkBoxTreeItem);
        }

        for (int i=0; i<listOfGenres.size(); i++ ) {
            Genre genre = listOfGenres.get(i);
            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(genre.getGenreName());
            genresTreeItem.getChildren().add(checkBoxTreeItem);
        }

        TreeItem<String> tree = new TreeItem<>();
        tree.getChildren().add(consolesTreeItem);
        tree.getChildren().add(developersTreeItem);
        tree.getChildren().add(genresTreeItem);
        filtersTreeView.setRoot(tree);
        filtersTreeView.setShowRoot(false);
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
