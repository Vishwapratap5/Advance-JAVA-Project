package DAO;

import DB.Properties.DatabaseData;
import DB.Properties.GetDBConnection;
import Model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowAllCoursesDAO {
    DatabaseData db=new DatabaseData();
    GetDBConnection getDBConnection=new GetDBConnection();
    public final String URL=db.getURL();
    public final String USER=db.getUser();
    public final String PASSWORD=db.getPassword();
    public ArrayList<Course> ShowAllCourses() {
        ArrayList<Course> courses=new ArrayList<>();
        try(Connection connection=getDBConnection.getConnection(URL,USER,PASSWORD)){
            String query="select * from Course";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Course course=Course.builder()
                        .courseID(resultSet.getInt("CourseID"))
                        .courseFees(resultSet.getInt("CourseFees"))
                        . courseName(resultSet.getString("CourseName"))
                        . courseDescription(resultSet.getString("CourseDescription"))
                        . courseDuration(resultSet.getString("CourseDuration"))
                        .build();
                courses.add(course);
            }


        }catch(SQLException e){
            System.out.println("Connection Error : "+e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Connection Error : "+e.getMessage());
        }
        return courses;
    }
}
