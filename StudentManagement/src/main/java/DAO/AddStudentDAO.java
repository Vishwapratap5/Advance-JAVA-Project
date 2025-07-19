package DAO;

import DB.Properties.DatabaseData;
import DB.Properties.getDBConnection;
import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddStudentDAO {
    private static String URL= DatabaseData.URL;
    private static String USERNAME= DatabaseData.USERNAME;
    private static String PASSWORD= DatabaseData.PASSWORD;
    public boolean AddStudent(Student student) {
        try(Connection con=getDBConnection.getConnection(URL,USERNAME,PASSWORD)){
            String query="select count(*) from Student where email=? ";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,student.getEmail());
            ResultSet rs=pst.executeQuery();
            int count=0;
            while(rs.next()){
                count =rs.getInt(1);
            }
            if(count==0){
                System.out.println(count);
                String sql="insert into Student(Student_name , email ,password) values (?,?,?)";
                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1,student.getStudentName());
                ps.setString(2,student.getEmail());
                ps.setString(3,student.getPassword());
                int result=ps.executeUpdate();
                if(result>0){
                    System.out.println("new Student added successfully");
                    return true;
                }else{
                    System.out.println("failed to insert new student");
                    return false;
                }

            }else{
                System.out.println("Student Already exists..!");
                return false;
            }

        }catch(SQLException e){
            System.out.println("Connection error : "+e.getMessage());
            return false;
        }

    }
}
