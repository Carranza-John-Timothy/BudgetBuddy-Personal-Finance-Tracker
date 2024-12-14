import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager();
        User currentUser  = null;

        while (true) {
            System.out.println("\nWelcome to the Transaction Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1: // Register
                    registerUser (scanner);
                    break;

                case 2: // Login
                    currentUser  = loginUser (scanner);
                    if (currentUser  != null) {
                        System.out.println("Login successful! Welcome, " + currentUser .getUsername());
                        transactionMenu(scanner, transactionManager);
                    } else {
                        System.out.println("Login failed. Please check your credentials.");
                    }
                    break;

                case 3: // Exit
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser (Scanner scanner) {
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            if (username.isEmpty() || password.isEmpty()) {
                System.out.println("Username and password cannot be empty or whitespace. Please try again.");
                continue; // Ask for input again
            }

            if (User .register(username, password)) {
                System.out.println("Registration successful!");
                return; // Exit the registration loop
            } else {
                System.out.println("Username already taken. Please try again.");
            }
        }
    }

    private static User loginUser (Scanner scanner) {
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();
    
            User user = User.login(username, password);
            if (user != null) {
                return user; // Return the logged-in user
            } else {
                System.out.println("Invalid credentials. Please try again.");
                System.out.println("1. Try again");
                System.out.println("2. Go back to main menu");
                System.out.println("3. Register a new account");
                System.out.print("Choose an option: ");
    
                int choice = getValidIntegerInput(scanner);
                switch (choice) {
                    case 1:
                        // Continue the loop to try logging in again
                        break;
                    case 2:
                        return null; // Go back to the main menu
                    case 3:
                        registerUser (scanner); // Register a new account
                        return null; // Go back to the main menu after registration
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    private static void transactionMenu(Scanner scanner, TransactionManager transactionManager) {
        while (true) {
            System.out.println("\nTransaction Menu:");
            System.out.printf("Current Balance: %.2f PHP\n", transactionManager.getBalance()); // Display current balance
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1: // Add Income
                    addIncome(scanner, transactionManager);
                    break;

                case 2: // Add Expense
                    addExpense(scanner, transactionManager);
                    break;

                case 3: // View Transactions
                    viewTransactions(transactionManager);
                    break;

                case 4: // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addIncome(Scanner scanner, TransactionManager transactionManager) {
        System.out.print("Enter income amount: ");
        double amount = getValidDoubleInput(scanner);
        System.out.print("Enter income description: ");
        String description = scanner.nextLine().trim();
        transactionManager.addIncome(amount, description);
        System.out.println("Income added successfully.");
    }

    private static void addExpense(Scanner scanner, TransactionManager transactionManager) {
        System.out.print("Enter expense amount: ");
        double amount = getValidDoubleInput(scanner);
        System.out.print("Enter expense description: ");
        String description = scanner.nextLine().trim();
        transactionManager.addExpense(amount, description);
        System.out.println("Expense added successfully.");
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            } finally {
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    private static int getValidIntegerInput(Scanner scanner) {
        while (true) 
        {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            } finally {
                scanner.nextLine(); // Consume the newline character
            }
        }
    }

    private static void viewTransactions(TransactionManager transactionManager) {
        List<Transaction> transactions = transactionManager.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transactions:");
            for (Transaction transaction : transactions) {
                System.out.println(transaction); // Use the toString() method of Transaction
            }
        }
    }
}