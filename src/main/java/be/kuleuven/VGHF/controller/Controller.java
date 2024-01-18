package be.kuleuven.VGHF.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import be.kuleuven.VGHF.Model;
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

    public Model data;
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

    public void showNewWindow(String id, String gameTitle) {
        try {
            var stage = new Stage();
            var pane = new FXMLLoader(getClass().getClassLoader().getResource(id + "page.fxml"));
            var root = (VBox) pane.load();

            if (id == "gameinfo") {
                GameInfoController controller = pane.getController();
                stage.setResizable(false);
                controller.setModel(data);
                controller.setGameTitle(gameTitle);
            } else {
                Controller controller = pane.getController();
                controller.setModel(data);
            }

            var scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(id + "page");
            root.setAlignment(Pos.CENTER);
            stage.initOwner(ProjectMain.getRootStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            throw new RuntimeException("Kan scherm " + id + "page.fxml niet vinden", e);
        }
    }

    public void setModel(Model data) {
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

    public Model getModel(){
        return data;
    }

    public String getCurrentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        return date.format(formatter);
    }
}
