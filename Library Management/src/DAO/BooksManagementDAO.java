package DAO;
import DB.Properties.DataBaseData;
import Model.BooksManagement;
import Model.StudentManagement;

import java.sql.*;
import java.util.ArrayList;

public class BooksManagementDAO {
    DataBaseData db=new DataBaseData();
    private final String URL=db.getURL();
    private final String USERNAME= db.getUSERNAME();
    private final String PASSWORD= db.getPASSWORD();

    public void AddBooks(BooksManagement bm){
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="insert into BooksManagement(BookName,Auther,Category,TotalQuantity,AvailableQuantity) values(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,bm.getBookName());
            ps.setString(2,bm.getAuther());
            ps.setString(3,bm.getCategory());
            ps.setInt(4,bm.getTotalQuantity());
            ps.setInt(5,bm.getAvailableQuantity());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Book Added Successfully");
            }else{
                System.out.println("Book Not Added..!");
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
    }

    public void DeleteBooks(BooksManagement bm){
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="Delete from BooksManagement where BookID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,bm.getBookID());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Book Deleted Successfully");
            }else{
                System.out.println("Book Not Deleted..!");
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
    }

    public void UpdateBooks(BooksManagement bm){
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="update BooksManagement set BookName=?,Auther=? ,Category=? ,TotalQuantity=? , AvailableQuantity=? where BookID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,bm.getBookName());
            ps.setString(2,bm.getAuther());
            ps.setString(3,bm.getCategory());
            ps.setInt(4,bm.getTotalQuantity());
            ps.setInt(5,bm.getAvailableQuantity());
            ps.setInt(6,bm.getBookID());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Book info Updated Successfully");
            }else{
                System.out.println("Info Not Updated..!");
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
    }

    public ArrayList<BooksManagement> getAllBooks(){
        ArrayList<BooksManagement> books=new ArrayList<>();
        try(Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql="select * from BooksManagement";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                books.add(new BooksManagement(rs.getInt("BookID"),
                        rs.getString("BookName"),
                        rs.getString("Auther"),
                        rs.getString("Category"),
                        rs.getInt("TotalQuantity"),
                        rs.getInt("AvailableQuantity")));
            }

        }catch(SQLException e){
            System.out.println("Connection Error" +e);
        }
        return books;
    }

    public BooksManagement getBookByID(BooksManagement bm) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "Select * from BooksManagement where BookID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bm.getBookID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BooksManagement(rs.getInt("BookID"),
                        rs.getString("BookName"),
                        rs.getString("Auther"),
                        rs.getString("Category"),
                        rs.getInt("TotalQuantity"),
                        rs.getInt("AvailableQuantity"));
            } else {
                System.out.println("Book Not Found..!");
            }

        } catch (SQLException e) {
            System.out.println("Connection Error" + e);
        }
        return null;
    }
}
