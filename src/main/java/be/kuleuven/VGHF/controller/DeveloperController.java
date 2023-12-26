package be.kuleuven.VGHF.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DeveloperController extends Controller{

    @FXML
    private VBox parentPane;

    // New genre
    @FXML
    private TextField txtNewGenreName;
    @FXML
    private Button btnAddNewGenre;
    @FXML
    public GridPane compatibleConsolesPane;

    // New developer
    @FXML
    private TextField txtNewDeveloperName;
    @FXML
    private Button btnAddNewDeveloper;

    // New console
    @FXML
    private TextField txtNewConsoleName;
    @FXML
    private Button btnAddNewConsole;

    // New game
    @FXML
    private TextField txtNewGameTitle;
    @FXML
    private Button btnAddNewGame;
    @FXML
    private TextField txtReleaseYear;
    @FXML
    private TextField txtReleaseMonth;
    @FXML
    private TextField txtReleaseDay;

    // Game genres
    @FXML
    private GridPane gameGenresPane;

    // Game developers
    @FXML
    private GridPane gameDevelopersPane;

    // Game consoles
    @FXML
    private GridPane gameConsolesPane;

    // New copy
    @FXML
    private ChoiceBox<String> cbNewCopyGameTitle;
    @FXML
    private Button btnAddNewCopy;
    @FXML
    private TextField txtRentPrice;
    @FXML
    private TextField txtPurchasePrice;
    @FXML
    private ChoiceBox<String> cbNewCopyConsole;
    @FXML
    private TextField txtWarehouse;


    private ArrayList<Developer> developerList;
    private ArrayList<Genre> genreList;
    private ArrayList<Console> consoleList;

    public DeveloperController(){
        developerList = new ArrayList<>();
        genreList = new ArrayList<>();
        consoleList = new ArrayList<>();
    }

    public void initialize() {
        GridPane.setHalignment(gameDevelopersPane, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(gameDevelopersPane, VPos.CENTER); // To align vertically in the cell
        initChoiceBoxes();
        initCheckBoxes();
        btnAddNewGenre.setOnAction(e ->{
            addNewGenre();
        });
        btnAddNewDeveloper.setOnAction(e ->{
            addNewDeveloper();
        });
        btnAddNewConsole.setOnAction(e ->{
            addNewGenre();
        });
        btnAddNewGame.setOnAction(e -> {
            addNewGame();
        });
        btnAddNewCopy.setOnAction((e -> {
            addNewCopy();
        }));
    }

    private void initChoiceBoxes() {
        for (int i=0 ; i<ProjectMain.getDatabase().getAllConsoles().size() ; i++) {
            cbNewCopyConsole.getItems().add(ProjectMain.getDatabase().getAllConsoles().get(i).getConsoleName());
        }
        for (int i=0 ; i<ProjectMain.getDatabase().getAllGames().size() ; i++) {
            cbNewCopyGameTitle.getItems().add(ProjectMain.getDatabase().getAllGames().get(i).getTitle());
        }
    }

    private void initCheckBoxes() {
        // select compatible consoles
        int index = 0, j = 0, i = 0;
        while(index<ProjectMain.getDatabase().getAllConsoles().size()) {
            CheckBox checkBox = new CheckBox(ProjectMain.getDatabase().getAllConsoles().get(index).getConsoleName());
            compatibleConsolesPane.add(checkBox,i,j);
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal) {
                        //TODO voeg toe aan een lijst, net zoals hoe de filters werken in ShopController
                    } else {

                    }
            });
            if(j==4) {
                j=0;
                i++;
            } else {
                j++;
            }
            index++;
        }
        // select genres
        index = 0;
        i = 0;
        j = 0;
        while(index<ProjectMain.getDatabase().getAllGenres().size()) {
            CheckBox checkBox = new CheckBox(ProjectMain.getDatabase().getAllGenres().get(index).getGenreName());
            gameGenresPane.add(checkBox,i,j);
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    //TODO voeg toe aan een lijst, net zoals hoe de filters werken in ShopController
                } else {

                }
            });
            if(j==4) {
                j=0;
                i++;
            } else {
                j++;
            }
            index++;
        }

        // select developers
        index = 0;
        i = 0;
        j = 0;
        while(index<ProjectMain.getDatabase().getAllDevelopers().size()) {
            CheckBox checkBox = new CheckBox(ProjectMain.getDatabase().getAllDevelopers().get(index).getDeveloperName());
            gameDevelopersPane.add(checkBox,i,j);
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    //TODO voeg toe aan een lijst, net zoals hoe de filters werken in ShopController
                } else {

                }
            });
            if(j==4) {
                j=0;
                i++;
            } else {
                j++;
            }
            index++;
        }

        // select consoles
        index = 0;
        i = 0;
        j = 0;
        while(index<ProjectMain.getDatabase().getAllConsoles().size()) {
            CheckBox checkBox = new CheckBox(ProjectMain.getDatabase().getAllConsoles().get(index).getConsoleName());
            gameConsolesPane.add(checkBox,i,j);
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    //TODO voeg toe aan een lijst, net zoals hoe de filters werken in ShopController (lijst moet ook nog gedefinieerd worden)
                    // zie het stukje code vanaf lijn 494 in ShopController
                } else {

                }
            });
            if(j==4) {
                j=0;
                i++;
            } else {
                j++;
            }
            index++;
        }
    }

    private void addNewGenre(){
        // TODO bij een klik op de knop "Add new genre" wordt de text ingelezen en de nieuwe genre aangemaakt.
    }

    private void addNewDeveloper(){
        // TODO idem aan new genre.
    }

    private void addNewConsole(){
        // TODO de lijst die aangemaakt is, gevuld met de aangevinkte chechboxes, wordt uitgelezen (neem inspiratie bij de filters).
        /*String newConsoleName = txtNewConsole.getText().toString();

         * Console has a list of compatible consoles
         *      First the TreeView and all consoles are shown
         *      Then the compatible consoles are selected
         *          Console gets updated with a list of compatible consoles

        btnAddNew.setOnAction(e -> {
            List<Console> listOfConsoles = getAllConsoles();
            TreeItem<String> consolesTreeItem = new CheckBoxTreeItem<>("Consoles");
            consolesTreeItem.setExpanded(false);
            selectTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());
            int numberOfConsoles = 0;
            while (numberOfConsoles<listOfConsoles.size()) {
                Console console = listOfConsoles.get(numberOfConsoles);
                CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(console.getConsoleName());
                consolesTreeItem.getChildren().add(checkBoxTreeItem);
                checkBoxTreeItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println(checkBoxTreeItem.getValue() + " selection state: " + newVal);
                if (newVal) {
                    consoleList.add(console);
                } else {
                    consoleList.remove(console);
                    }
                });
                numberOfConsoles++;
            }
            Console newConsole = new Console(newConsoleName);
            for(int i = 0; i < numberOfConsoles; i++){
                newConsole.addCompatibleConsole(consoleList.get(i));
            }

            addConsoleBidirectionally(newConsole);
        });*/
    }

    private void addNewGame(){
        // TODO titel, releasedate en checkboxes worden uitgelezen
        /*//set the buttons and txtFields visible
        pane1.setVisible(true);
        //lijst met alle ... tonen
            //developers
            //genres
            //consoles 

        List<Developer> listOfDevelopers = getAllDevelopers();
        List<Console> listOfConsoles = getAllConsoles();
        List<Genre> listOfGenres = getAllGenres();
        TreeItem<String> developersTreeItem = new CheckBoxTreeItem<>("Developers");
        TreeItem<String> consolesTreeItem = new CheckBoxTreeItem<>("Consoles");
        TreeItem<String> genresTreeItem = new CheckBoxTreeItem<>("Genres");

        developersTreeItem.setExpanded(false);
        consolesTreeItem.setExpanded(false);
        genresTreeItem.setExpanded(false);
        selectTreeView.setCellFactory(CheckBoxTreeCell.forTreeView());

        for (int i=0; i<listOfDevelopers.size(); i++ ) {
            Developer developer = listOfDevelopers.get(i);
            CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>(developer.getDeveloperName());
            developersTreeItem.getChildren().add(checkBoxTreeItem);
            checkBoxTreeItem.selectedProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println(checkBoxTreeItem.getValue() + " selection state: " + newVal);
                if (newVal) {
                    developerList.add(developer);
                } else {
                    developerList.remove(developer);
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
                    consoleList.add(console);
                } else {
                    consoleList.remove(console);
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
                    genreList.add(genre);
                } else {
                    genreList.remove(genre);
                }
            });
        }

        TreeItem<String> tree = new TreeItem<>();
        tree.getChildren().add(developersTreeItem);
        tree.getChildren().add(consolesTreeItem);
        tree.getChildren().add(genresTreeItem);
        selectTreeView.setRoot(tree);
        selectTreeView.setShowRoot(false);

        //when confirming the game gets made, all parameters gets assigned
        btnAddNew.setOnAction(e -> {
            //check if releaseDate is valid
            boolean validReleasedate = false;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date ret = sdf.parse(txtReleasedate.getText().toString().trim());
                if (sdf.format(ret).equals(txtReleasedate.getText().toString().trim())) {
                    validReleasedate = true;
                }
            } catch (ParseException exxe) {
                validReleasedate = false;
                exxe.printStackTrace();
                }
            //show error message
            if(developerList.isEmpty() || genreList.isEmpty() || consoleList.isEmpty() || txtGameTitle.getText().toString().isBlank() || txtReleasedate.getText().toString().isBlank() || !validReleasedate){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please provide a developer, genre and console as well as a name and a releasedate!");
                alert.show();
            }else{
                //make a new game
                Game newGame = new Game();
                newGame.setTitle(txtGameTitle.getText().toString());
                newGame.setConsoles(consoleList);
                newGame.setGenres(genreList);
                newGame.setDevelopers(developerList);
                newGame.setReleaseDate(txtReleasedate.getText().toString());
                newGame.setCopies(null);
                //add game relationships with other list in the database
                addGameBidirectionally(newGame);

                //popup confirmation
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Game successfully addad");
                alert.show();
            }
        });
        consoleList.clear();
        genreList.clear();
        developerList.clear();*/
    }


    private void addNewCopy() {
        // TODO de choiseboxes met game titel en console worden uitgelezen, textfields voor de prijs en warehouse.
    }
 
    //getters for the selectionTreeView

    public List<Developer> getAllDevelopers() {
        return ProjectMain.getDatabase().getAllDevelopers();
    }

    private List<Console> getAllConsoles() {
        return ProjectMain.getDatabase().getAllConsoles();
    }

    private List<Genre> getAllGenres() {
        return ProjectMain.getDatabase().getAllGenres();
    }

    /*  Adds a new Game and updates its relationships.
     * 
     */
    public void addGameBidirectionally(Game game) {        
        HibernateManager db = ProjectMain.getDatabase();

        Game gameInDb = db.getGameByTitle(game.getTitle());
        if (gameInDb != null) {
            updateGameBidirectionalRelations(game, gameInDb);
        }
        else {
            db.saveNewEntity(game);   
            List <Console> consoles = game.getConsoles();
            List <Developer> developers = game.getDevelopers();
            List <Genre> genres = game.getGenres();
            for (Console console : consoles) {
                console.addGame(game);
            }     
            for (Developer dev : developers) {
                dev.addGame(game);
            }
            for (Genre genre : genres) {
                genre.addGame(game);
            }
        }
    }

    public void addCopyBidirectionally(Copy copy) {
        HibernateManager db = ProjectMain.getDatabase();
        copy.getGame().addCopy(copy);
        copy.getConsole().addCopy(copy);
        db.saveNewEntity(copy);
    }

    public void addConsoleBidirectionally(Console console) {
        HibernateManager db = ProjectMain.getDatabase();
        Console consoleInDb = db.getConsoleByName(console.getConsoleName());
        if (consoleInDb != null) {
            updateConsoleBidirectionalRelations(console, consoleInDb);
        }
        List<Console> compatibleConsoles = console.getCompatibleConsoles();
        if (compatibleConsoles != null) {
            for (Console compatibleConsole : compatibleConsoles) {
                compatibleConsole.addCompatibleConsole(console);
            }
        }
        db.saveNewEntity(console);
    }

    public void addGenreToDb(Genre genre) {
        HibernateManager db = ProjectMain.getDatabase();
        Genre genreInDb = db.getgenreByName(genre.getGenreName());
        if (genreInDb != null) {
            updateGenreInDb(genre, genreInDb);
        }
        db.saveNewEntity(genre);
    }

    public void addDeveloperToDb(Developer developer) {
        HibernateManager db = ProjectMain.getDatabase();
        Developer developerInDb = db.getdeveloperByName(developer.getDeveloperName());
        if (developerInDb != null) {
            updateDeveloperInDb(developer, developerInDb);
        }
        db.saveNewEntity(developer);
    }

    /*  Updates a Game and the Copies, Developers, Consoles and Genres
     *  associated with it.
     */
    public void updateGameBidirectionalRelations(Game gameNew, Game gameOld) {
        HibernateManager db = ProjectMain.getDatabase();
        boolean nameChanged = false;
        if (gameNew.getTitle() != gameOld.getTitle()) {
            nameChanged = true;
            List<Copy> copies = gameOld.getCopies();
            if (copies != null) {
                for ( Copy copy : copies) {
                    copy.setGame(gameNew);
                } 
            }
        }
        if (gameNew.getConsoles() != gameOld.getConsoles() || nameChanged) {
            List<Console> consolesOld = gameOld.getConsoles();
            List<Console> consolesNew = gameNew.getConsoles();
            for (Console oldConsole : consolesOld) {
                oldConsole.removeGame(gameOld);
            }
            for (Console newConsole : consolesNew) {
                newConsole.addGame(gameNew);
            }
        }
        if (gameNew.getDevelopers() != gameOld.getDevelopers() || nameChanged) {
            List<Developer> devsOld = gameOld.getDevelopers();
            List<Developer> devsNew = gameNew.getDevelopers();
            for (Developer developer : devsOld) {
                developer.removeGame(gameOld);
            }
            for (Developer developer : devsNew) {
                developer.addGame(gameNew);
            }
        }
        if (gameNew.getGenres() != gameOld.getGenres() || nameChanged) {
            List<Genre> genresOld = gameOld.getGenres();
            List<Genre> genreNew = gameNew.getGenres();
            for (Genre genre : genresOld) {
                genre.removeGame(gameOld);
            }
            for (Genre genre : genreNew) {
                genre.addGame(gameNew);
            }
        }
        if (!nameChanged) {
            gameOld = gameNew;
            db.updateEntity(gameOld);
        } else {
            db.deleteEntity(gameOld);
            db.saveNewEntity(gameNew);
        }
    }

    /*  Updates a copy and the Game and Console associated with it
     *  Cannot update associated Transactions or User.
     */
    public void updateCopyBidirectionalRelations(Copy copyNew, Copy copyOld) {
        HibernateManager db = ProjectMain.getDatabase();
        
        if (copyNew.getGame() != copyOld.getGame()) {
            copyOld.getGame().removeCopy(copyOld);
            copyNew.getGame().addCopy(copyNew);
        }
        if (copyNew.getConsole() != copyOld.getConsole()) {
            copyOld.getConsole().removeCopy(copyOld);
            copyNew.getConsole().addCopy(copyNew);
        }
        copyOld = copyNew;
        db.updateEntity(copyOld);
    }

    /*  Updates a Console and the Consoles associated with it.
     *  Cannot update Games or Copies associated with it. 
     */
    public void updateConsoleBidirectionalRelations(Console consoleNew, Console consoleOld) {
        HibernateManager db = ProjectMain.getDatabase();
        List<Console> oldCompatibleConsoles = consoleOld.getCompatibleConsoles();
        List<Console> newCompatibleConsoles = consoleNew.getCompatibleConsoles();
        if (oldCompatibleConsoles != null) {
            for (Console console : oldCompatibleConsoles) {
                console.removeCompatibleconsole(consoleOld);
            }
        }
        if (newCompatibleConsoles != null) {
            for (Console console : newCompatibleConsoles) {
                console.addCompatibleConsole(consoleNew);
            }
        }
        consoleOld = consoleNew;
        db.updateEntity(consoleOld);
    }

    public void updateGenreInDb(Genre genreNew, Genre genreOld) {
        HibernateManager db = ProjectMain.getDatabase();
        db.deleteEntity(genreOld);
        db.saveNewEntity(genreNew);
    }

    public void updateDeveloperInDb(Developer developerNew, Developer developerOld) {
        HibernateManager db = ProjectMain.getDatabase();
        db.deleteEntity(developerOld);
        db.saveNewEntity(developerNew);
    }

    /* Deletes a Game and its associated Copies.
     * Also deletes the Game and Copies from all relationships.
     */
    public void deleteGameAndRelationships(Game game) {
        HibernateManager db = ProjectMain.getDatabase();
        List<Copy> copies = game.getCopies();
        if (copies != null) {
            for (Copy copy : copies) {
                deleteCopyAndRelationships(copy);
            }
        }
        List <Console> consoles = game.getConsoles();
        List <Developer> developers = game.getDevelopers();
        List <Genre> genres = game.getGenres();

        for (Console console : consoles) {
            console.removeGame(game);
        }
        for (Developer dev : developers) {
            dev.removeGame(game);
        }
        for (Genre genre : genres) {
            genre.removeGame(game);
        }

        db.deleteEntity(game);
    }

    public void deleteCopyAndRelationships(Copy copy) {
        HibernateManager db = ProjectMain.getDatabase();
        copy.getGame().removeCopy(copy);
        copy.getConsole().removeCopy(copy);
        User user = copy.getUser();
        if (user != null) {
            copy.getUser().removeCopy(copy);
        }    
        db.deleteEntity(copy);
    }

    public void deleteUserAndRelationships(User user) {
        HibernateManager db = ProjectMain.getDatabase();
        List<Copy> copies = user.getCopies();
        List<MonetaryTransaction> usertansactions = user.getTransactions();
        if (copies != null) {
            for (Copy copy : copies) {
                copy.setUser(null);
            }
        }
        if (usertansactions != null) {
            for (MonetaryTransaction transaction : usertansactions) {
                transaction.setUser(null);
            }
        }
        db.deleteEntity(user);
    }

}
