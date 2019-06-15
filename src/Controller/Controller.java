package Controller;

import Models.Model;
import Objects.Category;
import Objects.Complaint;
import Objects.Event;
import Objects.User;
import Views.View;
import javafx.collections.ObservableList;

import java.util.Date;

public class Controller {

    View m_view;
    Model m_model;
    User loggedUser;

    public void setView(View view) {
        m_view = view;
    }

    public void setModel(Model model) {
        m_model = model;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean addUpdate(Event event, String description, Date date, User publishe) {
        return m_model.addUpdate( event,description , new java.sql.Date(date.getTime()) , publishe);
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public ObservableList<Complaint> searchAllComplaints() {
        return m_model.searchAllComplaints();
    }

    public ObservableList<Event> getPossibleEvents(){
        return m_model.showPossibleEventUpdate();
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
