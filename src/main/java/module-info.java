module com.example.workshops2session {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.workshops2session2 to javafx.fxml;
    opens com.example.workshops2session2.View to javafx.fxml;
    exports com.example.workshops2session2;
    exports com.example.workshops2session2.Model;
    exports com.example.workshops2session2.ViewModel;
}