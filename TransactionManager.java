import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionManager {
    private User currentUser ;

    public TransactionManager(User user) {
        this.currentUser  = user;
    }

    public void addIncome(double amount, String description) {
        Income income = new Income(amount, description, new Date());
        currentUser .addTransaction(income);
    }

    public void addExpense(double amount, String description) {
        Expense expense = new Expense(amount, description, new Date(), null);
        currentUser .addTransaction(expense);
    }

    public List<Transaction> getTransactions() {
        return currentUser .getTransactions();
    }

    public double getBalance() {
        return currentUser .getBalance();
    }
}