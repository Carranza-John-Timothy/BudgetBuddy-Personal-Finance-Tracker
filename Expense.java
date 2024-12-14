import java.util.Date;

public class Expense extends Transaction {
    private String note; // Optional note for Expense

    public Expense(double amount, String description, Date date, String note) {
        super(amount, description, date);
        this.note = note; // Initialize the note field
    }

    @Override
    public String toString() {
        return "Expense: " + getDescription() + " - Amount: " + getAmount() + " - Date: " + date + (note != null ? " (Note: " + note + ")" : "");
    }
}