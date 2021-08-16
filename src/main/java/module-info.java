open module spedycjaProgram {

    requires javafx.controls;
    requires javafx.graphics;
    requires javax.mail;
    requires javafx.fxml;
    requires commons.email;

    exports pl.mzalewski.spedycjaApp.main to javafx.graphics, javafx.controls;
}