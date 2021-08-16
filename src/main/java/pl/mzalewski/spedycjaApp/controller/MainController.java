package pl.mzalewski.spedycjaApp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.mail.EmailException;
import pl.mzalewski.spedycjaApp.dataBase.Cargo;
import pl.mzalewski.spedycjaApp.dataBase.Vehicle;
import pl.mzalewski.spedycjaApp.window.*;

import java.util.ArrayList;
import java.util.Date;


public class MainController {

    public static Cargo selectedItem;

    public static Cargo getSelectedItem() {
        return selectedItem;
    }

    public static void setSelectedItem(Cargo selectedItem) {
        MainController.selectedItem = selectedItem;
    }

    private static ObservableList<Cargo> cargoList = FXCollections.observableArrayList();

    public static ObservableList<Cargo> getCargoList() {
        return cargoList;
    }

    public static void setCargoList(ObservableList<Cargo> cargoList) {
        MainController.cargoList = cargoList;
    }

    private static ObservableList<String> vehicleOnlyPlateNumbersList = FXCollections.observableArrayList();

    public static ObservableList<String> getVehicleOnlyPlateNumbersList() {
        return vehicleOnlyPlateNumbersList;
    }

    public static void setVehicleOnlyPlateNumbersList(ObservableList<String> vehicleOnlyPlateNumbersList) {
        MainController.vehicleOnlyPlateNumbersList = vehicleOnlyPlateNumbersList;
    }

    private static ArrayList<Vehicle> vehicleList = new ArrayList<>();

    public static ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public static void setVehicleList(ArrayList<Vehicle> vehicleList) {
        MainController.vehicleList = vehicleList;
    }

    @FXML
    private MenuItem edditCargoButton;

    @FXML
    private MenuItem addCargoButton;
    @FXML
    private MenuItem addVehicleButton;

    @FXML
    private MenuItem removeVehicleButton;

    @FXML
    private MenuItem editVehicleButton;

    @FXML
    private MenuItem aboutProgramButton;

    @FXML
    public TableView<Cargo> mainPanelTableViwe;

    @FXML
    private TableColumn<Cargo, String> driverColumnTable;

    @FXML
    private TableColumn<Cargo, String> isPartloadTable;

    @FXML
    private TableColumn<Cargo, String> vehicleColumnTable;

    @FXML
    private TableColumn<Cargo, String> loadingPlaceColumTable;

    @FXML
    private TableColumn<Cargo, String> unloadingPlaceColumnTable;

    @FXML
    private TableColumn<Cargo, String> cargoColumnTable;

    @FXML
    private TableColumn<Cargo, Integer> priceOfCargoColumnTable;

    @FXML
    private TableColumn<Cargo, Date> dateLoadingColumnTable;

    @FXML
    private TableColumn<Cargo, Date> dateUnloadingColumnTable;

    @FXML
    private TableColumn<Cargo, String> commentsColumnTable;

    @FXML
    private TableColumn<Cargo, String> statusVehicleColumnTable;

    @FXML
    private Button loadedCargoButton;

    @FXML
    private Button unloadedCargoButton;

    @FXML
    private Button vehilceTravelToLoadingPlaceButton;

    @FXML
    private Button cancelCargoButton;

    ///////////////////////////////////////////////////////////////////////////////////////

    public void initialize() {

        setMainTableView();
        addVehicleButton.setOnAction(actionEvent -> createAddVehicleWindow());
        removeVehicleButton.setOnAction(actionEvent -> createRemoveVehicleWindow());
        editVehicleButton.setOnAction(actionEvent -> createEditVehicleWindow());
        addCargoButton.setOnAction(actionEvent -> createAddCargoWindow());
        vehilceTravelToLoadingPlaceButton.setOnAction(actionEvent -> vehicleTravelToLoadingPlaceStatus());
        loadedCargoButton.setOnAction(actionEvent -> {
            setCargoStatus();
            createSendMessageWindow();
        });
        unloadedCargoButton.setOnAction(actionEvent -> setUnloadedCargoStatus());
        cancelCargoButton.setOnAction(actionEvent -> cancelCargoStatus());
        edditCargoButton.setOnAction(actionEvent -> createEdditCargoWindow());
    }

