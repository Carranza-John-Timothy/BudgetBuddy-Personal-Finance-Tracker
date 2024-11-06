import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("john_doe", "password123");
        user.loadData("user_data.txt"); // Load data at startup

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Edit Income");
            System.out.println("4. Edit Expense");
            System.out.println("5. Delete Income");
            System.out.println("6. Delete Expense");
            System.out.println("7. Display Income");
            System.out.println("8. Display Expenses");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Income
                    System.out.print("Enter income description: ");
                    String incomeDesc = scanner.nextLine();
                    System.out.print("Enter income amount: ");
                    double incomeAmount = scanner.nextDouble();
                    user.addIncome(new Income(incomeAmount, incomeDesc, new Date()));
                    break;

                case 2: // Add Expense
                    System.out.print("Enter expense description: ");
                    String expenseDesc = scanner.nextLine();
                    System.out.print("Enter expense amount: ");
                    double expenseAmount = scanner.nextDouble();
                    System.out.print("Enter a note (optional): ");
                    String expenseNote = scanner.nextLine(); // Read the note
                    user.addExpense(new Expense(expenseAmount, expenseDesc, new Date(), expenseNote));
                    break;

                case 3: // Edit Income
                    user.displayIncome();
                    System.out.print("Enter the index of the income to edit: ");
                    int incomeIndex = scanner.nextInt() - 1; // Convert to zero-based index
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new income description: ");
                    String newIncomeDesc = scanner.nextLine();
                    System.out.print("Enter new income amount: ");
                    double newIncomeAmount = scanner.nextDouble();
                    user.editIncome(incomeIndex, new Income(newIncomeAmount, newIncomeDesc, new Date()));
                    break;

                case 4: // Edit Expense
                    user.displayExpenses();
                    System.out.print("Enter the index of the expense to edit: ");
                    int expenseIndex = scanner.nextInt() - 1; // Convert to zero-based index
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new expense description: ");
                    String newExpenseDesc = scanner.nextLine();
                    System.out.print("Enter new expense amount: ");
                    double newExpenseAmount = scanner.nextDouble();
                    System.out.print("Enter a new note (optional): ");
                    String newExpenseNote = scanner.nextLine(); // Read the new note
                    user.editExpense (expenseIndex, new Expense(newExpenseAmount, newExpenseDesc, new Date(), newExpenseNote));
                    break;

                case 5: // Delete Income
                    user.displayIncome();
                    System.out.print("Enter the index of the income to delete: ");
                    int deleteIncomeIndex = scanner.nextInt() - 1; // Convert to zero-based index
                    user.deleteIncome(deleteIncomeIndex);
                    break;

                case 6: // Delete Expense
                    user.displayExpenses();
                    System.out.print("Enter the index of the expense to delete: ");
                    int deleteExpenseIndex = scanner.nextInt() - 1; // Convert to zero-based index
                    user.deleteExpense(deleteExpenseIndex);
                    break;

                case 7: // Display Income
                    user.displayIncome();
                    break;

                case 8: // Display Expenses
                    user.displayExpenses();
                    break;

                case 9: // Exit
                    user.saveData("user_data.txt"); // Save data before exiting
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}