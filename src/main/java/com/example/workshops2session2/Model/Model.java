package com.example.workshops2state_observer_threads_mvvm.Model;

import java.beans.PropertyChangeListener;

public interface Model {
    void acceptTask(Person person, Task task);
    void startTask(Person person, Task task);
    void finishTask(Person person, Task task);
    void addTask(Task task);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
