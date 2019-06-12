package Views;

import Controller.Controller;
import Objects.Event;
import Objects.User;
import javafx.scene.control.Alert;
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

    public void submitUpdate() {
        boolean b = false;
        Alert a;
        if (cb_event.getValue() == null) {
            a = new Alert(Alert.AlertType.ERROR, "you must choose event");
            a.show();
        } else if (txt_updateDescription.getText().equals("")) {
            a = new Alert(Alert.AlertType.ERROR, "you must fill the update");
            a.show();
        } else {
            b = addUpdate(cb_event.getValue(), txt_updateDescription.getText(), new Date(), controller.getLoggedUser());

            if (!b) {
                a = new Alert(Alert.AlertType.ERROR, "something went wrong");
                a.show();
            } else {
                a = new Alert(Alert.AlertType.CONFIRMATION, "your update added to the event\n do you want to send anther update?");
                a.show();
            }
        }
    }

    private boolean addUpdate(Event event, String description, Date date, User publisher){
        return controller.addUpdate(event,description,date,publisher);
    }

}
