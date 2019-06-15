package Objects.Users;

import Controller.Controller;
import Models.Model;
import Objects.Complaint;
import Objects.Event;
import Objects.Update;
import javafx.collections.ObservableList;

import java.util.List;

public class Admin extends User {
    Model m_model;
    private List<Event> events;
    private List<RegularUser> users;
    private List<Complaint> complaints;
    private Controller controller;

    public Admin(String userName, String password, String organization) {
        super(userName, password, organization);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void setModel(Model model){
        this.m_model = model;
        events = m_model.getEventsOfOrg("Police");
        users = m_model.getUsersFromOrg("Police");
        complaints = m_model.searchAllComplaints("Police");
    }

    public ObservableList<Event> showAllPossibleEvents(){
        return m_model.showPossibleEventUpdate();
    }

    public ObservableList<Complaint> searchAllComplaints() {
        return m_model.searchAllComplaints("Police");
    }

    public boolean createNewUpdate(int event_id, String update_desc){
        Event event = getEvent(event_id);
        Update u = event.addNewUpdate(update_desc,controller.getLoggedUser());
        m_model.addUpdate(u);
        m_model.updateEventLastUpdate(update_desc,event);
        return true;
    }

    private Event getEvent(int event_id) {
        for (Event event: events) {
            if(event.getEventID()==event_id)
                return event;
        }
        return null;
    }

    public boolean addComplaint(String un_comp, String un_def, String desc) {
        RegularUser complainant = getUser(un_comp);
        RegularUser defendant = getUser(un_def);
        Complaint c = new Complaint(desc,complainant,defendant,"waiting");
        complaints.add(c);
        m_model.addComplaint(c);
        return true;
    }

    private RegularUser getUser(String username) {
        for (RegularUser reg_user: users) {
            if(reg_user.getUserName().equals(username))
                return reg_user;
        }
        return null;
    }
}
