package com.example.workshops2session2.client;

import com.example.workshops2session2.Model.Task;

import java.beans.PropertyChangeListener;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

public interface Client extends Closeable
{
  ArrayList<Task> getTasks() throws IOException;
  void startTask(Task task);
  void finishTask(Task task);
  void addTask(Task task);
  void addPropertyChangeListener(PropertyChangeListener listener);
  void removePropertyChangeListener(PropertyChangeListener listener);
}
