import java.util.Date;

public class Expense {
    private double amount;
    private String description;
    private Date date;
    private String note;

    public Expense(double amount, String description, Date date, String note) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.note = note;
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

    public String getNote() {
        return note;
    }
}