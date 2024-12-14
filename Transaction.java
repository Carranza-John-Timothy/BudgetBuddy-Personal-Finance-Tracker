import java.util.Date;

public abstract class Transaction {
    protected double amount;
    protected String description;
    protected Date date;

    public Transaction(double amount, String description, Date date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return description + ": " + amount + " on " + date;
    }
}