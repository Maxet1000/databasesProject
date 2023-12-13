package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.domain.Copy;


public class Controller {

    List<Copy> lijstOmOpTeFilteren = new ArrayList<Copy>();


    public <S,T> List<S> filter(List<S> listToFilter, List<T> filter) {
        for(S element : listToFilter) {
            if (!filter.contains(element)) {
                listToFilter.remove(element);
            }
        }
        return listToFilter;
    }
}
