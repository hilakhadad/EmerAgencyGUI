package Models;

import Controller.Controller;
import Objects.Category;
import Objects.Complaint;
import Objects.Event;
import Objects.Update;
import Objects.Users.RegularUser;
import Objects.Users.TelephoneRecp;
import DBConnection.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        createTelephoneRecpTable();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    public void updateEventLastUpdate(String description, Event event) {
        String sql = "UPDATE Events SET lastUpdate = '" + description + "' WHERE id = " + event.getEventID() + ";";
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Update getLastUpdate(Event e) {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM EventUpdates WHERE id='" + e.getEventID() +"' ORDER BY timeCreated DESC";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            Update u = getUpdateObject(resultSet, e);
            conn.close();
            return u;
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }
        return null;
    }

    private Update getUpdateObject(ResultSet resultSet, Event e) {
        try{
            String desc = resultSet.getString("description");
            Date d = resultSet.getTimestamp("timeCreated");
            String username = resultSet.getString("username");
            return new Update(e, desc, d, getUser(username), null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public RegularUser getUser(String username) {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM Users WHERE username='" + username +"'";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            RegularUser u = getUserObject(resultSet);
            conn.close();
            return u;
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }
        return null;
    }

    private RegularUser getUserObject(ResultSet resultSet) {
        try{
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String organization = resultSet.getString("organization");
            int rank = resultSet.getInt("rank");
            String status = resultSet.getString("status");
            String email = resultSet.getString("email");
            return new RegularUser(username,password,organization,rank,status,email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TelephoneRecp getTeleRecp(String username) {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM TelephoneRecpUsers WHERE username='" + username +"'";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            TelephoneRecp tr = getTeleRecpObject(resultSet);
            conn.close();
            return tr;
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }
        return null;
    }

    private TelephoneRecp getTeleRecpObject(ResultSet resultSet) {
        try{
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String organization = resultSet.getString("organization");
            return new TelephoneRecp(username,password,organization);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getCategories(int event_id) {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM EventCategory WHERE event_id='" + event_id +"'";

        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            List<Category> list = getCategoriesList(resultSet);
            conn.close();
            return list;
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }
        return null;
    }

    private List<Category> getCategoriesList(ResultSet resultSet) {
        List<Category> listC = new LinkedList<>();
        try{
            while(resultSet.next()) {
                String category = resultSet.getString("category_name");
                listC.add(new Category(category,""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listC;
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
                "WHERE organization ='"+controller.getLoggedUser().getOrganization()+"' " +
                "AND rank <= " + ((RegularUser)controller.getLoggedUser()).getDegree() + "))";
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

    public ObservableList<RegularUser> showUsers(){
        ResultSet resultSet;
        ObservableList result = null;
        String sql = "SELECT * " +
                "FROM Users";
//                +"WHERE role = 'armed force man'";
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertUsersResultsToObservableList(resultSet);
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
                String userCreated = resultSet.getString("user_created");
                String title = resultSet.getString("title");
                Update update = new Update(null,resultSet.getString("lastUpdate"),null,null,null);
                Event event = new Event(event_id,title,timeCreated,getTeleRecp(userCreated),null,update,eventStatus);
                observableList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return observableList;
    }

    private ObservableList<RegularUser> convertUsersResultsToObservableList(ResultSet resultSet) {
        ObservableList<RegularUser> observableList = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String organization = resultSet.getString("organization");
                int rank = resultSet.getInt("rank");
                String status = resultSet.getString("status");
                String email = resultSet.getString("email");
                observableList.add(new RegularUser(username, password, organization, rank, status, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return observableList;
    }

    public boolean addUpdate(Update update){
        try {
            String sql = "INSERT INTO EventUpdates(event_id,timeCreated,description,username) VALUES(?,?,?,?);";
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, update.getEvent().getEventID());
            pstmt.setObject(2, update.getDate());
            pstmt.setString(3, update.getDescription());
            pstmt.setString(4, update.getPublisher().getUserName());
            pstmt.executeUpdate();
            this.closeConnection(conn);
            updateEventLastUpdate(update.getDescription(),update.getEvent());
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public List<Event> getEventsOfOrg(String org) {
        String sql = "SELECT * FROM Events WHERE id IN(SELECT event_id FROM ResponsibleUser WHERE username IN(SELECT username FROM Users WHERE organization= '" + org + "'))";
        ResultSet resultSet;
        List<Event> result = null;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertToEventList(resultSet);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Event> convertToEventList(ResultSet resultSet) {
        LinkedList<Event> list = new LinkedList<>();
        try {
            while (resultSet.next()) {
                int event_id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Date timeCreated = resultSet.getDate("timeCreated");
                String eventStatus = resultSet.getString("eventStatus");
                TelephoneRecp user = getTeleRecp(resultSet.getString("user_created"));
                List<Category> categories = getCategories(event_id);
                Event e = new Event(event_id,title,timeCreated,user,categories,null,eventStatus);
                Update lastUpdate = getLastUpdate(e);
                e.setLastUpdate(lastUpdate);
                list.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

    private void createTelephoneRecpTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS TelephoneRecpUsers (\n"
                + "	username text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	organization text NOT NULL\n"
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

    public ObservableList<Complaint> searchAllComplaints(String org) {
        String sql = "SELECT * FROM Complaints WHERE organization = '" + org + "'";
        ResultSet resultSet;
        ObservableList result = null;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.convertComplaintResultsToObservableList(resultSet);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ObservableList<Complaint> convertComplaintResultsToObservableList(ResultSet resultSet) {
        ObservableList<Complaint> observableList = FXCollections.observableArrayList();
        try {
            while (resultSet.next()) {
                RegularUser complainant = getUser(resultSet.getString("complainant"));
                RegularUser defendant = getUser(resultSet.getString("defendant"));
                String status = resultSet.getString("status");
                String description = resultSet.getString("description");
                observableList.add(new Complaint(description,complainant,defendant,status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return observableList;
    }

    public boolean createCategory(Category category) {
//        String query = "SELECT COUNT(1) FROM Categories WHERE category_name = '" + category.getCategoryName() + "'";
        String query = "SELECT Count(*) AS newCategoryName FROM Categories WHERE category_name = '" + category.getCategoryName() + "'";
        ResultSet resultSet;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
            if (resultSet.getInt("newCategoryName") == 0) {
                conn.close();
                return addCategory(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean addCategory(Category category) {
        String query = "INSERT INTO Categories (category_name, description) VALUES (?,?)";
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, category.getCategoryName());
            pstmt.setString(2, category.getDescription());
            pstmt.executeUpdate();
            this.closeConnection(conn);
        } catch (SQLException e) { e.printStackTrace(); return false; }
        return true;
    }

    public boolean addComplaint(Complaint complaint) {
        String query = "INSERT INTO Complaints (complainant, defendant, description, status, organization) VALUES (?,?,?,?,?)";
        try {
            Connection conn = this.openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setInt(1, complaint.getId());
            pstmt.setString(1, complaint.getComplainant().getUserName());
            pstmt.setString(2, complaint.getDefendant().getUserName());
            pstmt.setString(3, complaint.getDescription());
            pstmt.setString(4, complaint.getStatus());
            pstmt.setString(5, controller.getLoggedUser().getOrganization());
            pstmt.executeUpdate();
            this.closeConnection(conn);
        } catch (SQLException e) { e.printStackTrace(); return false; }
        return true;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM Categories";
        ResultSet resultSet;
        List<Category> result = null;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.getAllCategoriesList(resultSet);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Category> getAllCategoriesList(ResultSet resultSet) {
        List<Category> listC = new LinkedList<>();
        try{
            while(resultSet.next()) {
                String category = resultSet.getString("category_name");
                String description = resultSet.getString("description");
                listC.add(new Category(category,description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listC;
    }

    public List<RegularUser> getUsersFromOrg(String org) {
        String sql = "SELECT * FROM Users WHERE organization = '" + org + "'";
        ResultSet resultSet;
        List<RegularUser> result = null;
        try {
            Connection conn = this.openConnection();
            Statement stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            result = this.getUserList(resultSet);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<RegularUser> getUserList(ResultSet resultSet) {
        List<RegularUser> list = new LinkedList<>();
        try {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String organization = resultSet.getString("organization");
                int rank = resultSet.getInt("rank");
                String status = resultSet.getString("status");
                String email = resultSet.getString("email");
                list.add(new RegularUser(username, password, organization, rank, status, email));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //endregion
}