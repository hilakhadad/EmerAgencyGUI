package Views;

import Controller.Controller;
import Objects.Users.RegularUser;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FilingComplaintController {

    public ComboBox<RegularUser> cb_users;
    public TextArea txt_complaintDescription;
    private Controller controller;

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
            String defendant = cb_users.getValue().getUserName();
            if (!addComplaint(defendant,txt_complaintDescription.getText())){
                alert = new Alert(Alert.AlertType.ERROR, "Category already exists");
                alert.show();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION, "your complaint added successfully and waiting to confirmation");
                alert.show();
                ((Stage)txt_complaintDescription.getScene().getWindow()).close();
            }
        }
    }

    private boolean addComplaint(String username_def, String desc){
        return controller.addComplaint(username_def, desc);
    }

    public void setChoiceBoxItems() {
        ObservableList<RegularUser> list = controller.getAllUsers();
        list = removeUserFromList(list);
        cb_users.setItems(list);
        cb_users.setCellFactory(new Callback<ListView<RegularUser>, ListCell<RegularUser>>() {
           @Override
           public ListCell<RegularUser> call(ListView<RegularUser> param) {
               final ListCell<RegularUser> cell = new ListCell<RegularUser>(){
                   @Override
                   protected void updateItem(RegularUser u, boolean bln) {
                       super.updateItem(u, bln);
                       if(u != null){
                           setText(u.getUserName());
                       }else{
                           setText(null);
                       }
                   }
               };
               return cell;
           }
       });
    }

    private ObservableList<RegularUser> removeUserFromList(ObservableList<RegularUser> list) {
        int idx = -1;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getUserName().equals(controller.getLoggedUser().getUserName()))
                idx = i;
        }
        if(idx!=-1)
            list.remove(idx);
        return list;
    }
}
