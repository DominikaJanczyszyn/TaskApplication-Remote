package com.example.workshops2state_observer_threads_mvvm.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model{
    private ArrayList<Task> tasks;
    private final PropertyChangeSupport support;
    public ModelManager(){
        this.tasks = new ArrayList<>();
        this.support = new PropertyChangeSupport(tasks);
    }
    @Override
    public void acceptTask(Person person, Task task) {
        person.acceptTask(task);
        ArrayList<Task> newValue = tasks;
        support.firePropertyChange("List", null, newValue);
    }

    @Override
    public void startTask(Person person, Task task) {
        person.startTask(task);
        ArrayList<Task> newValue = tasks;
        support.firePropertyChange("List", null, newValue);
    }

    @Override
    public void finishTask(Person person,Task task) {
        person.finishTask(task);
        ArrayList<Task> newValue = tasks;
        support.firePropertyChange("List", null, newValue);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        support.firePropertyChange("List", null, task);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
