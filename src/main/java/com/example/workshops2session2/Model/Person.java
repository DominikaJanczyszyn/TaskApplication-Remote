package com.example.workshops2state_observer_threads_mvvm.Model;

import java.util.ArrayList;

public class Person {
    private final String name;
    public ArrayList<Task> tasks;

    public Person(String name){
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public void acceptTask(Task task){
        if(task.getState() instanceof Added){
            task.setResponsible(this);
            task.acceptTask();
            tasks.add(task);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void startTask(Task task){
        if(task.getState() instanceof Accepted && task.getResponsible().equals(this)){
            task.startTask();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public void finishTask(Task task){
        if(task.getState() instanceof InProgress && task.getResponsible().equals(this)){
            task.finishTask();
        }
        else{
            throw  new IllegalArgumentException();
        }
    }

    public boolean equals(Object obj){
        if(obj == null || obj.getClass()!= getClass()) return false;
        Person p = (Person) obj;
        for(int i = 0; i < tasks.size(); i++){
            if(!tasks.get(i).equals(p.getTasks().get(i))) return false;
        }
        return p.name.equals(this.getName());
    }
}
