
import Controller.BooksManagementController;
import Controller.IssuedBookManagementController;
import Controller.StudentManagementController;

import java.util.Scanner;

public class LibraryManagement {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        LibraryManagement.DisplayMenu();
    }

    private static void DisplayMenu() {
        System.out.println(":: Welcome to the Library Management System ::");
        int choice;
            System.out.println(":Showing Menu:");
            System.out.println(":: 1.Student Management System");
            System.out.println(":: 2.Books Management System");
            System.out.println(":: 3.Issued Books Management System");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    StudentManagementController.main(new String[]{});
                    break;
                case 2:
                    BooksManagementController.main(new String[]{});
                    break;
                case 3:IssuedBookManagementController.main(new String[]{});
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

    }
}
