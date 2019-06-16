package Controller;

import Objects.Complaint;
import Objects.Event;
import Objects.Users.Admin;
import Objects.Users.CenterAdmin;
import Objects.Users.RegularUser;
import Objects.Users.User;
import Views.View;
import javafx.collections.ObservableList;

public class Controller {

    private View m_view;
    private User loggedUser;
    private Admin policeAdmin;
    private CenterAdmin centerAdmin;

    public Controller(){
    }

    public Admin getPoliceAdmin() {
        return policeAdmin;
    }

    public CenterAdmin getCenterAdmin() {
        return centerAdmin;
    }

    public void setView(View view) {
        m_view = view;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean addUpdate(int event_id, String description) {
        return policeAdmin.createNewUpdate(event_id,description);
    }

    public ObservableList<Complaint> searchAllComplaints() {
        return policeAdmin.searchAllComplaints();
    }

    public ObservableList<Event> getPossibleEvents(){
        return policeAdmin.showAllPossibleEvents();
    }

    public void setPoliceAdmin(Admin policeAdmin) {
        this.policeAdmin = policeAdmin;
        policeAdmin.setController(this);
    }

    public void setCenterAdmin(CenterAdmin centerAdmin) {
        this.centerAdmin = centerAdmin;
        centerAdmin.setController(this);
    }

    public boolean createCategory(String category_name, String category_desc) {
        return centerAdmin.createNewCategory(category_name, category_desc);
    }

    public boolean addComplaint(String username_def, String desc) {
        return policeAdmin.CreateNewComplaint(loggedUser.getUserName(), username_def,desc);
    }

//    public ObservableList<User> getAllUsers(){
//        return m_model.showUsers();
//    }

    public ObservableList<RegularUser> getAllUsers(){
        return centerAdmin.getAllUsers();
    }
}
