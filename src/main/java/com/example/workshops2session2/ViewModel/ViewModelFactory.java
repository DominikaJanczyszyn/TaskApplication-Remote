package com.example.workshops2session2.ViewModel;


import com.example.workshops2session2.Model.Model;

public class ViewModelFactory {
    private StartViewModel startViewModel;
    private AddTaskViewModel addTaskViewModel;
    private ManageTasksViewModel manageTasksViewController;


    public ViewModelFactory(Model model){
        this.startViewModel = new StartViewModel(model);
        this.addTaskViewModel = new AddTaskViewModel(model);
        this.manageTasksViewController = new ManageTasksViewModel(model);
    }
    public StartViewModel getStartViewModel(){
        return startViewModel;
    }
    public AddTaskViewModel getAddTaskViewModel(){
        return addTaskViewModel;
    }
    public ManageTasksViewModel getManageTasksViewModel(){return  manageTasksViewController;}
}
