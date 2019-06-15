package Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Complaint {

    private String description;
//    private User complainant;
//    private User defendant;
    private String complainant;
    private String defendant;
    private String status;
//    private static int idAll=10;
//    private int id;

    private StringProperty sp_description;
    private StringProperty sp_complainant;
    private StringProperty sp_defendant;
    private StringProperty sp_status;

    public Complaint( String description, String complainant, String defendant,String status) {
        this.description = description;
        this.complainant = complainant;
        this.defendant = defendant;
        this.status = status;
//        this.id = idAll;
        sp_description= new SimpleStringProperty(description);
        sp_complainant= new SimpleStringProperty(complainant);
        sp_defendant= new SimpleStringProperty(defendant);
        sp_status= new SimpleStringProperty(status);
//        idAll++;
    }



    public String getStatus() {
        return status;
    }

    public String getComplainant() {
        return complainant;
    }

    public void setComplainant(String complainant) {
        this.complainant = complainant;
    }

    public String getDefendant() {
        return defendant;
    }

    public void setDefendant(String defendant) {
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
