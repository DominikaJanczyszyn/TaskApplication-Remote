package com.example.workshops2session2.Model;

import java.beans.PropertyChangeListener;

public interface Model {
    void startTask(Task task);
    void finishTask(Task task);
    void addTask(Task task);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);

}
