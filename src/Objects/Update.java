package Objects;

public class Update {

    private String updateID;
    private Event event;
    private String description;
    private String date;
    private User publisher;

    public Update(String updateID, Event event, String description, String date, User publisher) {
        this.updateID = updateID;
        this.event = event;
        this.description = description;
        this.date = date;
        this.publisher = publisher;
    }

    public String getUpdateID() {
        return updateID;
    }

    public void setUpdateID(String updateID) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}
