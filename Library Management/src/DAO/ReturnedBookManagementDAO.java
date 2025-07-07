package DAO;
import DB.Properties.DataBaseData;
import Model.IssuedBooksManagement;
import Model.ReturnedBookManagement;

import java.sql.*;
import java.util.ArrayList;

public class ReturnedBookManagementDAO {
    DataBaseData db = new DataBaseData();
    private final String URL=db.getURL();
    private final String USERNAME=db.getUSERNAME();
    private final String PASSWORD=db.getPASSWORD();

    public void returnBook(ReturnedBookManagement book) {
        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            con.setAutoCommit(false);

            // Step 1: Check quantity issued
            String sql1 = "SELECT quantityIssued FROM IssuedBook WHERE StudentID = ? AND BookID = ?";
            try (PreparedStatement ps1 = con.prepareStatement(sql1)) {
                ps1.setInt(1, book.getStudentID());
                ps1.setInt(2, book.getBookID());
                ResultSet rs1 = ps1.executeQuery();

                if (!rs1.next()) {
                    System.out.println("No record found for this Book ID and Student ID.");
                    con.rollback();
                    return;
                }

                int issuedBooksCount = rs1.getInt("quantityIssued");

                // Step 2: Compare quantities
                if (book.getQuantityRecieved() > issuedBooksCount) {
                    System.out.println("You're returning more books than issued!");
                    con.rollback();
                    return;
                }
                if (book.getQuantityRecieved() < issuedBooksCount) {
                    System.out.println("You're returning Less books than issued!");
                    con.rollback();
                    return;
                }

                // Step 3: Insert into ReturnBooks
                String sql2 = "INSERT INTO ReturnBooks (IssueID, BookID, StudentID, IssueDate, ReturnDate, quantityRecieved) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
                    ps2.setInt(1, book.getIssueID());
                    ps2.setInt(2, book.getBookID());
                    ps2.setInt(3, book.getStudentID());
                    ps2.setDate(4, Date.valueOf(book.getIssueDate().toLocalDate()));
                    ps2.setDate(5, Date.valueOf(book.getReturnDate().toLocalDate()));
                    ps2.setInt(6, book.getQuantityRecieved());

                    int insertResult = ps2.executeUpdate();

                    // Step 4: Update Books stock
                    String sql3 = "UPDATE BooksManagement SET AvailableQuantity = AvailableQuantity + ? WHERE BookID = ?";
                    try (PreparedStatement ps3 = con.prepareStatement(sql3)) {
                        ps3.setInt(1, book.getQuantityRecieved());
                        ps3.setInt(2, book.getBookID());
                        int updateResult = ps3.executeUpdate();

                        if (insertResult > 0 && updateResult > 0) {
                            con.commit();
                            System.out.println("✅ Book Returned Successfully! Thank you.");
                        } else {
                            con.rollback();
                            System.out.println("❌ Book return failed, transaction rolled back.");
                        }
                    }
                }
            } catch (SQLException e) {
                con.rollback();
                System.out.println("❌ Operation failed. Rolled back. Reason: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("🚫 Connection problem: " + e.getMessage());
        }
    }

    public ArrayList<ReturnedBookManagement> getReturnedBooks() {
        ArrayList<ReturnedBookManagement> booklist=new ArrayList<>();
        try(Connection con=DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            Statement stmt=con.createStatement();
            String sql1="Select * from ReturnBooks";
            ResultSet rs1=stmt.executeQuery(sql1);
            while(rs1.next()){
                booklist.add(ReturnedBookManagement.builder()
                        .returnID(rs1.getInt("IssueID"))
                        .issueID(rs1.getInt("IssueID"))
                        .bookID(rs1.getInt("BookID"))
                        .issueDate(rs1.getDate("IssueDate"))
                        .returnDate(rs1.getDate("ReturnDate"))
                        .quantityRecieved(rs1.getInt(" quantityRecieved"))
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
