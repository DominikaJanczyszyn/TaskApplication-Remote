package com.example.workshops2session2.server;

import com.example.workshops2session2.Shared.Connector;
import com.example.workshops2session2.Model.Task;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RemoteConnector implements Connector {
    private final ArrayList<Task> tasks;
    private final RemotePropertyChangeSupport support;

    public RemoteConnector(){
        this.tasks = new ArrayList<>();
        this.support = new RemotePropertyChangeSupport();
    }
    @Override
    public ArrayList<Task> getTasks() throws RemoteException {
        return tasks;
    }

    @Override
    public void startTask(Task task) throws RemoteException {
        for (int i = 0; i < tasks.size(); i++)
        {
            if (tasks.get(i).equals(task)) {
                tasks.get(i).startTask();
                break;
            }
        }
        this.support.firePropertyChange("List", null, tasks);
    }

    @Override
    public void finishTask(Task task) throws RemoteException {
        for (int i = 0; i < tasks.size(); i++)
        {
            if (tasks.get(i).equals(task)) {
                tasks.get(i).finishTask();
                break;
            }
        }
        this.support.firePropertyChange("List", null, tasks);
    }

    @Override
    public void addTask(Task task) throws RemoteException {
        tasks.add(task);
        this.support.firePropertyChange("List", null, tasks);
    }

    @Override
    public void addRemotePropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException {
        this.support.addPropertyChangeListener(listener);
    }
}
