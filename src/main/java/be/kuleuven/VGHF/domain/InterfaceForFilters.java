package be.kuleuven.VGHF.domain;

import java.util.*;

import be.kuleuven.VGHF.ProjectMain;

public interface InterfaceForFilters {    

    List<Game> getGames();

    default List<Copy> getCopies() {
        List<Copy> listOfCopiesWithDoubles = new ArrayList<Copy>();
        List<Game> listOfGames = this.getGames();
        if (listOfGames != null) {
            for (Game game: listOfGames) {
                listOfCopiesWithDoubles.addAll(game.getCopies());
            }
        }
        List<Copy> listOfCopiesUnique = new ArrayList<>(new HashSet<>(listOfCopiesWithDoubles));
        return listOfCopiesUnique;
    }
}
