package be.kuleuven.VGHF.domain;

import java.util.*;

import be.kuleuven.VGHF.ProjectMain;

public interface InterfaceForFilters {    

    List<Game> getGames();


    default List<Copy> getCopies() {
        HibernateManager database = ProjectMain.getDatabase();
        List<Copy> listOfCopiesWithDoubles = new ArrayList<Copy>();
        List<Game> listOfGames = this.getGames();
        System.out.println("bruuuuuuuuuuuhhhhh");
        System.out.println(listOfGames);
        System.out.println(database.getAllDevelopers().get(0).getDeveloperName());
        System.out.println(database.getAllDevelopers().get(0).getGames());
                System.out.println("AAAAAAAAAAAAAAAAAA");

        if (listOfGames != null) {
            for (Game game: listOfGames) {
                listOfCopiesWithDoubles.addAll(game.getCopies());
            }
        }
        List<Copy> listOfCopiesUnique = new ArrayList<>(new HashSet<>(listOfCopiesWithDoubles));
        return listOfCopiesUnique;
    }
}
