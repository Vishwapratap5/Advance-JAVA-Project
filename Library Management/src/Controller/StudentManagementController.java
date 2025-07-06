package Controller;

import DAO.StudentManagementDAO;
import Model.StudentManagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentManagementController {
    static Scanner sc = new Scanner(System.in);
    static StudentManagementDAO smd=new StudentManagementDAO();
    public static void main(String[] args) {
        StudentManagementController.DisplayMenu();
    }

    private static void DisplayMenu() {
        System.out.println(":: Welcome to the Student Management System ::");
        String Continue;
        int choice;
        do{
            System.out.println(":Showing Menu:");
            System.out.println(":: 1. Add Student");
            System.out.println(":: 2. Delete Student");
            System.out.println(":: 3. Edit Student");
            System.out.println(":: 4. Show All Students");
            System.out.println(":: 5. Find Student by ID");
            System.out.println("Enter your choice :");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:StudentManagementController.addStudent();
                break;
                case 2:StudentManagementController.deleteStudent();
                break;
                case 3:StudentManagementController.updateStudent();
                break;
                case 4:StudentManagementController.getAllStudents();
                break;
                case 5:StudentManagementController.getStudentByID();
                break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.println("Do you want to continue (Y/N):");
            Continue = sc.nextLine();
        }while(Continue.equalsIgnoreCase("Y"));
    }

    private static void addStudent() {
        System.out.println("Adding Student");
        System.out.println("Enter Student's First Name:");
        String firstName = sc.nextLine();
        System.out.println("Enter Student's Last Name:");
        String lastName = sc.nextLine();
        System.out.println("Enter Student's Email:");
        String email = sc.nextLine();
        System.out.println("Enter Student's Phone Number:");
        String phoneNumber = sc.nextLine();

        StudentManagement sm = new StudentManagement();
        sm.setFirstName(firstName);
        sm.setLastName(lastName);
        sm.setEmail(email);
        sm.setPhoneNo(phoneNumber);
        smd.AddStudent(sm);
    }

    private static void deleteStudent() {
        System.out.println("Deliting Student:");
        System.out.println("Enter Student's ID:");
        int id = sc.nextInt();
        StudentManagement sm = new StudentManagement();
        sm.setStudentID(id);
        smd.DeleteStudent(sm);
    }
    private static void updateStudent() {
        System.out.println("Updating Student");
        System.out.println("Enter Student's ID to Update:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Student's new First Name:");
        String firstName = sc.nextLine();
        System.out.println("Enter Student's new Last Name:");
        String lastName = sc.nextLine();
        System.out.println("Enter Student's new Email:");
        String email = sc.nextLine();
        System.out.println("Enter Student's new Phone Number:");
        String phoneNumber = sc.nextLine();
        StudentManagement sm = new StudentManagement();
        sm.setStudentID(id);
        sm.setFirstName(firstName);
        sm.setLastName(lastName);
        sm.setEmail(email);
        sm.setPhoneNo(phoneNumber);
        smd.UpdateStudent(sm);
    }

    private static void getAllStudents() {
        ArrayList<StudentManagement> students = smd.getAllStudents();
        Iterator<StudentManagement> iterator = students.iterator();
        while(iterator.hasNext()){
            StudentManagement sm = iterator.next();
            System.out.println(sm);
        }
        if(students.isEmpty()){
            System.out.println("No Students Found");
        }else{
            System.out.println("Fetched Successfully");
        }

    }

    private static void getStudentByID() {
        System.out.println("getting Student:");
        System.out.println("Enter Student's ID:");
        int id = sc.nextInt();
        StudentManagement sm = new StudentManagement();
        sm.setStudentID(id);
        StudentManagement sm1=smd.getStudentByID(sm);
        if(sm1!=null) {
            System.out.println(sm1);
        }else{
            System.out.println("Student not found");
        }
    }



}
