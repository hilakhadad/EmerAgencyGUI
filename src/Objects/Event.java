package Objects;


import Objects.Users.TelephoneRecp;
import Objects.Users.User;

import java.util.Date;
import java.util.List;

public class Event {
    private int eventID;
    private String title;
    private Date date;
    private TelephoneRecp creator;
    private List<Category> categories;
    private Update lastUpdate;
    private String status;

    public Event(int eventID, String title, Date date, TelephoneRecp creator, List<Category> categories, Update lastUpdate, String status) {
        this.eventID = eventID;
        this.title = title;
        this.date = date;
        this.creator = creator;
        this.categories = categories;
        this.lastUpdate = lastUpdate;
        this.status = status;
    }


    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(TelephoneRecp creator) {
        this.creator = creator;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Update getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Update lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return title + ", " + date + ", Last Update: " + lastUpdate.getDescription();
    }

    public Update addNewUpdate(String update_desc, User user){
        Date d = new Date();
        Update u = new Update(this,update_desc,d,user, getLastUpdate());
        lastUpdate = u;
        return u;
    }
}
