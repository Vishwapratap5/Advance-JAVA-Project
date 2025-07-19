package DAO;

import DB.Properties.DatabaseData;
import DB.Properties.getDBConnection;
import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginStudentDAO {
    private static String URL= DatabaseData.URL;
    private static String USERNAME= DatabaseData.USERNAME;
    private static String PASSWORD= DatabaseData.PASSWORD;

    public boolean LoginStudent(Student student) {
        try(Connection con= getDBConnection.getConnection(URL,USERNAME,PASSWORD)){
            String query="select count(*) from Student where email=? and password=?";
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1,student.getEmail());
            pst.setString(2,student.getPassword());
            ResultSet rs=pst.executeQuery();
            int count=0;
            while(rs.next()){
               count =rs.getInt(1);
            }
            if(count>0){
                System.out.println(count);
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            System.out.println("Connection error"+e.getMessage());
            return false;
        }

    }
}
