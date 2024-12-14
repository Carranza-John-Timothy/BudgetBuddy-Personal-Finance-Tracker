import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User.loadUsers(); // Load users at the start
        Scanner scanner = new Scanner(System.in);
        User currentUser   = null; // Initialize currentUser   as null

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            User.saveUsers(); // Save users on application exit
        }));

        while (true) {
            System.out.println("\nWelcome to the Transaction Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidIntegerInput(scanner);
            scanner.nextLine(); // Clear the newline character from the buffer

            switch (choice) {
                case 1: // Register
                    registerUser (scanner);
                    break;

                case 2: // Login
                    currentUser  = loginUser (scanner);
                    if (currentUser  != null) {
                        System.out.println("Login successful! Welcome, " + currentUser .getUsername());
                        transactionMenu(scanner, currentUser ); // Pass currentUser  to transactionMenu
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
            String username = scanner.nextLine().trim(); // Read username
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim(); // Read password

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
                scanner.nextLine(); // Clear the newline character from the buffer
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

    private static void transactionMenu(Scanner scanner, User currentUser ) {
        TransactionManager transactionManager = new TransactionManager(currentUser ); // Create TransactionManager with currentUser   
        while (true) {
            System.out.println("\nTransaction Menu:");
            System.out.printf("Current Balance: %.2f PHP\n", transactionManager.getBalance());
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. View Transactions by Date");
            System.out.println("5. Edit Transaction"); // New option to edit transaction
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
    
            int choice = getValidIntegerInput(scanner);
            scanner.nextLine(); // Clear the newline character from the buffer
    
            switch (choice) {
                case 1:
                    addIncome(scanner, transactionManager);
                    break;
                case 2:
                    addExpense(scanner, transactionManager);
                    break;
                case 3:
                    viewTransactions(transactionManager);
                    break;
                case 4:
                    viewTransactionsByDate(scanner, transactionManager);
                    break;
                case 5:
                    editTransaction(scanner, transactionManager); // Call edit transaction method
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return; // Exit the transaction menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addIncome(Scanner scanner, TransactionManager transactionManager) {
        System.out.print("Enter income amount: ");
        double amount = getValidDoubleInput(scanner);
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter income description: "); // Prompt for description
        String description = scanner.nextLine().trim(); // Read description
    
        // Display income categories
        System.out.println("Select a category for income:");
        System.out.println("1. Salary");
        System.out.println("2. Part Time");
        System.out.println("3. Investments");
        System.out.println("4. Others (custom)");
        System.out.print("Choose an option: ");
        int categoryChoice = getValidIntegerInput(scanner);
        
        String category;
    
        switch (categoryChoice) {
            case 1:
                category = "Salary";
                break;
            case 2:
                category = "Part Time";
                break;
            case 3:
                category = "Investments";
                break;
            case 4:
                // Clear the buffer before asking for custom category
                scanner.nextLine(); // Clear any leftover newline characters
                // Loop until a valid custom category is provided
                while (true) {
                    System.out.print("Enter custom category: ");
                    category = scanner.nextLine().trim(); // Read custom category
                    if (!category.isEmpty()) {
                        break; // Exit the loop if input is valid
                    }
                    System.out.println("Custom category cannot be empty. Please enter a valid category.");
                }
                break;
            default:
                category = "Others"; // Default category if invalid option
        }
    
        transactionManager.addIncome(amount, description, category); // Pass amount, description, and category
        System.out.println("Income added successfully!");
    }
    
    private static void addExpense(Scanner scanner, TransactionManager transactionManager) {
        System.out.print("Enter expense amount: ");
        double amount = getValidDoubleInput(scanner);
        scanner.nextLine(); // Clear the newline character from the buffer
        System.out.print("Enter expense description: "); // Prompt for description
        String description = scanner.nextLine().trim(); // Read description
    
        // Display expense categories
        System.out.println("Select a category for expense:");
        System.out.println("1. Shopping");
        System.out.println("2. Food");
        System.out.println("3. Entertainment");
        System.out.println("4. Education");
        System.out.println("5. Transportation");
        System.out.println("6. Clothing");
        System.out.println("7. Social");
        System.out.println("8. Others (custom)");
        System.out.print("Choose an option: ");
        int categoryChoice = getValidIntegerInput(scanner);
        
        String category;
    
        switch (categoryChoice) {
            case 1:
                category = "Shopping";
                break;
            case 2:
                category = "Food";
                break;
            case 3:
                category = "Entertainment";
                break;
            case 4:
                category = "Education";
                break;
            case 5:
                category = "Transportation";
                break;
            case 6:
                category = "Clothing";
                break;
            case 7:
                category = "Social";
                break;
            case 8:
                // Clear the buffer before asking for custom category
                scanner.nextLine(); // Clear any leftover newline characters
                // Loop until a valid custom category is provided
                while (true) {
                    System.out.print("Enter custom category: ");
                    category = scanner.nextLine().trim(); // Read custom category
                    if (!category.isEmpty()) {
                        break; // Exit the loop if input is valid
                    }
                    System.out.println("Custom category cannot be empty. Please enter a valid category.");
                }
                break;
            default:
                category = "Others"; // Default category if invalid option
        }
    
        transactionManager.addExpense(amount, description, category ); // Pass amount, description, and category
        System.out.println("Expense added successfully!");
    }

    private static void viewTransactions(TransactionManager transactionManager) {
        List<Transaction> transactions = transactionManager.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transactions:");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println(i + ": " + transactions.get(i)); // Display index and transaction
            }
        }
    }

    private static void viewTransactionsByDate(Scanner scanner, TransactionManager transactionManager) {
        System.out.print("Enter date (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateInput);
            List<Transaction> transactions = transactionManager.getTransactionsByDate(date);
            if (transactions.isEmpty()) {
                System.out.println("No transactions found for this date.");
            } else {
                System.out.println("Transactions for " + dateInput + ":");
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void editTransaction(Scanner scanner, TransactionManager transactionManager) {
        List<Transaction> transactions = transactionManager.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions available to edit.");
            return;
        }
    
        // Display transactions with index
        System.out.println("Select a transaction to edit:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(i + ": " + transactions.get(i)); // Display index and transaction
        }
    
        // Get the index of the transaction to edit
        System.out.print("Enter the index of the transaction you want to edit: ");
        int index = getValidIntegerInput(scanner);
    
        // Validate index
        if (index < 0 || index >= transactions.size()) {
            System.out.println("Invalid index. Please try again.");
            return;
        }
    
        Transaction transactionToEdit = transactions.get(index);
        System.out.println("Editing transaction: " + transactionToEdit);
    
        // Store the old amount for balance adjustment
        double oldAmount = transactionToEdit.getAmount();
    
        // Edit amount
        System.out.print("Enter new amount (current: " + oldAmount + "): ");
        double newAmount = getValidDoubleInput(scanner);
        
        // Edit category
        System.out.println("Available categories:");
        if (transactionToEdit instanceof Income) {
            System.out.println("1. Salary");
            System.out.println("2. Part Time");
            System.out.println("3. Investments");
            System.out.println("4. Others (custom)");
        } else if (transactionToEdit instanceof Expense) {
            System.out.println("1. Shopping");
            System.out.println("2. Food");
            System.out.println("3. Entertainment");
            System.out.println("4. Education");
            System.out.println("5. Transportation");
            System.out.println("6. Clothing");
            System.out.println("7. Social");
            System.out.println("8. Others (custom)");
        }
    
        System.out.print("Enter new category (or choose from above): ");
        scanner.nextLine(); // Clear the newline character from the buffer
        String newCategory = scanner.nextLine().trim();
    
        // Update the transaction
        if (transactionToEdit instanceof Income) {
            ((Income) transactionToEdit).setAmount(newAmount);
            if (!newCategory.isEmpty()) {
                ((Income) transactionToEdit).setCategory(newCategory);
            }
        } else if (transactionToEdit instanceof Expense) {
            ((Expense) transactionToEdit).setAmount(newAmount);
            if (!newCategory.isEmpty()) {
                ((Expense) transactionToEdit).setCategory(newCategory);
            }
        }
    
        // Update the balance
        // Update the balance
        double balanceChange = newAmount - oldAmount;
        transactionManager.getCurrentUser ().setBalance(transactionManager.getCurrentUser ().getBalance() + balanceChange);
    
        System.out.println("Transaction updated successfully!");
    }

    private static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}