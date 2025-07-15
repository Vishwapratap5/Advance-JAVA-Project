package DAO;

import DB.Properties.DatabaseData;
import DB.Properties.GetDBConnection;
import Model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateCourseDAO {
    DatabaseData db=new DatabaseData();
    GetDBConnection getDB=new GetDBConnection();
    final String URL=db.getURL();
    final String USER=db.getUser();
    final String PASSWORD=db.getPassword();
    public Course UpdateCourse(Course course) {
        Course course2=null;
        try(Connection con= getDB.getConnection(URL,USER,PASSWORD)){
            String sql="Update Course set CourseName=?, CourseFees=?, CourseDescription=?,CourseDuration=? where CourseID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,course.getCourseName());
            ps.setInt(2,course.getCourseFees());
            ps.setString(3,course.getCourseDescription());
            ps.setString(4,course.getCourseDuration());
            ps.setInt(5,course.getCourseID());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Update Course Successfull");
                String sql2="Select * from Course where CourseID=?";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ps2.setInt(1,course.getCourseID());
                ResultSet rs2=ps2.executeQuery();
                if(rs2.next()){
                    course2=Course.builder()
                            .courseID(rs2.getInt("CourseID"))
                            .courseDescription(rs2.getString("CourseDescription"))
                            .courseDuration(rs2.getString("CourseDuration"))
                            .courseFees(rs2.getInt("CourseFees"))
                            .courseName(rs2.getString("CourseName"))
                            .build();
                }

            }else{
                System.out.println("Update Course Failed");
            }


        }catch(SQLException e){
            System.out.println("Connection Error : "+e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Class Error : "+e.getMessage());
        }
        return course2;
    }
}
