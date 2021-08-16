
package pl.mzalewski.spedycjaApp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.dataBase.Vehicle;

import java.util.ArrayList;

public class EditVehicleController {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditVehicleController.stage = stage;
    }


    private String result;

    private int position;

    private ArrayList<Vehicle> vehicleList;

    @FXML
    private TextField driverNameTextField;

    @FXML
    private Button acceptButton;

    @FXML
    private TextField plateNumbersVehicleTextField;

    @FXML
    private TextField cargoSpaceTextField;

    @FXML
    private ComboBox<String> vehicleComboBox;

    public void initialize() {
        setComboBox();
        vehicleComboBox.setOnAction(actionEvent -> autoFillTextFields());
        acceptButton.setOnAction(actionEvent -> {

            vehicleList.get(position).setPlateNumbers(plateNumbersVehicleTextField.getText());
            vehicleList.get(position).setDriverName(driverNameTextField.getText());
            vehicleList.get(position).setCargoSpace(cargoSpaceTextField.getText());

            MainController.getVehicleOnlyPlateNumbersList().clear();
            ArrayList<Vehicle> vehicleList = MainController.getVehicleList();

            for (Vehicle vehicle : vehicleList) {
                MainController.getVehicleOnlyPlateNumbersList().add(vehicle.getPlateNumbers());
            }

            stage.close();
        });
    }

    private void autoFillTextFields() {
        result = vehicleComboBox.getSelectionModel().getSelectedItem();
        vehicleList = MainController.getVehicleList();

        if (result != null) {
            for (int i = 0; i < vehicleList.size(); i++) {
                if (vehicleList.get(i).getPlateNumbers().equals(result)) {
                    plateNumbersVehicleTextField.setText(vehicleList.get(i).getPlateNumbers());
                    driverNameTextField.setText(vehicleList.get(i).getDriverName());
                    cargoSpaceTextField.setText(vehicleList.get(i).getCargoSpace());
                    position = i;
                }
            }
        }
    }

    private void setComboBox() {
        vehicleComboBox.setItems(MainController.getVehicleOnlyPlateNumbersList());
    }


}
