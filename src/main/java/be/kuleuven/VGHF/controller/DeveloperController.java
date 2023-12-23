package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;

public class DeveloperController extends Controller{

    public void initialize() {

    }

    public void addGameBidirectionally(Game game) {        
        HibernateManager db = ProjectMain.getDatabase();

        Game gameInDb = db.getGameByTitle(game.getTitle());
        if (gameInDb != null) {
            gameInDb = game;
            updateGameBidirectionalRelations(gameInDb, db);
        }
        else {
            db.saveNewObject(game);   
            updateGameBidirectionalRelations(game, db);
        }
    }

    public void updateGameBidirectionalRelations(Game game, HibernateManager db) {
        List <Console> consoles = game.getConsoles();
        List <Developer> developers = game.getDevelopers();
        List <Genre> genres = game.getGenres();

        for (Console console : consoles) {
            if (!console.getGames().contains(game)) {
                console.addGame(game);
            }
        }     
        for (Developer dev : developers) {
            if (!dev.getGames().contains(game)) {
                dev.addGame(game);
            }
        }
        for (Genre genre : genres) {
            if (!genre.getGames().contains(game)) {
                genre.addGame(game);
            }
        }
    }

    public void deleteGameButtonPressed(Game game) {
        
    }

}
