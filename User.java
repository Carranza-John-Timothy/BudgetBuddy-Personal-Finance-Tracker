import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
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

    public void addIncome(Income income) {
        incomeList.add(income);
    }

    public void addExpense(Expense expense) {
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
            System.out.println((i + 1) + ". " + income.getDescription() + ": " + income.getAmount());
        }
    }

    public void displayExpenses() {
        System.out.println("Expenses:");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.get(i);
            System.out.println((i + 1) + ". " + expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void saveData(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Income income : incomeList) {
                writer.write("Income," + income.getDescription() + "," + income.getAmount() + "\n");
            }
            for (Expense expense : expenseList) {
                writer.write("Expense," + expense.getDescription() + "," + expense.getAmount() + "\n");
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
                    addIncome(new Income(Double.parseDouble(parts[2]), parts[1], new Date()));
                } else if (parts[0].equals("Expense")) {
                    addExpense(new Expense(Double.parseDouble(parts[2]), parts[1], new Date(), "Description"));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}