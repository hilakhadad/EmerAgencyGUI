package Objects;

import java.util.Date;

public class Update {

    private int updateID;
    private Event event;
    private String description;
    private Date date;
    private User publisher;
    private Update previousUpdate;

    public Update(int updateID, Event event, String description, Date date, User publisher, Update previousUpdate) {
        this.updateID = updateID;
        this.event = event;
        this.description = description;
        this.date = date;
        this.publisher = publisher;
        this.previousUpdate = previousUpdate;
    }

    public int getUpdateID() {
        return updateID;
    }

    public void setUpdateID(int updateID) {
        this.updateID = updateID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public Update getPreviousUpdate() {
        return previousUpdate;
    }

    public void setPreviousUpdate(Update previousUpdate) {
        this.previousUpdate = previousUpdate;
    }
}
