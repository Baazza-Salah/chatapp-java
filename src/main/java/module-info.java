module com.poo.chatapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.poo.chatapp.presentation to javafx.fxml;
    exports com.poo.chatapp.presentation;
}