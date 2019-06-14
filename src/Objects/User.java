package Objects;

public class User {

    private String userName;
    private String password;
    private String organization;
    private String degree;
    private String status;
    private String email;
    private String role;

    public User(String userName, String password, String organization, String degree, String status, String email, String role) {
        this.userName = userName;
        this.password = password;
        this.organization = organization;
        this.degree = degree;
        this.status = status;
        this.email = email;
        this.role = role;
    }

    public User(String userName){
        this.userName = userName;
        this.password = "";
        this.organization = "";
        this.degree = "";
        this.status = "";
        this.email = "";
        this.role = "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String position) {
        this.role = position;
    }
}
