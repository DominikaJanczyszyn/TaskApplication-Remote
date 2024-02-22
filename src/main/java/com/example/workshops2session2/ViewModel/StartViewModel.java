package com.example.workshops2session2.ViewModel;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StartViewModel {
    private StringProperty name;
    private StringProperty message;

    private Model model;

    public StartViewModel(Model model){
        this.model = model;
        this.name = new SimpleStringProperty("");
        this.message = new SimpleStringProperty("");
    }
    public void add(){
        try{

            if (!name.get().isEmpty() && !name.get().equals("")){
                Person person = new Person(name.get());
                User.name = person.getName();
            }
            else{
                message.set("Fill all of the fields!");
            }

        }catch (IllegalArgumentException e){
            message.set("Wrong input!");
        }
    }

    public void bindName(StringProperty property){
        property.bindBidirectional(name);
    }
    public void bindMessage(StringProperty property){
        property.bindBidirectional(message);
    }

}
