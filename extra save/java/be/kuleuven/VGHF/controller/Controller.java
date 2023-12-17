package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.DbContentScript;
import be.kuleuven.VGHF.domain.*;

public class Controller {

    List<Copy> lijstOmOpTeFilteren = new ArrayList<Copy>();
    

    public <S,T> List<S> filter(List<S> listToFilter, List<T> filter) {
        List<S> listAfterFilter = new ArrayList<>();
        for (S element : listToFilter) {
            for (T elementOfFilter: filter){
                if (element instanceof Game) {
                    if (((InterfaceForFilters) elementOfFilter).getGames().contains(element)) {
                        listAfterFilter.add(element);
                    }
                }
                else {
                    if (((InterfaceForFilters) elementOfFilter).getCopies().contains(element)) {
                        listAfterFilter.add(element);
                    }
                }
            }
        }
        return listAfterFilter;
    }

    public ArrayList<Copy> filterDevelopers(List<Copy> listToFilter, List<Developer> developersFilter) {
        ArrayList<Copy> filteredList = new ArrayList<>();
        for(Copy element : listToFilter) {
            for(Developer developer: developersFilter) {
                if (element.getGame().getDevelopers().contains(developer)) {
                    filteredList.add(element);
                }
            }
        }
        Set<Copy> set = new HashSet<>(filteredList); // Om dubbele items te verwijderen, dit verandert wel de volgorde
        filteredList.clear();
        filteredList.addAll(set);
        return filteredList;
    }
    public ArrayList<Copy> filterConsoles(List<Copy> listToFilter, List<Console> consolesFilter) {
        ArrayList<Copy> filteredList = new ArrayList<>();
        for(Copy element : listToFilter) {
            for(Console console: consolesFilter) {
                if (element.getConsole()==console) {
                    filteredList.add(element);
                }
            }
        }
        Set<Copy> set = new HashSet<>(filteredList); // Om dubbele items te verwijderen, dit verandert wel de volgorde
        filteredList.clear();
        filteredList.addAll(set);
        return filteredList;
    }
    public ArrayList<Copy> filterGenres(List<Copy> listToFilter, List<Genre> genresFilter) {
        ArrayList<Copy> filteredList = new ArrayList<>();
        for(Copy element : listToFilter) {
            for(Genre genre: genresFilter) {
                if (element.getGame().getGenres().contains(genre)) {
                    filteredList.add(element);
                }
            }
        }
        Set<Copy> set = new HashSet<>(filteredList); // Om dubbele items te verwijderen, dit verandert wel de volgorde
        filteredList.clear();
        filteredList.addAll(set);
        return filteredList;
    }

    public void setModel(DataCommunicationModel data) {
        this.data = data;
    }

    public DataCommunicationModel getModel(){
        return data;
    }
}
