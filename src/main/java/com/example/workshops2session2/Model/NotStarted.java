package com.example.workshops2session2.Model;

public class NotStarted implements State{

    @Override
    public void startTask(Task task) {
        task.setState(new InProgress());
    }

    @Override
    public void finishTask(Task task){
        throw new RuntimeException("You can not finish not started task!!!");
    }
    public String toString(){
        return "Not Started";
    }
}
