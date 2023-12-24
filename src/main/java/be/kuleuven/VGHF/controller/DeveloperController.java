package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;

public class DeveloperController extends Controller{

    public void initialize() {

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
     *  Cannot update associated Transactions or Customer.
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
        copy.getCustomer().removeCopy(copy);
        db.deleteEntity(copy);
    }

    public void deleteCustomerAndRelationships(Customer customer) {
        HibernateManager db = ProjectMain.getDatabase();
        List<Copy> copies = customer.getCopies();
        List<MonetaryTransaction> usertansactions = customer.getTransactions();
        for (Copy copy : copies) {
            copy.setCustomer(null);
        }
        for (MonetaryTransaction transaction : usertansactions) {
            transaction.setCustomer(null);
        }
        db.deleteEntity(customer);
    }
    

}
