package sample.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;


    /**
     * close the connection
     */
    public void closeConnection(){
        try{
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Connect to a Control database
     */
    public Connection connect() {

        try {
            // db parameters
            String url = "jdbc:sqlite:EmerAgency.db";
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
