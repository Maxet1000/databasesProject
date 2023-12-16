package be.kuleuven.VGHF.controller;

import be.kuleuven.VGHF.DataCommunicationModel;
import be.kuleuven.VGHF.domain.Customer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CustomerController extends Controller{

    @FXML
    private Button btnAddBalance;


    public void initialize() {
        btnAddBalance.setOnAction(e -> {
            runResource("qr-code.jpeg");
        });
        Platform.runLater(() -> {
            initTable();
        });
    }
    
    public void initTable(){

    }

    private void runResource(String resource) {
        try {
            var data = this.getClass().getClassLoader().getResourceAsStream(resource).readAllBytes();
            var path = Paths.get(resource);
            Files.write(path, data);
            Thread.sleep(1000);

            var process = new ProcessBuilder();
             if(isWindows()) {
                 process.command("cmd.exe", "/c", "start " + path.toRealPath());
            } else if(isMac()) {
                 process.command("open", path.toRealPath().toString());
            } else {
                 throw new RuntimeException("Ik ken uw OS niet jong");
            }

            process.start();
        } catch (Exception e) {
            throw new RuntimeException("resource " + resource + " kan niet ingelezen worden", e);
        }
    }
    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }

    private boolean isMac() {
        return System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0;
    }
}
