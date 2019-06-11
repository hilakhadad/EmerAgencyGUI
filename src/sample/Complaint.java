package sample;

public class Complaint {

    private User complainant;
    private User defendant;

    public Complaint(User complainant, User defendant) {
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
}
