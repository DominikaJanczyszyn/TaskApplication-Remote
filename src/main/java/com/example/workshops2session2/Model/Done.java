package com.example.workshops2session2.Model;

public class Done implements State{

    @Override
    public void startTask(Task task){

        throw new RuntimeException("You can not start finished task!!!");
    }

    @Override
    public void finishTask(Task task){
        task.setState(this);
        throw new RuntimeException("Task is already finished!!!");
    }

    public String toString(){
        return "Done";
    }
}
