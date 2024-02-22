package com.example.workshops2session2.Model;

public class Added implements State{
    @Override
    public void acceptTask(Task task) {
        task.setState(new Accepted());
    }

    @Override
    public void startTask(Task task) {
        task.setState(new InProgress());//error
    }

    @Override
    public void finishTask(Task task) {
        task.setState(new Done());// error
    }
}
