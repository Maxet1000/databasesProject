package be.kuleuven.VGHF.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class BuygamesController {

    
    @FXML
    private TableView tblBuy;

    public void initialize(){
        initTable();
    }

    public void initTable(){
        tblBuy.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblBuy.getColumns().clear();


        // TODO verwijderen en "echte data" toevoegen!
        int colIndex = 0;
        for(var colName : new String[]{"Roepnaam", "Categorie", "Prijs", "Stock"}) {
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(colName);
            final int finalColIndex = colIndex;
            col.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().get(finalColIndex)));
            tblBuy.getColumns().add(col);
            colIndex++;
        }


        for(int i = 0; i < 10; i++) {

            tblBuy.getItems().add(FXCollections.observableArrayList("Budget PCke " + i, "Budget type 1", i*10 + "", i * 33 + ""));
        }
    }
}
