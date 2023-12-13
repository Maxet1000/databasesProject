package be.kuleuven.VGHF.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Console;
import be.kuleuven.VGHF.domain.Developer;
import be.kuleuven.VGHF.domain.Genre;
import be.kuleuven.VGHF.enums.Availability;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import java.util.List;


public class RentgamesController extends Controller{
    
    @FXML
    private TableView tblRent;
    @FXML
    private Button btnAddGameToCart;
    @FXML
    public VBox pane1;
    @FXML
    private VBox filtersBox;
    @FXML
    private TreeView<String> filtersTreeView;
    @FXML
    private TableView tblCart;

    public void initialize(){
        initTable();
        initTableCart();
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
        btnAddGameToCart.setOnAction(e -> {
                AddGameToCart();
        });
    }
    
    public void AddGameToCart(){
        
        List selectedItem =  (List) tblRent.getSelectionModel().getSelectedItem();

        System.out.println(selectedItem);
         
        for (int i = 0; i < 1; i++){
            int x = (int) selectedItem.get(selectedItem.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(x);
            var gameName = copy.getGame().getTitle();
            var copyId = copy.getCopyID();
            
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

            tblCart.getItems().add(FXCollections.observableArrayList(gameName, developers, consoles, genres, copyId));
        }
    }

    public void initTableCart(){
        tblCart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblCart.getColumns().clear();

        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre", "Id"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblCart.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
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

        System.out.println(listOfCopies);

        for(int i = 0; i < listOfCopies.size(); i++) {
            if(listOfCopies.get(i).getAvailability() == Availability.AVAILABLE){
                var gameCopyName = listOfCopies.get(i).getGame().getTitle();
                var copyId = listOfCopies.get(i).getCopyID();

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
