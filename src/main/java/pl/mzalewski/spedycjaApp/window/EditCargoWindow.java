package pl.mzalewski.spedycjaApp.window;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.controller.EditCargoController;

public class EditCargoWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        EditCargoController.setStage(stage);
        stage.setTitle("Edytuj ładunek");
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.show();

    }
}
