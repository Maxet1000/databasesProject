package be.kuleuven.VGHF;

import javax.persistence.Persistence;

import java.util.*;

import be.kuleuven.VGHF.domain.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * DB Taak 2021-2022: PC Customizer concept
 * Zie Taak-DATAB-2122.pdf voor opgave details
 *
 * Deze code is slechts een quick-start om je op weg te helpen met de integratie van JavaFX tabellen en data!
 * Het is NIET voldoende om dit slechts over te nemen! JavaFX is GEEN vereiste, maar integratie met SQL in Java/Kotlin wel.
 *
 * Denk aan je eigen ER schema, welke data en dus schermen heb je nodig? Welke relaties? Hoe ga je dit presenteren aan gebruikers?
 * Is dit programma een admin tool voor de VERkopers of voor de kopers van de custom PCs in jullie ogen? Pas aan waar nodig!
 */
public class ProjectMain extends Application {

    private static Stage rootStage;
    public static HibernateManager database;

    public static Stage getRootStage() {
        return rootStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootStage = stage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("background.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Video Game History Foundation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        var sessionFactory = Persistence.createEntityManagerFactory("be.kuleuven.VGHF.domain");
        var entityManager = sessionFactory.createEntityManager();
        database = new HibernateManager(entityManager);

        DbContentScript.scriptMain();
        List<MonetaryTransaction> testlist = database.getMonetaryTransactionsByCustomerID(5);
        System.out.println(testlist);
        launch();
    }
    
    public static HibernateManager getDatabase(){
        return database;
    }
    
    //mauro is minder goed dan max, bjorn en dries

}
