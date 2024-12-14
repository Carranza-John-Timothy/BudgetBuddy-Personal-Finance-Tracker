import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {
    private User currentUser ;

    public TransactionManager(User user) {
        this.currentUser  = user;
    }

    public User getCurrentUser () {
        return currentUser ;
    }

    public void addIncome(double amount, String description, String category) {
        Income income = new Income(amount, description, new Date(), category);
        currentUser .addTransaction(income);
    }

    public void addExpense(double amount, String description, String category) {
        Expense expense = new Expense(amount, description, new Date(), null, category);
        currentUser .addTransaction(expense);
    }

    public List<Transaction> getTransactions() {
        return currentUser .getTransactions();
    }

    public double getBalance() {
        return currentUser .getBalance();
    }

    public List<Transaction> getTransactionsByDate(Date date) {
        return currentUser .getTransactions().stream()
                .filter(transaction -> isSameDay(transaction.getDate(), date))
                .collect(Collectors.toList());
    }

    private boolean isSameDay(Date date1, Date date2) {
        // Compare year, month, and day
        return date1.getYear() == date2.getYear() &&
               date1.getMonth() == date2.getMonth() &&
               date1.getDate() == date2.getDate();
    }
}