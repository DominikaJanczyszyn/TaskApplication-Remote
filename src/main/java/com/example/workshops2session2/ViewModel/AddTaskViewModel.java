package com.example.workshops2session2.ViewModel;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.Person;
import com.example.workshops2session2.Model.Task;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddTaskViewModel {
    private StringProperty title;
    private StringProperty description;
    private StringProperty message;

    private Model model;

    public AddTaskViewModel(Model model) {
        this.model = model;
        this.title = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");
        this.message = new SimpleStringProperty("");
    }

    public void add() {
        try {

            if (!title.get().isEmpty() && !title.get().equals("") && !description.get().isEmpty() && !description.get().equals("")){
                Person person = new Person(User.name);
                Task task = new Task(title.get(), description.get(), person);
                model.addTask(task);
                message.set("You added new Task: \n" + task);
            }
            else{
                message.set("Fill all of the fields!");
            }

        } catch (IllegalArgumentException e) {
            message.set(e.getMessage());
        }
    }
    public void bindTitle(StringProperty property) {
        property.bindBidirectional(title);
    }
    public void bindDescription(StringProperty property) {
        property.bindBidirectional(description);
    }
    public void bindMessage(StringProperty property) {
        property.bindBidirectional(message);
    }
}
