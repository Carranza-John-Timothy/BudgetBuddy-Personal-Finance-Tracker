# BudgetBuddy - Personal Finance Tracker

## I. Project Overview
BudgetBuddy is a personal finance tracker designed to help users effectively manage their income and expenses. The application allows users to register, log in, and track their financial transactions, providing insights into their spending habits and overall financial health. With features such as transaction history, balance display, and filtering options, BudgetBuddy aims to empower users to make informed financial decisions.

## II. OOP Principles Applied
The project utilizes Object-Oriented Programming (OOP) principles to enhance code organization and reusability:

- **Encapsulation**: The `User `, `Transaction`, `Income`, and `Expense` classes encapsulate their respective data and behaviors. For example, the `User ` class manages user-related data and methods, while the `Transaction` class serves as a base class for `Income` and `Expense`, encapsulating common properties and methods.

- **Inheritance**: The `Income` and `Expense` classes inherit from the `Transaction` class, allowing them to share common attributes (like amount, description, and date) while also introducing specific features (like category and notes).

- **Polymorphism**: The `toString()` method is overridden in both `Income` and `Expense` classes to provide specific string representations of the objects, allowing for flexible handling of different transaction types.

## III. Chosen SDG and Integration
This project aligns with the United Nations Sustainable Development Goal (SDG) 12: Responsible Consumption and Production. By providing users with tools to track their spending and manage their finances, BudgetBuddy encourages responsible financial behavior and promotes awareness of personal consumption patterns. Users can analyze their expenses, identify areas for improvement, and make more sustainable financial choices.

## IV. Instructions for Running the Program

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor

### Installation
1. Clone the repository or download the project files.
git clone https://github.com/Carranza-John-Timothy/BudgetBuddy-Personal-Finance-Tracker.git
2. Navigate to the project directory:
    cd BudgetBuddy-Personal-Finance-Tracker
3. Compile the Java code using the `javac` command:
    javac src/main/java/com/example/budgetbuddy/*.java
4. Run the program using the `java` command:
    java com.example.budgetbuddy.Main
5. Follow the in-program instructions to register, log in, and use the application.

## Running the Program
Open the project in your IDE or text editor.
Compile the Java files.
Run the Main class.
Follow the on-screen instructions to register a new account or log in to an existing account.
Use the transaction menu to add income or expenses, view transaction history, and manage your financial data.
For any inquiries, please contact:

John Timothy S. Carranza - carranza.timothy12@gmail.com
GitHub: Carranza-John-Timothy