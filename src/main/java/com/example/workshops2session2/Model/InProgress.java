package com.example.workshops2session2.Model;

public class InProgress implements State{
    @Override
    public void startTask(Task task){
        task.setState(this);
        throw new RuntimeException("Task is already started!!!");
    }

    @Override
    public void finishTask(Task task){
        task.setState(new Done());
    }
    public String toString(){
        return "In Progress";
    }
}
