package pl.mzalewski.spedycjaApp.controller;

import pl.mzalewski.spedycjaApp.dataBase.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVehicleController {

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AddVehicleController.stage = stage;
    }

    private static Stage stage;


    @FXML
    private TextField driverNameTextField;

    @FXML
    private Button acceptButton;

    @FXML
    private TextField plateNumbersVehicleTextField;

    @FXML
    private TextField cargoSpaceTextField;

    public void initialize() {
        acceptButton.setOnAction(actionEvent -> {
            addVehicleToList();
            stage.close();
        });


    }

    private void addVehicleToList() {
        final boolean test = plateNumbersVehicleTextField.getText().isEmpty() && cargoSpaceTextField.getText().isEmpty() && driverNameTextField.getText().isEmpty();
        if (!test) {
            System.out.println("dodano");
            MainController.getVehicleList().add(new Vehicle(plateNumbersVehicleTextField.getText(), driverNameTextField.getText(), cargoSpaceTextField.getText()));
            MainController.getVehicleOnlyPlateNumbersList().add(plateNumbersVehicleTextField.getText());
        }
    }

}
