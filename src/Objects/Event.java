package Objects;


import java.util.Date;
import java.util.List;

public class Event {

    private String title;
    private Date date;
    private User creator;
    private List<Category> categories;
    private String firstUpdate;
    private String status;

    public Event(String title, Date date, User creator, List<Category> categories, String firstUpdate, String status) {
        this.title = title;
        this.date = date;
        this.creator = creator;
        this.categories = categories;
        this.firstUpdate = firstUpdate;
        this.status = status;
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

    public String getFirstUpdate() {
        return firstUpdate;
    }

    public void setFirstUpdate(String firstUpdate) {
        this.firstUpdate = firstUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
