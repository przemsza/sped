package pl.mzalewski.spedycjaApp.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.dataBase.Vehicle;

import java.util.ArrayList;

public class RemoveVehicleController {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        RemoveVehicleController.stage = stage;
    }

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox vehicleListComboBox;

    public void initialize() {
        setComboBox();

        removeButton.setOnAction(actionEvent -> {
            removeVehicle();
            stage.close();
        });

    }

    private void removeVehicle() {
        String result = vehicleListComboBox.getSelectionModel().getSelectedItem().toString();
        ObservableList<String> vehicleOnlyPlateNumbersList = MainController.getVehicleOnlyPlateNumbersList();

        for (int i = 0; i < vehicleOnlyPlateNumbersList.size(); i++) {
            if (vehicleOnlyPlateNumbersList.get(i).compareTo(result) == 0) vehicleOnlyPlateNumbersList.remove(i);
        }

        ArrayList<Vehicle> vehicleList = MainController.getVehicleList();
        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getPlateNumbers().compareTo(result) == 0) vehicleList.remove(i);
        }
    }

    private void setComboBox() {
        vehicleListComboBox.setItems(MainController.getVehicleOnlyPlateNumbersList());
    }


}
