package Views;

import Controller.Controller;
import Objects.Complaint;
import Objects.User;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ShowComplaintsController {

    public TableView tv_ComplaintsTableView;
    public TableColumn<Complaint,String> tc_defendant,tc_complainant,tc_description,tc_status;

    Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void showResults(ObservableList<Complaint> searchResults) {
        if (searchResults != null) {
            tc_status.setCellValueFactory(cellData -> cellData.getValue().getSP_statusProperty());
            tc_defendant.setCellValueFactory(cellData -> cellData.getValue().getSP_defendantProperty());
            tc_complainant.setCellValueFactory(cellData -> cellData.getValue().getSP_complainantProperty());
            tc_description.setCellValueFactory(cellData -> cellData.getValue().getSP_complaintDescription());
            this.tv_ComplaintsTableView.setItems(searchResults);
        }
    }

}
