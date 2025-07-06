package Controller;

import DAO.IssuedBooksManagementDAO;
import Model.IssuedBooksManagement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class IssuedBookManagementController {
    static Scanner sc = new Scanner(System.in);
    static IssuedBooksManagementDAO ibmdao=new IssuedBooksManagementDAO();
    public static void main(String[] args) {
        IssuedBookManagementController.DisplayMenu();
    }

    private static void DisplayMenu() {
        System.out.println(":: Welcome to the Issued Books Management System ::");
        String Continue;
        int choice;
        do{
            System.out.println(":Showing Menu:");
            System.out.println(":: 1. Issue Books");
            System.out.println(":: 2. Show All Issued Books");
            System.out.println(":: 3. Get Issued Books by Book ID");
            System.out.println(":: 4. Get Issued Books by Student ID");
            System.out.println("Enter your choice :");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:IssuedBookManagementController.IssueBooks();
                    break;
                case 2:IssuedBookManagementController.ViewIssuedBooks();
                    break;
                case 3:IssuedBookManagementController.ViewIssuedBooksByBookID();
                    break;
                case 4:IssuedBookManagementController.ViewIssuedBooksByStudentID();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("Do you want to continue (Y/N):");
            Continue = sc.nextLine();
        }while(Continue.equalsIgnoreCase("Y"));
    }

    private static void IssueBooks() {
        System.out.println(":: Issueing books ::");
        System.out.println("Enter Book ID you want to issue :");
        int BookID = sc.nextInt();
        System.out.println("Enter Student's ID Who want to get issued books:");
       int StudentID = sc.nextInt();
       System.out.println("Enter No. of Copies you want of this books:");
       int NoCopies = sc.nextInt();
       sc.nextLine();
       System.out.println("Enter Issued date[yyyy-MM-dd]:");
       String IssuedDate = sc.nextLine();
       System.out.println("Enter Expected return date[yyyy-MM-dd]:");
       String ExpectedReturnDate = sc.nextLine();

        IssuedBooksManagement ibm=new IssuedBooksManagement();
        ibm.setIssuedBooksID(BookID);
        ibm.setStudentID(StudentID);
        ibm.setQuantityIssued(NoCopies);
        ibm.setIssuedDate(Date.valueOf(IssuedDate));
        ibm.setExpectedReturnDate(Date.valueOf(ExpectedReturnDate));
        ibmdao.issueBook(ibm);
    }

    private static void ViewIssuedBooks() {
        System.out.println(":: Viewing issued books ::");
        ArrayList<IssuedBooksManagement> booklist=ibmdao.VeiwIssuedBook();
        Iterator<IssuedBooksManagement> iterator=booklist.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(": Data Fetched..!:");
    }

    private static void ViewIssuedBooksByBookID() {
        System.out.println(":: Viewing issued books by ID ::");
        System.out.println("Enter Book ID you want to see :");
        int BookID = sc.nextInt();
        sc.nextLine();
        IssuedBooksManagement ibm=new IssuedBooksManagement();
        ibm.setIssuedBooksID(BookID);
        ArrayList<IssuedBooksManagement> booklist=ibmdao.VeiwIssuedBookbyBookID(ibm);
        Iterator<IssuedBooksManagement> iterator=booklist.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(": Data Fetched..!:");
    }

    private static void ViewIssuedBooksByStudentID() {
        System.out.println(":: Viewing issued books by Student ID ::");
        System.out.println("Enter Student ID you want to see his/her Issued Books :");
        int StudentID = sc.nextInt();
        sc.nextLine();
        IssuedBooksManagement ibm=new IssuedBooksManagement();
        ibm.setStudentID(StudentID);
        ArrayList<IssuedBooksManagement> booklist=ibmdao.VeiwIssuedBookbyStudentID(ibm);
        Iterator<IssuedBooksManagement> iterator=booklist.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(": Data Fetched..!:");
    }
}
