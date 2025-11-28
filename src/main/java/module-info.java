module com.example.john_super {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.john_super to javafx.fxml;
    exports com.example.john_super;
}