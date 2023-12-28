package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;
import be.kuleuven.VGHF.enums.Availability;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.textfield.TextFields;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GameDbController extends Controller{

    @FXML
    private TreeView<String> filtersTreeView;
    @FXML
    private TableView tblGameTable;
    @FXML
    private Button btnAddFilter;
    @FXML
    private Button btnRemoveFilters;
    @FXML
    private TextField txtBottomYear;
    @FXML
    private TextField txtTopYear;
    @FXML
    private TextField txtSearch;
    Game temp;
    Date lastClickTime;
    List<Game> listOfGames = new ArrayList<>();

    private ArrayList<Developer> toBeFilteredDevelopers;
    private ArrayList<Console> toBeFilteredConsoles;
    private ArrayList<Genre> toBeFilteredGenres;

    public GameDbController() {
        toBeFilteredDevelopers = new ArrayList<>();
        toBeFilteredConsoles = new ArrayList<>();
        toBeFilteredGenres = new ArrayList<>();
    }

    public void initialize() {
        listOfGames = ProjectMain.getDatabase().getAllGames();
        initTable(listOfGames);
        initFilters();



        btnAddFilter.setOnAction(e -> {
            activateFilters();
        });
        btnRemoveFilters.setOnAction(e -> {
            removeFilters();
        });
        txtSearch.textProperty().addListener((obs, oldVal, newVal) -> {
            startSearch();
        });
        
    }

    public void initTable(List<Game> listOfGames){
        tblGameTable.getItems().clear();
        tblGameTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblGameTable.getColumns().clear();

        int colIndex = 0;
        for(var colName : new String[]{"Game", "Developer", "Consoles", "Genre", "Release Date"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblGameTable.getColumns().add(col);
            colIndex++;
        }

        System.out.println(listOfGames);

        for(int i = 0; i < listOfGames.size(); i++) {
                String title = listOfGames.get(i).getTitle();
                String releaseDate = listOfGames.get(i).getReleaseDate();

                String developers = "";
                for (int j = 0; j < listOfGames.get(i).getDevelopers().size(); j++) {
                    developers = developers+ listOfGames.get(i).getDevelopers().get(j).getDeveloperName();
                    if (j+1 != listOfGames.get(i).getDevelopers().size()) {
                        developers = developers + ", ";
                    }
                }
                String consoles = "";
                for (int j = 0; j < listOfGames.get(i).getConsoles().size(); j++) {
                    consoles = consoles + listOfGames.get(i).getConsoles().get(j).getConsoleName();
                    if (j+1 != listOfGames.get(i).getConsoles().size()) {
                        consoles = consoles + ", ";
                    }
                }

                String genres = "";
                for (int j = 0; j < listOfGames.get(i).getGenres().size(); j++) {
                    genres = genres+ listOfGames.get(i).getGenres().get(j).getGenreName();
                    if (j+1 != listOfGames.get(i).getGenres().size()) {
                        genres = genres + ", ";
                    }
                }

                tblGameTable.getItems().add(FXCollections.observableArrayList(title, developers, consoles, genres, releaseDate));
        }

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

    private void activateFilters() {
        List<Game> listOfFilteredGames = new ArrayList<>(listOfGames);
        if(toBeFilteredDevelopers.isEmpty()){
            initTable(listOfFilteredGames);
        } else {
            listOfFilteredGames = filter(listOfFilteredGames, toBeFilteredDevelopers);
        }
        if(toBeFilteredConsoles.isEmpty()) {
            initTable(listOfFilteredGames);
        } else {
            listOfFilteredGames = filter(listOfFilteredGames, toBeFilteredConsoles);
        }
        if (toBeFilteredGenres.isEmpty()) {
        } else {
            listOfFilteredGames = filter(listOfFilteredGames, toBeFilteredGenres);
        }

        try {
            int bottomYear = Integer.parseInt(txtBottomYear.getText());
            int topYear = Integer.parseInt(txtTopYear.getText());
            if (bottomYear>topYear) {
                txtBottomYear.setStyle(txtBottomYear.getStyle() + "-fx-border-color: #ff8080;");
                txtTopYear.setStyle(txtTopYear.getStyle() + "-fx-border-color: #ff8080;");
            } else {
                listOfFilteredGames = filterOnDates(listOfFilteredGames, bottomYear, topYear);
                txtBottomYear.setStyle(txtSearch.getStyle());
                txtTopYear.setStyle(txtSearch.getStyle());
            }
        } catch (NumberFormatException ex) {
            txtBottomYear.setStyle(txtBottomYear.getStyle() + "-fx-border-color: #ff8080;");
            txtTopYear.setStyle(txtTopYear.getStyle() + "-fx-border-color: #ff8080;");
        }

        initTable(listOfFilteredGames);
    }

    public void removeFilters() {
        filtersTreeView.getRoot().getChildren().clear();
        toBeFilteredConsoles.clear();
        toBeFilteredDevelopers.clear();
        toBeFilteredGenres.clear();
        txtBottomYear.clear();
        txtTopYear.clear();
        txtBottomYear.setStyle(txtSearch.getStyle());
        txtTopYear.setStyle(txtSearch.getStyle());
        initFilters();
        var listOfGames1 = new ArrayList<>(listOfGames);
        initTable(listOfGames1);
    }

    public void startSearch() {
        if (!txtSearch.getText().isEmpty()) {
            //gepakt van hier boven, misch een apparte functie vr maken om herhaling te vermijden...
            filtersTreeView.getRoot().getChildren().clear();
            toBeFilteredConsoles.clear();
            toBeFilteredDevelopers.clear();
            toBeFilteredGenres.clear();
            txtBottomYear.clear();
            txtTopYear.clear();
            txtBottomYear.setStyle(txtSearch.getStyle());
            txtTopYear.setStyle(txtSearch.getStyle());
            initFilters();

            List<Game> listOfSearchResults = ProjectMain.getDatabase().searchGames(txtSearch.getText(), 50);
            initTable(listOfSearchResults);
        } else {
            System.out.println("///////////////EEEEEEEEEMMMMMMMMMMMPPPPPPPPPPPTTTTTTTTTTTTTTTTYYYYYYYYYYYYYYYYYY////////////////////////");
            List<Game> listOfGames1 = new ArrayList<>(listOfGames);
            initTable(listOfGames1);
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

    public void handleRowSelect(javafx.scene.input.MouseEvent mouseEvent) {

        List selectedItem =  (List) tblGameTable.getSelectionModel().getSelectedItem();
        String x = (String) selectedItem.get(0);
        Game row = ProjectMain.getDatabase().getGameByTitle(x);

        if (row == null) return;
        if(row != temp){
            temp = row;
            lastClickTime = new Date();
        } else {
            Date now = new Date();
            long diff = now.getTime() - lastClickTime.getTime();
            if (diff < 300){ //another click registered in 300 millis
                System.out.println(row.getTitle());
                showNewWindow("gameinfo",row.getTitle());
            } else {
                lastClickTime = new Date();
            }
        }
    }
}
