package be.kuleuven.VGHF.controller;

import java.util.*;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller{

    public DataCommunicationModel data;

    
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
    public void showLoginScherm() {
        try {
            var stage = new Stage();
            var pane = new FXMLLoader(getClass().getClassLoader().getResource("customerloginpage.fxml"));
            var root = (VBox) pane.load();

            var controller = pane.<CustomerLoginController>getController();
            controller.setModel(data);

            var scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("customerloginpage");
            root.setAlignment(Pos.CENTER);
            stage.initOwner(ProjectMain.getRootStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
            System.out.println("Loggen in as:  " + data.getUser().getCustomerName());

        } catch (Exception e) {
            throw new RuntimeException("Kan scherm customerloginpage.fxml niet vinden", e);
        }
    }

    public void setModel(DataCommunicationModel data) {
        this.data = data;
    }

    public DataCommunicationModel getModel(){
        return data;
    }
}
