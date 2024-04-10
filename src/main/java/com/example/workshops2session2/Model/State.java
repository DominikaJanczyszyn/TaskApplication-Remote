package com.example.workshops2session2.Model;


public interface State {

    void startTask(Task task);
    void finishTask(Task task);
}
