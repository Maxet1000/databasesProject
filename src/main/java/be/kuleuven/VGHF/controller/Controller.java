package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.domain.Copy;


public class Controller {

    List<Copy> lijstOmOpTeFilteren = new ArrayList<Copy>();


    public <S,T> List<S> filter(List<S> original, List<T> filter) {
        for(S element : original) {
            if (!filter.contains(element)) {
                original.remove(element);
            }
        }
        return original;
    }
}
