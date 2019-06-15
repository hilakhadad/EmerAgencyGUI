package Objects.Users;

public class RegularUser extends User {
    private int degree;
    private String status;
    private String email;

    public RegularUser(String userName, String password, String organization, int degree, String status, String email) {
        super(userName, password, organization);
        this.degree = degree;
        this.status = status;
        this.email = email;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
