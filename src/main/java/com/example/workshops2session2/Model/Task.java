package com.example.workshops2state_observer_threads_mvvm.Model;

public class Task {
    private final String title;
    private final String description;
    private State state;
    private Person responsible;
    private final Person creator;

    public Task(String title, String description, Person creator) {
        this.title = title;
        this.description = description;
        this.state = new Added();
        this.creator = creator;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Person getCreator() {
        return creator;
    }

    public void acceptTask(){
        state.acceptTask(this);
    }
    public void startTask(){
        state.startTask(this);
    }
    public void finishTask(){
        state.finishTask(this);
    }
    public boolean equals(Object obj){
        if(obj == null || obj.getClass()!= getClass()) return false;
        Task p = (Task) obj;
        return p.getTitle().equals(this.getTitle()) && p.getDescription().equals(this.getDescription()) && p.getResponsible().equals(this.getResponsible()) && p.getState().equals(this.getState()) && p.getCreator().equals(this.getCreator());
    }
}
