package pl.mzalewski.spedycjaApp.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.apache.commons.mail.EmailException;
import pl.mzalewski.spedycjaApp.dataBase.Cargo;

public class SendMessageController {

    private static Stage stage;

    private static Cargo selectedItem;

    public static Cargo getSelectedItem() {
        return selectedItem;
    }

    public static void setSelectedItem(Cargo selectedItem) {
        SendMessageController.selectedItem = selectedItem;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SendMessageController.stage = stage;
    }

    @FXML
    private TextArea loadedAmountTextArea;

    @FXML
    private Button sendButton;

    @FXML
    private Button cancelButton;


    public void initialize() {

        setTextArea();
        cancelButton.setOnAction(actionEvent -> stage.close());
        sendButton.setOnAction(actionEvent -> sendEmail());
    }

    private void sendEmail() {
        selectedItem.setCargoType(loadedAmountTextArea.getText());
        try {
            new  pl.mzalewski.spedycjaApp.dataBase.SendEmail().sendEmail(selectedItem.getStatus(),
                    selectedItem.getClientEmail(),
                    selectedItem.getClientName(),
                    selectedItem.getLoadingPlace(),
                    selectedItem.getUnloadingPlace(),
                    selectedItem.getCargoType());

        } catch (EmailException e) {
            System.out.println(e);
        }

        stage.close();
    }

    private void setTextArea() {
        loadedAmountTextArea.setText(selectedItem.getCargoType());
    }


}


