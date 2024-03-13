package com.example.workshops2session2.Model;

import com.example.workshops2session2.client.Client;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements Model, PropertyChangeListener{
    private final Client client;
    private final PropertyChangeSupport support;
    public ModelManager(Client client){
        this.client = client;
        this.client.addPropertyChangeListener(this);
        this.support = new PropertyChangeSupport(this);
    }

    @Override public ArrayList<Task> getTasks() throws IOException
    {
        return client.getTasks();
    }

    @Override
    public synchronized void startTask(Task task) {
        client.startTask(task);
    }

    @Override
    public synchronized void finishTask(Task task) {
        client.finishTask(task);
    }

    @Override
    public synchronized void addTask(Task task) {
        client.addTask(task);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override public void propertyChange(PropertyChangeEvent evt)
    {
        Platform.runLater(() -> {
            if (evt.getPropertyName().equals("List")) {
                this.support.firePropertyChange("List", null, evt.getNewValue());
            }
        });
    }
}
