package be.kuleuven.VGHF.controller;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.util.*;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.ProjectMain;
import be.kuleuven.VGHF.domain.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller{

    public DataCommunicationModel data;
    @FXML
    private Pane parentPane;

    
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
        List<S> listNoDoubles = new ArrayList<>(new HashSet<>(listAfterFilter));
        return listNoDoubles;
    }

    public List<Game> filterOnDates(List<Game> listToFilter, int bottomDate, int topDate) {
        List<Game> listAfterFilter = new ArrayList<>();

        for (Game element : listToFilter) {
            int year = Integer.parseInt(element.getReleaseDate().substring(0, Math.min(element.getReleaseDate().length(), 4)));
            if (bottomDate<=year && year<=topDate) {
                listAfterFilter.add(element);
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
    //EXTRA filters (later verwijderen, misch handig vr testen bij bugs)

    // public ArrayList<Copy> filterDevelopers(List<Copy> listToFilter, List<Developer> developersFilter) {
    //     ArrayList<Copy> filteredList = new ArrayList<>();
    //     for(Copy element : listToFilter) {
    //         for(Developer developer: developersFilter) {
    //             if (element.getGame().getDevelopers().contains(developer)) {
    //                 filteredList.add(element);
    //             }
    //         }
    //     }
    //     Set<Copy> set = new HashSet<>(filteredList); // Om dubbele items te verwijderen, dit verandert wel de volgorde
    //     filteredList.clear();
    //     filteredList.addAll(set);
    //     return filteredList;
    // }
    
    // public ArrayList<Copy> filterConsoles(List<Copy> listToFilter, List<Console> consolesFilter) {
    //     ArrayList<Copy> filteredList = new ArrayList<>();
    //     for(Copy element : listToFilter) {
    //         for(Console console: consolesFilter) {
    //             if (element.getConsole()==console) {
    //                 filteredList.add(element);
    //             }
    //         }
    //     }
    //     Set<Copy> set = new HashSet<>(filteredList); // Om dubbele items te verwijderen, dit verandert wel de volgorde
    //     filteredList.clear();
    //     filteredList.addAll(set);
    //     return filteredList;
    // }
    // public ArrayList<Copy> filterGenres(List<Copy> listToFilter, List<Genre> genresFilter) {
    //     ArrayList<Copy> filteredList = new ArrayList<>();
    //     for(Copy element : listToFilter) {
    //         for(Genre genre: genresFilter) {
    //             if (element.getGame().getGenres().contains(genre)) {
    //                 filteredList.add(element);
    //             }
    //         }
    //     }
    //     Set<Copy> set = new HashSet<>(filteredList); // Om dubbele items te verwijderen, dit verandert wel de volgorde
    //     filteredList.clear();
    //     filteredList.addAll(set);
    //     return filteredList;
    // }

    public void showLoginScherm() {
        try {
            var stage = new Stage();
            var pane = new FXMLLoader(getClass().getClassLoader().getResource("customerloginpage.fxml"));
            var root = (VBox) pane.load();

            var controller = pane.<Controller>getController();
            controller.setModel(data);

            var scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("customerloginpage");
            root.setAlignment(Pos.CENTER);
            stage.initOwner(ProjectMain.getRootStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
            System.out.println("Loggen in as:  " + data.getUser().getuserName());

        } catch (Exception e) {
            throw new RuntimeException("Kan scherm customerloginpage.fxml niet vinden", e);
        }
    }public void showSignUpScherm() {
        try {
            var stage = new Stage();
            var pane = new FXMLLoader(getClass().getClassLoader().getResource("newuserpage.fxml"));
            var root = (VBox) pane.load();

            var controller = pane.<Controller>getController();
            controller.setModel(data);

            var scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("signuppage");
            root.setAlignment(Pos.CENTER);
            stage.initOwner(ProjectMain.getRootStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            throw new RuntimeException("Kan scherm newuserpage.fxml niet vinden", e);
        }
    }

    public void setModel(DataCommunicationModel data) {
        this.data = data;
    }


    public void switchScreen (String id) throws IOException {

        var pane = new FXMLLoader(getClass().getClassLoader().getResource(id + "page.fxml"));
        var rootLoader = (VBox) pane.load();

        var controller = pane.<Controller>getController();
        controller.setModel(data);

        rootLoader.autosize();
        parentPane.getChildren().setAll(rootLoader);
        StackPane.setAlignment(rootLoader, Pos.CENTER);
    }
    public DataCommunicationModel getModel(){
        return data;
    }
}
