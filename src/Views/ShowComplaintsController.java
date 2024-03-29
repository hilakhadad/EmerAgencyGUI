package Views;

import Controller.Controller;
import Objects.Complaint;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ShowComplaintsController {

    public TableView<Complaint> tv_ComplaintsTableView;
    public TableColumn<Complaint,String> tc_defendant,tc_complainant,tc_description,tc_status;

    void showResults(ObservableList<Complaint> searchResults) {
        if (searchResults != null && searchResults.size()>0) {
            tc_status.setCellValueFactory(cellData -> cellData.getValue().getSP_statusProperty());
            tc_defendant.setCellValueFactory(cellData -> cellData.getValue().getSP_defendantProperty());
            tc_complainant.setCellValueFactory(cellData -> cellData.getValue().getSP_complainantProperty());
            tc_description.setCellValueFactory(cellData -> cellData.getValue().getSP_complaintDescription());
            this.tv_ComplaintsTableView.setItems(searchResults);
        }
    }

}
