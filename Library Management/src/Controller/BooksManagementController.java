package Controller;

import DAO.BooksManagementDAO;
import Model.BooksManagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BooksManagementController {
    static BooksManagementDAO bmd = new BooksManagementDAO();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        BooksManagementController.DisplayMenu();
    }

    private static void DisplayMenu() {
        System.out.println(":: Welcome to the Books Management System ::");
        String Continue;
        int choice;
        do{
            System.out.println(":Showing Menu:");
            System.out.println(":: 1. Add Books");
            System.out.println(":: 2. Delete Books");
            System.out.println(":: 3. Edit books Info");
            System.out.println(":: 4. Show All Books");
            System.out.println(":: 5. Find Books by ID");
            System.out.println("Enter your choice :");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:BooksManagementController.addBook();
                    break;
                case 2:BooksManagementController.deleteBook();
                    break;
                case 3:BooksManagementController.updateBook();
                    break;
                case 4:BooksManagementController.getAllBooks();
                    break;
                case 5:BooksManagementController.getBooksByID();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("Do you want to continue (Y/N):");
            Continue = sc.nextLine();
        }while(Continue.equalsIgnoreCase("Y"));
    }

    private static void addBook() {
        System.out.println(":: Adding Book ::");
        System.out.println("1.Enter book name:");
        String bookName = sc.nextLine();
        System.out.println("2.Enter book author:");
        String author = sc.nextLine();
        System.out.println("3.Enter book category:");
        String category = sc.nextLine();
        System.out.println("4.Enter total Books quantity:");
        int quantity = sc.nextInt();
        System.out.println("5.Enter total Available Books:");
        int availableBooks = sc.nextInt();
        sc.nextLine();

        BooksManagement bms = new BooksManagement();
        bms.setBookName(bookName);
        bms.setAuther(author);
        bms.setCategory(category);
        bms.setTotalQuantity(quantity);
        bms.setAvailableQuantity(availableBooks);
        bmd.AddBooks(bms);
    }

    private static void deleteBook() {
        System.out.println(":: Deleting Book ::");
        System.out.println("1.Enter book ID to delete:");
        int bookID = sc.nextInt();
        sc.nextLine();
        BooksManagement bms = new BooksManagement();
        bms.setBookID(bookID);
        bmd.DeleteBooks(bms);
    }

    private static void updateBook() {
        System.out.println(":: Updating Book ::");
        System.out.println("1.Enter book ID to Update:");
        int bookID = sc.nextInt();
        sc.nextLine();
        System.out.println("2.Enter new book name:");
        String bookName = sc.nextLine();
        System.out.println("3.Enter new book author:");
        String author = sc.nextLine();
        System.out.println("4.Enter new book category:");
        String category = sc.nextLine();
        System.out.println("5.Enter new total Books quantity:");
        int quantity = sc.nextInt();
        System.out.println("6.Enter new total Available Books:");
        int availableBooks = sc.nextInt();
        sc.nextLine();
        BooksManagement bms = new BooksManagement();
        bms.setBookID(bookID);
        bms.setBookName(bookName);
        bms.setAuther(author);
        bms.setCategory(category);
        bms.setTotalQuantity(quantity);
        bms.setAvailableQuantity(availableBooks);
        bmd.UpdateBooks(bms);
    }

    private static void getAllBooks() {
        System.out.println(":: All Books ::");
        ArrayList<BooksManagement> books = bmd.getAllBooks();
        if(books.size()>0){
            Iterator<BooksManagement> iterator = books.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
            System.out.println("All Books Fetched Successfully");
        }else{
            System.out.println("no Books  Found..!");
        }
    }

    private static void getBooksByID() {
        System.out.println(":: Getting Book ::");
        System.out.println("1.Enter book ID:");
        int bookID = sc.nextInt();
        sc.nextLine();
        BooksManagement bms = new BooksManagement();
        bms.setBookID(bookID);
        BooksManagement bmd1=bmd.getBookByID(bms);
        if(bmd1!=null){
            System.out.println(bmd1);
        }else{
            System.out.println("No Books Found..!");
        }

    }

}
