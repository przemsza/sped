package pl.mzalewski.spedycjaApp.window;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.controller.AddCargoController;

public class AddCargoWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        AddCargoController.setStage(stage);
        stage.setTitle("Dodaj towar");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();

    }
}
