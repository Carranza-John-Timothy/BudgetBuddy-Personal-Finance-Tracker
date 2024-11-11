import java.util.Date;

public class Income {
    private double amount;
    private String description;
    private Date date;
    private Category category;

    public Income(double amount, String description, Date date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public Income(double amount, String description, Date date, Category category) {
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

    public Category getCategory() {
        return category;
    }

}