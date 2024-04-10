module com.example.workshops2session {
    requires javafx.controls;
    requires javafx.fxml;
    requires remoteobserver;
    requires java.desktop;
    requires com.google.gson;
    requires java.rmi;

    opens com.example.workshops2session2.server to com.google.gson;
    opens com.example.workshops2session2.client to java.rmi;
    opens com.example.workshops2session2.Model to com.google.gson, javafx.base;
    opens com.example.workshops2session2 to javafx.fxml;
    opens com.example.workshops2session2.View to javafx.fxml;
    opens com.example.workshops2session2.Shared to remoteobserver, java.rmi;


    exports com.example.workshops2session2;
}