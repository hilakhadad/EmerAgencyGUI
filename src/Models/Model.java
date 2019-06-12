package Models;

import Controller.Controller;
import Objects.Event;
import Objects.Update;
import Objects.User;
import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Model {
    private DBConnection driver = new DBConnection();
    private String dbUrl = "jdbc:sqlite:EmerAgency.db";
    private Controller controller;

    public Model(){
        createUsersTable();
        createEventTable();
        createUpdateTable();
        createComplaintTable();
        createCategoryTable();
        createEventCategoryTable();
        createResponsibleUserTable();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void insertUser(User newUser) {
        String sql = "INSERT INTO Users(username, password,organization,rank,status,email,role) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUserName());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getOrganization());
            pstmt.setString(4, newUser.getDegree());
            pstmt.setString(5, newUser.getStatus());
            pstmt.setString(6, newUser.getEmail());
            pstmt.setString(7, newUser.getRole());
            pstmt.executeUpdate();
            this.closeConnection(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void getUsers() {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Users";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            System.out.println(resultSet.getString("username"));
            conn.close();
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

    }

    public ObservableList<Event> showPossibleEventUpdate(){
        ResultSet resultSet;
        ObservableList result = null;
        String sql = "SELECT * " +
                "FROM Events " +
                "WHERE id IN (SELECT event_id " +
                "FROM ResponsibleUser " +
                "WHERE username IN (SELECT username " +
                "FROM Users " +
                "WHERE organization ='"+controller.getLoggedUser().getOrganization()+"'" +
                "AND rank <= '" + controller.getLoggedUser().getDegree() +"'";
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertEventUpdateResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    private ObservableList convertEventUpdateResultsToObservableList(ResultSet resultSet) {
        ObservableList<Event> observableList = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                int event_id = resultSet.getInt("id");
                Date timeCreated = resultSet.getDate("timeCreated");
                String eventStatus = resultSet.getString("eventStatus");
                String userCreated = resultSet.getString("userCreated");
                String title = resultSet.getString("title");
                Update update = new Update(null,null,resultSet.getString("lastUpdate"),null,null,null);
                Event event = new Event(event_id,title,timeCreated,new User(userCreated,null,null,null,null,null,null),null,update,eventStatus);
                observableList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return observableList;
    }


    //region createTables
    private void createUsersTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + "	username text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	organization text NOT NULL,\n"
                + "	rank INTEGER NOT NULL,\n"
                + "	status text NOT NULL,\n"
                + "	email text NOT NULL,\n"
                + "	role text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createEventTable(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Events (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	timeCreated DATETIME NOT NULL,\n"
                + "	eventStatus text NOT NULL,\n"
                + "	user_created text NOT NULL,\n"
                + "	lastUpdate text NOT NULL,\n"
                + " title text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUpdateTable(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS EventUpdates (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	event_id DATETIME NOT NULL,\n"
                + "	timeCreated DATE NOT NULL,\n"
                + "	description text NOT NULL,\n"
                + " username text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCategoryTable(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Categories (\n"
                + "	category_name text NOT NULL PRIMARY KEY,\n"
                + " description text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createComplaintTable(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Complaints (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " complainant text NOT NULL,\n"
                + " defendant text NOT NULL,\n"
                + " description text NOT NULL,\n"
                + " status text NOT NULL,\n"
                + " organization text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createEventCategoryTable(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS EventCategory (\n"
                + " event_id INTEGER,\n"
                + " category_name text NOT NULL,\n"
                + " PRIMARY KEY (event_id, category_name)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createResponsibleUserTable(){
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS ResponsibleUser (\n"
                + " event_id INTEGER,\n"
                + " username text NOT NULL,\n"
                + " PRIMARY KEY (event_id, username)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region DBConnection
    /**
     * open a connection to the database
     *
     * @return connection
     */
    private Connection openConnection() {
        return driver.connect();
    }

    /**
     * close connection to the database
     *
     * @param connection connection
     */
    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //endregion
}
