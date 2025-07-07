package Controller;

import DAO.ReturnedBookManagementDAO;
import Model.IssuedBooksManagement;
import Model.ReturnedBookManagement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ReturnedBookController {
    static Scanner sc=new Scanner(System.in);
    static ReturnedBookManagementDAO rbmdao=new ReturnedBookManagementDAO();
    public static void main(String[] args) {
        ReturnedBookController.DisplayMenu();
    }

    private static void DisplayMenu() {
        System.out.println(":: Welcome to the Returned Book System ::");
        String Continue;
        int choice;
        do{
            System.out.println(":Showing Menu:");
            System.out.println(":: 1. Return Book");
            System.out.println(":: 2. view Returned book");
            System.out.println("Enter your choice :");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:ReturnedBookController.ReturnBook();
                    break;
                case 2:ReturnedBookController.ViewReturnedBooks();
                break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("Do you want to continue (Y/N):");
            Continue = sc.nextLine();
        }while(Continue.equalsIgnoreCase("Y"));
    }

    private static void ReturnBook() {
        System.out.println(":: Returning books ::");
        System.out.println("Enter Issue ID you want to Return :");
        int IssueID = sc.nextInt();
        System.out.println("Enter Book ID you want to Return :");
        int BookID = sc.nextInt();
        System.out.println("Enter Student's ID Who want to return this books:");
        int StudentID = sc.nextInt();
        System.out.println("Enter No. of Copies you Returned:");
        int NoCopies = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Issued date[yyyy-MM-dd]:");
        String IssuedDate = sc.nextLine();
        System.out.println("Enter Expected return date[yyyy-MM-dd]:");
        String ExpectedReturnDate = sc.nextLine();

        ReturnedBookManagement rbm=new ReturnedBookManagement();
        rbm.setIssueID(IssueID);
        rbm.setBookID(BookID);
        rbm.setStudentID(StudentID);
        rbm.setQuantityRecieved(NoCopies);
        rbm.setIssueDate(Date.valueOf(IssuedDate));
        rbm.setReturnDate(Date.valueOf(ExpectedReturnDate));

        rbmdao.returnBook(rbm);
    }

    private static void ViewReturnedBooks() {
        System.out.println(":: Viewing issued books ::");
        ArrayList<ReturnedBookManagement> booklist=rbmdao.getReturnedBooks();
        Iterator<ReturnedBookManagement> iterator=booklist.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(": Data Fetched..!:");
    }
}
