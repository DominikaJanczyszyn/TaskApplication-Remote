package com.example.workshops2session2.Model;

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
    public synchronized void startTask(Task task) {
        try{
            task.getCreator().startTask(task);
            support.firePropertyChange("List", null, tasks);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public synchronized void finishTask(Task task) {
        try{
            task.getCreator().finishTask(task);
            support.firePropertyChange("List", null, tasks);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public synchronized void addTask(Task task) {
        tasks.add(task);
        support.firePropertyChange("List", null, tasks);

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
