package com.example.workshops2session2;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.ModelManager;
import com.example.workshops2session2.Model.Person;
import com.example.workshops2session2.Model.Task;
import com.example.workshops2session2.Threads.Anna;
import com.example.workshops2session2.Threads.Bob;
import com.example.workshops2session2.View.ViewHandler;
import com.example.workshops2session2.ViewModel.ViewModelFactory;
import com.example.workshops2session2.client.Client;
import com.example.workshops2session2.client.ClientImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Client client = new ClientImpl("localhost", 8080, "230.0.0.0", 8888);
        Model model = new ModelManager(client);
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}