package be.kuleuven.VGHF.controller;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import org.hibernate.internal.util.MarkerObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.Console;
import be.kuleuven.VGHF.domain.Copy;
import be.kuleuven.VGHF.domain.Developer;
import be.kuleuven.VGHF.domain.Genre;
import be.kuleuven.VGHF.enums.Availability;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.VBox;
import net.bytebuddy.asm.Advice.AllArguments;

import java.util.List;

public class RentgamesController extends Controller{
    
    @FXML
    private TableView tblRent;
    @FXML
    private Button btnAddGameToCart;
    @FXML
    private Button btnAddFilter;
    @FXML Button btnRemoveFilters;
    @FXML
    public VBox pane1;
    @FXML
    private VBox filtersBox;
    @FXML
    private TreeView<String> filtersTreeView;
    @FXML
    private TableView tblCart;
    @FXML
    private Button btnRemoveFromCart;
    @FXML
    private Button btnRentGames;
    @FXML
    private Button btnAddGameToBuy;
    @FXML
    private TableView tblBuyCart;

    private ArrayList<Developer> toBeFilteredDevelopers;
    private ArrayList<Console> toBeFilteredConsoles;
    private ArrayList<Genre> toBeFilteredGenres;


    public RentgamesController() {
        toBeFilteredDevelopers = new ArrayList<>();
        toBeFilteredConsoles = new ArrayList<>();
        toBeFilteredGenres = new ArrayList<>();
    }

    public void initialize(){
        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        System.out.println(listOfCopies);
        initTable(listOfCopies);
        initTableCart();
        initFilters();
        btnAddGameToCart.setOnAction(e -> {
            addGameToCart();
        });
        btnAddFilter.setOnAction(e -> {
            activateFilters();
        });
        btnRemoveFilters.setOnAction(e -> {
            removeFilters();
        });
        btnRemoveFromCart.setOnAction(e -> {
            removeGameFromCart(tblCart);
            removeGameFromCart(tblBuyCart);
        });
        btnRentGames.setOnAction(e -> {
            rentAndBuyGamesFromCart(tblCart);
            rentAndBuyGamesFromCart(tblBuyCart);
        });
        btnAddGameToBuy.setOnAction(e -> {
            addGameToBuyCart();
        });
    }

    public void addGameToBuyCart(){
        try {
                    List selectedItem =  (List) tblRent.getSelectionModel().getSelectedItem();
         
        int x = (int) selectedItem.get(selectedItem.size()-1);
        var copy = ProjectMain.getDatabase().getCopyById(x);
        var gameName = copy.getGame().getTitle();           
        var copyId = copy.getCopyID();
        boolean doubleCopy = false;
    
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
        
            ObservableList<List> items = tblBuyCart.getItems();
            for (List item : items) {
                if (item.get(item.size()-1).equals(copyId)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("This copy is already in your cart");
                    alert.showAndWait();
                    doubleCopy = true; 
                }
            }
            ObservableList<List> items1 = tblCart.getItems();
            for (List item : items1) {
                if (item.get(item.size()-1).equals(copyId)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("This copy is already in your cart");
                    alert.showAndWait();
                    doubleCopy = true; 
                }
            }

        if (!doubleCopy){
            tblBuyCart.getItems().add(FXCollections.observableArrayList(gameName, developers, consoles, genres, copyId));
        }else {
            doubleCopy = false;
        }
        } catch (Exception e) {
            System.out.println("No game selected");
        }

    }

