package DAO;

import DB.Properties.DatabaseData;
import DB.Properties.GetDBConnection;
import Model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCourseDAO {
    DatabaseData db=new DatabaseData();
    final String URL=db.getURL();
    final String USER=db.getUser();
    final String PASSWORD=db.getPassword();
    GetDBConnection getDBConnection=new GetDBConnection();
    public void AddCourse(Course course){
        try(Connection conn= getDBConnection.getConnection(URL,USER,PASSWORD)){
            String query="insert into course(CourseName , CourseFees , CourseDescription,CourseDuration) values(?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setString(1,course.getCourseName());
            ps.setInt(2,course.getCourseFees());
            ps.setString(3,course.getCourseDescription());
            ps.setString(4,course.getCourseDuration());
            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Course Added Successfully");
            }else{
                System.out.println("Course Adding Failed");
            }

        }catch(SQLException e){
            System.out.println("Connection Problem : "+e);
        }catch(ClassNotFoundException e){
            System.out.println("Connection Problem : "+e);
        }
    }
}
