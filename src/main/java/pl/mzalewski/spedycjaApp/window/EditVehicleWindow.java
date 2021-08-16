package pl.mzalewski.spedycjaApp.window;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.controller.EditVehicleController;

public class EditVehicleWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        EditVehicleController.setStage(stage);
        stage.setTitle("Edytuj dane pojazdu");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
