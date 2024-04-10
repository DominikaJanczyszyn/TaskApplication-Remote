package com.example.workshops2session2;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.ModelManager;
import com.example.workshops2session2.Shared.Connector;
import com.example.workshops2session2.View.ViewHandler;
import com.example.workshops2session2.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8080);
        Connector connector = (Connector) registry.lookup("rmiServer");
        Model model = new ModelManager(connector);
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}