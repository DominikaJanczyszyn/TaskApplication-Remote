package com.example.workshops2session2.View;

import com.example.workshops2session2.ViewModel.StartViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class StartViewController {
    @FXML
    public TextField name;
    @FXML public Label message;
    private Region root;
    private ViewHandler viewHandler;
    private StartViewModel viewModel;

    public void init(ViewHandler viewHandler, StartViewModel viewModel, Region root){
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel =viewModel;
        message.setText("");
        name.setText("");

        viewModel.bindName(name.textProperty());
        viewModel.bindMessage(message.textProperty());

    }
    @FXML
    public void onOK(){
        viewModel.add();
        viewHandler.openView(ViewFactory.ADD);

    };
    public void reset() {
        message.setText("");
        name.setText("");
    }
    public Region getRoot() {
        return root;
    }
}
