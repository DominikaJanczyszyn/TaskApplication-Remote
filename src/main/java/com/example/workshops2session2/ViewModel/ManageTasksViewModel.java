package com.example.workshops2session2.ViewModel;

import handin1.model.Model;
import handin1.model.Person;
import handin1.model.Vinyl;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ManageVinylViewModel implements PropertyChangeListener {
    private StringProperty name;
    private StringProperty lastname;
    private StringProperty message;
    private final SimpleListProperty<Vinyl> vinyls;
    private SimpleObjectProperty<Vinyl> vinyl;
    private Model model;


    public ManageVinylViewModel(Model model){
        this.model = model;
        this.name = new SimpleStringProperty("");
        this.lastname = new SimpleStringProperty("");
        this.message = new SimpleStringProperty("");
        this.vinyls = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.vinyl = new SimpleObjectProperty<>();
        model.addPropertyChangeListener(this);
    }
    public void borrow(){
        try{
            if(!name.get().isEmpty() && !name.get().equals("") && !lastname.get().isEmpty() && !lastname.get().equals("")){
                Person person = new Person(name.get(), lastname.get());
                message.set("You lent vinyl\n" + vinyl.get().getTitle()+ " to\n" + person);
                model.borrowVinyl(person, vinyl.get());
            }else{
                message.set("Input person's data!");
            }

        }catch (IllegalArgumentException e){
            message.set("Error");
        }
        catch(RuntimeException e){
            message.set("Choose a vinyl");
        }
    }
    public void reserve(){
        try{
            if(!name.get().isEmpty() && !name.get().equals("") && !lastname.get().isEmpty() && !lastname.get().equals("")){
                Person person = new Person(name.get(), lastname.get());
                message.set("You reserved vinyl\n" + vinyl.get().getTitle() + "\nfor " + person);
                model.reserveVinyl(person, vinyl.get());
            }else{
                message.set("Input person's data!");
            }
        }catch (IllegalArgumentException e){
            message.set("Error");
        }
    }
    public void returnVinyl(){
        try{
            Person person = new Person(name.get(), lastname.get());
            message.set(person + " returned vinyl :\n" + vinyl.get().getTitle());
            model.returnVinyl(person, vinyl.get());
        }catch (IllegalArgumentException e){
            message.set("Error");
        }
    }
    public void delete(){
        try{
            message.set("You deleted :\n " + vinyl.get().getTitle());
            model.deleteVinyl(vinyl.get());
        }catch(IllegalArgumentException e){

        }
    }
    public void bindName(StringProperty property){
        property.bindBidirectional(name);
    }
    public void bindLastName(StringProperty property){
        property.bindBidirectional(lastname);
    }
    public void bindMessage(StringProperty property){
        property.bindBidirectional(message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> { if (evt.getPropertyName().equals("List")) vinyls.setAll((ArrayList<Vinyl>)evt.getNewValue());});
    }
    public void bindVinyls(ObjectProperty<ObservableList<Vinyl>> property) {
        property.bind(vinyls);
    }
    public void bindSelected(ReadOnlyObjectProperty<Vinyl> property){vinyl.bind(property);}
}
