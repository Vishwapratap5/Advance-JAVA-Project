package DB.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class getDBConnection {
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {}
    }
    static Connection con=null;

    public static Connection getConnection(String URL,String USER,String PASSWORD) throws SQLException {
        try{
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(SQLException e){
            System.out.println("Connection Failed!"+e.getMessage());
        }
        return con;
    }
}
