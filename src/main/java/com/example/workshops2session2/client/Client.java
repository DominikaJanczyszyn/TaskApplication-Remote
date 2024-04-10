package com.example.workshops2session2.client;

import com.example.workshops2session2.Model.Task;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Client
{
  ArrayList<Task> getTasks() throws RemoteException;
  void startTask(Task task) throws RemoteException;
  void finishTask(Task task) throws RemoteException;
  void addTask(Task task) throws RemoteException;
  void addPropertyChangeListener(PropertyChangeListener listener);
}
