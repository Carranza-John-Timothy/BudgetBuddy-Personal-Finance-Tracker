import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;
    private double balance; // Current balance

    public TransactionManager() {
        transactions = new ArrayList<>();
        balance = 0.0; // Start balance at 0.00
    }

    public void addIncome(double amount, String description) {
        Income income = new Income(amount, description, new Date());
        transactions.add(income);
        balance += amount; // Update balance with income
    }

    public void addExpense(double amount, String description) {
        Expense expense = new Expense(amount, description, new Date(), null);
        transactions.add(expense);
        balance -= amount; // Update balance with expense
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance; // Return current balance
    }
}