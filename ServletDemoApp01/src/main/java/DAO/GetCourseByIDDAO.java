package DAO;

import DB.Properties.DatabaseData;
import DB.Properties.GetDBConnection;
import Model.Course;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCourseByIDDAO {
    DatabaseData db=new DatabaseData();
    final String URL=db.getURL();
    final String USER=db.getUser();
    final String PASSWORD=db.getPassword();
    GetDBConnection getDBConnection=new GetDBConnection();
    Course course2=null;
    public Course getCourse(Course course) {
        try(Connection con=getDBConnection.getConnection(URL,USER,PASSWORD)){
            String sql="select Count(*) from course where CourseID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,course.getCourseID());
            ResultSet rs=ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if(count>0){
                String sql1="select * from course where CourseID=?";
                PreparedStatement ps1=con.prepareStatement(sql1);
                ps1.setInt(1,course.getCourseID());
                ResultSet rs1=ps1.executeQuery();
                if(rs1.next()){
                    course2=Course.builder()
                            .courseID(rs1.getInt("CourseID"))
                            .courseDescription(rs1.getString("CourseDescription"))
                            .courseDuration(rs1.getString("CourseDuration"))
                            .courseFees(rs1.getInt("CourseFees"))
                            .courseName(rs1.getString("CourseName"))
                            .build();
                }else{
                    System.out.println("No Such Course ID");
                }
            }
        }catch(SQLException e){
            System.out.println("Connection error : "+e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("Class not found : "+e.getMessage());
        }
        return course2;
    }
}
