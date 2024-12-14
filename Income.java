import java.io.Serializable;
import java.util.Date;

public class Income extends Transaction implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization

    public Income(double amount, String description, Date date) {
        super(amount, description, date);
    }

    @Override
    public String toString() {
        return "Income: " + getDescription() + " - Amount: " + getAmount() + " - Date: " + date;
    }
}