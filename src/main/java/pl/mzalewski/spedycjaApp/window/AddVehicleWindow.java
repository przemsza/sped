package pl.mzalewski.spedycjaApp.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.controller.AddVehicleController;

import java.io.IOException;

public class AddVehicleWindow extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        AddVehicleController.setStage(stage);
        stage.setTitle("Dodaj pojazd");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }
}
