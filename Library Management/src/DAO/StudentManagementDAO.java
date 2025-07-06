package DAO;
import DB.Properties.DataBaseData;
import Model.StudentManagement;

import java.sql.*;
import java.util.ArrayList;

public class StudentManagementDAO {
    DataBaseData db=new DataBaseData();
    private final String URL=db.getURL();
    private final String USERNAME=db.getUSERNAME();
    private final String PASSWORD=db.getPASSWORD();

    public void AddStudent(StudentManagement sm){
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="insert into StudentManagement(FirstName,Lastname,Email,PhoneNo) values(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,sm.getFirstName());
            ps.setString(2,sm.getLastName());
            ps.setString(3,sm.getEmail());
            ps.setString(4,sm.getPhoneNo());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Student Added Successfully");
            }else{
                System.out.println("Student Not Added..!");
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
    }

    public void DeleteStudent(StudentManagement sm){
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="Delete from StudentManagement where StudentID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,sm.getStudentID());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Student Deleted Successfully");
            }else{
                System.out.println("Student Not Deleted..!");
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
    }

    public void UpdateStudent(StudentManagement sm){
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="update StudentManagement set firstName=?,lastName=? ,email=? ,phoneNo=? where StudentID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,sm.getFirstName());
            ps.setString(2,sm.getLastName());
            ps.setString(3,sm.getEmail());
            ps.setString(4,sm.getPhoneNo());
            ps.setInt(5,sm.getStudentID());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Student Updated Successfully");
            }else{
                System.out.println("Student Not Updated..!");
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
    }

    public ArrayList<StudentManagement> getAllStudents(){
        ArrayList<StudentManagement> students=new ArrayList<>();
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="Select * from StudentManagement";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
           while(rs.next()){
              students.add(new StudentManagement(
                      rs.getInt("StudentID"),
                      rs.getString("FirstName"),
                      rs.getString("LastName"),
                      rs.getString("Email"),
                      rs.getString("PhoneNo")));
           }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
        return students;
    }

    public StudentManagement getStudentByID(StudentManagement sm){
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="Select * from StudentManagement where StudentID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,sm.getStudentID());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new StudentManagement(
                        rs.getInt("StudentID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNo"));
            }else{
                System.out.println("Student Not Found..!");
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
        return null;
    }
}
