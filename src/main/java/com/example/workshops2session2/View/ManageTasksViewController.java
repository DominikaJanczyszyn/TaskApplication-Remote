package com.example.workshops2session2.View;

import com.example.workshops2session2.Model.*;
import com.example.workshops2session2.ViewModel.ManageTasksViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ManageTasksViewController {
    @FXML private TableView<Task> ownTaskTableView;
    @FXML private TableColumn<Task, String> ownTaskTitle;
    @FXML private TableColumn<Task, String> ownTaskDescription;
    @FXML private TableColumn<Task, String> ownTaskStatus;

    @FXML private TableView<Task> taskTableView;
    @FXML private TableColumn<Task, String> taskTitle;
    @FXML private TableColumn<Task, String> taskDescription;
    @FXML private TableColumn<Task, Person> taskCreatedBy;
    @FXML private TableColumn<Task, State> taskStatus;

    @FXML private Button startButton;
    @FXML private Button finishButton;
    private Region root;
    private ViewHandler viewHandler;
    private ManageTasksViewModel viewModel;

    private ReadOnlyObjectProperty<Task> selected;



    public void init(ViewHandler viewHandler, ManageTasksViewModel viewModel, Region root)
    {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        viewModel.bindOwnTasks(ownTaskTableView.itemsProperty());
        this.ownTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.ownTaskDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.ownTaskStatus.setCellValueFactory(new PropertyValueFactory<>("state"));
        this.selected = ownTaskTableView.getSelectionModel().selectedItemProperty();
        this.viewModel.bindSelected(selected);

        viewModel.bindTasks(taskTableView.itemsProperty());
        this.taskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.taskDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.taskCreatedBy.setCellValueFactory(new PropertyValueFactory<>("person"));
        this.taskStatus.setCellValueFactory(new PropertyValueFactory<>("state"));
    }
    @FXML
    public void goToAdd(){
        viewHandler.openView(ViewFactory.ADD);
    }
    @FXML
    public void onStart(){
        viewModel.startTask();
    }
    @FXML
    public void onFinish(){
        viewModel.finishTask();
    }
    @FXML
    public void onSelect(){
        if (selected != null)
        {
            if (selected.get().getState() instanceof NotStarted)
            {
                finishButton.setDisable(true);
                startButton.setDisable(false);
            }
            if (selected.get().getState() instanceof InProgress)
            {
                startButton.setDisable(true);
                finishButton.setDisable(false);
            }
            if (selected.get().getState() instanceof Done)
            {
                finishButton.setDisable(true);
                startButton.setDisable(true);
            }
        }
    }

    public Region getRoot() {
        return root;
    }
}
