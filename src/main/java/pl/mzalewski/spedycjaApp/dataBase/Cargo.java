package pl.mzalewski.spedycjaApp.dataBase;

import pl.mzalewski.spedycjaApp.controller.MainController;

import java.util.ArrayList;


public class Cargo {

    private String vehicle;
    private String clientName;
    private String clientCompanyName;
    private String clientEmail;
    private int cargoPrize;
    private String cargoType;
    private String comments;
    private String loadingDate;
    private String unloadingDate;
    private String driver;
    private String status;
    private String loadingPlace;
    private String unloadingPlace;
    private String partLoadStatus;


    public Cargo(String vehicle, String clientName, String clientCompanyName, String clientEmail, int cargoPrize, String cargoType, String comments, String loadingDate, String unloadingDate, String loadingPlace, String unloadingPlace, boolean partLoadStatus) {
        this.vehicle = vehicle;
        this.clientName = clientName;
        this.clientCompanyName = clientCompanyName;
        this.clientEmail = clientEmail;
        this.cargoPrize = cargoPrize;
        this.cargoType = cargoType;
        this.comments = comments;
        this.loadingDate = loadingDate;
        this.unloadingDate = unloadingDate;
        this.status = "Brak";
        this.loadingPlace = loadingPlace;
        this.unloadingPlace = unloadingPlace;
        this.partLoadStatus = partLoadStatus ? "Doładunek" : "Solo";

        setDriverName(vehicle);
    }

    private void setDriverName(String vehicle) {
        ArrayList<Vehicle> vehicleList = MainController.getVehicleList();

        for (int i = 0; i < vehicleList.size(); i++) {
            if (vehicleList.get(i).getPlateNumbers().compareTo(vehicle) == 0)
                this.driver = vehicleList.get(i).getDriverName();
        }
    }

    public String isPartLoadStatus() {
        return partLoadStatus;
    }

    public void setPartLoadStatus(String partLoadStatus) {
        this.partLoadStatus = partLoadStatus;
    }

    public String getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(String loadingPlace) {
        this.loadingPlace = loadingPlace;
    }

    public String getUnloadingPlace() {
        return unloadingPlace;
    }

    public void setUnloadingPlace(String unloadingPlace) {
        this.unloadingPlace = unloadingPlace;
    }


    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCompanyName() {
        return clientCompanyName;
    }

    public void setClientCompanyName(String clientCompanyName) {
        this.clientCompanyName = clientCompanyName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public int getCargoPrize() {
        return cargoPrize;
    }

    public void setCargoPrize(int cargoPrize) {
        this.cargoPrize = cargoPrize;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getUnloadingDate() {
        return unloadingDate;
    }

    public void setUnloadingDate(String unloadingDate) {
        this.unloadingDate = unloadingDate;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
