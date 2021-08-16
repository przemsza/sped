package pl.mzalewski.spedycjaApp.controller;

import pl.mzalewski.spedycjaApp.dataBase.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.dataBase.Cargo;
import pl.mzalewski.spedycjaApp.main.Main;

import java.util.ArrayList;

public class EditCargoController {

    public static Cargo selectedItem;

    public static Cargo getSelectedItem() {
        return selectedItem;
    }

    public static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        EditCargoController.stage = stage;
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
        setFieldsText();


        cancelButton.setOnAction(actionEvent -> stage.close());
        acceptButton.setOnAction(actionEvent -> editCargoFunc());
    }


    private void editCargoFunc() {
        selectedItem.setVehicle(vehicleListComboBox.getValue());
        selectedItem.setLoadingDate(loadingDateDatePicker.getEditor().getText());
        selectedItem.setUnloadingDate(unloadingDateDataPicker.getEditor().getText());
        selectedItem.setClientName(clientNameTextField.getText());
        selectedItem.setClientCompanyName(companyNameTextField.getText());
        selectedItem.setClientEmail(clientEmailTextField.getText());
        selectedItem.setCargoType(cargoTextField.getText());
        selectedItem.setComments(commentsTextArea.getText());
        selectedItem.setCargoPrize(Integer.valueOf(priceCargoTextField.getText()));
        selectedItem.setLoadingPlace(loadingPlaceTextField.getText());
        selectedItem.setUnloadingPlace(unloadingPlaceTextField.getText());
        selectedItem.setPartLoadStatus((partLoadCheckBox.isSelected() ? "Doładunek" : "Solo"));
        setDriverName(vehicleListComboBox.getValue());

        stage.close();

        refreshCargoList();

    }

    private void refreshCargoList() {
        MainController controller = Main.getLoader().getController();
        controller.mainPanelTableViwe.refresh();
    }

    private void setDriverName(String vehicle) {
        ArrayList<Vehicle> vehicleList = MainController.getVehicleList();

        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getPlateNumbers().compareTo(vehicle) == 0)
                selectedItem.setDriver(vehicleList.get(i).getDriverName());
        }
    }

    private void setFieldsText() {
        vehicleListComboBox.getSelectionModel().select(selectedItem.getVehicle());
        commentsTextArea.setText(selectedItem.getComments());
        cargoTextField.setText(selectedItem.getCargoType());
        clientEmailTextField.setText(selectedItem.getClientEmail());
        companyNameTextField.setText(selectedItem.getClientCompanyName());
        clientNameTextField.setText(selectedItem.getClientName());
        loadingDateDatePicker.getEditor().setText(selectedItem.getLoadingDate());
        unloadingDateDataPicker.getEditor().setText(selectedItem.getUnloadingDate());
        priceCargoTextField.setText(String.valueOf(selectedItem.getCargoPrize()));
        loadingPlaceTextField.setText(selectedItem.getLoadingPlace());
        unloadingPlaceTextField.setText(selectedItem.getUnloadingPlace());
        partLoadCheckBox.setSelected(selectedItem.isPartLoadStatus().compareTo("Doładunek") == 0);
    }

    private void setComboBox() {
        vehicleListComboBox.setItems(MainController.getVehicleOnlyPlateNumbersList());
    }
}
