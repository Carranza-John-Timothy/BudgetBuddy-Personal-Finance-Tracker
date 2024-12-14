import java.util.HashMap;
import java.util.Map;

public class User {
    private static Map<String, String> users = new HashMap<>();
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
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
}