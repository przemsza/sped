package pl.mzalewski.spedycjaApp.window;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.controller.RemoveVehicleController;

public class RemoveVehicleWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        RemoveVehicleController.setStage(stage);
        stage.setTitle("Usuń pojazd");
        stage.show();
    }


}
