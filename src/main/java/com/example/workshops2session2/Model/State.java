package com.example.workshops2state_observer_threads_mvvm.Model;

public interface State {

    void acceptTask(Task task);
    void startTask(Task task);
    void finishTask(Task task);
    String toString();

}
