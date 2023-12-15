package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.domain.*;


public class Controller{

    public DataCommunicationModel data;

    // public <S,T,U> List<S> filter(List<S> listToFilter, List<T> filter) {
    //     ArrayList<S> filteredList = new ArrayList<>();
    //     for(S element : listToFilter) {
    //         if (filter instanceof Console) {
    //             for (T filterelem : filter) {
    //                 if (((Console) console).getGames().contains(element)) {
    //                     filteredList.add(element);
    //                 }
    //             }
    //         }
    //     }
    
    //     return filteredList;
    // }

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
