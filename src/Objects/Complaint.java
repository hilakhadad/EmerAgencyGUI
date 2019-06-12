package Objects;

public class Complaint {

    private String complaintID;
    private User complainant;
    private User defendant;

    public Complaint(String complaintID, User complainant, User defendant) {
        this.complaintID = complaintID;
        this.complainant = complainant;
        this.defendant = defendant;
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

    public String getComplaintID() { return complaintID; }

    public void setComplaintID(String complaintID) { this.complaintID = complaintID; }
}
