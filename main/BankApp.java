package main;

import model.Account;
import service.BankService;
import data.BankDatabase;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        // Use try-with-resources to automatically close the scanner
        try (Scanner scanner = new Scanner(System.in)) {
            BankDatabase.initDemoUsers();
            BankService service = new BankService();

            System.out.println("🔐 Welcome to Secure Bank");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (!service.login(username, password)) {
                System.out.println("❌ Invalid credentials");
                return;
            }

            System.out.println("✅ Login successful!");
            Account acc = service.getLoggedInAccount();

            while (true) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. View Balance");
                System.out.println("2. Transfer Funds");
                System.out.println("3. View Transaction History");
                System.out.println("4. Exit");
                System.out.print("Choose: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> System.out.println("💰 Balance: $" + acc.getBalance());
                    case 2 -> {
                        System.out.print("Enter receiver account number: ");
                        String toAcc = scanner.next();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();
                        if (service.transferFunds(toAcc, amount))
                            System.out.println("✅ Transfer successful.");
                        else
                            System.out.println("❌ Transfer failed. Check details or balance.");
                    }
                    case 3 -> acc.getTransactions().forEach(System.out::println);
                    case 4 -> {
                        System.out.println("👋 Thank you for banking with us!");
                        return;
                    }
                    default -> System.out.println("❌ Invalid option");
                }
            }
        }
    }
}

