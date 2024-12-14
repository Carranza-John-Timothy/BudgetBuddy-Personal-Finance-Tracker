import java.util.Date;

public class Income extends Transaction {
    public Income(double amount, String description, Date date) {
        super(amount, description, date);
    }

    @Override
    public String toString() {
        return "Income: " + getDescription() + " - Amount: " + getAmount() + " - Date: " + date;
    }
}