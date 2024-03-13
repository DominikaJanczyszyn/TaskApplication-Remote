package com.example.workshops2session2.Model;

public class Task {
    private final String title;
    private final String description;
    private State state;
    private Person person;

    public Task(String title, String description, Person creator) {
        this.title = title;
        this.description = description;
        this.state = new NotStarted();
        this.person = creator;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Person getPerson() {
        return person;
    }

    public void startTask() {
        state.startTask(this);
    }

    public void finishTask() {
        state.finishTask(this);
    }

    public boolean equals(Object obj) {
        if(obj == null || obj.getClass()!= getClass()) return false;
        Task p = (Task) obj;
        return p.getTitle().equals(this.getTitle()) && p.getDescription().equals(this.getDescription()) && p.getState().equals(this.getState()) && p.getPerson().equals(this.getPerson());
    }

    public String toString() {
        return title+": " +description;
    }
}
