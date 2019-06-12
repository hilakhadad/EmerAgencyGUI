package Objects;


import java.util.Date;
import java.util.List;

public class Event {
    private int eventID;
    private String title;
    private Date date;
    private User creator;
    private List<Category> categories;
    private Update firstUpdate;
    private String status;

    public Event(int eventID, String title, Date date, User creator, List<Category> categories, Update firstUpdate, String status) {
        this.eventID = eventID;
        this.title = title;
        this.date = date;
        this.creator = creator;
        this.categories = categories;
        this.firstUpdate = firstUpdate;
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

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Update getFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(Update firstUpdate) {
        this.firstUpdate = firstUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
