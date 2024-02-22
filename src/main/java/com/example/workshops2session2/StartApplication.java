package com.example.workshops2session2;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.ModelManager;
import com.example.workshops2session2.Model.Person;
import com.example.workshops2session2.Model.Task;
import com.example.workshops2session2.Threads.Anna;
import com.example.workshops2session2.Threads.Bob;
import com.example.workshops2session2.View.ViewHandler;
import com.example.workshops2session2.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Model model = new ModelManager();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(stage);

        Bob bob = new Bob(model);
        Thread thread1 = new Thread(bob);
       // thread1.start();

        Anna anna = new Anna(model);
        Thread thread2 = new Thread(anna);
       // thread2.start();


    }

    public static void main(String[] args) {
        launch();
    }
}