    //TODO voor RentGamesFromCart
        //customerID toevoegen mbv het inloggen van de customer
        //checken voor balance
    public void rentAndBuyGamesFromCart(TableView table){
        var datalist = table.getItems();
        System.out.println(datalist);
        int i = 0;
        int j = 0;
        boolean faultyGame = false;
        while (j != datalist.size()){
            List data = (List) datalist.get(j);
            System.out.println(data);
            int copyId = (int) data.get(data.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(copyId);
            if(table == tblCart && copy.getAvailability() == Availability.AVAILABLE){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("congratulations");
                alert.setHeaderText(null);
                alert.setContentText("Games are now rented");
                alert.show();
            }else if(table == tblCart){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("congratulations");
                alert.setHeaderText(null);
                alert.setContentText("Games cannot be rented");
                alert.show();
            }
            if(table == tblBuyCart && copy.getAvailability() == Availability.AVAILABLE && copy.getPurchasePrice() > 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("congratulations");
                alert.setHeaderText(null);
                alert.setContentText("Games are now yours, hopefully worth the money <3");
                alert.show();
            }else if(table == tblBuyCart){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Game not for sale!");
                alert.show();
                faultyGame = true;
            }
            j++;
        }
        while (i != datalist.size() && faultyGame != true){
            List data = (List) datalist.get(i);
            System.out.println(data);
            int copyId = (int) data.get(data.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(copyId);
            copy.setAvailability(Availability.RENTED);
            System.out.println(copy.getAvailability());
            copy.setDateOfReturn(twoWeeksLonger());
            ProjectMain.getDatabase().updateCopy(copy);
            i++;
        }
        datalist.clear();

        
        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        initTable(listOfCopies);
        activateFilters();
    }

    public String twoWeeksLonger(){
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return futureDate.format(formatter);
    }

    public void removeGameFromCart(TableView table){
        //zit bug in die de grijze geselecteerde ook verwijderd wanneer een andere element geselecteerd is in een andere lijst
        var selectedItem = table.getSelectionModel().getSelectedItem();
        var data = table.getItems();
        data.remove(selectedItem);
    }

    public void addGameToCart(){
        try {
                    List selectedItem =  (List) tblRent.getSelectionModel().getSelectedItem();

        System.out.println(selectedItem);
         
            int x = (int) selectedItem.get(selectedItem.size()-1);
            var copy = ProjectMain.getDatabase().getCopyById(x);
            var gameName = copy.getGame().getTitle();
            var copyId = copy.getCopyID();

            boolean doubleCopy = false;
    


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
            
                ObservableList<List> items = tblCart.getItems();
                for (List item : items) {
                    if (item.get(item.size()-1).equals(copyId)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("This copy is already in your cart");
                        alert.showAndWait();
                        doubleCopy = true; 
                    }
                }
                ObservableList<List> items1 = tblBuyCart.getItems();
            for (List item : items1) {
                if (item.get(item.size()-1).equals(copyId)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("This copy is already in your cart");
                    alert.showAndWait();
                    doubleCopy = true; 
                }
            }
        if (!doubleCopy){
            tblCart.getItems().add(FXCollections.observableArrayList(gameName, developers, consoles, genres, copyId));
        }else {
            doubleCopy = false;
        }
        } catch (Exception e) {
            System.out.println("No game selected");
        }
    }

    private void activateFilters() {
        var listOfFilteredCopies = ProjectMain.getDatabase().getAllCopies();
        if(toBeFilteredDevelopers.isEmpty()){
            initTable(listOfFilteredCopies);
        } else {
            listOfFilteredCopies = filterDevelopers(listOfFilteredCopies, toBeFilteredDevelopers);
        }
        if(toBeFilteredConsoles.isEmpty()) {
            initTable(listOfFilteredCopies);
        } else {
            listOfFilteredCopies = filterConsoles(listOfFilteredCopies, toBeFilteredConsoles);
        }
        if (toBeFilteredGenres.isEmpty()) {
        } else {
            listOfFilteredCopies = filterGenres(listOfFilteredCopies, toBeFilteredGenres);
        }
        initTable(listOfFilteredCopies);
    }

    public void removeFilters() {
        filtersTreeView.getRoot().getChildren().clear();
        toBeFilteredConsoles.clear();
        toBeFilteredDevelopers.clear();
        toBeFilteredGenres.clear();
        initFilters();
        var listOfCopies = ProjectMain.getDatabase().getAllCopies();
        initTable(listOfCopies);
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

        tblBuyCart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblBuyCart.getColumns().clear();

        int colIndex1 = 0;
        for(var colName : new String[]{"Game", "Developer", "Console", "Genre", "Id"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex1;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblBuyCart.getColumns().add(col);
            if(colName == "Id"){
                col.setVisible(false);
            }
            colIndex1++;
        }
    }

    public void initTable(List<Copy> listOfCopies){
        tblRent.getItems().clear();
        tblRent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblRent.getColumns().clear();

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
                //de console word nu naar de copy verwezen maar is nog geen compatible lijst toegevoegd
                String consoles = "";
                for (int j = 0; j < listOfCopies.get(i).getGame().getConsoles().size(); j++) {
                    consoles = consoles+ listOfCopies.get(i).getConsole().getConsoleName();
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

    public void initFilters() {
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