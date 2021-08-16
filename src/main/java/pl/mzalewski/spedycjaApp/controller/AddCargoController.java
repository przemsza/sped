package pl.mzalewski.spedycjaApp.controller;

import pl.mzalewski.spedycjaApp.dataBase.Cargo;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class AddCargoController {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AddCargoController.stage = stage;
    }

    @FXML
    private ComboBox<String> vehicleListComboBox;

    @FXML
    private DatePicker loadingDateDatePicker;

    @FXML
    private DatePicker unloadingDateDataPicker;

    @FXML
    private TextField clientNameTextField;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private TextField clientEmailTextField;

    @FXML
    private TextField cargoTextField;

    @FXML
    private TextArea commentsTextArea;

    @FXML
    private Button acceptButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField priceCargoTextField;


    @FXML
    private CheckBox partLoadCheckBox;

    @FXML
    private TextField loadingPlaceTextField;

    @FXML
    private TextField unloadingPlaceTextField;

    public void initialize() {
        setComboBox();

        acceptButton.setOnAction(actionEvent -> addCargoToList());
        cancelButton.setOnAction(actionEvent -> stage.close());

    }

    private void addCargoToList() {
        ObservableList<Cargo> cargoList = MainController.getCargoList();
        cargoList.add(new Cargo(vehicleListComboBox.getValue(), clientNameTextField.getText(), companyNameTextField.getText(), clientEmailTextField.getText(), Integer.valueOf(priceCargoTextField.getText()), cargoTextField.getText(), commentsTextArea.getText(), loadingDateDatePicker.getValue().toString(), unloadingDateDataPicker.getValue().toString(), loadingPlaceTextField.getText(), unloadingPlaceTextField.getText(), partLoadCheckBox.isSelected()));
        stage.close();
    }

    private void setComboBox() {
        vehicleListComboBox.setItems(MainController.getVehicleOnlyPlateNumbersList());
    }

}
