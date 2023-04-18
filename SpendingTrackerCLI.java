import java.util.*;
import java.text.*;
class SpendingTrackerCLI {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double budget = getBudget();
        double totalSpent = 0.0;
        Map<String, Double> expenses = new LinkedHashMap<>();

        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    double amount = getAmount();
                    if (totalSpent + amount > budget) {
                        System.out.println("Error: Exceeds budget limit.");
                    } else {
                        totalSpent += amount;
                        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
                        expenses.put(timeStamp, amount);
                        System.out.println(String.format("$%.2f added to expenses.", amount));
                    }
                    break;
                case 2:
                    displayExpenses(expenses);
                    break;
                case 3:
                    System.out.println(String.format("Budget: $%.2f", budget));
                    System.out.println(String.format("Total spent: $%.2f", totalSpent));
                    System.out.println(String.format("Remaining balance: $%.2f", budget - totalSpent));
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Error: Invalid choice.");
                    break;
            }
        }
    }

    private static double getBudget() {
        double budget = 0.0;
        while (true) {
            try {
                System.out.print("Enter budget: $");
                budget = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid budget.");
                scanner.next();
            }
        }
        return budget;
    }

    private static void displayMenu() {
        System.out.println("\n1. Add expense");
        System.out.println("2. View expenses");
        System.out.println("3. View budget and total spent");
        System.out.println("4. Exit");
    }

    private static int getChoice() {
        int choice = 0;
        while (true) {
            try {
                System.out.print("\nEnter choice: ");
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid choice.");
                scanner.next();
            }
        }
        return choice;
    }

    private static double getAmount() {
        double amount = 0.0;
        while (true) {
            try {
                System.out.print("Enter amount spent: $");
                amount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a valid amount.");
                scanner.next();
            }
        }
        return amount;
    }

    private static void displayExpenses(Map<String, Double> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses added.");
        } else {
            System.out.println("Expenses:");
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                System.out.println(entry.getKey() + ": $" + entry.getValue());
                
            }
        }
    }
}