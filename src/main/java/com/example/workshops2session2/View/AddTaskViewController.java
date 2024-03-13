package com.example.workshops2session2.View;

import com.example.workshops2session2.ViewModel.AddTaskViewModel;
import com.example.workshops2session2.ViewModel.StartViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class AddTaskViewController {
    @FXML
    public TextField title;
    @FXML public TextArea description;
    @FXML public Label message;
    private Region root;
    private ViewHandler viewHandler;
    private AddTaskViewModel viewModel;

    public void init(ViewHandler viewHandler, AddTaskViewModel viewModel, Region root){
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel =viewModel;
        message.setText("");
        title.setText("");
        description.setText("");

        viewModel.bindTitle(title.textProperty());
        viewModel.bindDescription(description.textProperty());
        viewModel.bindMessage(message.textProperty());

    }
    @FXML
    public void onAdd() {
        viewModel.add();
    }
    public void onGoToOverview() {
        viewHandler.openView(ViewFactory.MANAGE);
    }
    public void reset() {
        title.setText("");
        description.setText("");
        message.setText("");
    }
    public Region getRoot() {
        return root;
    }
}
