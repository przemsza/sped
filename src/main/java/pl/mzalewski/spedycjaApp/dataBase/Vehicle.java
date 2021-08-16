package pl.mzalewski.spedycjaApp.dataBase;

public class Vehicle {

    private String plateNumbers;
    private String driverName;
    private String cargoSpace;

    public Vehicle(String plateNumbers, String driverName, String cargoSpace) {
        this.plateNumbers = plateNumbers;
        this.driverName = driverName;
        this.cargoSpace = cargoSpace;
    }

    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCargoSpace() {
        return cargoSpace;
    }

    public void setCargoSpace(String cargoSpace) {
        this.cargoSpace = cargoSpace;
    }
}
