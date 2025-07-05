package DAO;
import DB.Properties.DataBaseData;
import Model.Expense;

import java.sql.*;
import java.util.ArrayList;

public class ExpenseDAO {
    DataBaseData db=new DataBaseData();
    private final String URL=db.getURL();
    private final String USER=db.getUSER();
    private final String PASSWORD=db.getPASSWORD();


    public void AddExpense(Expense expense){
        try(Connection  con= DriverManager.getConnection(URL,USER,PASSWORD)){


            String sql="insert into expense(TransactionCategory, TransactionAmount ,TransactionDescription, TransactionDate ) values(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,expense.getTransactionCategory());
            ps.setDouble(2,expense.getTransactionAmount());
            ps.setString(3,expense.getTransactionDescription());
            ps.setDate(4,expense.getTransactionDate());
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Expense added successfully");
            }else{
                System.out.println("Expense not added");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void UpdateExpense(Expense expense,int id){
        try(Connection  con= DriverManager.getConnection(URL,USER,PASSWORD)){
            String sql="Update expense set TransactionCategory=?, TransactionAmount=? ,TransactionDescription=? where TransactionID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,expense.getTransactionCategory());
            ps.setDouble(2,expense.getTransactionAmount());
            ps.setString(3,expense.getTransactionDescription());
            ps.setInt(4,id);
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Expense Updated successfully");
            }else{
                System.out.println("Expense not updated");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public void DeleteExpense(int ID){
        try(Connection  con= DriverManager.getConnection(URL,USER,PASSWORD)){
            String sql="Delete from  expense where  TransactionID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,ID);
            int result=ps.executeUpdate();
            if(result>0){
                System.out.println("Expense Deleted successfully");
            }else{
                System.out.println("Expense not deleted");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public ArrayList<Expense> GetAllExpenses(){
        ArrayList<Expense> expenses=new ArrayList<>();
        try(Connection  con= DriverManager.getConnection(URL,USER,PASSWORD)){
            String sql="select * from  expense";
            Statement ps=con.createStatement();
            ResultSet result=ps.executeQuery(sql);
            while (result.next()) {
                Expense expense = new Expense(
                        result.getInt("TransactionID"),
                        result.getString("TransactionCategory"),
                        result.getDouble("TransactionAmount"),
                        result.getString("TransactionDescription"),
                        result.getDate("TransactionDate")
                );
                expenses.add(expense);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return expenses;
    }
    public Expense GetExpenseByID(int expenseID){
        try(Connection  con= DriverManager.getConnection(URL,USER,PASSWORD)){
            String sql="Select * from  expense where  TransactionID=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,expenseID);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                return new Expense(
                        rs.getInt("TransactionID"),
                        rs.getString("TransactionCategory"),
                        rs.getDouble("TransactionAmount"),
                        rs.getString("TransactionDescription"),
                        rs.getDate("TransactionDate")
                );
            } else {
                System.out.println("⚠ No expense found with ID: " + expenseID);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Expense>  GetExpenseByDate(Date expenseDate){
        ArrayList<Expense> expenses=new ArrayList<>();
        try(Connection  con= DriverManager.getConnection(URL,USER,PASSWORD)){
            String sql="Select * from  expense where  TransactionDate=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1,expenseDate);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                expenses.add(new Expense(
                        rs.getInt("TransactionID"),
                        rs.getString("TransactionCategory"),
                        rs.getDouble("TransactionAmount"),
                        rs.getString("TransactionDescription"),
                        rs.getDate("TransactionDate")
                ));
            }
            if(expenses.size()==0){
                System.out.println("No expense found with date: " + expenseDate);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return expenses;

    }
}
