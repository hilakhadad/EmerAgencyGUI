package Objects.Users;

public abstract class User {

    private String userName;
    private String password;
    private String organization;

    public User(String userName, String password, String organization) {
        this.userName = userName;
        this.password = password;
        this.organization = organization;
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

}
