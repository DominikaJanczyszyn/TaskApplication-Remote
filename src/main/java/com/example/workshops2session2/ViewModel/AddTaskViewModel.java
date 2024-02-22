package handin1.viewmodel;

import handin1.model.Model;
import handin1.model.Person;
import handin1.model.Vinyl;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;

public class AddVinylViewModel {

    private StringProperty title;
    private StringProperty name;
    private StringProperty lastname;
    private StringProperty year;
    private StringProperty message;

    private Model model;

    public AddVinylViewModel(Model model){
        this.model = model;
        this.title = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.lastname = new SimpleStringProperty("");
        this.year = new SimpleStringProperty("");
        this.message = new SimpleStringProperty("");
    }
    public void add(){
        try{
            try{
                if (!title.get().isEmpty() && !title.get().equals("") && !name.get().isEmpty() && !name.get().equals("") && !lastname.get().isEmpty() && !lastname.get().equals("")){
                    Vinyl vinyl = new Vinyl(title.get(), new Person(name.get(), lastname.get()), Integer.parseInt(year.get()));
                    model.addVinyl(vinyl);
                    message.set("You added new Vinyl: \n" + vinyl);
                }
                else{
                    message.set("Fill all of the fields!");
                }

            }catch (NumberFormatException e){
                message.set("Wrong input in field year!");
            }
        }catch (IllegalArgumentException e){
            message.set("Wrong input!");
        }
    }
    public void bindTitle(StringProperty property){
        property.bindBidirectional(title);
    }
    public void bindName(StringProperty property){
        property.bindBidirectional(name);
    }
    public void bindLastName(StringProperty property){
        property.bindBidirectional(lastname);
    }
    public void bindYear(StringProperty property){property.bindBidirectional(year);}
    public void bindMessage(StringProperty property){
        property.bindBidirectional(message);
    }

}
