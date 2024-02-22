package com.example.workshops2state_observer_threads_mvvm.Model;

public class InProgress implements State{
    @Override
    public void acceptTask(Task task) {
        task.setState(new Accepted()); //error
    }

    @Override
    public void startTask(Task task) {
        task.setState(new InProgress());
    }

    @Override
    public void finishTask(Task task) {
        task.setState(new Done());//error
    }
}