    private void createEdditCargoWindow() {
        EditCargoController.selectedItem = mainPanelTableViwe.getSelectionModel().getSelectedItem();
        try {
            new EditCargoWindow().start(new CreateWindow().createStage("/fxml/editCargo.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCargoStatus() {
        try {
            loadedCargoStatus();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }


    private void createSendMessageWindow() {
        try {
            new SendMessageWindow().start(new CreateWindow().createStage("/fxml/sendMessage.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUnloadedCargoStatus() {
        try {
            unloadedCargoStatus();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }


    private void cancelCargoStatus() {
        Cargo selectedItem = mainPanelTableViwe.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.getVehicle());
        selectedItem.setStatus("Towar anulowany");
        mainPanelTableViwe.refresh();
    }

    private void vehicleTravelToLoadingPlaceStatus() {
        selectedItem = mainPanelTableViwe.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.getVehicle());
        selectedItem.setStatus("Jedzie na załadunek");
        mainPanelTableViwe.refresh();
    }

    private void loadedCargoStatus() throws EmailException {
        Cargo selectedItem = mainPanelTableViwe.getSelectionModel().getSelectedItem();
        selectedItem.setStatus("Towar załadowany");
        SendMessageController.setSelectedItem(selectedItem);
        mainPanelTableViwe.refresh();
    }

    private void unloadedCargoStatus() throws EmailException {
        Cargo selectedItem = mainPanelTableViwe.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem.getVehicle());
        selectedItem.setStatus("Towar rozładowany");
        new pl.mzalewski.spedycjaApp.dataBase.SendEmail().sendEmail("unloaded", selectedItem.getClientEmail(), selectedItem.getClientName(), selectedItem.getLoadingPlace(), selectedItem.getUnloadingPlace(), selectedItem.getCargoType());
        mainPanelTableViwe.refresh();
    }

    private void setMainTableView() {
        mainPanelTableViwe.setItems(cargoList);
        vehicleColumnTable.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        loadingPlaceColumTable.setCellValueFactory(new PropertyValueFactory<>("loadingPlace"));
        unloadingPlaceColumnTable.setCellValueFactory(new PropertyValueFactory<>("unloadingPlace"));
        cargoColumnTable.setCellValueFactory(new PropertyValueFactory<>("cargoType"));
        priceOfCargoColumnTable.setCellValueFactory(new PropertyValueFactory<>("cargoPrize"));
        dateLoadingColumnTable.setCellValueFactory(new PropertyValueFactory<>("loadingDate"));
        dateUnloadingColumnTable.setCellValueFactory(new PropertyValueFactory<>("unloadingDate"));
        commentsColumnTable.setCellValueFactory(new PropertyValueFactory<>("comments"));
        statusVehicleColumnTable.setCellValueFactory(new PropertyValueFactory<>("status"));
        driverColumnTable.setCellValueFactory(new PropertyValueFactory<>("driver"));
        isPartloadTable.setCellValueFactory(new PropertyValueFactory<>("partLoadStatus"));
    }

    private void createAddCargoWindow() {

        try {
            new AddCargoWindow().start(new CreateWindow().createStage("/fxml/addCargo.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createEditVehicleWindow() {
        try {
            new EditVehicleWindow().start(new CreateWindow().createStage("/fxml/editVehicle.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createRemoveVehicleWindow() {

        try {
            new RemoveVehicleWindow().start(new CreateWindow().createStage("/fxml/removeVehicle.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void createAddVehicleWindow() {
        try {
            new AddVehicleWindow()
                    .start(new CreateWindow().createStage("/fxml/addVehicle.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
