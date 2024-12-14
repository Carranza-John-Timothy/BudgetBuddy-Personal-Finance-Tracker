import java.io.*;
import java.util.*;

public class User implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private static Map<String, String> users = new HashMap<>();
    private String username;
    private double balance;
    private List<Transaction> transactions;

    public User(String username) {
        this.username = username;
        this.transactions = new ArrayList<>();
        loadUserData(); // Load user data from file
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already taken
        }
        users.put(username, password);
        return true; // Registration successful
    }

    public static User login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            return new User(username); // Login successful
        }
        return null; // Login failed
    }

    private void loadUserData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username + ".dat"))) {
            this.balance = ois.readDouble();
            this.transactions = (List<Transaction>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            this.balance = 0.0; // Default balance
            this.transactions = new ArrayList<>(); // Default empty transactions
        }
    }

    public void saveUserData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(username + ".dat"))) {
            oos.writeDouble(balance);
            oos.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        if (transaction instanceof Income) {
            balance += transaction.getAmount();
        } else if (transaction instanceof Expense) {
            balance -= transaction.getAmount();
        }
        saveUserData(); // Save data after adding transaction
    }
}