package autop;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    static Connection conn = null;
    static String url = "jdbc:mysql://localhost:3306/populate?allowMultiQueries=true";
    static String user = "root";
    static String pass = "";

    static Connection makeConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
