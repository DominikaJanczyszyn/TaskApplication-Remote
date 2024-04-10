package com.example.workshops2session2.Model;

import java.io.Serializable;

public class NotStarted implements State, Serializable {

    @Override
    public void startTask(Task task) {
        task.setState(new InProgress());
    }

    @Override
    public void finishTask(Task task){
        throw new RuntimeException("You can not finish not started task!!!");
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == getClass();
    }

    public String toString(){
        return "Not Started";
    }
}
