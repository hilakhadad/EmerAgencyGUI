package Views;

import Controller.Controller;
import Objects.Complaint;
import Objects.Event;
import Objects.User;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Date;

public class FilingComplaintController {

    public ComboBox<String> cb_users;
    public TextArea txt_complaintDescription;
    Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void submitComplaint() {
        Alert alert;
        if (cb_users.getValue() == null) {
            alert = new Alert(Alert.AlertType.ERROR, "you must choose defendant");
            alert.show();
        } else if (txt_complaintDescription.getText().equals("")) {
            alert = new Alert(Alert.AlertType.ERROR, "you must fill the complaint details");
            alert.show();
        } else {
            String defendant = cb_users.getValue();
            String complaint = controller.getLoggedUser().getUserName();
            Complaint newComplaint = new Complaint(txt_complaintDescription.getText(), complaint, defendant, "waiting");
            if (!addComplaint(newComplaint)){
                alert = new Alert(Alert.AlertType.ERROR, "something went wrong");
                alert.show();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION, "your complaint added successfully and waiting to confirmation");
                alert.show();
                ((Stage)txt_complaintDescription.getScene().getWindow()).close();
            }
        }
    }

    private boolean addComplaint(Complaint complaint){
        return controller.addComplaint(complaint);
    }

    public void setChoiceBoxItems() {
        cb_users.setItems(controller.getAllUsers());
//        cb_users.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
//            @Override
//            public ListCell<User> call(ListView<User> param) {
//                final ListCell<User> cell = new ListCell<User>(){
//                    @Override
//                    protected void updateItem(User u, boolean bln) {
//                        super.updateItem(u, bln);
//                        if(u != null){
//                            setText(u.getUserName());
//                        }else{
//                            setText(null);
//                        }
//                    }
//                };
//                return cell;
//            }
//        });
    }
}
