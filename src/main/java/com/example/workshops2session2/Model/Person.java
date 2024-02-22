package com.example.workshops2session2.Model;
public class Person {
    private final String name;

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void startTask(Task task){
        if(task.getState() instanceof NotStarted){
            task.startTask();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public void finishTask(Task task){
        if(task.getState() instanceof InProgress){
            task.finishTask();
        }
        else{
            throw  new IllegalArgumentException();
        }
    }

    public boolean equals(Object obj){
        if(obj == null || obj.getClass()!= getClass()) return false;
        Person p = (Person) obj;
        return p.name.equals(this.getName());
    }
    public String toString(){
        return name;
    }
}
