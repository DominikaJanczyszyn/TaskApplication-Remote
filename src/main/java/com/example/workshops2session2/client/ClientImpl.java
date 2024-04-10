package com.example.workshops2session2.client;

import com.example.workshops2session2.Shared.Connector;
import com.example.workshops2session2.Model.Task;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientImpl extends UnicastRemoteObject implements Client , RemotePropertyChangeListener
{
  private final Connector connector;
  private final PropertyChangeSupport support;


  public ClientImpl(Connector connector) throws RemoteException
  {
    this.connector = connector;
    this.support = new PropertyChangeSupport(this);
    this.connector.addRemotePropertyChangeListener(this);
  }

  @Override public ArrayList<Task> getTasks() throws RemoteException
  {
    try{
      return connector.getTasks();
    }catch (Exception e){
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void startTask(Task task) throws RemoteException
  {
    try{
      connector.startTask(task);
    }catch (Exception e){
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void finishTask(Task task) throws RemoteException
  {
    try{
      connector.finishTask(task);
    }catch (Exception e){
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void addTask(Task task) throws RemoteException
  {
    try{
      connector.addTask(task);
    }catch (Exception e){
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void addPropertyChangeListener (
      PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override
  public void propertyChange(RemotePropertyChangeEvent event) throws RemoteException {
    Platform.runLater(()->{
      if (event.getPropertyName().equals("List"))
        this.support.firePropertyChange("List", null, event.getNewValue());
    });
  }
}
