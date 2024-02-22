package com.example.workshops2session2.ViewModel;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.Task;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ManageTasksViewModel implements PropertyChangeListener {
    private StringProperty message;
    private final SimpleListProperty<Task> tasks;

    private final SimpleListProperty<Task> ownTasks;
    private SimpleObjectProperty<Task> task;
    private Model model;

    private final Object tasksLock = new Object();


    public ManageTasksViewModel(Model model){
        this.model = model;
        this.message = new SimpleStringProperty("");
        this.tasks = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.ownTasks = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.task = new SimpleObjectProperty<>();
        model.addPropertyChangeListener(this);
    }

    public void startTask(){
        try{
            model.startTask(task.get());
        }catch (Error e){
            message.set(e.getMessage());
        }
    }
    public void finishTask(){
        try{
            model.finishTask(task.get());
        }catch (Error e){
            message.set(e.getMessage());
        }
    }
    public void bindMessage(StringProperty property){
        property.bindBidirectional(message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> { if (evt.getPropertyName().equals("List")) tasks.setAll((ArrayList<Task>)evt.getNewValue());
        ArrayList<Task> temp = new ArrayList<>();
        for(int i =0; i <tasks.size(); i++){
            if(tasks.get(i).getCreator().getName().equals(User.name)){
                temp.add(tasks.get(i));
            }
        }
        ownTasks.setAll(temp);
        });
    }
    public void bindTasks(ObjectProperty<ObservableList<Task>> property) {
        property.bind(tasks);
    }
    public void bindSelected(ReadOnlyObjectProperty<Task> property){task.bind(property);}

    public void bindOwnTasks(ObjectProperty<ObservableList<Task>> property){
        property.bind(ownTasks);
    }
}
