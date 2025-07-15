package DB.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetDBConnection {
    public Connection getConnection(String URL,String USER, String PASSWORD) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection(URL,USER,PASSWORD);
        return conn;
    }
}
