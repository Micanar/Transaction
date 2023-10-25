package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Wallet Service!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                walletService.registerPlayer(username, password);
                System.out.println("User registered successfully!");
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                Player player = walletService.login(username, password);
                if (player != null) {
                    System.out.println("Login successful!");
                    handlePlayerTransactions(scanner, player);
                    walletService.auditAction(player, "logout");
                } else {
                    System.out.println("Login failed.");
                }
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handlePlayerTransactions(Scanner scanner, Player player) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Transaction History");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                player.deposit(UUID.randomUUID().toString(), amount);
                System.out.println("Deposit successful!");
            } else if (choice == 2) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); 
                player.withdraw(UUID.randomUUID().toString(), amount);
            } else if (choice == 3) {
                List<Transaction> transactions = player.getTransactionHistory();
                System.out.println("Transaction history:");
                for (Transaction transaction : transactions) {
                    System.out.println("Transaction ID: " + transaction.getTransactionId() +
                            ", Type: " + transaction.getType() +
                            ", Amount: " + transaction.getAmount() +
                            ", Timestamp: " + transaction.getTimestamp());
                }
            } else if (choice == 4) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
