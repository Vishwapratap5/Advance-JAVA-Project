package DAO;
import DB.Properties.DataBaseData;
import Model.IssuedBooksManagement;

import java.sql.*;
import java.util.ArrayList;

public class IssuedBooksManagementDAO {
    DataBaseData db=new DataBaseData();
    private final String URL=db.getURL();
    private final String USERNAME=db.getUSERNAME();
    private final String PASSWORD=db.getPASSWORD();

    public void issueBook(IssuedBooksManagement book){


        /*================================================================================*/
        try(Connection con= DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            con.setAutoCommit(false);
            String sql1="Select AvailableQuantity from BooksManagement where BookID=?";
            PreparedStatement ps1=con.prepareStatement(sql1);
            ps1.setInt(1,book.getIssuedBooksID());
            ResultSet rs1=ps1.executeQuery();
            int AvailableBooksCount=0;
            if(rs1.next()){
                AvailableBooksCount=rs1.getInt("AvailableQuantity");
            }else{
                con.rollback();
                System.out.println("Book ID is Unavailable");
                return;
            }
            /*================================================================================*/
            if( AvailableBooksCount>=1 &&  AvailableBooksCount>=book.getQuantityIssued()){
                String sql2="insert into IssuedBook (BookID ,StudentID ,IssueDate,ExpectedReturnDate ,quantityIssued ) values (?,?,?,?,?)";
                PreparedStatement ps2=con.prepareStatement(sql2);
                ps2.setInt(1,book.getIssuedBooksID());
                ps2.setInt(2,book.getStudentID());
                ps2.setDate(3,book.getIssuedDate());
                ps2.setDate(4,book.getExpectedReturnDate());
                ps2.setInt(5,book.getQuantityIssued());
                int Insertresult=ps2.executeUpdate();

                /*================================================================================*/
                String sql3="update BooksManagement set AvailableQuantity=AvailableQuantity-? where BookID=?";
                PreparedStatement ps3=con.prepareStatement(sql3);
                ps3.setInt(1,book.getQuantityIssued());
                ps3.setInt(2,book.getIssuedBooksID());
                int Updateresult=ps3.executeUpdate();

                if(Updateresult>0 && Insertresult>0){
                    con.commit();
                    System.out.println("Book Issued Successfully");
                }else{
                    con.rollback();
                    System.out.println("Book Issued Failed");
                }
            }else{
                System.out.println("Sorry Requested book is not available");
                con.rollback();
            }


        }catch(SQLException e){
            System.out.println("Problem in Connection "+e);
        }
    }

    /********************************************************************************************/

    public ArrayList<IssuedBooksManagement> VeiwIssuedBook(){
        ArrayList<IssuedBooksManagement> booklist=new ArrayList<>();
        try(Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            Statement stmt=con.createStatement();
            String sql1="Select * from IssuedBook";
            ResultSet rs1=stmt.executeQuery(sql1);
            while(rs1.next()){
                booklist.add(IssuedBooksManagement.builder()
                        .issuedID(rs1.getInt("IssueID"))
                        .issuedBooksID(rs1.getInt("BookID"))
                        .studentID(rs1.getInt("StudentID"))
                        .issuedDate(rs1.getDate("IssueDate"))
                        .expectedReturnDate(rs1.getDate("ExpectedReturnDate"))
                        .quantityIssued(rs1.getInt("quantityIssued"))
                        .build());

            }
            if(booklist.size()>0){
                System.out.println("Issued Books data fetched Successfully");
            }else{
                System.out.println("No issued Books data found");
            }
        }catch(SQLException e){
            System.out.println("Problem in Connection "+e);
        }
        return booklist;
    }

    /********************************************************************************************/

    public ArrayList<IssuedBooksManagement> VeiwIssuedBookbyBookID(IssuedBooksManagement book){
        ArrayList<IssuedBooksManagement> booklist=new ArrayList<>();
        try(Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql1="Select * from IssuedBook where BookID=? ";
            PreparedStatement ps1=con.prepareStatement(sql1);
            ps1.setInt(1,book.getIssuedBooksID());
            ResultSet rs1=ps1.executeQuery();
            while(rs1.next()){
                booklist.add(IssuedBooksManagement.builder()
                        .issuedID(rs1.getInt("IssueID"))
                        .issuedBooksID(rs1.getInt("BookID"))
                        .studentID(rs1.getInt("StudentID"))
                        .issuedDate(rs1.getDate("IssueDate"))
                        .expectedReturnDate(rs1.getDate("ExpectedReturnDate"))
                        .quantityIssued(rs1.getInt("quantityIssued"))
                        .build());

            }
            if(booklist.size()>0){
                System.out.println("Issued Books data fetched Successfully");
            }else{
                System.out.println("No issued Books data found");
            }
        }catch(SQLException e){
            System.out.println("Problem in Connection "+e);
        }
        return booklist;
    }

    public ArrayList<IssuedBooksManagement> VeiwIssuedBookbyStudentID(IssuedBooksManagement book){
        ArrayList<IssuedBooksManagement> booklist=new ArrayList<>();
        try(Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            String sql1="Select * from IssuedBook where  StudentID=?";
            PreparedStatement ps1=con.prepareStatement(sql1);
            ps1.setInt(1,book.getStudentID());
            ResultSet rs1=ps1.executeQuery();
            while(rs1.next()){
                booklist.add(IssuedBooksManagement.builder()
                        .issuedID(rs1.getInt("IssueID"))
                        .issuedBooksID(rs1.getInt("BookID"))
                        .studentID(rs1.getInt("StudentID"))
                        .issuedDate(rs1.getDate("IssueDate"))
                        .expectedReturnDate(rs1.getDate("ExpectedReturnDate"))
                        .quantityIssued(rs1.getInt("quantityIssued"))
                        .build());

            }
            if(booklist.size()>0){
                System.out.println("Issued Books data fetched Successfully");
            }else{
                System.out.println("No issued Books data found");
            }
        }catch(SQLException e){
            System.out.println("Problem in Connection "+e);
        }
        return booklist;
    }
}
