package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.text.Text;

public class DeveloperController extends Controller{

    @FXML
    private Button btnAddNewGame;
    @FXML
    private TreeView<String> selectTreeView;
    @FXML
    private Button btnAddNew;
    @FXML
    private TextField txtGameTitle;
    @FXML
    private TextField txtReleasedate;
    @FXML
    private Text txtGameTitletxt;
    @FXML
    private Text txtReleaseDatetxt;

    private ArrayList<Developer> developerList;
    private ArrayList<Genre> genreList;
    private ArrayList<Console> consoleList;

    public DeveloperController(){
        developerList = new ArrayList<>();
        genreList = new ArrayList<>();
        consoleList = new ArrayList<>();
    }

    public void initialize() {
        //set buttons invisable
        txtGameTitle.setVisible(false);
        txtReleasedate.setVisible(false);
        selectTreeView.setVisible(false);
        txtGameTitletxt.setVisible(false);
        txtReleaseDatetxt.setVisible(false);
        
        btnAddNewGame.setOnAction(e -> {
            addNewGame();
        });
    }

    private void addNewGame(){
        //set the buttons and txtFields visible
        txtGameTitle.setVisible(true);
        txtReleasedate.setVisible(true);
        selectTreeView.setVisible(true);
        txtGameTitletxt.setVisible(true);
        txtReleaseDatetxt.setVisible(true);


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
            if(developerList.isEmpty() || genreList.isEmpty() || consoleList.isEmpty() || txtGameTitle.getText().toString().isBlank() || txtReleasedate.getText().toString().isBlank()){
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
            }

        });
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
        List<Console> compatibleConsoles = console.getCompatibleConsoles();
        for (Console compatibleConsole : compatibleConsoles) {
            compatibleConsole.addCompatibleConsole(console);
        }
        db.saveNewEntity(console);
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
            for ( Copy copy : copies) {
                copy.setGame(gameNew);
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
        for (Console console : oldCompatibleConsoles) {
            console.removeCompatibleconsole(consoleOld);
        }
        for (Console console : newCompatibleConsoles) {
            console.addCompatibleConsole(consoleNew);
        }
        consoleOld = consoleNew;
        db.updateEntity(consoleOld);
    }

    /* Deletes a Game and its associated Copies.
     * Also deletes the Game and Copies from all relationships.
     */
    public void deleteGameAndRelationships(Game game) {
        HibernateManager db = ProjectMain.getDatabase();
        List<Copy> copies = game.getCopies();
        for (Copy copy : copies) {
            deleteCopyAndRelationships(copy);
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
        copy.getUser().removeCopy(copy);
        db.deleteEntity(copy);
    }

    public void deleteUserAndRelationships(User user) {
        HibernateManager db = ProjectMain.getDatabase();
        List<Copy> copies = user.getCopies();
        List<MonetaryTransaction> usertansactions = user.getTransactions();
        for (Copy copy : copies) {
            copy.setUser(null);
        }
        for (MonetaryTransaction transaction : usertansactions) {
            transaction.setUser(null);
        }
        db.deleteEntity(user);
    }
    

}
