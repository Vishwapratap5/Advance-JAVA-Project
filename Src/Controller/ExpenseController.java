package Controller;
import Model.Expense;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import DAO.ExpenseDAO;
import lombok.*;
@Builder
public class ExpenseController {
    static Scanner sc = new Scanner(System.in);
   static Expense expense = new Expense();
   static ExpenseDAO expenseDAO = new ExpenseDAO();
    public static void main(String[] args) {
        ExpenseController.DisplayMenu();
    }

    public static void DisplayMenu() {
        System.out.println("Welcome to Expense Controller");
        System.out.println("Showing Menu");
        String Continue="y";
        int choice;
        do{
            System.out.println("1. Add Expense");
            System.out.println("2. Delete Expense");
            System.out.println("3. Edit Expense");
            System.out.println("4. Display All Expense");
            System.out.println("5. Expense by ID");
            System.out.println("6. Expense by Date");
            System.out.println("Enter your choice: ");
            choice=sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:ExpenseController.AddExpense();
                break;
                case 2:ExpenseController.DeleteExpense();
                break;
                case 3:ExpenseController.UpdateExpense();
                break;
                case 4:ExpenseController.DisplayAllExpense();
                break;
                case 5:ExpenseController.DisplayExpense();
                break;
                case 6:ExpenseController.DisplayExpenseByDate();
                break;
                default:System.out.println("Invalid choice");
                break;
            }
            System.out.println("Do you want to continue? (y/n)");
            Continue=sc.nextLine();
        }while(Continue.equals("y"));
    }

    private static void AddExpense() {
        System.out.println("Adding Expense:");
        System.out.println("1.Enter Transaction Category:");
        String category = sc.nextLine();

        System.out.println("2.Enter Transaction Amount:");
        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.println("3.Enter Transaction Description:");
        String description = sc.nextLine();

        System.out.println("4.Enter Transaction Date[YYYY-MM-DD]:");
        String date = sc.nextLine();
        Date DATE=Date.valueOf(date);
        Expense expense=Expense.builder()
        .transactionCategory(category)
        .transactionAmount(amount)
        .transactionDescription(description)
        .transactionDate(DATE)
        .build();
        expenseDAO.AddExpense(expense);
    }

    private static void UpdateExpense() {
        System.out.println("Updating Expense:");
        System.out.println("1.Enter Transaction ID you want to update:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("1.Enter New Transaction Category:");
        String category = sc.nextLine();

        System.out.println("2.Enter New Transaction Amount:");
        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.println("3.Enter New Transaction Description:");
        String description = sc.nextLine();

        System.out.println("4.Enter Transaction Date[YYYY-MM-DD]:");
        String date = sc.nextLine();
        Date DATE=Date.valueOf(date);
        Expense expense=Expense.builder()
                .transactionCategory(category)
                .transactionAmount(amount)
                .transactionDescription(description)
                .transactionDate(DATE)
                .build();
        expenseDAO.UpdateExpense(expense,id);
    }

    private static void DeleteExpense() {
        System.out.println("Deleting Expense:");
        System.out.println("Enter ID of Expense to Delete:");
        int id = sc.nextInt();
        expenseDAO.DeleteExpense(id);
    }

    private static void DisplayAllExpense() {
        System.out.println("Displaying Expense:");
        ArrayList<Expense> expenses = expenseDAO.GetAllExpenses();
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void DisplayExpense() {
        System.out.println("Showing Expense By ID:");
        System.out.println("Enter ID of Expense to Show:");
        int id = sc.nextInt();
        Expense result=expenseDAO.GetExpenseByID(id);
        System.out.println(result);
    }

    private static void DisplayExpenseByDate() {
        System.out.println("Enter Transaction Date[YYYY-MM-DD]:");
        String date = sc.nextLine();
        Date DATE=Date.valueOf(date);
        ArrayList<Expense> expenses = expenseDAO.GetExpenseByDate(DATE);
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}

