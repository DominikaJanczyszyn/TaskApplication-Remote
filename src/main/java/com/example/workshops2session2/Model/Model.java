package com.example.workshops2session2.Model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public interface Model {
    ArrayList<Task> getTasks() throws IOException;
    void startTask(Task task);
    void finishTask(Task task);
    void addTask(Task task);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
