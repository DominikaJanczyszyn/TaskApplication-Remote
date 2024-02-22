module com.example.workshops2session2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.workshops2session2 to javafx.fxml;
    exports com.example.workshops2session2;
}