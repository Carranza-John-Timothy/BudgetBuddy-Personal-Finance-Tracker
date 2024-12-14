import java.util.Date;

public class Expense extends Transaction {
    private String note; // Optional note for Expense
    private String category; // New field for category

    public Expense(double amount, String description, Date date, String note, String category) {
        super(amount, description, date);
        this.note = note; // Initialize the note field
        this.category = category; // Initialize the category field
    }

    public void setAmount(double amount) {
        this.amount = amount; // Setter for amount
    }

    public void setCategory(String category) {
        this.category = category; // Setter for category
    }

    public String getCategory() {
        return category; // Getter for category
    }

    @Override
    public String toString() {
        return "Expense: " + getDescription() + " - Amount: " + getAmount() + " - Category: " + category + " - Date: " + getDate() + (note != null ? " (Note: " + note + ")" : "");
    }
}