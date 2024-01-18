package be.kuleuven.VGHF;

import javax.persistence.Persistence;
import java.util.*;
import be.kuleuven.VGHF.domain.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        database = new HibernateManager(entityManager, sessionFactory);

        DbContentScript.scriptMain();
        List<MonetaryTransaction> testlist = database.getMonetaryTransactionsByUserID(5);
        System.out.println(testlist);
        launch();
    }
    
    public static HibernateManager getDatabase(){
        return database;
    }
    
}