package Controller;

import Models.Model;
import Objects.Category;
import Objects.Complaint;
import Objects.Event;
import Objects.Users.Admin;
import Objects.Users.User;
import Views.View;
import javafx.collections.ObservableList;

public class Controller {

    private View m_view;
    private User loggedUser;
    private Admin policeAdmin;

    public Controller(){
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

    public boolean createCategory(Category category) {
        return m_model.createCategory(category);
    }

    public boolean addComplaint(Complaint complaint) {
        return m_model.addComplaint(complaint);
    }

//    public ObservableList<User> getAllUsers(){
//        return m_model.showUsers();
//    }

    public ObservableList<String> getAllUsers(){
        return m_model.showUsers();
    }
}
