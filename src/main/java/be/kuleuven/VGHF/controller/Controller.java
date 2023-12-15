package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.domain.Console;
import be.kuleuven.VGHF.domain.Copy;
import be.kuleuven.VGHF.domain.Developer;
import be.kuleuven.VGHF.domain.Genre;


public class Controller{

    List<Copy> lijstOmOpTeFilteren = new ArrayList<Copy>();


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
}
