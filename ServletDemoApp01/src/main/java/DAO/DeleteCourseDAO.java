package DAO;

import DB.Properties.DatabaseData;
import DB.Properties.GetDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCourseDAO {
    DatabaseData db=new DatabaseData();
    GetDBConnection getDBConnection=new GetDBConnection();
    private final String URL=db.getURL();
    private final String USER=db.getUser();
    private final String PASSWORD=db.getPassword();

    public void deleteCourse(int courseId) {
        try(Connection con=getDBConnection.getConnection(URL,USER,PASSWORD)){

            String sql="delete from course where courseId=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,courseId);
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Course deleted successfully");
            }else{
                System.out.println("Course not deleted");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
