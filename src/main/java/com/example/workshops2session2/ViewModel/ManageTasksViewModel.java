package com.example.workshops2session2.ViewModel;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.Task;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public class ManageTasksViewModel implements PropertyChangeListener {
    private StringProperty message;
    private final ListProperty<Task> tasks;
    private final ListProperty<Task> ownTasks;
    private ObjectProperty<Task> task;
    private Model model;


    public ManageTasksViewModel(Model model) throws IOException
    {
        this.model = model;
        this.message = new SimpleStringProperty("");
        this.tasks = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.ownTasks = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.task = new SimpleObjectProperty<>();
        model.addPropertyChangeListener(this);

        ArrayList<Task> temp = model.getTasks();
        tasks.clear();
        temp.forEach(task -> tasks.add(task));
        ownTasks.clear();
        temp.forEach(task -> {
            if(task.getPerson().getName() != null && task.getPerson().getName().equals(User.name))
                ownTasks.add(task);
        });
    }

    public void startTask() {
        try {
            if (task.get() != null)
                model.startTask(task.get());
        } catch (Error e) {
            message.set(e.getMessage());
        }
    }
    public void finishTask(){
        try {
            if (task.get() != null)
                model.finishTask(task.get());
        } catch (Error e) {
            message.set(e.getMessage());
        }
    }

    public void bindTasks(ObjectProperty<ObservableList<Task>> property) {
        property.bind(tasks);
    }
    public void bindSelected(ReadOnlyObjectProperty<Task> property) {
        task.bind(property);
    }

    public void bindOwnTasks(ObjectProperty<ObservableList<Task>> property) {
        property.bind(ownTasks);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> { if (evt.getPropertyName().equals("List"))
        {
            ArrayList<Task> temp = ((ArrayList<Task>)evt.getNewValue());
            tasks.clear();
            temp.forEach(task -> tasks.add(task));
            ownTasks.clear();
            temp.forEach(task -> {
                if(task.getPerson().getName() != null && task.getPerson().getName().equals(User.name))
                    ownTasks.add(task);
            });
        }
        });
    }
}
