package com.example.workshops2session2.Model;

import java.io.Serializable;

public class InProgress implements State, Serializable {
    @Override
    public void startTask(Task task){
        task.setState(this);
        throw new RuntimeException("Task is already started!!!");
    }

    @Override
    public void finishTask(Task task){
        task.setState(new Done());
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == getClass();
    }

    public String toString(){
        return "In Progress";
    }
}
