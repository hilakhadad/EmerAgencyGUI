package Views;

import Controller.Controller;
import Objects.Event;
import Objects.Users.User;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Date;

public class UpdateEventAController {
    public ComboBox<Event> cb_event;
    public TextArea txt_updateDescription;
    private Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void submitUpdate() {
        boolean b;
        Alert a;
        if (cb_event.getValue() == null) {
            a = new Alert(Alert.AlertType.ERROR, "you must choose event");
            a.show();
        } else if (txt_updateDescription.getText().equals("")) {
            a = new Alert(Alert.AlertType.ERROR, "you must fill the update");
            a.show();
        } else {
            b = addUpdate(cb_event.getValue().getEventID(), txt_updateDescription.getText());

            if (!b) {
                a = new Alert(Alert.AlertType.ERROR, "something went wrong, please try again");
                a.show();
            } else {
                a = new Alert(Alert.AlertType.INFORMATION, "your update was added to the event\n");
                a.show();
                ((Stage)txt_updateDescription.getScene().getWindow()).close();
            }
        }
    }

    private boolean addUpdate(int event_id, String desc){
        return controller.addUpdate(event_id,desc);
    }

    void setChoiceBoxItems() {
        cb_event.setItems(controller.getPossibleEvents());
        cb_event.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {
            @Override
            public ListCell<Event> call(ListView<Event> param) {
                final ListCell<Event> cell = new ListCell<Event>(){

                    @Override
                    protected void updateItem(Event t, boolean bln) {
                        super.updateItem(t, bln);

                        if(t != null){
                            setText(t.toString());
                        }else{
                            setText(null);
                        }
                    }



                };
                return cell;
            }
        });
    }
}
