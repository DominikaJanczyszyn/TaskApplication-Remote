package com.example.workshops2session2.Model;

import java.io.Serializable;

public class Done implements State, Serializable {
    @Override
    public void startTask(Task task) {

        throw new RuntimeException("You can not start finished task!!!");
    }

    @Override
    public void finishTask(Task task) {
        task.setState(this);
        throw new RuntimeException("Task is already finished!!!");
    }

    public boolean equals(Object obj) {
      return obj != null && obj.getClass() == getClass();
    }

    public String toString() {
        return "Done";
    }
}
