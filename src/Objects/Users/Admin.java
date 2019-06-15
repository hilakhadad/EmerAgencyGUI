package Objects.Users;

import Controller.Controller;
import Models.Model;
import Objects.Complaint;
import Objects.Event;
import Objects.Update;
import javafx.collections.ObservableList;

import java.util.List;

public class Admin extends User {
    private Model m_model;
    private List<Event> events;
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
    }

    public ObservableList<Event> showAllPossibleEvents(){
        return m_model.showPossibleEventUpdate();
    }

    public ObservableList<Complaint> searchAllComplaints() {
        return m_model.searchAllComplaints();
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

}
