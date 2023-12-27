package be.kuleuven.VGHF.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.transaction.SystemException;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;
import be.kuleuven.VGHF.domain.Copy.CopyBuilder;
import be.kuleuven.VGHF.enums.Availability;
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
    @FXML
    private ChoiceBox<String> cbNewAvailability;


    private ArrayList<Developer> developerList;
    private ArrayList<Genre> genreList;
    private ArrayList<Console> consoleList;
    private ArrayList<Console> compConsoleList; 

    public DeveloperController(){
        developerList = new ArrayList<>();
        genreList = new ArrayList<>();
        consoleList = new ArrayList<>();
        compConsoleList = new ArrayList<>();
    }

    public void initialize() {
        GridPane.setHalignment(gameDevelopersPane, HPos.CENTER); // To align horizontally in the cell
        GridPane.setValignment(gameDevelopersPane, VPos.CENTER); // To align vertically in the cell
        initChoiceBoxConsole();
        initChoiceBoxForGame();
        initChoiceBoxForAvailability();
        initCheckBoxes();
        btnAddNewGenre.setOnAction(e ->{
            addNewGenre();
        });
        btnAddNewDeveloper.setOnAction(e ->{
            addNewDeveloper();
        });
        btnAddNewConsole.setOnAction(e ->{
            addNewConsole();
        });
        btnAddNewGame.setOnAction(e -> {
            addNewGame();
        });
        btnAddNewCopy.setOnAction((e -> {
            addNewCopy();
        }));
    }

    private void initChoiceBoxConsole() {
        for (int i=0 ; i<ProjectMain.getDatabase().getAllConsoles().size() ; i++) {
            cbNewCopyConsole.getItems().add(ProjectMain.getDatabase().getAllConsoles().get(i).getConsoleName());
        }
    }
    private void initChoiceBoxForGame(){
        for (int i=0 ; i<ProjectMain.getDatabase().getAllGames().size() ; i++) {
            cbNewCopyGameTitle.getItems().add(ProjectMain.getDatabase().getAllGames().get(i).getTitle());
        }
    }
    private void initChoiceBoxForAvailability(){
            cbNewAvailability.getItems().add(Availability.BROKEN.toString());
            cbNewAvailability.getItems().add(Availability.AVAILABLE.toString());
            cbNewAvailability.getItems().add(Availability.EXTENDED.toString());
            cbNewAvailability.getItems().add(Availability.RENTED.toString());
            cbNewAvailability.getItems().add(Availability.SOLD.toString());
    }

    private void initCheckBoxes() {
        // select compatible consoles
        int index = 0, j = 0, i = 0;
        while(index<ProjectMain.getDatabase().getAllConsoles().size()) {
            CheckBox checkBox = new CheckBox(ProjectMain.getDatabase().getAllConsoles().get(index).getConsoleName());
            compatibleConsolesPane.add(checkBox,i,j);
            int dikkeMa = index;
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    if (newVal) {
                        if(compConsoleList == null){
                            compConsoleList = new ArrayList<>();
                            compConsoleList.add(ProjectMain.getDatabase().getAllConsoles().get(dikkeMa));
                        }else{
                            compConsoleList.add(ProjectMain.getDatabase().getAllConsoles().get(dikkeMa));
                        }
                        
                    } else {
                        compConsoleList.remove(ProjectMain.getDatabase().getAllConsoles().get(dikkeMa));
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
            int dikkaMa = index;
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    if(genreList == null){
                        genreList = new ArrayList<>();
                        genreList.add(ProjectMain.getDatabase().getAllGenres().get(dikkaMa));
                    }else{
                        genreList.add(ProjectMain.getDatabase().getAllGenres().get(dikkaMa));
                    }
                    
                } else {
                    genreList.remove(ProjectMain.getDatabase().getAllGenres().get(dikkaMa));
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
            int dikkeMa = index;
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    if(developerList == null){
                        developerList = new ArrayList<>();
                        developerList.add(ProjectMain.getDatabase().getAllDevelopers().get(dikkeMa));
                    }
                    developerList.add(ProjectMain.getDatabase().getAllDevelopers().get(dikkeMa));
                } else {
                    developerList.remove(ProjectMain.getDatabase().getAllDevelopers().get(dikkeMa));
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
            int dikkaMa = index;
            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal) {
                    if(consoleList == null){
                        consoleList = new ArrayList<>();
                        consoleList.add(ProjectMain.getDatabase().getAllConsoles().get(dikkaMa));
                    }
                    consoleList.add(ProjectMain.getDatabase().getAllConsoles().get(dikkaMa));
                } else {
                    consoleList.remove(ProjectMain.getDatabase().getAllConsoles().get(dikkaMa));
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
        String newGenreName = txtNewGenreName.getText().toString();
        Genre newGenre = new Genre(newGenreName);
        addGenreToDb(newGenre);
        System.out.println("Dit werkt???");
    }

    private void addNewDeveloper(){
        String newDeveloperName = txtNewDeveloperName.getText().toString();
        Developer newDeveloper = new Developer(newDeveloperName);
        addDeveloperToDb(newDeveloper);
        System.out.println("Dit werkt???");
    }

    private void addNewConsole(){
        System.out.println(compConsoleList.toString());
        String newConsoleName = txtNewConsoleName.getText().toString();
        Console newConsole = new Console(newConsoleName, compConsoleList);
        addConsoleBidirectionally(newConsole);
        System.out.println("HELLLLLLLLLLLLLLLOOO xDxDxD");
        compConsoleList.clear();;
    }

    private void addNewGame(){
        String newGameTitle = txtNewGameTitle.getText().toString();
        String releaseDay = txtReleaseDay.getText().toString();
        String releaseMonth = txtReleaseMonth.getText().toString();
        String releaseYear = txtReleaseYear.getText().toString();
        String releaseDate = releaseYear +"-"+ releaseMonth +"-"+ releaseDay;
        Game newGame = new Game(newGameTitle, releaseDate,consoleList,developerList,genreList);
        addGameBidirectionally(newGame);
        consoleList.clear();
        developerList.clear();
        genreList.clear();
    }   


    private void addNewCopy() {

        CopyBuilder builder = new CopyBuilder();
        Copy newCopy = builder.game(ProjectMain.getDatabase().getGameByTitle(cbNewCopyGameTitle.getValue().toString()))
                                .console(ProjectMain.getDatabase().getConsoleByName(cbNewCopyConsole.getValue().toString()))
                                .availability(Availability.valueOf(cbNewAvailability.getValue().toString()))
                                .warehouse(txtWarehouse.getText().toString())
                                .purchasePrice(Integer.parseInt(txtPurchasePrice.getText().toString()))
                                .rentPrice(Integer.parseInt(txtRentPrice.getText().toString()))
                                .dateOfReturn(null)
                                .build();
        
        addCopyBidirectionally(newCopy);
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
