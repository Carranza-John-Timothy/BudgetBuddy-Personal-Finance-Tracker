import java.util.Date;

public class Income {
    private double amount;
    private String description;
    private Date date;

    public Income(double amount, String description, Date date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description; // This is the getDescription method
    }

    public Date getDate() {
        return date;
    }
}