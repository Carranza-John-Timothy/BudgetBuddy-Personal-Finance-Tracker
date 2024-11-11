import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private ArrayList<Income> incomeList;
    private ArrayList<Expense> expenseList;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.incomeList = new ArrayList<>();
        this.expenseList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addIncome(double amount, String description, Category category) {
        Income income = new Income(amount, description, new Date(), category);
        incomeList.add(income);
    }

    public void addExpense(double amount, String description, String note, Category category) {
        Expense expense = new Expense(amount, description, new Date(), note, category);
        expenseList.add(expense);
    }

    public ArrayList<Income> getIncomeList() {
        return incomeList;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public void editIncome(int index, Income newIncome) {
        if (index >= 0 && index < incomeList.size()) {
            incomeList.set(index, newIncome);
        } else {
            System.out.println("Invalid index for income.");
        }
    }

    public void editExpense(int index, Expense newExpense) {
        if (index >= 0 && index < expenseList.size()) {
            expenseList.set(index, newExpense);
        } else {
            System.out.println("Invalid index for expense.");
        }
    }

    public void deleteIncome(int index) {
        if (index >= 0 && index < incomeList.size()) {
            incomeList.remove(index);
        } else {
            System.out.println("Invalid index for income.");
        }
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenseList.size()) {
            expenseList.remove(index);
        } else {
            System.out.println("Invalid index for expense.");
        }
    }

    public void displayIncome() {
        System.out.println("Income Sources:");
        for (int i = 0; i < incomeList.size(); i++) {
            Income income = incomeList.get(i);
            System.out.println((i + 1) + ". " + income.getDescription() + ": " + income.getAmount() + " (Category: " + income.getCategory().getName() + ")");
        }
    }

    public void displayExpenses() {
        System.out.println("Expenses:");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.get(i);
            System.out.println((i + 1) + ". " + expense.getDescription() + ": " + expense.getAmount() + " (Category: " + expense.getCategory().getName() + ")");
        }
    }

    public void saveData(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Income income : incomeList) {
                writer.write("Income," + income.getDescription() + "," + income.getAmount() + "," + income.getCategory().getName() + "\n");
            }
            for (Expense expense : expenseList) {
                writer.write("Expense," + expense.getDescription() + "," + expense.getAmount() + "," + expense.getNote() + "," + expense.getCategory().getName() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("Income")) {
                    // Create a new category from the last part of the line
                    Category category = new Category(parts[3]);
                    addIncome(Double.parseDouble(parts[2]), parts[1], category);
                } else if (parts[0].equals("Expense")) {
                    // Create a new category from the last part of the line
                    Category category = new Category(parts[4]);
                    addExpense(Double.parseDouble(parts[2]), parts[1], parts[3], category);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}