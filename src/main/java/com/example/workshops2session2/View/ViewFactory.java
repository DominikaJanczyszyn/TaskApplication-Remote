package com.example.workshops2session2.View;

import com.example.workshops2session2.ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory {
    public static final String ADD = "add";
    public static final String MANAGE = "manage";
    public static final String START = "start";

    private final ViewHandler viewHandler;
    private final ViewModelFactory viewModelFactory;
    private StartViewController startViewController;
    private ManageTasksViewController manageTasksViewController;
    private AddTaskViewController addTaskViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.startViewController = null;
        this.manageTasksViewController = null;
        this.addTaskViewController = null;
    }
    public Region loadStartView() {
        if (startViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/workshops2session2/StartView.fxml"));
            try {
                Region root = loader.load();
                startViewController = loader.getController();
                startViewController.init(viewHandler, viewModelFactory.getStartViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        startViewController.reset();
        return startViewController.getRoot();
    }
    public Region loadAddTaskView() {
        if (addTaskViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/workshops2session2/AddTaskView.fxml"));
            try {
                Region root = loader.load();
                addTaskViewController = loader.getController();
                addTaskViewController.init(viewHandler, viewModelFactory.getAddTaskViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        addTaskViewController.reset();
        return addTaskViewController.getRoot();
    }
    public Region loadManageVinylsView() {
        if (manageTasksViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/workshops2session2/ManageTasksView.fxml"));
            try {
                Region root = loader.load();
                manageTasksViewController = loader.getController();
                manageTasksViewController.init(viewHandler, viewModelFactory.getManageTasksViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        return manageTasksViewController.getRoot();
    }


    public Region loadView(String id) {
        return switch (id) {
            case START -> loadStartView();
            case ADD -> loadAddTaskView();
            case MANAGE -> loadManageVinylsView();
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
    }
}
