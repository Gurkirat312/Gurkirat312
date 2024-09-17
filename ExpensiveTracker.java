import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Expense {
    private String category;
    private double amount;
    private LocalDate date;
    private String description;
    
    public Expense(String category, double amount, LocalDate date, String description) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Category: " + category + ", Amount: $" + amount + ", Description: " + description;
    }
}

public class ExpenseTracker {
    private ArrayList<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(String category, double amount, LocalDate date, String description) {
        Expense expense = new Expense(category, amount, date, description);
        expenses.add(expense);
    }

    public void displayExpenses() {
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void displayCategoryExpenses(String category) {
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
            }
        }
    }

    public void displayTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.println("Total Expenses: $" + total);
    }

    public void generateReportByCategory() {
        double foodTotal = 0;
        double rentTotal = 0;
        double utilitiesTotal = 0;
        for (Expense expense : expenses) {
            switch (expense.getCategory().toLowerCase()) {
                case "food":
                    foodTotal += expense.getAmount();
                    break;
                case "rent":
                    rentTotal += expense.getAmount();
                    break;
                case "utilities":
                    utilitiesTotal += expense.getAmount();
                    break;
            }
        }
        System.out.println("Expense Report:");
        System.out.println("Food: $" + foodTotal);
        System.out.println("Rent: $" + rentTotal);
        System.out.println("Utilities: $" + utilitiesTotal);
    }

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses by Category");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter Category (Food, Rent, Utilities): ");
                String category = scanner.nextLine();
                System.out.print("Enter Amount: ");
                double amount = scanner.nextDouble();
                System.out.print("Enter Date (YYYY-MM-DD): ");
                String dateString = scanner.next();
                LocalDate date = LocalDate.parse(dateString);
                scanner.nextLine(); // Consume newline
                System.out.print("Enter Description: ");
                String description = scanner.nextLine();
                tracker.addExpense(category, amount, date, description);
            } else if (choice == 2) {
                tracker.displayExpenses();
            } else if (choice == 3) {
                System.out.print("Enter Category to View: ");
                String category = scanner.nextLine();
                tracker.displayCategoryExpenses(category);
            } else if (choice == 4) {
                tracker.generateReportByCategory();
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
