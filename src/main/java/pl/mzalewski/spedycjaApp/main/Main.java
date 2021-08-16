package pl.mzalewski.spedycjaApp.main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.mzalewski.spedycjaApp.controller.MainController;
import pl.mzalewski.spedycjaApp.dataBase.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

    public static FXMLLoader loader;

    public static FXMLLoader getLoader() {
        return loader;
    }

    public static void setLoader(FXMLLoader loader) {
        Main.loader = loader;
    }

    //////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage stage) throws Exception {
        showStage(stage);
    }

    @Override
    public void init() throws Exception {
        readFromFileVehicleList();

    }

    @Override
    public void stop() throws Exception {
        safeToFileVehicleList();
    }

    ////////////////////////////////////////////////////////////////////////
    private void showStage(Stage stage) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/fxml/mainPanel.fxml"));
        Parent load = loader.load();
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Program spedycyjny");
        stage.show();
    }


    private void readFromFileVehicleList() throws IOException {
        ArrayList<Vehicle> vehicleList = MainController.getVehicleList();
        ObservableList<String> vehicleOnlyPlateNumbersList = MainController.getVehicleOnlyPlateNumbersList();

        File file = new File("vehicleList.txt");

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split(";");
            vehicleList.add(new Vehicle(split[0], split[1], split[2]));
            vehicleOnlyPlateNumbersList.add(split[0]);
        }
    }


    private void safeToFileVehicleList() throws IOException {
        ArrayList<Vehicle> vehicleList = MainController.getVehicleList();

        File file = new File("vehicleList.txt");

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < vehicleList.size(); i++) {
            bufferedWriter.write(vehicleList.get(i).getPlateNumbers() + ';');
            bufferedWriter.write(vehicleList.get(i).getDriverName() + ';');
            bufferedWriter.write(vehicleList.get(i).getCargoSpace());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
