import java.io.Serializable;
import java.util.Date;

public class Income extends Transaction implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private String category; // New field for category

    public Income(double amount, String description, Date date, String category) {
        super(amount, description, date);
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
        return "Income: " + getDescription() + " - Amount: " + getAmount() + " - Category: " + category + " - Date: " + getDate();
    }
}