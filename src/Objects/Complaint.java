package Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Complaint {

    private String description;
    private User complainant;
    private User defendant;
    private String status;

    private StringProperty sp_description;
    private StringProperty sp_complainant;
    private StringProperty sp_defendant;
    private StringProperty sp_status;

    public Complaint(String description, User complainant, User defendant,String status) {
        this.description = description;
        this.complainant = complainant;
        this.defendant = defendant;
        this.status = status;
        sp_description= new SimpleStringProperty(description);
        sp_complainant= new SimpleStringProperty(complainant.getUserName());
        sp_defendant= new SimpleStringProperty(defendant.getUserName());
        sp_status= new SimpleStringProperty(status);
    }

    public User getComplainant() {
        return complainant;
    }

    public void setComplainant(User complainant) {
        this.complainant = complainant;
    }

    public User getDefendant() {
        return defendant;
    }

    public void setDefendant(User defendant) {
        this.defendant = defendant;
    }

    public String getDescription() { return description; }

    public void setDescription(String complaintID) { this.description = complaintID; }

    public StringProperty getSP_complaintDescription() {
        return sp_description;
    }

    public StringProperty getSP_complainantProperty() {
        return sp_complainant;
    }

    public StringProperty getSP_defendantProperty() {
        return sp_defendant;
    }

    public StringProperty getSP_statusProperty() {
        return sp_status;
    }
}
