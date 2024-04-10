package com.example.workshops2session2.Shared;

import com.example.workshops2session2.Model.Task;
import dk.via.remote.observer.RemotePropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Connector extends Remote {

    ArrayList<Task> getTasks() throws RemoteException;
    void startTask(Task task) throws RemoteException;
    void finishTask(Task task) throws RemoteException;
    void addTask(Task task) throws RemoteException;
    void addRemotePropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException;
}
