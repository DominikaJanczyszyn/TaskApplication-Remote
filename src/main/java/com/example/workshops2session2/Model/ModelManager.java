package com.example.workshops2session2.Model;

import com.example.workshops2session2.Shared.Connector;
import com.example.workshops2session2.client.Client;
import com.example.workshops2session2.client.ClientImpl;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model, PropertyChangeListener{
    private final Client client;
    private final PropertyChangeSupport support;
    public ModelManager(Connector connector) throws RemoteException {
        this.client = new ClientImpl(connector);
        this.client.addPropertyChangeListener(this);
        this.support = new PropertyChangeSupport(this);
    }

    @Override public ArrayList<Task> getTasks() throws IOException
    {
        return client.getTasks();
    }

    @Override
    public synchronized void startTask(Task task) {
        try {
            client.startTask(task);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void finishTask(Task task) {
        try {
            client.finishTask(task);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void addTask(Task task) {
        try {
            client.addTask(task);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
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
