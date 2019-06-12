package Views;

import Controller.Controller;
import Objects.Event;
import Objects.Update;
import Objects.User;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.util.Date;

public class UpdateEventAController {


    public ChoiceBox<Event> cb_event;
    public TextArea txt_updateDescription;
    Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void submitUpdate(){
        addUpdate(cb_event.getValue(),txt_updateDescription.getText(),new Date(),controller.getLoggedUser());
    }

    private boolean addUpdate(Event event, String description, Date date, User publishe){
        return controller.addUpdate(event,description,date,publishe);
    }

}
