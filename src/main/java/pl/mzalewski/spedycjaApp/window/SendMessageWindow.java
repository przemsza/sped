package pl.mzalewski.spedycjaApp.window;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.controller.SendMessageController;

public class SendMessageWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SendMessageController.setStage(stage);
        stage.setTitle("Wyślij informacjo o załadowany ładunku");
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
